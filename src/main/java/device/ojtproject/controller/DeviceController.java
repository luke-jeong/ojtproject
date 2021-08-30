package device.ojtproject.controller;


import device.ojtproject.dto.CreateDevice;
import device.ojtproject.dto.DeviceDetailDto;
import device.ojtproject.dto.DeviceDto;
import device.ojtproject.dto.EditDevice;
import device.ojtproject.service.DeviceService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/device")
public class DeviceController {

    private final DeviceService deviceService;
    //구현체로 받으면 모킹이 안되기 때문에 serviceimpl이 아니라 service를 받아야한다.

    @GetMapping("/devices/normal")
    public List<DeviceDto> getNormalDevelopers() {
        log.info("GET /devices HTTP/1.1");

        return deviceService.getAllNormalDevices();
    }
    @GetMapping("/devices")
    public List<DeviceDto> getAllDevelopers() {
        log.info("GET /devices HTTP/1.1");

        return deviceService.getAllDevices();
    }
    @GetMapping("/detail/{serialNumber}")
    public DeviceDetailDto getDeviceDetail(
            @PathVariable Long serialNumber
    ) {
        log.info("GET /devices HTTP/1.1");

        return deviceService.getDeviceDetailDto(serialNumber);
    }

    //create
    @PostMapping("/create")
    public CreateDevice.Response createDevices(
            @Valid @RequestBody CreateDevice.Request request
    ){
        log.info("request : {}", request);

        return deviceService.createDevice(request);
    }
    //update
    @PutMapping("/detail/{serialNumber}")
    public DeviceDetailDto editDevice(
            @PathVariable Long serialNumber,
            @Valid @RequestBody EditDevice.Request request
    ) {
        log.info("GET /devices HTTP/1.1");

        return deviceService.editDevice(request, serialNumber);
    }

    @DeleteMapping("/detail/{serialNumber}")
    public DeviceDetailDto deleteDevice(
            @PathVariable Long serialNumber
    ){
        return deviceService.deleteDevice(serialNumber);
    }

}
