package device.ojtproject.controller;

import com.google.gson.Gson;
import device.ojtproject.DeviceObjectMother;
import device.ojtproject.service.DeviceServiceImpl;
import device.ojtproject.service.dto.DeviceDto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.nio.charset.StandardCharsets;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@RunWith(SpringJUnit4ClassRunner.class)
@WebMvcTest(DeviceController.class)
public class DeviceControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private DeviceServiceImpl deviceService;

    protected MediaType contentType =
            new MediaType(MediaType.APPLICATION_JSON.getType(),
                    MediaType.APPLICATION_JSON.getSubtype(),
                    StandardCharsets.UTF_8);

    //------------------조회
    @Test
    public void findAllDevices() throws Exception{
        when(deviceService.searchDevice(null, null, null))
                .thenReturn(DeviceObjectMother.deviceSearchDtos());

        mockMvc.perform(get("/devices").contentType(contentType))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$[0].serialNumber").value("1111"))
                .andExpect(jsonPath("$[1].serialNumber").value("2222"));
    }

    //-------------------생성
    @Test
    public void createDevice() throws Exception{
        when(deviceService.createDevice(any()))
                .thenReturn(DeviceObjectMother.createDeviceDto());

        DeviceDto devicedto = DeviceObjectMother.createDeviceDto();
        Gson gson = new Gson();
        String content = gson.toJson(devicedto);

        mockMvc.perform(
                        post("/devices")
                                .contentType(contentType)
                                .accept(contentType)
                                .content(content))

                .andDo(print())
                .andExpect(status().isCreated());

    }
    //-------------------수정
    @Test
    public void editDevice() throws Exception{
        when(deviceService.createDevice(any()))
                .thenReturn(DeviceObjectMother.createDeviceDto());
        DeviceDto devicedto = DeviceObjectMother.createDeviceDto();
        Gson gson = new Gson();
        String content = gson.toJson(devicedto);

        mockMvc.perform(
                        put("/devices/1111")
                                .contentType(contentType)
                                .accept(contentType)
                                .content(content))
                .andDo(print())
                .andExpect(status().isOk());

    }

    //-------------------삭제
    @Test
    public void dicardDevice() throws Exception{
        when(deviceService.createDevice(any()))
                .thenReturn(DeviceObjectMother.DiscardDeviceDto());
        DeviceDto devicedto = DeviceObjectMother.DiscardDeviceDto();
        Gson gson = new Gson();
        String content = gson.toJson(devicedto);

        mockMvc.perform(
                        delete("/devices/Discard/1111")
                                .contentType(contentType)
                                .accept(contentType)
                                .content(content))
                .andDo(print())
                .andExpect(status().isOk());


    }

}