package com.exc.pojo.task;

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
    private Date loadTime;
}
