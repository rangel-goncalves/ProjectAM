package projectwitharduino;

import java.util.ArrayList;
import java.util.Scanner;


/**
 *
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
        m.AddDevice("COM4", 9600, -9.532860575530622, -35.791900658297514, "1",30);
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