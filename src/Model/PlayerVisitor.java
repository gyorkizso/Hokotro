package Model;

public interface PlayerVisitor {
    void visit(BusDriverPlayer player);

    void visit(CleanerPlayer player);
}
