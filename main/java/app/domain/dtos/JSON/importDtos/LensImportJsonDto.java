package app.domain.dtos.JSON.importDtos;

import com.google.gson.annotations.Expose;

import java.io.Serializable;

/**
 * Created by Ico on 11.12.2016 г..
 */
public class LensImportJsonDto implements Serializable{
    @Expose
    private String make;

    @Expose
    private int focalLength;

    @Expose
    private double maxAperture;

    @Expose
    private String CompatibleWith;

    public LensImportJsonDto() {
    }

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public int getFocalLength() {
        return focalLength;
    }

    public void setFocalLength(int focalLength) {
        this.focalLength = focalLength;
    }

    public double getMaxAperture() {
        return maxAperture;
    }

    public void setMaxAperture(double maxAperture) {
        this.maxAperture = maxAperture;
    }

    public String getCompatibleWith() {
        return CompatibleWith;
    }

    public void setCompatibleWith(String compatibleWith) {
        CompatibleWith = compatibleWith;
    }
}
