package device.ojtproject.repository;

import device.ojtproject.entity.ActiveStatus;
import device.ojtproject.entity.DiscardStatus;
import device.ojtproject.entity.Device;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;


public interface DeviceRepository extends JpaRepository<Device, String>{
    Optional<Device> findBySerialNumber(String serialNumber);

    List<Device> findBySerialNumberContaining (String serialNumber);
    List<Device> findByQrCodeContaining (String qrCode);
    List<Device> findByMacAddressContaining (String macAddress);
    List<Device> findByDiscardStatusEquals(DiscardStatus discardStatus);
    List<Device> findByActiveStatusEquals(ActiveStatus activeStatus);

}

