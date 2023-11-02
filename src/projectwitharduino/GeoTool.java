package projectwitharduino;

/**
 *
 * @author Rangel
 *  Fórmula de Haversine
 * https://forum.maparadar.com/viewtopic.php?t=5181
 */
public class GeoTool {

    public GeoTool() {
    }
    
    public double SearchCaller(double firstLatitude, double firstLongitude, double secondLatitude, double secondLongitude, double maxDist){
        if(this.latAndLgnToDistance(firstLatitude, firstLongitude, secondLatitude, secondLongitude) <= maxDist){
            //return this.angleCalculator(firstLatitude, firstLongitude, secondLatitude, secondLongitude);
            return 1;
        }
        return -1;
    }
    /**
     * ajuste o valor da var medida para fazer a alteração da unidade da distancia[1= Km, 1000 = m]
     * @param firstLatitude
     * @param firstLongitude
     * @param secondLatitude
     * @param secondLongitude
     * @return 
     */
    public Double latAndLgnToDistance(double firstLatitude, double firstLongitude, double secondLatitude, double secondLongitude) {
        double EARTH_RADIUS_KM = 6371.009;
        double firstLatToRad = Math.toRadians(firstLatitude);
        double secondLatToRad = Math.toRadians(secondLatitude);
        int medida = 1000;
        // Diferença das longitudes
        double deltaLongitudeInRad = Math.toRadians(firstLongitude - secondLongitude);
        // calculo da distancia
        Double a = Math.acos(Math.cos(firstLatToRad) * Math.cos(secondLatToRad)
                * Math.cos(deltaLongitudeInRad) + Math.sin(firstLatToRad)
                * Math.sin(secondLatToRad)) * EARTH_RADIUS_KM*medida;
        this.angleCalculator(firstLatitude, firstLongitude, secondLatitude, secondLongitude);
        return a;
    }

    /**
     * calculo do angulo, o resultado é dado levando em conta o sentido
     * anthorario de um plano cartesiano
     *
     * @param lat1
     * @param long1
     * @param latitude
     * @param longitude
     * @return
     */
    public double angleCalculator(double lat1, double long1, double latitude, double longitude) {
        double brng;
        double y = Math.sin(longitude - long1) * Math.cos(latitude);
        double x = Math.cos(lat1) * Math.sin(latitude) - Math.sin(lat1) * Math.cos(latitude) * Math.cos(longitude - long1);
        double angle = Math.atan2(y, x);
        brng = (angle * 180 / Math.PI + 360.0) % 360.0;
        return brng;
    } 
}
