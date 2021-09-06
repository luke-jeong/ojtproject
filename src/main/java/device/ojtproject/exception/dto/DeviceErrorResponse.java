package device.ojtproject.exception.dto;


import device.ojtproject.exception.DeviceErrorCode;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DeviceErrorResponse {
    private DeviceErrorCode errorCode;
    private String errorMessage;
}