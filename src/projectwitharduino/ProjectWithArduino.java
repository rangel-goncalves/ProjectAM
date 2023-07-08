package projectwitharduino;

import java.util.Scanner;
import com.panamahitek.ArduinoException;
import com.panamahitek.PanamaHitek_Arduino;
import java.text.DecimalFormat;
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
        /*
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
        }*/
            double graus1 = 5.0;  // Ângulo do ponto P1 em graus
        double graus2 = 350.0;  // Ângulo do ponto P2 em graus

        // Conversão dos ângulos para radianos
        double theta1 = Math.toRadians(graus1);
        double theta2 = Math.toRadians(graus2);

        // Cálculo das coordenadas x e y do ponto médio
        double x = (Math.cos(theta1) + Math.cos(theta2)) / 2;
        double y = (Math.sin(theta1) + Math.sin(theta2)) / 2;

        // Cálculo do raio e ângulo do ponto médio
        double raioMedio = Math.sqrt(x * x + y * y);
        double thetaMedio = Math.atan2(y, x);

        // Conversão do ângulo para graus
        double grausMedio = Math.toDegrees(thetaMedio);
        if(grausMedio<0){
            grausMedio +=360;
        }
        System.out.println(grausMedio);
        // Arredondamento do resultado
        DecimalFormat df = new DecimalFormat("#.######");
        String xArredondado = df.format(raioMedio * Math.cos(thetaMedio));
        String yArredondado = df.format(raioMedio * Math.sin(thetaMedio));
        String grausMedioArredondado = df.format(grausMedio);

        // Impressão do ponto médio em seno e cosseno
        System.out.println("Ponto Médio: (" + xArredondado + ", " + yArredondado + ")");
        System.out.println("Ângulo Médio em Graus: " + grausMedioArredondado);
    }

}