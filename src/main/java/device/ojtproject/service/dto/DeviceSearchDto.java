package device.ojtproject.service.dto;

import device.ojtproject.entity.ActiveStatus;
import device.ojtproject.entity.Device;
import device.ojtproject.entity.DiscardStatus;
import lombok.*;

@Getter @Setter
@Builder @NoArgsConstructor @AllArgsConstructor
@ToString
public class DeviceSearchDto {
    private String serialNumber;
    private String macAddress;
    private String qrCode;
    private ActiveStatus activeStatus;
    private DiscardStatus discardStatus;

    public static DeviceSearchDto fromEntity(Device device){
        return DeviceSearchDto.builder()
                .serialNumber(device.getSerialNumber())
                .qrCode(device.getQrCode())
                .macAddress(device.getMacAddress())
                .activeStatus(device.getActiveStatus())
                .discardStatus(device.getDiscardStatus())
                .build();
    }
    public static Device toEntity(DeviceSearchDto deviceSearchDto) {
        return Device.builder()
                .serialNumber(deviceSearchDto.getSerialNumber())
                .macAddress(deviceSearchDto.getMacAddress())
                .qrCode(deviceSearchDto.getQrCode())
                .activeStatus(deviceSearchDto.getActiveStatus())
                .discardStatus(deviceSearchDto.getDiscardStatus())
                .build();
    }
}