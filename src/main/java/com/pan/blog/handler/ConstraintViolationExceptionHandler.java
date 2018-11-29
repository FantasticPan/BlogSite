package com.pan.blog.handler;

import org.apache.commons.lang.StringUtils;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.ArrayList;
import java.util.List;

/**
 * ConstraintViolationException 处理器
 * Created by FantasticPan on 2018/11/23.
 */
public class ConstraintViolationExceptionHandler {

    public static String getMessage(ConstraintViolationException e) {
        List<String> msgList = new ArrayList<>();
        for (ConstraintViolation<?> constraintViolation : e.getConstraintViolations()) {
            msgList.add(constraintViolation.getMessage());
        }
        return StringUtils.join(msgList.toArray(), ";");
    }
}
