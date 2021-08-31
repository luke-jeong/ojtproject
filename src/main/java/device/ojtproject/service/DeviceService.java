package device.ojtproject.service;

import device.ojtproject.domain.ActiveStatus;
import device.ojtproject.domain.DeleteStatus;
import device.ojtproject.domain.Device;
import device.ojtproject.dto.CreateDevice;
import device.ojtproject.dto.DeviceDetailDto;
import device.ojtproject.dto.DeviceDto;
import device.ojtproject.dto.EditDevice;
import device.ojtproject.exception.DeviceException;
import device.ojtproject.repository.DeviceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

import static device.ojtproject.exception.DeviceErrorCode.*;

@Service
@RequiredArgsConstructor
public class DeviceService {
    private final DeviceRepository deviceRepository;

    @Transactional
    public CreateDevice.Response createDevice (CreateDevice.Request request){
        validateCreateDeviceRequest(request);
        Device device = Device.builder()
                .serialNumber(request.getSerialNumber())
                .qrcode(request.getQrcode())
                .macAddress(request.getMacAddress())
                .activeStatus(ActiveStatus.ACTIVE)
                .deleteStatus(DeleteStatus.NORMAL)
                .build();
        deviceRepository.save(device);
        return CreateDevice.Response.fromEntity(device);
    }

    private void validateCreateDeviceRequest(CreateDevice.Request request) {
        //business validation
        validDeviceLevel(
                request.getSerialNumber(),
                request.getActiveStatus(),
                request.getQrcode()
        );

        deviceRepository.findBySerialNumber(request.getSerialNumber())
                .ifPresent((device -> {
                    throw new DeviceException(DUPLICATED_SN);
                }));
    }



    public List<DeviceDto> getAllNormalDevices() {
        return deviceRepository.findDevicesByDeleteStatusEquals(DeleteStatus.NORMAL)
                .stream().map(DeviceDto::fromEntity)
                .collect(Collectors.toList());
    }
    public List<DeviceDto> getAllDevices() {
        return deviceRepository.findAll()
                .stream().map(DeviceDto::fromEntity)
                .collect(Collectors.toList());
    }


    public DeviceDetailDto getDeviceDetailDto(Long serialNumber) {
        return deviceRepository.findBySerialNumber(serialNumber)
                .map(DeviceDetailDto::fromEntity)
                .orElseThrow(() -> new DeviceException(NO_SERIAL_NUMBER));
    }

    @Transactional
    public DeviceDetailDto editDevice(EditDevice.Request request, Long serialNumber) {
        validateEditDeviceRequest(request, serialNumber);
        Device device = deviceRepository.findBySerialNumber(serialNumber).orElseThrow(
                () -> new DeviceException(NO_MEMBER)
        );
        device.setSerialNumber(request.getSerialNumber());
        device.setMacAddress(request.getMacAddress());
        device.setQrcode(request.getQrcode());
        device.setActiveStatus(request.getActiveStatus());
        return DeviceDetailDto.fromEntity(device);
    }

    private void validateEditDeviceRequest(
            EditDevice.Request request,
            Long serialNumber
    ) {
        validDeviceLevel(
                request.getSerialNumber(),
                request.getActiveStatus(),
                request.getQrcode()
        );


    }

    private void validDeviceLevel(Long serialNumber, ActiveStatus activeStatus, String qrcode) {
        if(serialNumber == null){
            throw new DeviceException(SERIAL_NUMBER_IS_NOT_ACCEPTABLE);}
        if(qrcode == null){
            throw new DeviceException(NO_QRCODE);}
    }

    @Transactional
    public DeviceDetailDto deleteDevice(Long serialNumber){
        //NORMAL -> DELETE
        //DELETE 테이블에 상태가 저장 됨.
        Device device = deviceRepository.findBySerialNumber(serialNumber)
                .orElseThrow(() -> new DeviceException(NO_MEMBER));
        device.setDeleteStatus(DeleteStatus.DELETE);

        return DeviceDetailDto.fromEntity(device);

    }
//
}
