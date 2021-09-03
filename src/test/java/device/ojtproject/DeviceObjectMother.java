package device.ojtproject;

import device.ojtproject.domain.Device;
import device.ojtproject.dto.DeviceDto;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class DeviceObjectMother {
    public static Optional<Device> createDevice() {
        return Optional.of(Device.builder()
                .serialNumber("1111")
                .qrcode("luke23")
                .macAddress("luke")
                .build());
    }


    public static List<Device> createDevices() {
        Device device1 = Device.builder()
                .serialNumber("2222")
                .qrcode("luke34")
                .macAddress("lukeluke")
                .build();
        Device device2 = Device.builder()
                .serialNumber("333")
                .qrcode("luke44")
                .macAddress("lukeluke2")
                .build();
        return Arrays.asList(device1, device2);
    }

    public static List<DeviceDto> createDeviceDto() {
        return createDevices().stream()
                .map(device -> DeviceDto.fromEntity(device))
                .collect(Collectors.toList());
    }

}
