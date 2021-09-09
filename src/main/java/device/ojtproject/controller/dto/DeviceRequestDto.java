package device.ojtproject.controller.dto;

import device.ojtproject.entity.ActiveStatus;
import device.ojtproject.entity.DiscardStatus;
import lombok.*;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class DeviceRequestDto {
    @NotNull
    @Size(min = 3, max=10, message="길이는 3~10자리 사이만 가능합니다.")
    private String serialNumber;
    @NotNull
    @Size(min = 3, max=10, message="길이는 3~10자리 사이만 가능합니다.")
    private String macAddress;
    @NotNull
    @Size(min = 3, max=10, message="길이는 3~10자리 사이만 가능합니다.")
    private String qrCode;

}
