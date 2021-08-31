package device.ojtproject.service;

import device.ojtproject.domain.ActiveStatus;
import device.ojtproject.domain.DeleteStatus;
import device.ojtproject.dto.CreateDevice;
import device.ojtproject.dto.DeviceDto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class DeviceServiceTest {

    @Autowired
    private DeviceService deviceService;

    @Test
    public void testtest() {
        deviceService.createDevice(CreateDevice.Request.builder()
                .serialNumber(1111L)
                .qrcode("luke")
                .macAddress("luke111")
                .activeStatus(ActiveStatus.ACTIVE)
                .deleteStatus(DeleteStatus.NORMAL)
                .build()
        );
        List<DeviceDto> allNormalDevice = deviceService.getAllNormalDevices();
        System.out.println(allNormalDevice);
    }
}