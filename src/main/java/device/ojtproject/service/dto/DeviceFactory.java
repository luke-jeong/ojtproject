package device.ojtproject.service.dto;

import device.ojtproject.entity.ActiveStatus;
import device.ojtproject.entity.Device;
import device.ojtproject.entity.DiscardStatus;

public class DeviceFactory {
    public static Device getDevice(DeviceDto deviceDto) {
        return Device.builder()
                .serialNumber(deviceDto.getSerialNumber())
                .macAddress(deviceDto.getMacAddress())
                .qrCode(deviceDto.getQrCode())
                .activeStatus(ActiveStatus.ACTIVE)
                .discardStatus(DiscardStatus.NORMAL)
                .build();

    }
    public static DeviceDto getDeviceDto(Device device) {
        return DeviceDto.builder()
                .serialNumber(device.getSerialNumber())
                .macAddress(device.getMacAddress())
                .qrCode(device.getQrCode())
                .activeStatus(device.getActiveStatus())
                .discardStatus(device.getDiscardStatus())
                .build();

    }

    public static DeviceSearchDto getDeviceSearchDto(Device device) {
        return DeviceSearchDto.builder()
                .serialNumber(device.getSerialNumber())
                .macAddress(device.getMacAddress())
                .qrCode(device.getQrCode())
                .activeStatus(device.getActiveStatus())
                .discardStatus(device.getDiscardStatus())
                .build();
    }

}
