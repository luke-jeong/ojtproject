package device.ojtproject.repository;

import device.ojtproject.domain.DeleteStatus;
import device.ojtproject.domain.Device;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;


public interface DeviceRepository extends JpaRepository<Device, Long>{
    Optional<Device> findByQrcode(String qrcode);

    Optional<Device> findBySerialNumber(Long serialNumber);

    List<Device> findDevicesByDeleteStatusEquals(DeleteStatus deleteStatus);

}

