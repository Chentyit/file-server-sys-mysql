package cn.chentyit.fileserversystem.model.pojo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

import java.util.Map;

/**
 * @Author Chentyit
 * @Date 2020/5/16 10:28
 * @Description:
 */
@Data
@Builder
public class ResDataTemplate {

    @JsonProperty("status")
    private int status;

    @JsonProperty("message")
    private String message;

    @JsonProperty("data")
    private Map<String, Object> data;
}
