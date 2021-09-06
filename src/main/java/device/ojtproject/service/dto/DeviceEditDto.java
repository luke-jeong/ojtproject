package device.ojtproject.service.dto;

import device.ojtproject.controller.dto.DeviceRequestDto;
import device.ojtproject.entity.ActiveStatus;
import device.ojtproject.entity.Device;
import device.ojtproject.entity.DiscardStatus;
import lombok.*;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class DeviceEditDto {
        @NotNull
        @Size(min = 3, max=10, message="길이는 3~10자리 사이만 가능합니다.")
        private String serialNumber;
        @NotNull
        @Size(min = 3, max=10, message="길이는 3~10자리 사이만 가능합니다.")
        private String macAddress;
        @NotNull
        @Size(min = 3, max=10, message="길이는 3~10자리 사이만 가능합니다.")
        private String qrCode;
        private ActiveStatus activeStatus;
        private DiscardStatus discardStatus;

        public static DeviceEditDto fromEntity(Device device){
            return DeviceEditDto.builder()
                    .serialNumber(device.getSerialNumber())
                    .qrCode(device.getQrCode())
                    .macAddress(device.getMacAddress())
                    .activeStatus(device.getActiveStatus())
                    .discardStatus(device.getDiscardStatus())
                    .build();
        }

        public static DeviceEditDto toDto(DeviceRequestDto deviceRequestDto){
            return DeviceEditDto.builder()
                    .serialNumber(deviceRequestDto.getSerialNumber())
                    .macAddress(deviceRequestDto.getMacAddress())
                    .qrCode(deviceRequestDto.getQrCode())
                    .build();
        }

        public static Device toEntity(DeviceEditDto deviceEditDto){
            return Device.builder()
                    .serialNumber(deviceEditDto.getSerialNumber())
                    .qrCode(deviceEditDto.getQrCode())
                    .macAddress(deviceEditDto.getMacAddress())
                    .activeStatus(deviceEditDto.getActiveStatus())
                    .discardStatus(deviceEditDto.getDiscardStatus())
                    .build();
        }





}
