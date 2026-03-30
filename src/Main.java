import javax.swing.*;
import java.util.*;

public class Main {
    public static void main(String[] args){
        Skeleton skeleton = new Skeleton();

        //A főmenü opcióit tárolja párban a metódussal, ami meghívódik kiválasztásakor.
        List<AbstractMap.SimpleEntry<String, Runnable>> menu = new ArrayList<>();
        menu.add(new AbstractMap.SimpleEntry<>("Kilépés", ()->{}));
        menu.add(new AbstractMap.SimpleEntry<>("Mérkőzés indítása", skeleton::testMatchStart));
        menu.add(new AbstractMap.SimpleEntry<>("Havazás kezelése", skeleton::testSnowfall));
        menu.add(new AbstractMap.SimpleEntry<>("Jég kialakulása", skeleton::testIcing));
        menu.add(new AbstractMap.SimpleEntry<>("Jármű mozgatása", skeleton::testVehicleMoving));
        menu.add(new AbstractMap.SimpleEntry<>("Söprő fej használata", skeleton::testUsingBroomHead));
        menu.add(new AbstractMap.SimpleEntry<>("Hányó fej használata", skeleton::testUsingThrowerHead));
        menu.add(new AbstractMap.SimpleEntry<>("Jégtörő fej használata", skeleton::testUsingIceBreakerHead));
        menu.add(new AbstractMap.SimpleEntry<>("Sószóró fej használata", skeleton::testUsingSaltSpreaderHead));
        menu.add(new AbstractMap.SimpleEntry<>("Sárkány fej használata", skeleton::testUsingDragonHead));
        menu.add(new AbstractMap.SimpleEntry<>("Ütközés kezelése", skeleton::testCollisionHandling));
        menu.add(new AbstractMap.SimpleEntry<>("Akadály eltávolítása", skeleton::testObstacleRemoval));
        menu.add(new AbstractMap.SimpleEntry<>("Hókotrófej cseréje", skeleton::testPlowHeadChange));
        menu.add(new AbstractMap.SimpleEntry<>("Autók útvonalválasztása", skeleton::testCarRouteSelection));
        menu.add(new AbstractMap.SimpleEntry<>("Sávváltás akadály esetén", skeleton::testLaneChangeOnObstacle));
        menu.add(new AbstractMap.SimpleEntry<>("Buszjárat teljesítése", skeleton::testCompleteBusRoute));
        menu.add(new AbstractMap.SimpleEntry<>("Buszmegállók érintése", skeleton::testVisitBusStop));
        menu.add(new AbstractMap.SimpleEntry<>("Hókotróval történő takarítás", skeleton::testCleanWithSnowPlow));
        menu.add(new AbstractMap.SimpleEntry<>("Jármű elakadása", skeleton::testVehicleStuckInLane));
        menu.add(new AbstractMap.SimpleEntry<>("Garázsban történő vásárlás", skeleton::testPurchaseSnowPlowInGarage));
        menu.add(new AbstractMap.SimpleEntry<>("Akadályozott jármű kiszabadítása", skeleton::testFreeVehicle));
        menu.add(new AbstractMap.SimpleEntry<>("Takarító játékos pontozása", skeleton::testCleanerPoints));

        //Addig újra megjelenik a főmenü, ameddig a felhasználó nem lép ki expliciten
        int input = -1;
        while (input != 0){
            input = skeleton.getListSelectionFromUser(menu.stream().map(AbstractMap.SimpleEntry::getKey).toList());
            menu.get(input).getValue().run();
        }
    }
}
