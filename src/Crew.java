import java.util.ArrayList;
import java.util.List;

public class Crew extends Personeel{

    static List<Crew> crews = new ArrayList<>();

    public Crew(String naam, String voornaam, int leeftijd, String adres) {
        super(naam, voornaam, leeftijd, adres);
    }


}
