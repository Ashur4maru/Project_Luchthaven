import java.util.ArrayList;
import java.util.List;

public class Bestemming {

    String stad;
    private String land;
    static List<Bestemming> bestemmingen = new ArrayList<>();

    public Bestemming(String stad) {
        this.stad = stad;
        this.land = land;
    }

    @Override
    public String toString() {
        return stad + " (" + land + ")";
    }
}
