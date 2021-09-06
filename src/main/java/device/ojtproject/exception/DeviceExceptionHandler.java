package device.ojtproject.exception;

import device.ojtproject.exception.dto.DeviceErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@RestControllerAdvice
public class DeviceExceptionHandler {

    @ExceptionHandler(DeviceException.class)
    public DeviceErrorResponse handleException(
            DeviceException e,
            HttpServletRequest request
    ){
        log.error("errorCode:{}, url:{}, message:{}",
                e.getDeviceErrorCode(), request.getRequestURI(), e.getDetailMessage());
        return DeviceErrorResponse.builder()
                .errorCode(e.getDeviceErrorCode())
                .errorMessage(e.getDetailMessage())
                .build();
    }

    @ExceptionHandler(value = {
            HttpRequestMethodNotSupportedException.class,
            //post, put 에러 발생 시
            MethodArgumentNotValidException.class
            //size, min, max 등에서 에러 발생 시
    })
    public DeviceErrorResponse handleBadRequest(
            Exception e, HttpServletRequest request
    ){
        log.error("url:{}, message:{}", request.getRequestURI(), e.getMessage());
        return DeviceErrorResponse.builder()
                .errorCode(DeviceErrorCode.INVALID_REQUEST)
                .errorMessage(DeviceErrorCode.INVALID_REQUEST.getMessage())
                .build();
    }
    @ExceptionHandler(Exception.class)
    public DeviceErrorResponse handleException(
            Exception e, HttpServletRequest request
    ){
        log.error("url:{}, message:{}", request.getRequestURI(), e.getMessage());
        return DeviceErrorResponse.builder()
                .errorCode(DeviceErrorCode.INTERNAL_SERVER_ERROR)
                .errorMessage(DeviceErrorCode.INTERNAL_SERVER_ERROR.getMessage())
                .build();
    }

}
