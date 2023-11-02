package projectwitharduino;

import java.util.ArrayList;
import java.util.Scanner;


/**
 * @author Rangel
 * inicio do git
 * 11099.5403
 * m.AddDevice("COM6", 9600, -34.83333, -58.5166646, "1", 1, 20);

        ArrayList<Target> t = new ArrayList<>();
        Target t1 = new Target(49.0083899664, 2.53844117956,1);
 */
public class ProjectWithArduino {
        public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Manager m = new Manager(2000.00);
        m.AddDevice("COM6", 9600, -9.553197759667203, -35.77669155520027, "1", 200, 20);

        ArrayList<Target> t = new ArrayList<>();
        Target t1 = new Target(-9.553151721288552, -35.77675916913939,1);
        Target t2 = new Target(-9.553135897751222, -35.77707121424325,2);
        t.add(t1);
        t.add(t2);

        ArrayList<Thread> threads = new ArrayList<>();
        
        for (Target target : t) {
            double latitude = scanner.nextDouble();
            if(latitude == -1.00){
                break;
            }
            Thread thread = new SearchThread(m, target);
            threads.add(thread);
            thread.start();
        }

        // Wait for all threads to finish
        for (Thread thread : threads) {
            try {
                System.out.println("terminei");
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    /**
     * @param args the command line arguments
     */
    /*
    public static void main(String[] args){
        
        Scanner scanner = new Scanner(System.in);
        Manager m  = new Manager(200.00);
        m.AddDevice("COM6", 9600, -9.553197759667203, -35.77669155520027, "1",300, 20);
        ArrayList<Target> t = new ArrayList();
        int i = 0;
        Target t1 = new Target(-9.553151721288552, -35.77675916913939);
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
    }*/

}
