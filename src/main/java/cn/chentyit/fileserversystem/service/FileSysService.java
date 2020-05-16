package cn.chentyit.fileserversystem.service;

import cn.chentyit.fileserversystem.model.entity.FileSys;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * @Author Chentyit
 * @Date 2020/5/15 22:05
 * @Description:
 */
public interface FileSysService extends IService<FileSys> {

    /**
     * 获取所有
     * @return
     */
    List<FileSys> getAllFile();
}
