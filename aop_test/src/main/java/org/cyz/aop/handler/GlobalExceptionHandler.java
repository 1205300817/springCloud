package org.cyz.aop.handler;

import lombok.extern.slf4j.Slf4j;
import org.cyz.aop.pojo.R;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;

/**
 * 统一异常处理
 *
 * @author chengyz
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(value = Exception.class)
    public R defaultErrorHandler(HttpServletRequest req, Exception e) throws Exception {
        log.info("异常：{}", e);
        return R.error(e.getMessage());
    }
}
