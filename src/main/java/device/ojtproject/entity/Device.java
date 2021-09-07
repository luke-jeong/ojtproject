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

    public boolean isInactive() {
        return this.activeStatus.equals(ActiveStatus.INACTIVE);
    }

    public boolean isActive() {
        return this.activeStatus.equals(ActiveStatus.ACTIVE);
    }

    public void changeToActive() {
        if(ActiveStatus.ACTIVE.equals(this.activeStatus)) {
            throw new DeviceException(DeviceErrorCode.ACTIVE_ERROR);
        }
        this.activeStatus = ActiveStatus.ACTIVE;
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
