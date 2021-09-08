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
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/devices")
public class DeviceController {
//

    private final DeviceService deviceService;


    //  device 조회
    @GetMapping
    public List<DeviceResponseDto> searchAllDevices(
            @RequestParam(value = "serialNumber", required = false) String serialNumber,
            @RequestParam(value = "qrCode", required = false) String qrCode,
            @RequestParam(value = "macAddress", required = false) String macAddress
    ) {
        log.info("GET /devices HTTP/1.1");
        List<DeviceSearchDto> searchDevices = deviceService.searchDevice(serialNumber,qrCode,macAddress);
        return searchDevices
                .stream().map(DeviceResponseDto::fromSearchDto)
                .collect(Collectors.toList());
    }

    //생성
    @PostMapping
    public DeviceResponseDto createDevice(
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
        DeviceDto editDevice = deviceService.editDevice(
                DeviceDto.toDto(deviceRequestDto), serialNumber
        );

        log.info("GET /devices HTTP/1.1");

        return DeviceResponseDto.fromDto(editDevice);
    }


    //삭제
    @DeleteMapping("/{serialNumber}")
    public DeviceResponseDto discardDevice(
            @PathVariable String serialNumber
    ){
        DeviceDto discardDevice  = deviceService.discardDevice(serialNumber);
        return DeviceResponseDto.fromDto(discardDevice);
    }

    // 동작정지
    /*@PutMapping("/{serialNumber}")
    public DeviceResponseDto inactiveDevice(
            @PathVariable String serialNumber
    ){
        DeviceDto inactiveDevice  = deviceService.inactiveDevice(serialNumber);
        return DeviceResponseDto.fromDto(inactiveDevice);
    }*/


}
