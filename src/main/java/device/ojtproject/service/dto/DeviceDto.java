package device.ojtproject.service.dto;

import device.ojtproject.entity.ActiveStatus;
import device.ojtproject.entity.DiscardStatus;
import device.ojtproject.entity.Device;
import lombok.*;

@Getter @Setter
@Builder @NoArgsConstructor @AllArgsConstructor
@ToString
public class DeviceDto {
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
}
