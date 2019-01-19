package com.exc.web.controller;

import com.exc.pojo.request.TaskRequestParamVo;
import com.exc.pojo.response.ResultVo;
import com.exc.pojo.task.TaskVo;
import com.exc.service.TaskService;
import org.apache.ibatis.annotations.Param;
import org.quartz.SchedulerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@EnableAutoConfiguration
@ComponentScan(basePackages={"com.exc.service"})//添加的注解
@RequestMapping("/job")
public class JobController {
    @Autowired
    private TaskService taskService;

    @GetMapping("/start")
    public ResultVo startJob(@PathVariable Integer taskId){
        try {
            taskService.startTask(taskId);
            return new ResultVo("SUCCESS", "启动定时任务成功");
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
        return new ResultVo("FAIL", "启动定时任务失败");
    }

    @GetMapping("/stop")
    public ResultVo stopJob(@Param("taskId") Integer taskId){
        return null;
    }

    @PostMapping("/update")
    public ResultVo updateJob(@RequestBody TaskRequestParamVo paramVo){
        try {
            taskService.updateTask(paramVo);
            return new ResultVo("SUCCESS", "更新定时任务成功");
        } catch (Exception e) {
            e.printStackTrace();
        }

        return new ResultVo("FAIL", "更新定时任务失败");
    }

    @GetMapping("/delete")
    public ResultVo deleteJob(Integer[] taskIds){
        try {
            taskService.deleteTask(taskIds);
            return new ResultVo("SUCCESS", "删除定时任务成功");
        } catch (Exception e) {
            e.printStackTrace();
        }

        return new ResultVo("FAIL", "删除定时任务失败");
    }

    @GetMapping("/list")
    public List<TaskVo> getTaskList(){
        return taskService.getTaskList();
    }

    @PostMapping("/insert")
    public ResultVo insertTask(@PathVariable TaskRequestParamVo paramVo){
        try {
            taskService.insertTask(paramVo);
            return new ResultVo("SUCCESS", "插入定时任务成功");
        } catch (Exception e) {
            e.printStackTrace();
        }

        return new ResultVo("FAIL", "插入定时任务失败");
    }
}
