package cn.chentyit.fileserversystem.utils;

import cn.chentyit.fileserversystem.model.entity.FileSys;
import cn.chentyit.fileserversystem.model.vo.FileSysVo;

/**
 * @Author Chentyit
 * @Date 2020/5/15 22:42
 * @Description:
 */
public class TransObj {

    public static FileSysVo fileSysEntity2Vo(FileSys fileSys) {
        return FileSysVo.builder()
                .fileId(fileSys.getFileId())
                .fileName(fileSys.getFileName())
                .fileSize(fileSys.getFileSize())
                .fileUploadDate(fileSys.getFileUploadDate())
                .fileMd5(fileSys.getFileMd5())
                .build();
    }
}
