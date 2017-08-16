package com.javaproject.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;
import org.hibernate.validator.constraints.Range;

@Entity
public class Camera {

    private Long cameraId = null;
    private int megapixels;
    private String make = null;
    private String model = null;

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="camera_id")
    public Long getCameraId() {return cameraId;}
    public void setCameraId(Long cameraId) {this.cameraId = cameraId;}

    @Column(name="megapixels")
    @NotNull
    @Range(min = 2, max = 50, message="The megapixel number should be greater than 2 and less than 50")
    public int getMegapixels() {
        return megapixels;
    }

    public void setMegapixels(int megapixels) {
        this.megapixels = megapixels;
    }

    @Column(name="camera_make")
    @NotEmpty
    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    @Column(name="camera_model")
    @NotEmpty
    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }
}
