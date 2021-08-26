package device.ojtproject.controller;

import device.ojtproject.domain.Device;
import device.ojtproject.service.DeviceService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.sound.midi.Soundbank;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class DeviceController {

    private final DeviceService deviceService;

    @GetMapping("/deviceList")
    public String deviceList(Model model) {
        List<Device> devices = deviceService.findDevices();
        model.addAttribute("devices", devices);
        return "deviceList";

    }
}
