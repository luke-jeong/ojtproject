package device.ojtproject;

import device.ojtproject.controller.dto.DeviceRequestDto;
import device.ojtproject.entity.ActiveStatus;
import device.ojtproject.entity.Device;
import device.ojtproject.entity.DiscardStatus;
import device.ojtproject.service.dto.DeviceCreateDto;
import device.ojtproject.service.dto.DeviceDto;
import device.ojtproject.service.dto.DeviceEditDto;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class DeviceObjectMother {
    public static Device createDevice() {
        return Device.builder()
                .serialNumber("1111")
                .qrCode("lukeQR")
                .macAddress("lukeMAC")
                .build();
    }


    public static List<Device> createDevices() {

        return Arrays.asList(
                Device.builder()
                        .serialNumber("1111")
                        .qrCode("lukeQR1")
                        .macAddress("lukeMAC1")
                        .build(),
                Device.builder()
                        .serialNumber("1122")
                        .qrCode("lukeQR2")
                        .macAddress("lukeMAC2")
                        .discardStatus(DiscardStatus.DISCARD)
                        .build(),
                Device.builder()
                        .serialNumber("2233")
                        .qrCode("lukeQR3")
                        .macAddress("lukeMAC3")
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
    public static DeviceCreateDto deviceCreateDto(){
        return DeviceCreateDto.builder()
                .serialNumber("1111")
                .qrCode("lukeQR1")
                .macAddress("lukeMAC1")
                .build();
    }

    public static DeviceRequestDto deviceRequestDto(){
        return DeviceRequestDto.builder()
                .serialNumber("1111")
                .qrCode("lukeQR1")
                .macAddress("lukeMAC1")
                .build();
    }

    public static DeviceEditDto deviceEditDto(){
        return DeviceEditDto.builder()
                .serialNumber("5555")
                .qrCode("newQR")
                .macAddress("newMac")
                .build();
    }





}