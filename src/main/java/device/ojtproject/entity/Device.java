package device.ojtproject.entity;

import device.ojtproject.exception.DeviceErrorCode;
import device.ojtproject.exception.DeviceException;
import device.ojtproject.service.dto.DeviceDto;
import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;

@Entity
@EntityListeners(AuditingEntityListener.class)
@Getter
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

    public void edit(Device device){
        //nullPointException을 피하기 위해 equals 사용시
        //!"문자".equals(변수) 로 써야한다.
        if(! device.getSerialNumber().equals(this.serialNumber)){
            this.serialNumber = device.getSerialNumber();
        }
        if(! device.getMacAddress().equals(this.macAddress)) {
            this.macAddress = device.getMacAddress();
        }
        if(! device.getQrCode().equals(this.qrCode)) {
            this.qrCode = device.getQrCode();
        }
    }


    public boolean isInactive() {
        return this.activeStatus.equals(ActiveStatus.INACTIVE);
    }
    public boolean isActive() {
        return this.activeStatus.equals(ActiveStatus.ACTIVE);
    }
    public void changeToINActive() {
        if(ActiveStatus.INACTIVE.equals(this.activeStatus)) {
            throw new DeviceException(DeviceErrorCode.ACTIVE_ERROR);
        }
        this.activeStatus = ActiveStatus.INACTIVE;
    }


    public boolean isDiscard() {
        return this.discardStatus.equals(DiscardStatus.DISCARD);
    }
    public boolean isNormal() {
        return this.discardStatus.equals(DiscardStatus.NORMAL);
    }
    public void changeToDiscard() {
        if(DiscardStatus.DISCARD.equals(this.discardStatus)) {
            throw new DeviceException(DeviceErrorCode.ALREADY_DISCARDED);
        }
        this.discardStatus = DiscardStatus.DISCARD;
    }
}
