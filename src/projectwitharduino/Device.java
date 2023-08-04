package projectwitharduino;

import com.fazecast.jSerialComm.SerialPort;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Locale;



/**
 *
 * @author Rangel
 */
public class Device {

    private String portName;
    private int baudRate;
    private String code;
    private double Latitude, Longitude;
    private double angle;
    private double sharedAngle;
    private double viewingAngle;
    private double minViewingAngle;
    private double maxViewingAngle;
    private boolean aiming;
    private ArrayList<Target> targets;
    private double maxDist;
    private GeoTool geoTool;

    public Device(String portName, int baudRate, double Latitude, double Longitude, String code, double maxDist){
        this.portName = portName;
        this.baudRate = baudRate;
        this.Latitude = Latitude;
        this.Longitude = Longitude;
        this.code = code;
        this.maxDist = maxDist;
        this.targets = new ArrayList();
        this.geoTool = new GeoTool();
        this.setViewingAngle(90.0);
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

    public void LookAt() {
        if (true) {

        }
    }

    /**
     * atualizar o nome da função comecei pra um proposito e mudei pra outro
     * totalmente diferente
     *
     * @param targ
     */
    public void UnusedAddTarget(Target targ, int nada) {
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
            System.out.println(this.getMinViewingAngle() + " ate " + this.getMaxViewingAngle());
            System.out.println(this.getAngle());
        } else {
            double theta2 = this.geoTool.angleCalculator(this.Latitude, this.Longitude, targ.getLatitude(), targ.getLongitude());
            double theta1 = this.getAngle();
            System.out.println(theta2 + " esse");
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
                this.sharedAngle = grausMedio;
                System.out.println(grausMedio);
            } else {
                System.out.println("fora de vista");
            }
        }
    }
    
    public void AddTarget(Target targ) {
        if (this.targets.size() == 0) {
            this.PriorityTargetAnglecalculator(targ);
        } else {
            this.SharedAngleCalculartor(targ);
        }
    }
        
    public void RemoveTarget(Target targ) {
        for (int i = 0; i < this.targets.size(); i++) {
            if (this.targets.get(i).getTargetCode() == targ.getTargetCode()) {
                this.targets.remove(i);
                this.targets.get(i).setPriority(false);
                if (i == 0) {
                    //função de calcular o angulo separado, vai ficar mais organizado
                    if(!this.targets.isEmpty()){
                        this.targets.get(i+1).setPriority(true);
                        this.PriorityTargetAnglecalculator(this.targets.get(i+1));
                    }
                }
            }
        }
    }

    public void PriorityTargetAnglecalculator(Target targ) {
        this.targets.add(targ);
        targ.setPriority(true);
        this.setAngle(geoTool.angleCalculator(this.Latitude, this.Longitude, targ.getLatitude(), targ.getLongitude()));
        this.setMaxViewingAngle((this.getAngle() + this.getViewingAngle() / 2) % 360.0);
        double min = (this.getAngle() - this.getViewingAngle() / 2) % 360.0;
        if (min < 0) {
            min += 360;
        }
        this.setMinViewingAngle(min);
        System.out.println(this.getMinViewingAngle() + " ate " + this.getMaxViewingAngle());

            this.LookAt(this.getAngle());

        System.out.println(this.getAngle());
    }

    public void SharedAngleCalculartor(Target targ) {
            double theta2 = this.geoTool.angleCalculator(this.Latitude, this.Longitude, targ.getLatitude(), targ.getLongitude());
            double theta1 = this.getAngle();
            System.out.println(theta2 + " esse");
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
                this.sharedAngle = grausMedio;
                    this.LookAt(this.sharedAngle);

                System.out.println(grausMedio);
            }
    }

    /**
     * NÃo USE!
     *
     * @param angle
     * @throws SerialPortException
     * @throws ArduinoException
     */
    public void LookAt(double angle){
        String valueString = String.format(Locale.US, "%.8f", angle);
        this.sendData(valueString);
        this.setAngle(angle);
        
    }

    public void sendData(String data) {
        SerialPort port = SerialPort.getCommPort(portName);

        if (!port.openPort()) {
            System.out.println("Não foi possível abrir a porta serial.");
            return;
        }

        port.setComPortParameters(baudRate, 8, 1, 0);

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        data += "\n";
        System.out.println("data: "+data);
        port.writeBytes(data.getBytes(), data.length());

        port.closePort();
        //arduino.sendData(data);
    }

    public String receiveData(){
        SerialPort port = SerialPort.getCommPort(portName);

        if (!port.openPort()) {
            System.out.println("Não foi possível abrir a porta serial.");
            return null;
        }

        port.setComPortParameters(baudRate, 8, 1, 0);

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Lê os dados recebidos da porta serial
        byte[] buffer = new byte[1024]; // Tamanho do buffer para leitura
        int bytesRead = port.readBytes(buffer, buffer.length);

        // Converte os bytes lidos para uma String
        String receivedData = new String(buffer, 0, bytesRead);
        port.closePort();
        return receivedData;
    }

    public void disconnect(){
        SerialPort port = SerialPort.getCommPort(portName);
        port.setComPortParameters(baudRate, 8, 1, 0);
        port.closePort();
    }
}
