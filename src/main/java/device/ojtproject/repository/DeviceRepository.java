package device.ojtproject.repository;

import device.ojtproject.domain.Device;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface DeviceRepository extends JpaRepository<Device, String>{

}

