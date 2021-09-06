package device.ojtproject.controller;


import device.ojtproject.controller.dto.DeviceRequestDto;
import device.ojtproject.controller.dto.DeviceResponseDto;
import device.ojtproject.service.dto.*;
import device.ojtproject.service.DeviceService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/devices")
public class DeviceController {
//

    private final DeviceService deviceService;


//  device 조회
//  GET /devices?activateStatus=
    @GetMapping
    public List<DeviceSearchDto> getAllDevices(
            @RequestParam(value = "serialNumber", required = false) String serialNumber,
            @RequestParam(value = "qrCode", required = false) String qrCode,
            @RequestParam(value = "macAddress", required = false) String macAddress
    ) {
        log.info("GET /devices HTTP/1.1");
        return deviceService.getDeviceSearch(serialNumber,qrCode,macAddress);
    }

    //생성
    @PostMapping
    public DeviceResponseDto deviceCreate(
            @PathVariable String serialNumber,
            @Valid @RequestBody DeviceRequestDto deviceRequestDto
    ){
        log.info("request : {}", deviceRequestDto);
        DeviceDto createDevice = deviceService.createDevice(DeviceDto.toDto(deviceRequestDto));
        return DeviceResponseDto.fromDto(createDevice);


    }
    //수정
    @PutMapping("/{serialNumber}")
    public DeviceResponseDto editDevice(
            @PathVariable String serialNumber,
            @Valid @RequestBody DeviceRequestDto deviceRequestDto
    ) {
        DeviceDto deviceDto =
                deviceService.editDevice(
                        DeviceDto.toDto(deviceRequestDto), serialNumber);
        DeviceDto editDevice = deviceService.editDevice(deviceDto, serialNumber);

        log.info("GET /devices HTTP/1.1");

        return DeviceResponseDto.fromDto(editDevice);
    }


    //삭제
    @DeleteMapping("/{serialNumber}")
    public DeviceDto discardDevice(
            @PathVariable String serialNumber
    ){
        return deviceService.discardDevice(serialNumber);
    }



}
