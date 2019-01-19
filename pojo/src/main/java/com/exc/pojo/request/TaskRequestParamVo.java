package com.exc.pojo.request;

import lombok.Data;

import java.util.Date;

@Data
public class TaskRequestParamVo {
    private Integer taskId;
    private String taskName;
    private String taskClass;
    private Integer runType;
    private Integer runStatus;
    private Date loadTime;
}
