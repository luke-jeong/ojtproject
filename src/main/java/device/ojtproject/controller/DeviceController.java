package device.ojtproject.controller;


import device.ojtproject.domain.ActiveStatus;
import device.ojtproject.dto.*;
import device.ojtproject.exception.DeviceException;
import device.ojtproject.service.DeviceService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/devices")
public class DeviceController {
//

    private final DeviceService deviceService;
    //구현체로 받으면 모킹이 안되기 때문에 serviceimpl이 아니라 service를 받아야한다.

//  폐기되지 않은 device 조회
//  GET /devices?activateStatus=
    @GetMapping
    public List<DeviceDto> getAllDevices(
            @RequestParam(value = "activateStatus", required = false) ActiveStatus activeStatus,
            @RequestParam(value = "serialNumber", required = false) String serialNumber,
            @RequestParam(value = "qrcode", required = false) String qrcode,
            @RequestParam(value = "macAddress", required = false) String macAddress

    ) {

        log.info("GET /devices HTTP/1.1");

        return deviceService.getDeviceSearch();
    }

    //생성
    @PostMapping
    public CreateDevice.Response createDevices(
            @Valid @RequestBody CreateDevice.Request request
    ){
        log.info("request : {}", request);

        return deviceService.createDevice(request);
    }
    //수정
    @PutMapping("/{serialNumber}")
    public DeviceDetailDto editDevice(
            @PathVariable String serialNumber,
            @Valid @RequestBody EditDevice.Request request
    ) {
        log.info("GET /devices HTTP/1.1");

        return deviceService.editDevice(request, serialNumber);
    }


    //삭제
    @DeleteMapping("/{serialNumber}")
    public DeviceDetailDto deleteDevice(
            @PathVariable String serialNumber
    ){
        return deviceService.discardDevice(serialNumber);
    }

    //에러코드
    @ExceptionHandler(DeviceException.class)
    public DeviceErrorResponse handleException(
            DeviceException e,
            HttpServletRequest request
    ){
        log.error("errorCode:{}, url:{}, message:{}",
                e.getDeviceErrorCode(), request.getRequestURI(), e.getDetailMessage());
        return DeviceErrorResponse.builder()
                .errorCode(e.getDeviceErrorCode())
                .errorMessage(e.getDetailMessage())
                .build();
    }

}
