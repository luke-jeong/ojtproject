package device.ojtproject.repository;

import device.ojtproject.domain.DiscardStatus;
import device.ojtproject.domain.Device;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;


public interface DeviceRepository extends JpaRepository<Device, String>{
    Optional<Device> findByQrcode(String qrcode);

    Optional<Device> findBySerialNumber(String serialNumber);

    List<Device> findBySerialNumberContaining (String serialNumber);
    List<Device> findByqrcodeContaining (String qrcode);
    List<Device> findByMacAddressContaining (String macAddress);

    List<Device> findDevicesByDiscardStatusEquals(DiscardStatus discardStatus);

}

