package com.imooc.exception;

import com.imooc.utils.JSONResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.multipart.MaxUploadSizeExceededException;

/**
 * @author pengjunzhen
 * @description 自定义异常处理类
 * @date 2020/3/7 18:16
 */
@RestControllerAdvice
public class CustomExceptionHandler {

    /**
     * 上传文件超过1MB，捕获异常：MaxUploadSizeExceededException
     * @return JSONResult 错误信息
     */
    @ExceptionHandler(MaxUploadSizeExceededException.class)
    public JSONResult handleMaxUploadFile() {
        return JSONResult.errorMsg("文件上传大小不能超过1MB，请压缩图片或者降低图片质量再上传！");
    }
}
