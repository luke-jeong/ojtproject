package device.ojtproject.repository;

import device.ojtproject.domain.Device;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface DeleteDeviceRepository extends JpaRepository<Device, Long>{


}

