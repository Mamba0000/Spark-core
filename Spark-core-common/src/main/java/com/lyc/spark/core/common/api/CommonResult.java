package com.lyc.spark.core.common.api;

import com.lyc.spark.core.constant.BladeConstant;
import com.lyc.spark.core.tool.ObjectUtil;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.lang.Nullable;
import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

@Getter
@Setter
public class CommonResult<T> {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "状态码", required = true)
    private int code;
    @ApiModelProperty(value = "是否成功", required = true)
    private boolean success;
    @ApiModelProperty(value = "承载数据")
    private T data;
    @ApiModelProperty(value = "返回消息", required = true)
    private String msg;

    public CommonResult() {

    }

    private CommonResult(IResultCode resultCode) {
        this(resultCode, null, resultCode.getMessage());
    }

    private CommonResult(IResultCode resultCode, String msg) {
        this(resultCode, null, msg);
    }

    private CommonResult(IResultCode resultCode, T data) {
        this(resultCode, data, resultCode.getMessage());
    }

    private CommonResult(IResultCode resultCode, T data, String msg) {
        this(resultCode.getCode(), data, msg);
    }

    private CommonResult(int code, T data, String msg) {
        this.code = code;
        this.data = data;
        this.msg = msg;
        this.success = ResultCode.SUCCESS.getCode() == code;
    }

    /**
     * 判断返回是否为成功
     *
     * @param result Result
     * @return 是否成功
     */
    public static boolean isSuccess(@Nullable CommonResult<?> result) {
        return Optional.ofNullable(result)
                .map(x -> ObjectUtil.nullSafeEquals(ResultCode.SUCCESS.getCode(), x.code))
                .orElse(Boolean.FALSE);
    }

    /**
     * 判断返回是否为成功
     *
     * @param result Result
     * @return 是否成功
     */
    public static boolean isNotSuccess(@Nullable CommonResult<?> result) {
        return !CommonResult.isSuccess(result);
    }

    /**
     * 返回R
     *
     * @param data 数据
     * @param <T>  T 泛型标记
     * @return R
     */
    public static <T> CommonResult<T> data(T data) {
        return data(data, BladeConstant.DEFAULT_SUCCESS_MESSAGE);
    }

    /**
     * 返回R
     *
     * @param data 数据
     * @param msg  消息
     * @param <T>  T 泛型标记
     * @return R
     */
    public static <T> CommonResult<T> data(T data, String msg) {
        return data(HttpServletResponse.SC_OK, data, msg);
    }

    /**
     * 返回R
     *
     * @param code 状态码
     * @param data 数据
     * @param msg  消息
     * @param <T>  T 泛型标记
     * @return R
     */
    public static <T> CommonResult<T> data(int code, T data, String msg) {
        return new CommonResult<>(code, data, data == null ? BladeConstant.DEFAULT_NULL_MESSAGE : msg);
    }

    /**
     * 返回R
     *
     * @param msg 消息
     * @param <T> T 泛型标记
     * @return R
     */
    public static <T> CommonResult<T> success(String msg) {
        return new CommonResult<>(ResultCode.SUCCESS, msg);
    }

    /**
     * 返回R
     *
     * @param resultCode 业务代码
     * @param <T>        T 泛型标记
     * @return R
     */
    public static <T> CommonResult<T> success(IResultCode resultCode) {
        return new CommonResult<>(resultCode);
    }

    /**
     * 返回R
     *
     * @param resultCode 业务代码
     * @param msg        消息
     * @param <T>        T 泛型标记
     * @return R
     */
    public static <T> CommonResult<T> success(IResultCode resultCode, String msg) {
        return new CommonResult<>(resultCode, msg);
    }

    /**
     * 返回R
     *
     * @param msg 消息
     * @param <T> T 泛型标记
     * @return R
     */
    public static <T> CommonResult<T> fail(String msg) {
        return new CommonResult<>(ResultCode.FAILED, msg);
    }


    /**
     * 返回R
     *
     * @param code 状态码
     * @param msg  消息
     * @param <T>  T 泛型标记
     * @return R
     */
    public static <T> CommonResult<T> fail(int code, String msg) {
        return new CommonResult<>(code, null, msg);
    }

    /**
     * 返回R
     *
     * @param resultCode 业务代码
     * @param <T>        T 泛型标记
     * @return R
     */
    public static <T> CommonResult<T> fail(IResultCode resultCode) {
        return new CommonResult<>(resultCode);
    }

    /**
     * 返回R
     *
     * @param resultCode 业务代码
     * @param msg        消息
     * @param <T>        T 泛型标记
     * @return R
     */
    public static <T> CommonResult<T> fail(IResultCode resultCode, String msg) {
        return new CommonResult<>(resultCode, msg);
    }

    /**
     * 返回R
     *
     * @param flag 成功状态
     * @return R
     */
    public static <T> CommonResult<T> status(boolean flag) {
        return flag ? success(BladeConstant.DEFAULT_SUCCESS_MESSAGE) : fail(BladeConstant.DEFAULT_FAILURE_MESSAGE);
    }
}
