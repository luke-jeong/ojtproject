package device.ojtproject.service.dto;

import device.ojtproject.controller.dto.DeviceRequestDto;
import device.ojtproject.entity.ActiveStatus;
import device.ojtproject.entity.Device;
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
public class DeviceCreateDto {
    @NotNull
    @Min(1) @Max(999999)
    private String serialNumber;
    @NotNull
    @Size(min = 3, max=10, message="길이는 3~10자리 사이만 가능합니다.")
    private String macAddress;
    @NotNull
    @Size(min = 3, max=10, message="길이는 3~10자리 사이만 가능합니다.")
    private String qrCode;
    private ActiveStatus activeStatus;
    private DiscardStatus discardStatus;

    public static DeviceCreateDto toDto(DeviceRequestDto deviceRequestDto){
        return DeviceCreateDto.builder()
                .serialNumber(deviceRequestDto.getSerialNumber())
                .qrCode(deviceRequestDto.getQrCode())
                .macAddress(deviceRequestDto.getMacAddress())
                .activeStatus(ActiveStatus.ACTIVE)
                .discardStatus(DiscardStatus.NORMAL)
                .build();
    }

    public static DeviceCreateDto fromEntity(Device device){
        return DeviceCreateDto.builder()
                .serialNumber(device.getSerialNumber())
                .qrCode(device.getQrCode())
                .macAddress(device.getMacAddress())
                .activeStatus(device.getActiveStatus())
                .discardStatus(device.getDiscardStatus())
                .build();
    }
}

