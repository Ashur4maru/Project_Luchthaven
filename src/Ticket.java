import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class Ticket {
    private Passagier passagier;
    private String klasse;
    private Vlucht vlucht;
    public static List<Ticket> tickets = new ArrayList<>();

    public Ticket(Passagier passagier, String klasse, Vlucht vlucht) {
        this.passagier = passagier;
        this.klasse = klasse;
        this.vlucht = vlucht;
    }

    public Passagier getPassagier() {
        return passagier;
    }

    public String getKlasse() {
        return klasse;
    }

    public Vlucht getVlucht() {
        return vlucht;
    }

    @Override
    public String toString() {
        return "Ticket{" +
                "passagier=" + passagier +
                ", klasse='" + klasse + '\'' +
                ", vlucht=" + vlucht +
                '}';
    }

    public static void ticketAanmaken(Scanner scanner) {
        String passagierNaam;
        do {
            System.out.println("Voer passagier naam in:");
            passagierNaam = scanner.nextLine();
            if (passagierNaam.isEmpty()) {
                System.out.println("Passagier naam mag niet leeg zijn. Probeer opnieuw.");
            }
        } while (passagierNaam.isEmpty());

        Passagier passagier = null;
        for (Passagier p : Passagier.passagiers) {
            if (p.getNaam().equalsIgnoreCase(passagierNaam)) {
                passagier = p;
                break;
            }
        }
        if (passagier == null) {
            System.out.println("Passagier niet gevonden.");
            return;
        }

        String vluchtCode;
        do {
            System.out.println("Voer vluchtcode in:");
            vluchtCode = scanner.nextLine();
            if (vluchtCode.isEmpty()) {
                System.out.println("Vluchtcode mag niet leeg zijn. Probeer opnieuw.");
            }
        } while (vluchtCode.isEmpty());

        Vlucht vlucht = null;
        for (Vlucht v : Vlucht.getVluchten()) {
            if (v.getVluchtcode().equalsIgnoreCase(vluchtCode)) {
                vlucht = v;
                break;
            }
        }
        if (vlucht == null) {
            System.out.println("Vlucht niet gevonden.");
            return;
        }

        String klasse;
        do {
            System.out.println("Voer klasse in (economy/business):");
            klasse = scanner.nextLine();
            if (klasse.equalsIgnoreCase("eco")) {
                klasse = "economy";
            }
            if (klasse.isEmpty()) {
                System.out.println("Klasse mag niet leeg zijn. Probeer opnieuw.");
            }
        } while (klasse.isEmpty());

        Ticket ticket = new Ticket(passagier, klasse, vlucht);
        tickets.add(ticket);
        System.out.println("Ticket aangemaakt: " + ticket);
    }
}