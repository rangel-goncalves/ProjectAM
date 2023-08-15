package projectwitharduino;

import java.util.ArrayList;
import java.util.Scanner;


/**
 * @author Rangel
 * inicio do git
 */
public class ProjectWithArduino {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args){
        
        Scanner scanner = new Scanner(System.in);
        Manager m  = new Manager(200.00);
        m.AddDevice("COM3", 9600, -9.5531069903898, -35.77668498123376, "1",300, 20);
        ArrayList<Target> t = new ArrayList();
        int i = 0;
        Target t1 = new Target(-9.553094942290073, -35.776511462156925);
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
