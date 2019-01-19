package com.exc.web.controller;

import com.exc.web.vo.TaskRequestParamVo;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class JobController {

    @PostMapping("/start")
    public String startJob(@RequestBody TaskRequestParamVo taskRequestParamVo){
        return "SUCCESS";
    }
}
