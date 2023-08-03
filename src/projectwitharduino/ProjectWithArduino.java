package projectwitharduino;

import java.util.Scanner;
import com.panamahitek.ArduinoException;
import com.panamahitek.PanamaHitek_Arduino;
import java.text.DecimalFormat;
import java.util.ArrayList;
import jssc.SerialPortEvent;
import jssc.SerialPortEventListener;
import jssc.SerialPortException;
import com.fazecast.jSerialComm.SerialPort;


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
        m.AddDevice("COM3", 9600, -9.55300246234958, -35.777077364782144, "3",300);
        ArrayList<Target> t = new ArrayList();
        int i = 0;
        Target t1 = new Target(-9.552972136114636, -35.77740949388239);
        Target t2 = new  Target(-9.553135897751222, -35.77707121424325);
        t.add(t1);
        t.add(t2);
        while(true){
            double latitude = scanner.nextDouble();
            //double longitude = scanner.nextDouble();
            if(latitude == -1.00){
                break;
            }
            if(i==t.size()){
                break;
            }
            m.Search(t.get(i));
            i++;
        }
    }

}