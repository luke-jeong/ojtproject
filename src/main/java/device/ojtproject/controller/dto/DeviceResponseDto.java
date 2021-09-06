package device.ojtproject.controller.dto;

import device.ojtproject.entity.ActiveStatus;
import device.ojtproject.entity.DiscardStatus;


import device.ojtproject.service.dto.DeviceDto;
import device.ojtproject.service.dto.DeviceSearchDto;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DeviceResponseDto{
    private String serialNumber;
    private String macAddress;
    private String qrCode;
    private ActiveStatus activeStatus;
    private DiscardStatus discardStatus;

    public static DeviceResponseDto fromDto(DeviceDto deviceDto){
        return DeviceResponseDto.builder()
                .serialNumber(deviceDto.getSerialNumber())
                .qrCode(deviceDto.getQrCode())
                .macAddress(deviceDto.getMacAddress())
                .activeStatus(deviceDto.getActiveStatus())
                .discardStatus(deviceDto.getDiscardStatus())
                .build();
    }

    public static DeviceResponseDto fromDto(DeviceSearchDto deviceSearchDto){
        return DeviceResponseDto.builder()
                .serialNumber(deviceSearchDto.getSerialNumber())
                .qrCode(deviceSearchDto.getQrCode())
                .macAddress(deviceSearchDto.getMacAddress())
                .activeStatus(deviceSearchDto.getActiveStatus())
                .discardStatus(deviceSearchDto.getDiscardStatus())
                .build();
    }


}
