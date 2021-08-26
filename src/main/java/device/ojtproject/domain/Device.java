package device.ojtproject.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Getter @Setter
public class Device {
    @Id @GeneratedValue
    @Column(name="SN")
    private String serialNumber;

    private String macAddress;
    private String qrcode;
    private boolean activestatus; //활성화&비활성화 여부
    private boolean devicestatus; //삭제 여부
}
