package device.ojtproject.exception;

import lombok.Getter;

@Getter
public class DeviceException extends RuntimeException{
    private DeviceErrorCode deviceErrorCode;
    private String detailMessage;

    public DeviceException(DeviceErrorCode errorCode){
        super(errorCode.getMessage());
        this.deviceErrorCode = errorCode;
        this.detailMessage = errorCode.getMessage();
    }

    public DeviceException(DeviceErrorCode errorCode, String detailMessage){
        super(errorCode.getMessage());
        this.deviceErrorCode = errorCode;
        this.detailMessage = detailMessage;
    }

}
