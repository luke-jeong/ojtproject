package device.ojtproject.entity;

import device.ojtproject.service.dto.DeviceCreateDto;
import device.ojtproject.service.dto.DeviceSearchDto;
import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;

@Entity
@EntityListeners(AuditingEntityListener.class)
@Getter @Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Device {
    @Id @GeneratedValue
    private Long id;

    private String serialNumber;
    private String macAddress;
    private String qrCode;

    @Enumerated(EnumType.STRING)
    private ActiveStatus activeStatus;//활성화&비활성화 여부

    @Enumerated(EnumType.STRING)
    private DiscardStatus discardStatus;

    public static Device toDto(DeviceCreateDto deviceCreateDto){
        return Device.builder()
                .serialNumber(deviceCreateDto.getSerialNumber())
                .qrCode(deviceCreateDto.getQrCode())
                .macAddress(deviceCreateDto.getMacAddress())
                .activeStatus(deviceCreateDto.getActiveStatus())
                .discardStatus(deviceCreateDto.getDiscardStatus())
                .build();
    }

    public static Device toDto(DeviceSearchDto deviceSearchDto){
        return Device.builder()
                .serialNumber(deviceSearchDto.getSerialNumber())
                .qrCode(deviceSearchDto.getQrCode())
                .macAddress(deviceSearchDto.getMacAddress())
                .activeStatus(deviceSearchDto.getActiveStatus())
                .discardStatus(deviceSearchDto.getDiscardStatus())
                .build();
    }

}
