package com.exc.platform.utils;

import com.exc.pojo.task.TaskVo;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

import static org.quartz.CronScheduleBuilder.cronSchedule;
import static org.quartz.JobBuilder.newJob;
import static org.quartz.TriggerBuilder.newTrigger;

public class SchedulerUtil {
    private final static Logger logger = LoggerFactory.getLogger(SchedulerUtil.class);

    private final static String TASK_CLASS_NAME = "myJob";
    private final static String TASK_CLASS_GROUP = "group1";
    private final static String TRIGGER_NAME = "trigger";

    public static void executeJob(List<TaskVo> taskVoList) throws SchedulerException {
        Scheduler scheduler = initScheduler();
        logger.info("初始化Scheduler成功");

        //配置JobDetail 和 CronTrigger
        JobDetail jobDetail = null;
        CronTrigger cronTrigger = null;
        if (!taskVoList.isEmpty() && taskVoList.size() > 0) {
            for (int i=0; i<taskVoList.size(); i++) {
                TaskVo taskVo = taskVoList.get(i);
                jobDetail = initJobDetail(taskVo.getTaskClass(), TASK_CLASS_NAME + i);
                cronTrigger = initTrigger(taskVo.getExecuteCron(), TASK_CLASS_NAME + i, TRIGGER_NAME + i);
                scheduler.start();
                scheduler.scheduleJob(jobDetail, cronTrigger);
            }
        }else {
            throw new RuntimeException("定时任务列表为空");
        }

    }

    private static CronTrigger initTrigger(String executeCron, String taskClassName, String triggerName) {
        return newTrigger()
                .withIdentity(triggerName, TASK_CLASS_GROUP)
                .withSchedule(cronSchedule(executeCron))
                .forJob(taskClassName, TASK_CLASS_GROUP)
                .build();
    }

    private static JobDetail initJobDetail(String taskClass, String taskClassName) {
        try {
            return newJob((Class<? extends Job>) Class.forName(taskClass))
                    .withIdentity(taskClassName, TASK_CLASS_GROUP)
                    .build();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("找不到定时任务：" + taskClass);
        }
    }

    private static Scheduler initScheduler() throws SchedulerException {
        return StdSchedulerFactory.getDefaultScheduler();
    }
}
