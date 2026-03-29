/**
 * A Car számítógép által vezérelt normál jármű.
 *
 * Felelőssége, hogy a legrövidebb járható úton haladjon a célja felé.
 * Jégpáncélon csúszásra hajlamos, ütközés esetén pedig akadályt képezhet.
 *
 * Asszociáció:
 * - destination: az a célállomás, ami felé halad
 */
public class Car extends Vehicle {
    /**
     * Létrehoz egy új autót.
     *
     * @param currentLane az aktuális sáv
     * @param owner a jármű tulajdonosa vagy irányítója
     * @param destination a célállomás
     * @param speed a jármű sebessége
     */
    public Car(Lane currentLane, Player owner, Object destination, int speed) {
        super(currentLane, owner, destination, speed);
        Skeleton.instance.createObject(this, "currentLane",currentLane,"owner",owner,"destination",destination,"speed",speed);
    }

    /**
     * Végrehajtja az autó körét.
     *
     * A skeleton szintjén ez a metódus csak helykitöltő: nem számol valódi
     * útvonalat, hanem a hívható felület része.
     */
    public void executeTurn() {
        Skeleton.instance.methodCall(this,"executeTurn");
        // Skeleton implementáció: nincs valódi útvonaltervezés.
        Skeleton.instance.methodReturn(this,"executeTurn");
    }

    /**
     * Kezeli az ütközés eseményét.
     *
     * A skeleton szintjén ez a metódus csak helykitöltő, valódi akadályképzés
     * nélkül.
     */
    public void onCollision() {
        Skeleton.instance.methodCall(this,"onCollision");
        // Skeleton implementáció: nincs valódi ütközéskezelés.
        Skeleton.instance.methodReturn(this,"onCollision");
    }
}