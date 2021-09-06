package device.ojtproject.service;

import device.ojtproject.DeviceObjectMother;
import device.ojtproject.entity.Device;
import device.ojtproject.service.dto.*;
import device.ojtproject.repository.DeviceRepository;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@RunWith(SpringJUnit4ClassRunner.class)
@ExtendWith(MockitoExtension.class)
public class DeviceServiceTest {

    @Mock
    private DeviceRepository deviceRepository;

    @InjectMocks
    private DeviceServiceImpl deviceService;

    @Test
    public void getDeviceSerialNumber() {
        given(deviceRepository.findBySerialNumberContaining(anyString()))
                .willReturn(Arrays.asList(DeviceObjectMother.createDevice()));
        List<DeviceSearchDto> deviceSearchDto = deviceService.getDeviceSearch("1111",null,null);
        assertEquals(1, deviceSearchDto.size());
    }

    @Test
    public void getDeviceQrCode() {
        given(deviceRepository.findByQrCodeContaining(anyString()))
                .willReturn(Arrays.asList(DeviceObjectMother.createDevice()));
        List<DeviceSearchDto> deviceSearchDto = deviceService.getDeviceSearch(null,"lukeQR",null);
        assertEquals(1, deviceSearchDto.size());
    }

    @Test
    public void getDeviceMacAddress() {
        given(deviceRepository.findByMacAddressContaining(anyString()))
                .willReturn(Arrays.asList(DeviceObjectMother.createDevice()));
        List<DeviceSearchDto> deviceSearchDto = deviceService.getDeviceSearch(null,null,"lukeMAC");
        assertEquals(1, deviceSearchDto.size());
    }



    @Test
    public void DeviceSearch(){
        given(deviceRepository.findBySerialNumberContaining(anyString()))
                .willReturn(DeviceObjectMother.createDevices());
        List<DeviceSearchDto> deviceSearch = deviceService.getDeviceSearch(null,null,null);
        assertEquals(3, deviceSearch.size());
    }



    @Test
    public void DeviceCreate(){
        //given
        ArgumentCaptor<Device> captor =
                ArgumentCaptor.forClass(Device.class);
        //when
        DeviceDto device = deviceService.createDevice(
                DeviceObjectMother.deviceCreateDto()

        );
        //then
        verify(deviceRepository, times(1)).save(captor.capture());
        Device savedDevice = captor.getValue();
        assertEquals("1111", savedDevice.getSerialNumber());
        assertEquals("lukeQR1", savedDevice.getQrCode());
        assertEquals("lukeMAC1", savedDevice.getMacAddress());

    }

    @Test
    public void DeviceEdit(){
        given(deviceRepository.findBySerialNumber(anyString()))
                .willReturn(Optional.of(DeviceObjectMother.createDevice()));

        DeviceDto deviceDto = deviceService.editDevice(DeviceObjectMother.editDevice(), "1111");

        assertEquals("5555", deviceDto.getSerialNumber());
        assertEquals("newQR", deviceDto.getQrCode());
        assertEquals("newMac", deviceDto.getMacAddress());

    }

    @Test
    public void createTest() {
        given(deviceRepository.findBySerialNumber(anyString()))
                .willReturn(Optional.of(DeviceObjectMother.createDevice()));

        DeviceDto deviceDto = deviceService.createDevice(DeviceObjectMother.deviceCreateDto());
        assertEquals("1111", deviceDto.getSerialNumber());
    }
}