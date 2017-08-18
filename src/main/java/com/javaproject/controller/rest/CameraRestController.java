package com.javaproject.controller.rest;

import com.javaproject.model.Camera;
import com.javaproject.repository.CameraRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
public class CameraRestController {

    @Autowired
    protected CameraRepository cameraRepository;

    @CrossOrigin("*")
    @RequestMapping(value="/cameras", method = RequestMethod.GET)
    public List<Camera> getAllCameras() {return (List<Camera>)cameraRepository.findAll();}

    @CrossOrigin("*")
    @RequestMapping(value="/cameras/{cameraId}", method = RequestMethod.GET)
    public Camera getIndividualCamera(@PathVariable Long cameraId) {
        Camera camera = cameraRepository.findOne(cameraId);
        return camera;
    }

    @CrossOrigin("*")
    @RequestMapping(value="/cameras/{cameraId}", method=RequestMethod.POST)
    public void saveCamera(@Valid @RequestBody Camera camera) {
        cameraRepository.save(camera);
    }

    @CrossOrigin("*")
    @RequestMapping(value="/cameras/{cameraId}", method=RequestMethod.DELETE)
    public ResponseEntity<Void> deleteCamera(@PathVariable Long cameraId) {
        Camera camera = cameraRepository.findOne(cameraId);
        if (camera == null) {
            return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
        }
        cameraRepository.delete(cameraId);
        return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
    }
}
