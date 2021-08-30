package device.ojtproject.service;

import device.ojtproject.domain.ActiveStatus;
import device.ojtproject.domain.Device;
import device.ojtproject.repository.DeviceRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DeviceServiceTest {
    @Autowired
    private DeviceRepository deviceRepository;


    @Test
    public void testInsert(){
        Device device = new Device();
        device.setAstatus(ActiveStatus.ACTIVE);
        device.setQrcode("luke");
        device.setMacAddress("1q2w3e");
        deviceRepository.save(device);


    }

}