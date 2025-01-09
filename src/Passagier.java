import java.util.List;
import java.util.Locale;
import java.util.Scanner;
import java.util.ArrayList;

public class Passagier extends Persoon{

    static List<Passagier> passagiers = new ArrayList<>();
    private int bagageGewicht;

    public Passagier(String naam, String voornaam, int leeftijd, String adres, int bagageGewicht) {
        super(naam, voornaam, leeftijd, adres);
        this.bagageGewicht = bagageGewicht;
    }

    @Override
    public String toString() {
        return "Passagier{" + getNaam() + " " + getVoornaam() + " (" + getLeeftijd() + ") - " + getAdres() +
                ", bagageGewicht=" + bagageGewicht + '}';
    }

    public static void maakNieuwePassagier(Scanner scanner) {
        String naam;
        do {
            System.out.println("Voer naam in:");
            naam = scanner.nextLine();
            if (naam.isEmpty()) {
                System.out.println("Naam mag niet leeg zijn. Probeer opnieuw.");
            }
        } while (naam.isEmpty());

        String voornaam;
        do {
            System.out.println("Voer voornaam in:");
            voornaam = scanner.nextLine();
            if (voornaam.isEmpty()) {
                System.out.println("Voornaam mag niet leeg zijn. Probeer opnieuw.");
            }
        } while (voornaam.isEmpty());

        int leeftijd;
        while (true) {
            System.out.println("Voer leeftijd in:");
            String leeftijdInput = scanner.nextLine();
            try {
                leeftijd = Integer.parseInt(leeftijdInput);
                break;
            } catch (NumberFormatException e) {
                System.out.println("Ongeldige leeftijd. Probeer opnieuw.");
            }
        }

        String adres;
        do {
            System.out.println("Voer adres in:");
            adres = scanner.nextLine();
            if (adres.isEmpty()) {
                System.out.println("Adres mag niet leeg zijn. Probeer opnieuw.");
            }
        } while (adres.isEmpty());

        int gewicht;
        while (true) {
            System.out.println("Voer gewicht in:");
            gewicht = scanner.nextInt();
            scanner.nextLine(); // Consume newline
            if (gewicht > 23) {
                System.out.println("Uw bagage is te zwaar, max. 23kg. Probeer opnieuw.");
            } else {
                break;
            }
        }

        Passagier nieuwePassagier = new Passagier(naam, voornaam, leeftijd, adres, gewicht);
        Passagier.passagiers.add(nieuwePassagier);
        System.out.println("Nieuwe passagier aangemaakt: " + nieuwePassagier);
    }

    public static void zoekPassagier(Scanner scanner) {
        System.out.println("Voer naam in:");
        String naam = scanner.nextLine();
        System.out.println("Voer voornaam in:");
        String voornaam = scanner.nextLine();

        for (Passagier passagier : passagiers) {
            if (passagier.getNaam().toLowerCase(Locale.ROOT).equals(naam.toLowerCase(Locale.ROOT)) &&
                    passagier.getVoornaam().toLowerCase(Locale.ROOT).equals(voornaam.toLowerCase(Locale.ROOT))) {
                System.out.println(passagier);
                return;
            }
        }
        System.out.println("Passagier niet gevonden.");
    }
}
