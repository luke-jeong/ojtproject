package device.ojtproject.service.dto;

import device.ojtproject.controller.dto.DeviceRequestDto;
import device.ojtproject.entity.ActiveStatus;
import device.ojtproject.entity.DiscardStatus;
import device.ojtproject.entity.Device;
import lombok.*;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter @Setter
@Builder @NoArgsConstructor @AllArgsConstructor
@ToString
public class DeviceDto {
        private Long id;
        private String serialNumber;
        private String macAddress;
        private String qrCode;
        private ActiveStatus activeStatus;
        private DiscardStatus discardStatus;

        public static DeviceDto fromEntity(Device device){
            return DeviceDto.builder()
                    .serialNumber(device.getSerialNumber())
                    .qrCode(device.getQrCode())
                    .macAddress(device.getMacAddress())
                    .activeStatus(device.getActiveStatus())
                    .discardStatus(device.getDiscardStatus())
                    .build();
        }
        public static DeviceDto toDto(DeviceRequestDto deviceRequestDto){
                return DeviceDto.builder()
                        .serialNumber(deviceRequestDto.getSerialNumber())
                        .macAddress(deviceRequestDto.getMacAddress())
                        .qrCode(deviceRequestDto.getQrCode())
                        .build();
        }

        public static Device toEntity(DeviceDto deviceDto) {
                return Device.builder()
                        .serialNumber(deviceDto.getSerialNumber())
                        .macAddress(deviceDto.getMacAddress())
                        .qrCode(deviceDto.getQrCode())
                        .activeStatus(deviceDto.getActiveStatus())
                        .discardStatus(deviceDto.getDiscardStatus())
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
