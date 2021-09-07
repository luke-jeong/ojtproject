package device.ojtproject.service;

import device.ojtproject.entity.ActiveStatus;
import device.ojtproject.entity.Device;
import device.ojtproject.entity.DiscardStatus;
import device.ojtproject.exception.DeviceException;
import device.ojtproject.repository.DeviceRepository;
import device.ojtproject.service.dto.DeviceDto;
import device.ojtproject.service.dto.DeviceFactory;
import device.ojtproject.service.dto.DeviceSearchDto;
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

    //----------------------------------------조회

    @Override
    public DeviceDto getDeviceDto(String serialNumber) {
        return deviceRepository.findBySerialNumber(serialNumber)
                .map(DeviceDto::fromEntity)
                .orElseThrow(() -> new DeviceException(NO_SERIALNUMBER));
    }

    @Override
    public List<DeviceSearchDto> searchDevice(String serialNumber, String qrCode, String macAddress) {
        List<Device> devices;
        if(serialNumber != null) devices = deviceRepository.findBySerialNumberContaining(serialNumber);
        else if(qrCode != null) devices = deviceRepository.findByQrCodeContaining(qrCode);
        else if(macAddress != null) devices = deviceRepository.findByMacAddressContaining(macAddress);
        else devices = deviceRepository.findAll();

        return devices
                .stream().map(DeviceFactory::getDeviceSearchDto)
                .collect(Collectors.toList());
    }


    //----------------------------생성
    @Transactional
    @Override
    public DeviceDto createDevice (DeviceDto deviceDto){
        validateCreateDeviceRequest(deviceDto);
        Device device = DeviceFactory.getDevice(deviceDto);
        return DeviceFactory.getDeviceDto(deviceRepository.save(device));
    }

    private void validateCreateDeviceRequest(DeviceDto deviceDto) {
        //business validation
        /*validDeviceLevel(
                deviceDto.getSerialNumber(),
                deviceDto.getQrCode(),
                deviceDto.getMacAddress()
        );*/

        deviceRepository.findBySerialNumber(deviceDto.getSerialNumber())
                .ifPresent((device -> {
                    throw new DeviceException(DUPLICATED_SN); }));
    }

    //------------------------------수정
    @Transactional
    @Override
    public DeviceDto editDevice(DeviceDto deviceDto, String serialNumber) {
        validateDeviceEditRequest(deviceDto);
        Device device = deviceRepository.findBySerialNumber(serialNumber).orElseThrow(
                () -> new DeviceException(NO_MEMBER)
        );
        device.edit(DeviceFactory.getDevice(deviceDto));
        deviceRepository.save(device);
        return DeviceFactory.getDeviceDto(device);
    }

    private void validateDeviceEditRequest(DeviceDto deviceDto) {
        /*validDeviceLevel(
                deviceDto.getSerialNumber(),
                deviceDto.getQrCode(),
                deviceDto.getMacAddress()
        );*/
        deviceRepository.findBySerialNumber(deviceDto.getSerialNumber())
                .ifPresent((device -> {
                    throw new DeviceException(DUPLICATED_SN);
                }));


    }
    //--------------------------------------삭제
    @Transactional
    @Override
    public DeviceDto discardDevice(String serialNumber){
        //NORMAL -> DELETE
        //DELETE 테이블에 상태가 저장 됨.
        Device device = deviceRepository.findBySerialNumber(serialNumber)
                .orElseThrow(() -> new DeviceException(NO_MEMBER));
        device.changeToDiscard();
        deviceRepository.save(device);

        return DeviceFactory.getDeviceDto(device);

    }

    //------------------------동작 정지
    @Transactional
    @Override
    public DeviceDto inactiveDevice(String serialNumber){
        //active -> inactive
        Device device = deviceRepository.findBySerialNumber(serialNumber)
                .orElseThrow(() -> new DeviceException(NO_MEMBER));
        device.changeToINActive();
        deviceRepository.save(device);

        return DeviceFactory.getDeviceDto(device);

    }

    /// 사용 안함...
    private void validDeviceLevel(String serialNumber, String qrCode, String macAddress) {
        if(serialNumber == null){
            throw new DeviceException(NO_SERIALNUMBER);}
        if(qrCode == null){
            throw new DeviceException(NO_QRCODE);}
        if(macAddress == null){
            throw new DeviceException(NO_MACADDRESS);}
//        if(activeStatus == null){
//            throw new DeviceException(ACTIVE_NULL_ERROR);}
    }
    ///?????

}
