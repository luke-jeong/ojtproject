package device.ojtproject.dto;

import device.ojtproject.domain.ActiveStatus;
import device.ojtproject.domain.DeleteStatus;
import device.ojtproject.domain.Device;
import lombok.*;

@Getter @Setter
@Builder @NoArgsConstructor @AllArgsConstructor
@ToString
public class DeviceDto {
        private Long serialNumber;
        private String macAddress;
        private String qrcode;
        private ActiveStatus activeStatus;
        private DeleteStatus deleteStatus;

        public static DeviceDto fromEntity(Device device){
            return DeviceDto.builder()
                    .serialNumber(device.getSerialNumber())
                    .qrcode(device.getQrcode())
                    .macAddress(device.getMacAddress())
                    .activeStatus(device.getActiveStatus())
                    .deleteStatus(device.getDeleteStatus())
                    .build();


    }
}
