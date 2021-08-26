package device.ojtproject.service;

import device.ojtproject.domain.Device;
import device.ojtproject.repository.DeviceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DeviceService {
    private final DeviceRepository deviceRepository;

    public List<Device> findDevices(){
        return deviceRepository.findAll();
    }
}
