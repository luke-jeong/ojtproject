package device.ojtproject.service;

import device.ojtproject.service.dto.*;

import java.util.List;


public interface DeviceService {
    public DeviceDto createDevice(DeviceDto deviceDto);
    public DeviceDto getDeviceDto(String serialNumber);
    public DeviceDto editDevice(DeviceDto deviceDto, String serialNumber);
    public DeviceDto discardDevice(String serialNumber);
    public List<DeviceSearchDto> getDeviceSearch(String serialNumber, String qrCode, String macAddress);
//
}
