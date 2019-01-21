package com.exc.job.utils;

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

    private final static String TASK_CLASS_GROUP = "group";
    private final static String TRIGGER_NAME = "trigger";

    /**
     * 初始化定时任务
     * @param taskVoList
     * @throws SchedulerException
     */
    public static void executeJob(List<TaskVo> taskVoList) throws SchedulerException {
        Scheduler scheduler = initScheduler();
        logger.info("初始化Scheduler成功");

        //配置JobDetail 和 CronTrigger
        JobDetail jobDetail = null;
        CronTrigger cronTrigger = null;
        if (!taskVoList.isEmpty() && taskVoList.size() > 0) {
            for (int i=0; i<taskVoList.size(); i++) {
                TaskVo taskVo = taskVoList.get(i);
                jobDetail = initJobDetail(taskVo.getTaskClass(), taskVo.getTaskName(), TASK_CLASS_GROUP + i);
                cronTrigger = initCronTrigger(taskVo.getExecuteCron(), taskVo.getTaskName(), TRIGGER_NAME + i, TASK_CLASS_GROUP + i);
                scheduler.start();
                scheduler.scheduleJob(jobDetail, cronTrigger);
            }
        }else {
            throw new RuntimeException("定时任务列表为空");
        }
    }

    /**
     * 启动单个定时任务
     * @param taskVo
     * @throws SchedulerException
     */
    public static void executeSingleJob(TaskVo taskVo) throws SchedulerException {
        Scheduler scheduler = initScheduler();
        logger.info("初始化Scheduler成功");

        //配置JobDetail 和 CronTrigger
        JobDetail jobDetail = initJobDetail(taskVo.getTaskClass(), taskVo.getTaskName(), TASK_CLASS_GROUP + "_" + taskVo.getTaskName());
        CronTrigger cronTrigger = initCronTrigger(taskVo.getExecuteCron(), taskVo.getTaskName(), TRIGGER_NAME + "_" + taskVo.getTaskName(), TASK_CLASS_GROUP + "_" + taskVo.getTaskName());
        scheduler.start();
        scheduler.scheduleJob(jobDetail, cronTrigger);
        logger.info("启动{}成功", taskVo.getTaskName());
    }

    /**
     * 启动CronTrigger触发器
     * @param executeCron
     * @param taskName
     * @param triggerName
     * @param groupName
     * @return
     */
    private static CronTrigger initCronTrigger(String executeCron, String taskName, String triggerName, String groupName) {
        return newTrigger()
                .withIdentity(triggerName, groupName)
                .withSchedule(cronSchedule(executeCron))
                .forJob(taskName, groupName)
                .build();
    }

    private static JobDetail initJobDetail(String taskClass, String taskName, String groupName) {
        try {
            return newJob((Class<? extends Job>) Class.forName(taskClass))
                    .withIdentity(taskName, groupName)
                    .build();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("找不到定时任务：" + taskClass);
        }
    }

    private static Scheduler initScheduler() throws SchedulerException {
        return StdSchedulerFactory.getDefaultScheduler();
    }
}
