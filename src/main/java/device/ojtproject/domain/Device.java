package device.ojtproject.domain;

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

    private Long serialNumber;
    private String macAddress;
    private String qrcode;

    @Enumerated(EnumType.STRING)
    private ActiveStatus activeStatus;//활성화&비활성화 여부

    @Enumerated(EnumType.STRING)
    private DeleteStatus deleteStatus;

}
