import java.util.ArrayList;
import java.util.List;

public class Vliegtuig {

    private String registratienummer;
    private String model;
    private int capaciteit; // Maximaal aantal passagiers
    private boolean benzineVol; // Benzinestatus: vol (true) of leeg (false)

    private List<Vlucht> vluchten; // Lijst van vluchten
    public static List<Vliegtuig> vliegtuigen = new ArrayList<>(); // Lijst van vliegtuigen

    public Vliegtuig(String registratienummer, String model, int capaciteit, boolean benzineVol) {
        this.registratienummer = registratienummer;
        this.model = model;
        this.capaciteit = capaciteit;
        this.benzineVol = benzineVol;
        this.vluchten = new ArrayList<>();
    }

    public String getRegistratienummer() {
        return registratienummer;
    }

    public String getModel() {
        return model;
    }

    public int getCapaciteit() {
        return capaciteit;
    }

    public boolean isBenzineVol() {
        return benzineVol;
    }


    public void vulBenzine() {
        benzineVol = true;
        System.out.println("Het vliegtuig is getankt en de benzine is nu vol.");
    }

    public void gebruikBenzine() {
        this.benzineVol = false;
    }

    public List<Vlucht> getVluchten() {
        return vluchten;
    }

    public void voegVluchtToe(Vlucht vlucht) {
        if (!benzineVol) {
            throw new IllegalStateException("Het vliegtuig heeft geen benzine en kan niet vliegen!");
        }
        vluchten.add(vlucht);
        gebruikBenzine(); // Benzine wordt verbruikt na een vlucht
    }

    @Override
    public String toString() {
        return "Vliegtuig{" +
                "registratienummer='" + registratienummer + '\'' +
                ", model='" + model + '\'' +
                ", capaciteit=" + capaciteit +
                ", benzineVol=" + (benzineVol ? "vol" : "leeg") +
                ", vluchten=" + vluchten +
                '}';
    }
}

