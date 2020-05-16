package cn.chentyit.fileserversystem.controller;

import cn.chentyit.fileserversystem.config.ServerConfig;
import cn.chentyit.fileserversystem.model.entity.FileSys;
import cn.chentyit.fileserversystem.model.pojo.ResDataTemplate;
import cn.chentyit.fileserversystem.model.vo.FileSysVo;
import cn.chentyit.fileserversystem.service.FileSysService;
import cn.chentyit.fileserversystem.utils.Md5Util;
import cn.chentyit.fileserversystem.utils.TransObj;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDateTime;
import java.util.*;

/**
 * @Author Chentyit
 * @Date 2020/5/15 22:13
 * @Description:
 */
@Slf4j
@Controller
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class FileSysController {

    private final ServerConfig serverConfig;
    private final FileSysService fileSysService;

    /**
     * 进入首页
     *
     * @return
     */
    @GetMapping({"/", "/index", "/index.html"})
    public ModelAndView index(HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("index");
        // 创建存储前端视图对象的容器
        List<FileSysVo> fileSysVos = new ArrayList<>();
        // 获取所有的图片文件数据
        fileSysService.getAllFile().forEach(fileSys -> {
            FileSysVo fileSysVo = TransObj.fileSysEntity2Vo(fileSys);
            fileSysVo.setFileViewPath(serverConfig.getUrl() + "/files/view/" + fileSys.getFileId());
            fileSysVos.add(fileSysVo);
        });
        // 将图片数据集合保存到模型视图对象中一起返回给前端
        modelAndView.addObject("files", fileSysVos);
        return modelAndView;
    }

    /**
     * 上传文件接口
     *
     * @param file 上传的文件
     * @return
     */
    @ResponseBody
    @PostMapping("/files/upload")
    public ResDataTemplate uploadImageFile(@RequestParam("file") MultipartFile file) {
        boolean save = false;
        try {
            // 保存 MultipartFile 文件信息
            FileSys fileSys = FileSys.builder()
                    .fileId(UUID.randomUUID().toString().replace("-", ""))
                    .fileName(file.getOriginalFilename())
                    .fileContentType(file.getContentType())
                    .fileSize(file.getSize())
                    .fileContent(file.getBytes())
                    .fileUploadDate(LocalDateTime.now())
                    .fileMd5(Md5Util.getMd5(file.getInputStream()))
                    .build();
            // 保存到数据库
            save = fileSysService.save(fileSys);
            if (save) {
                return ResDataTemplate.builder()
                        .status(200)
                        .message("upload success")
                        .data(null)
                        .build();
            }
        } catch (IOException | NoSuchAlgorithmException e) {
            e.printStackTrace();
            return ResDataTemplate.builder()
                    .status(500)
                    .message(e.getMessage())
                    .data(null)
                    .build();
        }

        return ResDataTemplate.builder()
                .status(500)
                .message("upload failure")
                .data(null)
                .build();
    }

    /**
     * 下载文件信息
     *
     * @param fileId
     * @return
     */
    @ResponseBody
    @GetMapping("/files/{file_id}")
    public ResponseEntity<Object> getFile(@PathVariable("file_id") String fileId) throws UnsupportedEncodingException {
        FileSys imageFile = fileSysService.getById(fileId);

        if (!Objects.isNull(imageFile)) {
            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; fileName=" + new String(imageFile.getFileName().getBytes("utf-8"), "ISO-8859-1"))
                    .header(HttpHeaders.CONTENT_TYPE, "application/octet-stream")
                    .header(HttpHeaders.CONTENT_LENGTH, imageFile.getFileSize() + "").header("Connection", "close")
                    .body(imageFile.getFileContent());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("File was not fount");
        }
    }

    /**
     * 在线展示图片信息
     *
     * @param fileId 从前端得到图片文件的 ID 并显示相关数据
     * @return
     */
    @ResponseBody
    @GetMapping("/files/view/{file_id}")
    public ResponseEntity<Object> showImageOnline(@PathVariable("file_id") String fileId) {
        // 从数据库中获取图片数据
        FileSys imageFile = fileSysService.getById(fileId);

        if (!Objects.isNull(imageFile)) {
            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_DISPOSITION, "fileName=\"" + imageFile.getFileName() + "\"")
                    .header(HttpHeaders.CONTENT_TYPE, imageFile.getFileContentType())
                    .header(HttpHeaders.CONTENT_LENGTH, imageFile.getFileSize() + "").header("Connection", "close")
                    .body(imageFile.getFileContent());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("File was not fount");
        }
    }

    /**
     * 删除对应的图片数据
     *
     * @param fileId 文件 ID
     * @return
     */
    @GetMapping("/files/delete/{file_id}")
    public String deleteImageFile(@PathVariable("file_id") String fileId) {
        fileSysService.removeById(fileId);
        return "redirect:/";
    }
}
