package projectwitharduino;

import com.panamahitek.ArduinoException;
import com.panamahitek.PanamaHitek_Arduino;
import java.util.ArrayList;
import jssc.SerialPortEvent;
import jssc.SerialPortEventListener;
import jssc.SerialPortException;

/**
 *
 * @author Rangel
 */
public class Device {

    private PanamaHitek_Arduino arduino;
    private String portName;
    private int baudRate;
    private String code;
    private double Latitude, Longitude;
    private double angle;
    private double viewingAngle;
    private double minViewingAngle;
    private double maxViewingAngle;
    private boolean aiming;
    private ArrayList<Target> targets;
    private double maxDist;
    private GeoTool geoTool;

    public Device(String portName, int baudRate, double Latitude, double Longitude, String code, double maxDist) throws ArduinoException {
        this.portName = portName;
        this.baudRate = baudRate;
        this.Latitude = Latitude;
        this.Longitude = Longitude;
        this.code = code;
        this.maxDist = maxDist;
        this.targets = new ArrayList();
        this.geoTool = new GeoTool();
        this.setViewingAngle(90.0);
        arduino = new PanamaHitek_Arduino();
        //arduino.arduinoTX(portName, baudRate);
    }

    public double getLatitude() {
        return Latitude;
    }

    public void setLatitude(double Latitude) {
        this.Latitude = Latitude;
    }

    public double getLongitude() {
        return Longitude;
    }

    public void setLongitude(double Longitude) {
        this.Longitude = Longitude;
    }

    public double getAngle() {
        return angle;
    }

    public void setAngle(double angle) {
        this.angle = angle;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public double getViewingAngle() {
        return viewingAngle;
    }

    public void setViewingAngle(double viewingAngle) {
        this.viewingAngle = viewingAngle;
    }

    public boolean isAiming() {
        return aiming;
    }

    public void setAiming(boolean aiming) {
        this.aiming = aiming;
    }

    public ArrayList<Target> getTargets() {
        return targets;
    }

    public void setTargets(ArrayList<Target> targets) {
        this.targets = targets;
    }

    public double getMaxDist() {
        return maxDist;
    }

    public void setMaxDist(double maxDist) {
        this.maxDist = maxDist;
    }

    public double getMinViewingAngle() {
        return minViewingAngle;
    }

    public void setMinViewingAngle(double minViewingAngle) {
        this.minViewingAngle = minViewingAngle;
    }

    public double getMaxViewingAngle() {
        return maxViewingAngle;
    }

    public void setMaxViewingAngle(double maxViewingAngle) {
        this.maxViewingAngle = maxViewingAngle;
    }
    /**
     * atualizar o nome da função comecei pra um proposito e mudei pra outro totalmente diferente
     * @param targ 
     */
    public void AddTarget(Target targ) {
        if (this.targets.size() == 0) {
            this.targets.add(targ);
            targ.setPriority(true);
            this.setAngle(geoTool.angleCalculator(this.Latitude, this.Longitude, targ.getLatitude(), targ.getLongitude()));
            this.setMaxViewingAngle((this.getAngle() + this.getViewingAngle() / 2) % 360.0);
            double min = (this.getAngle() - this.getViewingAngle() / 2) % 360.0;
            if (min < 0) {
                min += 360;
            }
            this.setMinViewingAngle(min);
            System.out.println(this.getMinViewingAngle()+" ate "+ this.getMaxViewingAngle());
            System.out.println(this.getAngle());
        } else {
            double theta2 = this.geoTool.angleCalculator(this.Latitude, this.Longitude, targ.getLatitude(), targ.getLongitude());
            double theta1 = this.getAngle();
            System.out.println(theta2+" esse");
            if (theta2 <= this.getMaxViewingAngle() && theta2 >= this.getMinViewingAngle()) {
                // Cálculo das coordenadas x e y do ponto médio
                double x = (Math.cos(theta1) + Math.cos(theta2)) / 2;
                double y = (Math.sin(theta1) + Math.sin(theta2)) / 2;
                // Cálculo do ângulo do ponto médio
                double thetaMedio = Math.atan2(y, x);
                // Conversão do ângulo para graus
                double grausMedio = Math.toDegrees(thetaMedio);
                if (grausMedio < 0) {
                    grausMedio += 360;
                }
                System.out.println(grausMedio);
            }else{
                System.out.println("fora de vista");
            }
        }
    }

    public void LookAt(double angle) throws SerialPortException, ArduinoException {
        if (angle != this.getAngle()) {
            this.sendData(String.valueOf(angle));
            this.setAngle(angle);
        }
    }

    public void sendData(String data) throws SerialPortException, ArduinoException {
        arduino.sendData(data);
    }

    public byte[] receiveData() throws SerialPortException, ArduinoException {
        return arduino.receiveData();
    }

    public void disconnect() throws SerialPortException, ArduinoException {
        arduino.killArduinoConnection();
    }
}
