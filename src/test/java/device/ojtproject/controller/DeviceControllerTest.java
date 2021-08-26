package device.ojtproject.controller;

import device.ojtproject.domain.Device;
import device.ojtproject.repository.DeviceRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DeviceControllerTest {
    @Autowired
    private DeviceRepository deviceRepository;

    @Test
    public void testinsert(){
        Device device = new Device();
        device.setDevicestatus(true);
        device.setActivestatus(false);
        device.setSerialNumber("1111");
        device.setQrcode("luke");
        device.setMacAddress("1q2w3e");
        deviceRepository.save(device);

    }
}