package com.example.easyexcel.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * <p>
 * 请求响应结果集
 * <p>
 *
 * @author zhenhuaxiang
 * @since 2021-03-16
 */
@Data
@ApiModel(value = "响应结果集", description = "响应结果集")
@NoArgsConstructor
public class Result<T> implements Serializable {

    public Result(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Result(T data) {
        this.data = data;
    }

    public static Result success() {
        return new Result<>();
    }

    public static Result success(Object data) {
        return new Result<>(data);
    }

    public static Result<String> failed(Integer code, String message) {
        return new Result<>(code, message);
    }

    @ApiModelProperty(value = "请求成功响应码", example = "200")
    private int code = 200;

    @ApiModelProperty(value = "请求成功响应信息", example = "success")
    private String msg = "success";

    @ApiModelProperty(value = "请求成功响应结果集")
    private T data;
}
