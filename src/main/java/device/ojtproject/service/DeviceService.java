package device.ojtproject.service;

import device.ojtproject.controller.dto.DeviceRequestDto;
import device.ojtproject.service.dto.*;

import java.util.List;


public interface DeviceService {
    public DeviceCreateDto deviceCreate(DeviceCreateDto deviceCreateDto, DeviceRequestDto deviceRequestDto);
    public DeviceDetailDto getDeviceDetailDto(String serialNumber);
    public List<DeviceSearchDto> getDeviceSearch(String serialNumber, String qrCode, String macAddress);
    public DeviceEditDto deviceEdit(DeviceEditDto deviceEditDto, String serialNumber);
    public DeviceDetailDto discardDevice(String serialNumber);
//
}
