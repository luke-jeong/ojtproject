package device.ojtproject.service;

import device.ojtproject.controller.dto.DeviceRequestDto;
import device.ojtproject.entity.ActiveStatus;
import device.ojtproject.entity.Device;
import device.ojtproject.entity.DiscardStatus;
import device.ojtproject.service.dto.*;
import device.ojtproject.exception.DeviceException;
import device.ojtproject.repository.DeviceRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

import static device.ojtproject.exception.DeviceErrorCode.*;

@Service
@AllArgsConstructor
public class DeviceServiceImpl implements DeviceService{

    private final DeviceRepository deviceRepository;

    //----------------------------생성
    @Transactional
    @Override
    public DeviceCreateDto deviceCreate (DeviceCreateDto deviceCreateDto, DeviceRequestDto deviceRequestDto){
        validateCreateDeviceRequest(deviceCreateDto);
        return DeviceCreateDto.fromEntity(
                deviceRepository.save(Device.toDto(createDeviceFromRequest(deviceRequestDto))));
    }

    private DeviceCreateDto createDeviceFromRequest(DeviceRequestDto deviceRequestDto){
        return DeviceCreateDto.builder()
                .serialNumber(deviceRequestDto.getSerialNumber())
                .qrCode(deviceRequestDto.getQrCode())
                .macAddress(deviceRequestDto.getMacAddress())
                .activeStatus(ActiveStatus.ACTIVE)
                .discardStatus(DiscardStatus.NORMAL)
                .build();
    }

    private void validateCreateDeviceRequest(DeviceCreateDto deviceCreateDto) {
        //business validation
        validDeviceLevel(
                deviceCreateDto.getSerialNumber(),
                deviceCreateDto.getActiveStatus(),
                deviceCreateDto.getQrCode()
        );

        deviceRepository.findBySerialNumber(deviceCreateDto.getSerialNumber())
                .ifPresent((device -> {
                    throw new DeviceException(DUPLICATED_SN);
                }));
    }



    //----------------------------------------조회

    @Override
    public DeviceDetailDto getDeviceDetailDto(String serialNumber) {
        return deviceRepository.findBySerialNumber(serialNumber)
                .map(DeviceDetailDto::fromEntity)
                .orElseThrow(() -> new DeviceException(NO_SERIAL_NUMBER));
    }

    @Override
    public List<DeviceSearchDto> getDeviceSearch(String serialNumber, String qrCode, String macAddress) {
        List<Device> devices;
        if(serialNumber != null) devices = deviceRepository.findBySerialNumberContaining(serialNumber);
        else if(qrCode != null) devices = deviceRepository.findByQrCodeContaining(qrCode);
        else if(macAddress != null) devices = deviceRepository.findByMacAddressContaining(macAddress);
        else devices = deviceRepository.findAll();

        return devices
                .stream().map(DeviceSearchDto::fromEntity)
                .collect(Collectors.toList());
    }

    //------------------------------수정
    @Transactional
    @Override
    public DeviceEditDto deviceEdit(DeviceEditDto deviceEditDto, String serialNumber) {
        validateDeviceEditRequest(deviceEditDto, serialNumber);
        Device device = deviceRepository.findBySerialNumber(serialNumber).orElseThrow(
                () -> new DeviceException(NO_MEMBER)
        );
        device.setSerialNumber(deviceEditDto.getSerialNumber());
        device.setMacAddress(deviceEditDto.getMacAddress());
        device.setQrCode(deviceEditDto.getQrCode());
        device.setActiveStatus(deviceEditDto.getActiveStatus());
        return DeviceEditDto.fromEntity(device);
    }

    private void validateDeviceEditRequest(
            DeviceEditDto deviceEditDto,
            String serialNumber
    ) {
        validDeviceLevel(
                deviceEditDto.getSerialNumber(),
                deviceEditDto.getActiveStatus(),
                deviceEditDto.getQrCode()
        );


    }

    private void validDeviceLevel(String serialNumber, ActiveStatus activeStatus, String qrCode) {
        if(serialNumber == null){
            throw new DeviceException(NO_SERIAL_NUMBER);}
        if(qrCode == null){
            throw new DeviceException(NO_QRCODE);}
        if(activeStatus == ActiveStatus.INACTIVE){
            throw new DeviceException(ACTIVE_ERROR);}
        }


    //--------------------------------------삭제
    @Transactional
    @Override
    public DeviceDetailDto discardDevice(String serialNumber){
        //NORMAL -> DELETE
        //DELETE 테이블에 상태가 저장 됨.
        Device device = deviceRepository.findBySerialNumber(serialNumber)
                .orElseThrow(() -> new DeviceException(NO_MEMBER));
        device.setDiscardStatus(DiscardStatus.DISCARD);

        return DeviceDetailDto.fromEntity(device);

    }

}
