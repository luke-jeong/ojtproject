package device.ojtproject.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter @Setter
public class Device {
    @Id @GeneratedValue
    @Column(name="SN")
    private long serialNumber;

    private String macAddress;
    private String qrcode;
    @Enumerated(EnumType.STRING)
    private ActiveStatus astatus;//활성화&비활성화 여부
    @Enumerated(EnumType.STRING)
    private DeviceStatus dstatus; //삭제 여부
}
