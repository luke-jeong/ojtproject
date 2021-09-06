package device.ojtproject.controller.dto;

import device.ojtproject.entity.ActiveStatus;
import device.ojtproject.entity.DiscardStatus;
import device.ojtproject.service.dto.DeviceCreateDto;

import device.ojtproject.service.dto.DeviceEditDto;
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

    public static DeviceResponseDto fromDto(DeviceCreateDto deviceCreateDto){
        return DeviceResponseDto.builder()
                .serialNumber(deviceCreateDto.getSerialNumber())
                .qrCode(deviceCreateDto.getQrCode())
                .macAddress(deviceCreateDto.getMacAddress())
                .activeStatus(deviceCreateDto.getActiveStatus())
                .discardStatus(deviceCreateDto.getDiscardStatus())
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

    public static DeviceResponseDto fromDto(DeviceEditDto deviceEditDtoo){
        return DeviceResponseDto.builder()
                .serialNumber(deviceEditDtoo.getSerialNumber())
                .qrCode(deviceEditDtoo.getQrCode())
                .macAddress(deviceEditDtoo.getMacAddress())
                .activeStatus(deviceEditDtoo.getActiveStatus())
                .discardStatus(deviceEditDtoo.getDiscardStatus())
                .build();
    }

}
