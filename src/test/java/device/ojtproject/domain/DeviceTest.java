package device.ojtproject.domain;

import device.ojtproject.DeviceObjectMother;
import device.ojtproject.entity.Device;
import org.junit.Assert;
import org.junit.Test;

public class DeviceTest {

    @Test
    public void shouldChangeToActiveStatusWhenGivenDeviceInactiveState() {
        Device device = DeviceObjectMother.createInactiveDevice();

        Assert.assertTrue(device.isInactive());

        device.changeToActive();

        Assert.assertTrue(device.isActive());
    }

    @Test(expected = RuntimeException.class)
    public void shouldNotChangeToActiveStatusWhenGivenDeviceInactiveState() {
        Device device = DeviceObjectMother.createActiveDevice();

        Assert.assertTrue(device.isActive());

        device.changeToActive();
//
//        Assert.assertTrue(device.isActive());
    }
}
