package com.zhangyun.portalknight.server.domain.response;

import lombok.Data;

/**
 * description:
 *
 * @author: zhangyun
 * @date: 2022/8/7 18:21
 * @since: 1.0
 */
@Data
public class Response {

    private Integer code;

    private String msg;

    private Object data;

    private Response() {

    }

    private Response(Integer code) {
        this.code = code;
    }

    public Response(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    private Response(Integer code, String msg, Object data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public static Response ok() {
        return new Response(200, "success");
    }

    public static Response ok(String msg) {
        return new Response(200, msg);
    }

    public static Response ok(Integer code, String msg) {
        return new Response(code, msg);
    }

    public static Response ok(Integer code, String msg, Object data) {
        return new Response(code, msg, data);
    }

    public static Response fail() {
        return new Response(400, "fail");
    }

    public static Response fail(String msg) {
        return new Response(400, msg);
    }

    public static Response fail(Integer code, String msg) {
        return new Response(code, msg);
    }

}
