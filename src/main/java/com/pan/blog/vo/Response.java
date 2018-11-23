package com.pan.blog.vo;

import lombok.Data;

/**
 * Created by FantasticPan on 2018/11/23.
 */
@Data
public class Response {

    private boolean success;
    private String message;
    private Object body;

    public Response() {
    }

    public Response(boolean success, String message) {
        this.success = success;
        this.message = message;
    }

    public Response(boolean success, String message, Object body) {
        this.success = success;
        this.message = message;
        this.body = body;
    }
}
