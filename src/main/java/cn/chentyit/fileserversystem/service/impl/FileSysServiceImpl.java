package cn.chentyit.fileserversystem.service.impl;

import cn.chentyit.fileserversystem.dao.FileSysMapper;
import cn.chentyit.fileserversystem.model.entity.FileSys;
import cn.chentyit.fileserversystem.service.FileSysService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @Author Chentyit
 * @Date 2020/5/15 22:05
 * @Description:
 */
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Transactional(isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
public class FileSysServiceImpl extends ServiceImpl<FileSysMapper, FileSys> implements FileSysService {

    private final FileSysMapper fileSysMapper;

    @Override
    public List<FileSys> getAllFile() {
        return fileSysMapper.selectList(null);
    }
}
