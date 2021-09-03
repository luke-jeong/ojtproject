package device.ojtproject.controller;

import device.ojtproject.DeviceObjectMother;
import device.ojtproject.service.DeviceService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.BDDMockito.given;

@WebMvcTest(DeviceController.class)
public class DeviceControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private DeviceService deviceService;

  /*  protected MediaType contentType =
            new MediaType(MediaType.APPLICATION_JSON)*/

    @Test
    void getAllDevices() throws Exception{

        given(deviceService.getAllDevices())
                .willReturn(DeviceObjectMother.createDeviceDto());
    }
}