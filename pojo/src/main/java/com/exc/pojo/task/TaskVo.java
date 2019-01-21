package com.exc.pojo.task;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

@Data
public class TaskVo {
    private Integer taskId;
    private String taskName;
    private String taskClass;
    private Integer runType;
    private Integer runStatus;
    private String executeCron;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date loadTime;
}
