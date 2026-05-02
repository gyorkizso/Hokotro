import java.io.OutputStream;
import java.io.PrintStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
/**
 * Singleton osztály a Skeleton teszteseteinek, valamint ki és bemenetének kezelésére
 */
public class Skeleton {
    /** A singleton példány */
    public static Skeleton instance;
    /** A tesztek során létrehozott objektumok neveit tartja számon */
    public Map<Object, String> names = new HashMap<>();

    public PrintStream outStream = System.out;

    Scanner scanner;

    public Skeleton(){
        scanner = new Scanner(System.in);
        instance = this;
    }

    /** Bekér a felhasználótól egy Stringet.
     *
     * Az egész sort beolvassa szóközökkel együtt.
     * @param paramName a paraméter neve amihez kérjük az értéket.
     * @param extra extra szöveg amit a normál üzenet után ír.
     * @return a felhasználó által megadott szöveg
     */
    String getStringFromUser(String paramName, String extra){
        outStream.printf("Kérem adja meg a(z) %s értékét %s:", paramName, extra);
        String string = scanner.nextLine();
        return string.strip();
    }

    /** Bekér a felhasználótól egy szöveget
     *
     * Az egész sort beolvassa szóközökkel együtt.
     * @param paramName a paraméter neve amihez kérjük az értéket.
     * @return a felhasználó által megadott szöveg
     */
    String getStringFromUser(String paramName){
        return getStringFromUser(paramName, "");
    }

    /** Bekér a felhasználótól egy egész számot
     * @param paramName a paraméter neve amihez kérjük az értéket.
     * @return a felhasználó által megadott szám
     */
    int getIntFromUser(String paramName) {
        String raw = getStringFromUser(paramName);
        return Integer.parseInt(raw);
    }

    /** Bekér a felhasználótól egy valós számot
     * @param paramName a paraméter neve amihez kérjük az értéket.
     * @return a felhasználó által megadott szám
     */
    double getDoubleFromUser(String paramName) {
        String raw = getStringFromUser(paramName);
        return Double.parseDouble(raw);
    }

    /** Bekér a felhasználótól egy igaz hamis értéke

     * 0 jelenti az igazat és 1 a hamisat. Egyéb érték hibát dob.
     * @param paramName a paraméter neve amihez kérjük az értéket.
     * @return a felhasználó által megadott érték
     */
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

    /** Megkéri a felhasználót, hogy válasszon a megadott opciók közül

     * 0 jelenti az igazat és 1 a hamisat. Egyéb érték hibát dob.
     * @param options az opciók amik fel lesznek sorolva.
     * @return a felhasználó által választott opció sorszáma
     */
    int getListSelectionFromUser(List<String> options){
        for (int i = 0; i < options.size(); i++){
            outStream.printf("%d. %s%n", i, options.get(i));
        }
        outStream.print("Kérem válasszon a fentiek közül:");
        return Integer.parseInt(scanner.nextLine());
    }

    /** Kiírja standard kimenetre egy metódus meghívását
     *
     * @param o az objektum amihez tartozik a metódus
     * @param methodName a metódus neve
     * @param args a metódus argumentumainak nevei és értékei. A neveket és értékeket egymás után kell megadni felváltva, kezdve az első argumentum nevével.
     */
    public void methodCall(Object o, String methodName, Object... args){
        outStream.printf("%s.%s(", names.get(o), methodName);
        for (int j = 0; j < args.length; j+=2) {
            Object paramName = args[j];
            Object value = args[Math.min(j+1, args.length-1)];
            outStream.printf("%s=%s, ", paramName, names.containsKey(value) ? names.get(value) : value);
        }
        outStream.print(") meghívva\n");
    }

    /** Kiírja standard kimenetre egy objektum létrehozását
     *
     * @param o az objektum ami létrejött
     * @param args a konstruktor argumentumainak nevei és értékei. A neveket és értékeket egymás után kell megadni felváltva, kezdve az első argumentum nevével.
     */
    public void createObject(Object o, Object... args) {
        String type = o.getClass().getTypeName();
        int i = 1;
        while (names.containsValue(type+i)){
            i++;
        }
        String name = type+i;
        names.put(o, name);

        outStream.printf("%s típusú objektum létrehozva %s névvel", type, name);
        if (args.length != 0){
            outStream.print(", ezekkel a konstruktor paraméterekkel: ");
            for (int j = 0; j < args.length; j+=2) {
                Object paramName = args[j];
                Object value = args[Math.min(j+1, args.length-1)];
                outStream.printf("%s=%s, ", paramName, names.containsKey(value) ? names.get(value) : value);
            }
        }
        outStream.print("\n");
    }

    /** Kiírja standard kimenetre egy metódus visszatérését
     *
     * @param o az objektum amihez tartozik a metódus
     * @param methodName a metódus neve
     * @param returnValue a metódus visszatérési értéke
     */
    public void methodReturn(Object o, String methodName, Object returnValue) {
        outStream.printf("%s.%s visszatért", names.get(o), methodName);
        if (returnValue != null){
            outStream.printf(" %s értékkel", names.containsKey(returnValue) ? names.get(returnValue) : returnValue);
        }
        outStream.print("\n");
    }

    /** Kiírja standard kimenetre egy metódus visszatérését
     *
     * @param o az objektum amihez tartozik a metódus
     * @param methodName a metódus neve
     */
    public void methodReturn(Object o, String methodName) {
        methodReturn(o, methodName, null);
    }
}
