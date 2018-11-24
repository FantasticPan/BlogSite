package com.pan.blog.vo;

import lombok.Data;

/**
 * 返回对象
 * Created by FantasticPan on 2018/11/23.
 */
@Data
public class Response {

    private boolean success; //处理是否成功
    private String message; //处理后消息提示
    private Object body; //返回数据

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
