package projectwitharduino;

import com.panamahitek.ArduinoException;
import com.panamahitek.PanamaHitek_Arduino;
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

    public Device(String portName, int baudRate, double Latitude, double Longitude, String code) throws ArduinoException {
        this.portName = portName;
        this.baudRate = baudRate;
        this.Latitude = Latitude;
        this.Longitude = Longitude;
        this.code = code;
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
    
    
    
    public void LookAt(double angle) throws SerialPortException, ArduinoException{
        if(angle != this.getAngle() ){
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
