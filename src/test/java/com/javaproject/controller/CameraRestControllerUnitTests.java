package com.javaproject.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.javaproject.model.Camera;
import com.javaproject.repository.CameraRepository;
import com.javaproject.controller.rest.CameraRestController;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.MvcResult;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@WebMvcTest(value = CameraRestController.class)


public class CameraRestControllerUnitTests {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CameraRepository cameraRepository;

    @Test
    public void showIndividualCamera() throws Exception {
        Camera camera = new Camera();
        camera.setCameraId(1L);
        camera.setMake("Canon");
        camera.setModel("ABC");
        camera.setMegapixels(12);

        Mockito.when(cameraRepository.findOne(Mockito.anyLong())).thenReturn(camera);
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/cameras/1").accept(MediaType.APPLICATION_JSON);
        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        String expected = "{cameraId: 1, make:Canon, model:ABC, megapixels:12}";
        System.out.println(result.getResponse().getContentAsString());
        JSONAssert.assertEquals(expected, result.getResponse().getContentAsString(), false);
    }

    @Test
    public void showAllCameras() throws Exception {
        Camera camera1 = new Camera();
        camera1.setCameraId(1L);
        camera1.setMake("Canon");
        camera1.setModel("ABC");
        camera1.setMegapixels(12);

        Camera camera2 = new Camera();
        camera2.setCameraId(2L);
        camera2.setMake("Canon");
        camera2.setModel("DEF");
        camera2.setMegapixels(13);

        Camera camera3 = new Camera();
        camera3.setCameraId(3L);
        camera3.setMake("Canon");
        camera3.setModel("GHI");
        camera3.setMegapixels(14);

        List<Camera> cameras = new ArrayList<Camera>();

        cameras.add(camera1);
        cameras.add(camera2);
        cameras.add(camera3);

        Mockito.when(cameraRepository.findAll()).thenReturn(cameras);
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/cameras").accept(MediaType.APPLICATION_JSON);
        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        String expected = "[{cameraId: 1, make:Canon, model:ABC, megapixels:12},{cameraId: 2, make:Canon, model:DEF, megapixels:13}, {cameraId: 3, make:Canon, model:GHI, megapixels:14}]";
        System.out.println(result.getResponse().getContentAsString());
        JSONAssert.assertEquals(expected, result.getResponse().getContentAsString(), false);
    }

    @Test
    public void AddCamera() throws Exception {
        Camera camera = new Camera();
        camera.setMegapixels(15);
        camera.setMake("Nikon");
        camera.setModel("ABC");

        String cameraJSON = new ObjectMapper().writeValueAsString(camera);
        Mockito.when(cameraRepository.save(camera)).thenReturn(camera);
        RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/cameras/-1").contentType(MediaType.APPLICATION_JSON).content(cameraJSON);
        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        Mockito.verify(cameraRepository, Mockito.times(1)).save(Mockito.any(Camera.class));
        Assert.assertEquals(200, result.getResponse().getStatus());
    }

    @Test
    public void AddCameraNonValidMegapixelsTooLow() throws Exception {
        Camera camera = new Camera();
        camera.setMegapixels(1);
        camera.setMake("Nikon");
        camera.setModel("ABC");

        String cameraJSON = new ObjectMapper().writeValueAsString(camera);
        Mockito.when(cameraRepository.save(camera)).thenReturn(camera);
        RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/cameras/-1").contentType(MediaType.APPLICATION_JSON).content(cameraJSON);
        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        Assert.assertEquals(400, result.getResponse().getStatus());
    }

    @Test
    public void AddCameraNonValidMegapixelsTooHigh() throws Exception {
        Camera camera = new Camera();
        camera.setMegapixels(51);
        camera.setMake("Nikon");
        camera.setModel("ABC");

        String cameraJSON = new ObjectMapper().writeValueAsString(camera);
        Mockito.when(cameraRepository.save(camera)).thenReturn(camera);
        RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/cameras/-1").contentType(MediaType.APPLICATION_JSON).content(cameraJSON);
        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        Assert.assertEquals(400, result.getResponse().getStatus());
    }

    @Test
    public void AddCameraNonValidNoMake() throws Exception {
        Camera camera = new Camera();
        camera.setMegapixels(51);
        camera.setModel("ABC");

        String cameraJSON = new ObjectMapper().writeValueAsString(camera);
        Mockito.when(cameraRepository.save(camera)).thenReturn(camera);
        RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/cameras/-1").contentType(MediaType.APPLICATION_JSON).content(cameraJSON);
        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        Assert.assertEquals(400, result.getResponse().getStatus());
    }

    @Test
    public void AddCameraNonValidNoModel() throws Exception {
        Camera camera = new Camera();
        camera.setMegapixels(51);
        camera.setMake("Nikon");

        String cameraJSON = new ObjectMapper().writeValueAsString(camera);
        Mockito.when(cameraRepository.save(camera)).thenReturn(camera);
        RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/cameras/-1").contentType(MediaType.APPLICATION_JSON).content(cameraJSON);
        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        Assert.assertEquals(400, result.getResponse().getStatus());
    }


}
