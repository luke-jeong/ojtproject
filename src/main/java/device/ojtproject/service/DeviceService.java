package device.ojtproject.service;

import device.ojtproject.service.dto.*;

import java.util.List;


public interface DeviceService {
    public DeviceDto createDevice(DeviceDto deviceDto);
    public DeviceDto editDevice(DeviceDto deviceDto, String serialNumber);
    public DeviceDto discardDevice(String serialNumber);
    public DeviceDto inactiveDevice(String serialNumber);
    public List<DeviceSearchDto> searchDevice(String serialNumber, String qrCode, String macAddress);
//
}
