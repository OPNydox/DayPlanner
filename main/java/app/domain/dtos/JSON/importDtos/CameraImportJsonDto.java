package app.domain.dtos.JSON.importDtos;

import com.google.gson.annotations.Expose;

import java.io.Serializable;

/**
 * Created by Ico on 11.12.2016 г..
 */
public class CameraImportJsonDto implements Serializable{
    @Expose
    private String type;

    @Expose
    private String make;

    @Expose
    private String model;

    @Expose
    private String isFullName;

    @Expose
    private int minISO;

    @Expose
    private int maxISO;

    @Expose
    private int maxShutterSpeed;

    @Expose
    private String maxVideoResolution;

    @Expose
    private int maxFrameRate;

    public String getMaxVideoResolution() {
        return maxVideoResolution;
    }

    public void setMaxVideoResolution(String maxVideoResolution) {
        this.maxVideoResolution = maxVideoResolution;
    }

    public int getMaxFrameRate() {
        return maxFrameRate;
    }

    public void setMaxFrameRate(int maxFrameRate) {
        this.maxFrameRate = maxFrameRate;
    }

    public CameraImportJsonDto() {
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getIsFullName() {
        return isFullName;
    }

    public void setIsFullName(String isFullName) {
        this.isFullName = isFullName;
    }

    public int getMinISO() {
        return minISO;
    }

    public void setMinISO(int minISO) {
        this.minISO = minISO;
    }

    public int getMaxISO() {
        return maxISO;
    }

    public void setMaxISO(int maxISO) {
        this.maxISO = maxISO;
    }

    public int getMaxShutterSpeed() {
        return maxShutterSpeed;
    }

    public void setMaxShutterSpeed(int maxShutterSpeed) {
        this.maxShutterSpeed = maxShutterSpeed;
    }
}
