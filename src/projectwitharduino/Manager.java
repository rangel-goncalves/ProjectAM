package projectwitharduino;

import com.panamahitek.ArduinoException;
import com.panamahitek.PanamaHitek_Arduino;
import jssc.SerialPortEvent;
import jssc.SerialPortEventListener;
import jssc.SerialPortException;
import java.util.ArrayList;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 *
 * @author Rangel
 */
public class Manager {

    private ArrayList<Device> devices;
    private String log;
    private LocalDateTime date = LocalDateTime.now();
    private GeoTool geoTool;
    private double maxDistance;

    public Manager(double maxDistance) {
        this.maxDistance = maxDistance;
        this.devices = new ArrayList();
        this.geoTool = new GeoTool();
        date.format(DateTimeFormatter.ISO_LOCAL_DATE);
        this.log = "Gerenciador criado em: " + date.toString() +"\n";
        System.out.println(log);
    }

    public Manager() {
        this.devices = new ArrayList();
        this.geoTool = new GeoTool();
        date.format(DateTimeFormatter.ISO_LOCAL_DATE);
        this.log = "Gerenciador criado em: " + date.toString() +"\n";
        System.out.println(log);
    }

    public void AddDevice(String port, int baudRate, double Latitude, double Longitude, String code, double maxDist) throws ArduinoException {
        Device d = new Device(port, baudRate, Latitude, Longitude, code, maxDist);
        devices.add(d);
    }

    public void Search(double Latitude, double Longitude) throws SerialPortException, ArduinoException {
        for (Device device : devices) {
            double ang = this.geoTool.SearchCaller(device.getLatitude(), device.getLongitude(), Latitude, Longitude, this.maxDistance);
            System.out.println(ang);
            if(ang!=-1){
                //device.LookAt(ang);
                System.out.println(device.getCode() + "se moveu para " + ang + "---"+ LocalDateTime.now()+ "\n");
                this.log += device.getCode() + "se moveu para " + ang + "---"+ LocalDateTime.now()+ "\n";
                
            }
        }
    }

    public void Search(Target targ) throws SerialPortException, ArduinoException {
        for (Device device : devices) {
            double look = this.geoTool.SearchCaller(device.getLatitude(), device.getLongitude(), targ.getLatitude(), targ.getLongitude(), device.getMaxDist());
            System.out.println(look);
            if(look!=-1){
                device.AddTarget(targ);
            }
        }
    }

}
