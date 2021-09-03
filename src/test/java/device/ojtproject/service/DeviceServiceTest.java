package device.ojtproject.service;

import device.ojtproject.DeviceObjectMother;
import device.ojtproject.dto.DeviceDetailDto;
import device.ojtproject.repository.DeviceRepository;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import static org.junit.Assert.*;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.BDDMockito.given;

@RunWith(SpringJUnit4ClassRunner.class)
@ExtendWith(MockitoExtension.class)
public class DeviceServiceTest {

    @Mock
    private DeviceRepository deviceRepository;

    @InjectMocks
    private DeviceService deviceService;

    @Test
    public void getDeviceSerialNumber() {
        given(deviceRepository.findBySerialNumber(anyString()))
                .willReturn(DeviceObjectMother.createDevice());
        /*given(deviceRepository.save(any()))
                .willReturn(deviceRepository);*/
        DeviceDetailDto deviceDetailDtos = deviceService.getDeviceDetailDto("1111");
        assertEquals("1111", deviceDetailDtos.getSerialNumber());

        System.out.println("===========================");
        System.out.println("===========================");

    }

    @Test
    public void getDeviceDetailDto(){
        given(deviceRepository.findBySerialNumber(anyString()))
                .willReturn(DeviceObjectMother.createDevice());

    }
}