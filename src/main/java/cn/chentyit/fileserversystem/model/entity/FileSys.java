package cn.chentyit.fileserversystem.model.entity;

import com.alibaba.druid.mock.MockBlob;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.ibatis.javassist.bytecode.ByteArray;

import java.io.Serializable;
import java.sql.Blob;
import java.time.LocalDateTime;
import java.util.UUID;

/**
 * @Author Chentyit
 * @Date 2020/5/15 21:38
 * @Description:
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@TableName("file_sys_t")
public class FileSys implements Serializable {

    /**
     * 此属性用于序列化，不会映射数据库的列
     */
    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

    @TableId(value = "file_id", type = IdType.UUID)
    private String fileId;

    @TableField(value = "file_name")
    private String fileName;

    @TableField(value = "file_content_type")
    private String fileContentType;

    @TableField(value = "file_size")
    private Long fileSize;

    @TableField(value = "file_upload_date")
    private LocalDateTime fileUploadDate;

    @TableField(value = "file_md5")
    private String fileMd5;

    @TableField(value = "file_content")
    private byte[] fileContent;
}
