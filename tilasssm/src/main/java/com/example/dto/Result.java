package com.example.dto;

/**
 * 统一响应结果封装
 */
public class Result {
    /** 响应码: 1-成功, 0-失败 */
    private Integer code;
    /** 响应信息 */
    private String msg;
    /** 响应数据 */
    private Object data;

    public Result() {}

    public Result(Integer code, String msg, Object data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    /** 成功 */
    public static Result success() {
        return new Result(1, "success", null);
    }

    public static Result success(Object data) {
        return new Result(1, "success", data);
    }

    /** 失败 */
    public static Result error(String msg) {
        return new Result(0, msg, null);
    }

    public Integer getCode() { return code; }
    public void setCode(Integer code) { this.code = code; }
    public String getMsg() { return msg; }
    public void setMsg(String msg) { this.msg = msg; }
    public Object getData() { return data; }
    public void setData(Object data) { this.data = data; }

    @Override
    public String toString() {
        return "Result{code=" + code + ", msg='" + msg + '\'' + '}';
    }
}
