package device.ojtproject.repository;

import device.ojtproject.DeviceObjectMother;
import device.ojtproject.entity.Device;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;


@RunWith(SpringJUnit4ClassRunner.class)
@DataJpaTest
public class DeviceRepositoryTest {

    @Autowired
    private DeviceRepository deviceRepository;

    @Test
    public void createDevice(){

        Device saveDevice = deviceRepository.save(DeviceObjectMother.createDevice());

        assertEquals("1111", saveDevice.getSerialNumber());
        assertEquals("lukeQR", saveDevice.getQrCode());
        assertEquals("lukeMAC", saveDevice.getMacAddress());
    }

    @Test
    public void findSerialNumberContaining(){
        Device saveDevice = deviceRepository.save(DeviceObjectMother.createDevice());

        List<Device> findOne = deviceRepository.findBySerialNumberContaining("11");

        Assert.assertEquals("1111",findOne.get(0).getSerialNumber());
    }

    @Test
    public void findQrCodeContaining(){
        Device saveDevice = deviceRepository.save(DeviceObjectMother.createDevice());

        List<Device> findOne = deviceRepository.findByQrCodeContaining("ke");

        Assert.assertEquals("lukeQR",findOne.get(0).getQrCode());
    }

    @Test
    public void findMacAddressContaining(){
        Device saveDevice = deviceRepository.save(DeviceObjectMother.createDevice());

        List<Device> findOne = deviceRepository.findByMacAddressContaining("MA");

        Assert.assertEquals("lukeMAC",findOne.get(0).getMacAddress());
    }

}