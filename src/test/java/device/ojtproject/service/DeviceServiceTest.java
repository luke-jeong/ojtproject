package device.ojtproject.service;

import device.ojtproject.DeviceObjectMother;
import device.ojtproject.controller.dto.DeviceResponseDto;
import device.ojtproject.entity.ActiveStatus;
import device.ojtproject.entity.Device;
import device.ojtproject.entity.DiscardStatus;
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
        List<DeviceSearchDto> deviceSearchDto = deviceService.searchDevice("1111",null,null);
        assertEquals(1, deviceSearchDto.size());
    }
    @Test
    public void getDeviceQrCode() {
        given(deviceRepository.findByQrCodeContaining(anyString()))
                .willReturn(Arrays.asList(DeviceObjectMother.createDevice()));
        List<DeviceSearchDto> deviceSearchDto = deviceService.searchDevice(null,"lukeQR1",null);
        assertEquals(1, deviceSearchDto.size());
    }
    @Test
    public void getDeviceMacAddress() {
        given(deviceRepository.findByMacAddressContaining(anyString()))
                .willReturn(Arrays.asList(DeviceObjectMother.createDevice()));
        List<DeviceSearchDto> deviceSearchDto = deviceService.searchDevice(null,null,"lukeMAC1");
        assertEquals(1, deviceSearchDto.size());
    }
    @Test
    public void DeviceSearch(){
        given(deviceRepository.findAll())
                .willReturn(DeviceObjectMother.createDevices());
        List<DeviceSearchDto> deviceSearch = deviceService.searchDevice(null,null,null);
        assertEquals(3, deviceSearch.size());
    }

    @Test
    public void DeviceEdit(){
        given(deviceRepository.findBySerialNumber(anyString()))
                .willReturn(Optional.of(DeviceObjectMother.createDevice()));

        DeviceDto editDevice = deviceService.editDevice(DeviceObjectMother.editDevice(), "1111");

        assertEquals("5555", editDevice.getSerialNumber());
        assertEquals("newQR", editDevice.getQrCode());
        assertEquals("newMac", editDevice.getMacAddress());
    }

    @Test
    public void DiscardTest() {
        given(deviceRepository.findAll())
                .willReturn(DeviceObjectMother.createDevices());

        given(deviceRepository.findBySerialNumber(anyString()))
                .willReturn(Optional.of(DeviceObjectMother.DiscardDevice()));

        //초기 입력값
        Device device = DeviceObjectMother.createDevices().get(0);
        assertEquals(DiscardStatus.NORMAL, device.getDiscardStatus());

        //discard 된 값
        DeviceDto deviceDto = deviceService.discardDevice(device.getSerialNumber());
        assertEquals(DiscardStatus.DISCARD, deviceDto.getDiscardStatus());
    }


    @Test
    public void DeviceCreate(){
        //given
        given(deviceRepository.findBySerialNumber(anyString()))
                .willReturn(Optional.empty());
        ArgumentCaptor<Device> captor =
                ArgumentCaptor.forClass(Device.class);
        //when
        DeviceDto createDevice = deviceService.createDevice(DeviceObjectMother.deviceCreateDto());

        //then
        verify(deviceRepository, times(1)).save(captor.capture());
        Device savedDevice = captor.getValue();
        assertEquals("1111", savedDevice.getSerialNumber());
        assertEquals("lukeQR1", savedDevice.getQrCode());
        assertEquals("lukeMAC1", savedDevice.getMacAddress());

    }
}