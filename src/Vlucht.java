import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Vlucht extends Bestemming {

    private final String vluchtcode;
    private int aantalEconomy;
    private int aantalBusiness;
    private Vliegtuig vliegtuig;
    private Piloot piloot;
    private Crew crew;

    public static final List<Vlucht> vluchten = new ArrayList<>();

    public Vlucht(String stad, String vluchtcode, int aantalEconomy, int aantalBusiness, Vliegtuig vliegtuig) {
        super(stad);
        this.vluchtcode = vluchtcode;
        this.aantalEconomy = aantalEconomy;
        this.aantalBusiness = aantalBusiness;
        this.vliegtuig = vliegtuig;
    }

    public String getVluchtcode() {
        return vluchtcode;
    }

    public int getAantalEconomy() {
        return aantalEconomy;
    }

    public int getAantalBusiness() {
        return aantalBusiness;
    }

    public Vliegtuig getVliegtuig() {
        return vliegtuig;
    }

    public void setVliegtuig(Vliegtuig vliegtuig) {
        this.vliegtuig = vliegtuig;
    }

    public Piloot getPiloot() {
        return piloot;
    }

    public void setPiloot(Piloot piloot) {
        this.piloot = piloot;
    }

    public Crew getCrew() {
        return crew;
    }

    public void setCrew(Crew crew) {
        this.crew = crew;
    }

    public static List<Vlucht> getVluchten() {
        return vluchten;
    }

    @Override
    public String toString() {
        return "Vlucht{" +
                "vluchtcode='" + vluchtcode + '\'' +
                ", stad='" + stad + '\'' +
                ", aantalEconomy=" + aantalEconomy +
                ", aantalBusiness=" + aantalBusiness +
                ", vliegtuig=" + (vliegtuig != null ? vliegtuig.getRegistratienummer() : "Geen vliegtuig toegewezen") +
                '}';
    }

    public static void maakNieuweVlucht(Scanner scanner) {
        String vluchtcode = vraagInput(scanner, "Voer vluchtcode in:");
        String bestemming = vraagInput(scanner, "Voer bestemming in:");
        int aantalEconomy = vraagIntInput(scanner, "Voer aantal economy seats in:");
        int aantalBusiness = vraagIntInput(scanner, "Voer aantal business seats in:");

        Vlucht vlucht = new Vlucht(bestemming, vluchtcode, aantalEconomy, aantalBusiness, null);
        vluchten.add(vlucht);
        System.out.println("Vlucht aangemaakt: " + vlucht);
    }

    public static void zoekVlucht(Scanner scanner) {
        String vluchtcode = vraagInput(scanner, "Voer vluchtcode in:");

        for (Vlucht vlucht : vluchten) {
            if (vlucht.getVluchtcode().equalsIgnoreCase(vluchtcode)) {
                System.out.println(vlucht);
                return;
            }
        }
        System.out.println("Vlucht niet gevonden.");
    }

    public void boardPassenger(Passagier passagier, String klasse) throws Exception {
        boolean hasValidTicket = Ticket.tickets.stream()
                .anyMatch(ticket -> ticket.getPassagier().equals(passagier) &&
                        ticket.getVlucht().equals(this) &&
                        ticket.getKlasse().equalsIgnoreCase(klasse));

        if (!hasValidTicket) {
            throw new Exception("Passagier heeft geen geldig ticket voor deze vlucht.");
        }

        int totalSeats = aantalEconomy + aantalBusiness;
        if (Passagier.passagiers.size() >= totalSeats) {
            throw new Exception("Geen beschikbare plaatsen voor passagier: " + passagier.getNaam());
        }

        if ("economy".equalsIgnoreCase(klasse) && aantalEconomy <= 0) {
            throw new Exception("Geen beschikbare economy plaatsen voor passagier: " + passagier.getNaam());
        }

        if ("business".equalsIgnoreCase(klasse) && aantalBusiness <= 0) {
            throw new Exception("Geen beschikbare business plaatsen voor passagier: " + passagier.getNaam());
        }

        Passagier.passagiers.add(passagier);

        if ("economy".equalsIgnoreCase(klasse)) {
            aantalEconomy--;
        } else if ("business".equalsIgnoreCase(klasse)) {
            aantalBusiness--;
        }

        System.out.println("Passagier " + passagier.getNaam() + " is geboard op vlucht " + vluchtcode);
    }

    private static String vraagInput(Scanner scanner, String prompt) {
        String input;
        do {
            System.out.println(prompt);
            input = scanner.nextLine();
            if (input.isEmpty()) {
                System.out.println("Input mag niet leeg zijn. Probeer opnieuw.");
            }
        } while (input.isEmpty());
        return input;
    }

    private static int vraagIntInput(Scanner scanner, String prompt) {
        int result;
        while (true) {
            System.out.println(prompt);
            String input = scanner.nextLine();
            try {
                result = Integer.parseInt(input);
                break;
            } catch (NumberFormatException e) {
                System.out.println("Ongeldige invoer. Probeer opnieuw.");
            }
        }
        return result;
    }
}
