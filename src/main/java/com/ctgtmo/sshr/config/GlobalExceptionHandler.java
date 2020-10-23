package com.ctgtmo.sshr.config;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.ctgtmo.sshr.constant.ApiResponse;

@RestControllerAdvice
public class GlobalExceptionHandler {
  private final static Logger LOGGER = LoggerFactory.getLogger(GlobalExceptionHandler.class);

  /**
   * 
  * @Title: validationErrorHandler  
  * @Description:校验异常-@RequestBody类型 
  * @param @param ex
  * @param @return    参数  
  * @return LeeJSONResult    返回类型  
  * @throws
   */
  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ApiResponse validationErrorHandler(MethodArgumentNotValidException ex) {
    // 同样是获取BindingResult对象，然后获取其中的错误信息
    // 如果前面开启了fail_fast，事实上这里只会有一个信息
    LOGGER.info("校验异常=MethodArgumentNotValidException.class");
    // 如果没有，则可能又多个
    List<String> errorInformation = ex.getBindingResult().getAllErrors().stream().map(ObjectError::getDefaultMessage).collect(Collectors.toList());
    return new ApiResponse(ApiResponse.FAIL, errorInformation.toString());
  }

  /**
   * 
   * @Title: validationErrorHandler @Description:
   * (校验异常-@PathVariable和@RequestParam类型) 
   * 参数 @return LeeJSONResult 返回类型 @throws
   */
  @ExceptionHandler(ConstraintViolationException.class)
  public ApiResponse validationErrorHandlerOther(ConstraintViolationException ex) {
    List<String> errorInformation = ex.getConstraintViolations().stream().map(ConstraintViolation::getMessage).collect(Collectors.toList());
    return new ApiResponse(ApiResponse.FAIL, errorInformation.toString());
  }

  /**
   * 
   * @Title: validationErrorHandler
   * e @param @return 参数 @return LeeJSONResult 返回类型 @throws
   */
  @ExceptionHandler
  public ApiResponse validationErrorHandlerDefault(Exception e) {
    return new ApiResponse(ApiResponse.FAIL, e.getMessage());
  }
}
