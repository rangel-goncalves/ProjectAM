package projectwitharduino;

import java.util.Scanner;
import com.panamahitek.ArduinoException;
import com.panamahitek.PanamaHitek_Arduino;
import java.text.DecimalFormat;
import java.util.ArrayList;
import jssc.SerialPortEvent;
import jssc.SerialPortEventListener;
import jssc.SerialPortException;

/**
 *
 * @author Rangel
 * inicio do git
 */
public class ProjectWithArduino {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws SerialPortException, ArduinoException {
        
        Scanner scanner = new Scanner(System.in);
        Manager m  = new Manager(200.00);
        m.AddDevice("COM1", 0, -9.532860575530622, -35.791900658297514, "1",30);
        m.AddDevice("COM2", 0, -9.53309500221512, -35.79228221325831, "2",30);
        ArrayList<Target> t = new ArrayList();
        int i = 0;
        Target t1 = new Target(-9.532936603287444, -35.7925582442185);
        Target t2 = new  Target(-9.532878883895584, -35.79245432257558);
        t.add(t1);
        t.add(t2);
        while(true){
            double latitude = scanner.nextDouble();
            //double longitude = scanner.nextDouble();
            if(latitude == -1.00){
                break;
            }
            m.Search(t.get(i));
            i++;
        }
    }

}