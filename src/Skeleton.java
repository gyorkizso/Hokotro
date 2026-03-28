import java.util.List;
import java.util.Scanner;

public class Skeleton {
    Scanner scanner;

    Skeleton(){
        scanner = new Scanner(System.in);
    }

    String getStringFromUser(String paramName, String extra){
        System.out.printf("Kérem adja meg a(z) %s értékét %s:", paramName, extra);
        String string = scanner.nextLine();
        return string.strip();
    }

    String getStringFromUser(String paramName){
        return getStringFromUser(paramName, "");
    }

    int getIntFromUser(String paramName) {
        String raw = getStringFromUser(paramName);
        return Integer.parseInt(raw);
    }

    double getDoubleFromUser(String paramName) {
        String raw = getStringFromUser(paramName);
        return Double.parseDouble(raw);
    }

    boolean getBooleanFromUser(String paramName) {
        String raw = getStringFromUser(paramName, "(0=hamis, 1=igaz):");
        switch (raw){
            case "0":
                return false;
            case "1":
                return true;
            default:
                throw new NumberFormatException();
        }
    }

    int getListSelectionFromUser(List<String> options){
        for (int i = 0; i < options.size(); i++){
            System.out.printf("%i. %s%n", i, options.get(i));
        }
        System.out.print("Kérem válasszon a fentiek közül:");
        return Integer.parseInt(scanner.nextLine());
    }

    public void testMatchStart(){

    }

    public void testSnowfall() {

    }

    public void tesIcing() {

    }

    public void testVehicleMoving() {

    }

    public void testUsingBroomHead() {

    }

    public void testUsingThrowerHead() {

    }

    public void testUsingIceBreakerHead() {

    }

    public void testUsingSaltSpreaderHead() {

    }

    public void testUsingDragonHead() {

    }

    public void testCollisionHandling() {

    }

    public void testObstacleRemoval(){

    }

    public void testPlowHeadChange() {

    }

    public void testCarRouteSelection() {

    }

    public void testLaneChangeOnObstacle() {

    }

    public void testCompleteBusRoute(){

    }


    public void testVisitBusStop() {

    }

    public void testCleanWithSnowPlow() {

    }

    public void testVehicleStuckInLane() {

    }

    public void testPurchaseSnowPlowInGarage() {

    }

    public void testFreeVehicle() {

    }

    public void testCleanerPoints() {

    }
}
