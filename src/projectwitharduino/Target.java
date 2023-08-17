package projectwitharduino;

/**
 *
 * @author Rangel
 */
public class Target {
    
    private double latitude;
    private double longitude;
    private boolean priority;
    private boolean locked;
    private int targetCode;
    private String targetData;

    public Target(double latitude, double longitude, int targetCode) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.targetCode = targetCode;
    }
    
    public Target(double latitude, double longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }

    
    public Target(int targetCode) {
        this.targetCode = targetCode;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public boolean isPriority() {
        return priority;
    }

    public void setPriority(boolean priority) {
        this.priority = priority;
    }

    public int getTargetCode() {
        return targetCode;
    }

    public void setTargetCode(int targetCode) {
        this.targetCode = targetCode;
    }

    public String getTargetData() {
        return targetData;
    }

    public void setTargetData(String targetData) {
        this.targetData = targetData;
    }

    public boolean isLocked() {
        return locked;
    }

    public void setLocked(boolean locked) {
        this.locked = locked;
    }
    
}
