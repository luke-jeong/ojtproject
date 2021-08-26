package device.ojtproject.repository;

import device.ojtproject.domain.ActiveStatus;
import device.ojtproject.domain.Device;
import device.ojtproject.domain.DeviceStatus;
import device.ojtproject.service.DeviceService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;
@RunWith(SpringRunner.class)
@SpringBootTest
public class DeviceRepositoryTest {
    @Autowired
    private DeviceRepository deviceRepository;


    @Test
    public void testInsert(){
        Device device = new Device();
        device.setAstatus(ActiveStatus.ACTIVE);
        device.setDstatus(DeviceStatus.DELETE);
        device.setSerialNumber(1111);
        device.setQrcode("luke");
        device.setMacAddress("1q2w3e");
        deviceRepository.save(device);


    }

}