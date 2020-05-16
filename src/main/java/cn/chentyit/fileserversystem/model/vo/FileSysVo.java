package cn.chentyit.fileserversystem.model.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @Author Chentyit
 * @Date 2020/5/15 21:38
 * @Description:
 */
@Data
@Builder
public class FileSysVo {

    @JsonProperty("file_id")
    private String fileId;

    @JsonProperty("file_name")
    private String fileName;

    @JsonProperty("file_siz")
    private Long fileSize;

    @JsonProperty("file_upload_date")
    private LocalDateTime fileUploadDate;

    @JsonProperty("file_md5")
    private String fileMd5;

    @JsonProperty("file_view_path")
    private String fileViewPath;
}
