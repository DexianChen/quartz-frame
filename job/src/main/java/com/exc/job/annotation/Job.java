package com.exc.job.annotation;

import java.lang.annotation.*;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Job {
    String executeCron() default "0/30 * * * * ?";
}
