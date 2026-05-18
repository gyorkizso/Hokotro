package Model;

public interface VehicleVisitor {
    void visit(Bus vehicle);
    void visit(Snowplow vehicle);
    void visit(Car vehicle);
}
