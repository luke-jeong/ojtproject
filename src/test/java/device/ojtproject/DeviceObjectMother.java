package device.ojtproject;

import device.ojtproject.controller.dto.DeviceRequestDto;
import device.ojtproject.controller.dto.DeviceResponseDto;
import device.ojtproject.entity.ActiveStatus;
import device.ojtproject.entity.Device;
import device.ojtproject.entity.DiscardStatus;
import device.ojtproject.service.dto.DeviceDto;
import device.ojtproject.service.dto.DeviceSearchDto;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class DeviceObjectMother {
    public static Device createDevice() {
        return Device.builder()
                .serialNumber("1111")
                .qrCode("lukeQR")
                .macAddress("lukeMAC")
                .activeStatus(ActiveStatus.ACTIVE)
                .discardStatus(DiscardStatus.NORMAL)
                .build();
    }

    public static Device createInactiveDevice() {
        return Device.builder()
                .serialNumber("1111")
                .qrCode("lukeQR")
                .macAddress("lukeMAC")
                .activeStatus(ActiveStatus.INACTIVE)
                .discardStatus(DiscardStatus.NORMAL)
                .build();
    }


    public static List<Device> createDevices() {

        return Arrays.asList(
                Device.builder()
                        .id(1L)
                        .serialNumber("1111")
                        .qrCode("lukeQR1")
                        .macAddress("lukeMAC1")
                        .activeStatus(ActiveStatus.ACTIVE)
                        .discardStatus(DiscardStatus.NORMAL)
                        .build(),
                Device.builder()
                        .id(2L)
                        .serialNumber("1122")
                        .qrCode("lukeQR2")
                        .macAddress("lukeMAC2")
                        .activeStatus(ActiveStatus.ACTIVE)
                        .discardStatus(DiscardStatus.NORMAL)
                        .build(),
                Device.builder()
                        .id(3L)
                        .serialNumber("2233")
                        .qrCode("lukeQR3")
                        .macAddress("lukeMAC3")
                        .activeStatus(ActiveStatus.ACTIVE)
                        .discardStatus(DiscardStatus.NORMAL)
                        .build());
    }

    public static List<DeviceDto> createDeviceDto() {
        return createDevices().stream()
                .map(device -> DeviceDto.fromEntity(device))
                .collect(Collectors.toList());
    }

    public static List<Device> discardDevices() {

        return Arrays.asList(Device.builder()
                        .serialNumber("1111")
                        .qrCode("lukeQR1")
                        .macAddress("lukeMAC1")
                        .discardStatus(DiscardStatus.DISCARD)
                        .build(),
                Device.builder()
                        .serialNumber("1122")
                        .qrCode("lukeQR2")
                        .macAddress("lukeMAC2")
                        .discardStatus(DiscardStatus.DISCARD)
                        .activeStatus(ActiveStatus.INACTIVE)
                        .build());
    }
    public static DeviceDto deviceCreateDto(){
        return DeviceDto.builder()
                .serialNumber("1111")
                .qrCode("lukeQR1")
                .macAddress("lukeMAC1")
                .build();
    }
    public static DeviceSearchDto deviceSearchDto(){
        return DeviceSearchDto.builder()
                .serialNumber("2222")
                .qrCode("lukeQR1")
                .macAddress("lukeMAC1")
                .build();
    }
    public static DeviceResponseDto deviceResponseDto(){
        return DeviceResponseDto.builder()
                .serialNumber("1111")
                .qrCode("lukeQR1")
                .macAddress("lukeMAC1")
                .activeStatus(ActiveStatus.ACTIVE)
                .discardStatus(DiscardStatus.NORMAL)
                .build();
    }

    public static DeviceRequestDto deviceRequestDto(){
        return DeviceRequestDto.builder()
                .serialNumber("1111")
                .qrCode("lukeQR1")
                .macAddress("lukeMAC1")
                .build();
    }

    public static DeviceDto editDevice(){
        return DeviceDto.builder()
                .serialNumber("5555")
                .qrCode("newQR")
                .macAddress("newMac")
                .build();
    }


    public static Device createActiveDevice() {
        return Device.builder()
                .serialNumber("1111")
                .qrCode("lukeQR")
                .macAddress("lukeMAC")
                .activeStatus(ActiveStatus.ACTIVE)
                .discardStatus(DiscardStatus.NORMAL)
                .build();
    }

    public static Device DiscardDevice(){
        return Device.builder()
                .serialNumber("1111")
                .qrCode("lukeQR")
                .macAddress("lukeMAC")
                .activeStatus(ActiveStatus.ACTIVE)
                .discardStatus(DiscardStatus.NORMAL)
                .build();
    }
}
