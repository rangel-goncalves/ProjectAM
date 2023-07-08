package projectwitharduino;

import java.util.Scanner;
import com.panamahitek.ArduinoException;
import com.panamahitek.PanamaHitek_Arduino;
import jssc.SerialPortEvent;
import jssc.SerialPortEventListener;
import jssc.SerialPortException;

/**
 *
 * @author Rangel
 */
public class ProjectWithArduino {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws SerialPortException, ArduinoException {
        
        Scanner scanner = new Scanner(System.in);
        Manager m  = new Manager(200.00);
        m.AddDevice("COM1", 0, 0-9.532870552199384, -35.791899098423315, "1");
        m.AddDevice("COM2", 0, -9.53309500221512, -35.79228221325831, "2");
        while(true){
            double latitude = scanner.nextDouble();
            double longitude = scanner.nextDouble();
            if(latitude == -1.00){
                break;
            }
            m.Search(latitude, longitude);
        }
    }

}