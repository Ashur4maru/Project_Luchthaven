import java.util.Scanner;


    public static void main(String[] args) {
        // Initialiseer passagiers
        Passagier p1 = new Passagier("Jan", "Janssens", 25, "Kerkstraat 1", 20);
        Passagier p2 = new Passagier("Piet", "Pieters", 30, "Dorpstraat 2", 15);
        Passagier.passagiers.add(p1);
        Passagier.passagiers.add(p2);

        // Initialiseer piloten
        Piloot piloot1 = new Piloot("Jef", "Janssens", 45, "Kerkstraat 1");
        Piloot piloot2 = new Piloot("Peter", "Jan", 50, "Dorpstraat 2");
        Piloot.piloten.add(piloot1);
        Piloot.piloten.add(piloot2);

        // Initialiseer crewleden
        Crew c1 = new Crew("Jens", "Gooi", 45, "Kerkstraat 1");
        Crew c2 = new Crew("Jurg", "Bols", 50, "Dorpstraat 2");
        Crew c3 = new Crew("Jor", "Baal", 37, "Kerkstraat 1");
        Crew.crews.add(c1);
        Crew.crews.add(c2);
        Crew.crews.add(c3);

        // Initialiseer vliegtuigen
        Vliegtuig vliegtuig1 = new Vliegtuig("REG123", "Boeing 737", 150, true); // Benzine vol
        Vliegtuig vliegtuig2 = new Vliegtuig("REG456", "Airbus A320", 170, false); // Benzine leeg
        Vliegtuig.vliegtuigen.add(vliegtuig1);
        Vliegtuig.vliegtuigen.add(vliegtuig2);

        // Initialiseer vluchten
        Vlucht vlucht1 = new Vlucht("Amsterdam", "KL1702", 80, 25,vliegtuig1);
        Vlucht vlucht2 = new Vlucht("Malaga", "TB165", 100, 0,vliegtuig2);
        Vlucht.vluchten.add(vlucht1);
        Vlucht.vluchten.add(vlucht2);


        // Start het hoofdmenu
        Scanner scanner = new Scanner(System.in);
        while (true) {
            toonHoofdmenu();
            String keuze = scanner.nextLine();

            if (!keuze.matches("[1-9]")) {
                System.out.println("Ongeldige invoer. Kies een optie tussen 1 en 9.");
                continue;
            }

            switch (Integer.parseInt(keuze)) {
                case 1 -> handlePassagierMenu(scanner);
                case 2 -> handleVluchtMenu(scanner);
                case 3 -> Ticket.ticketAanmaken(scanner);
                case 4 -> boardPassenger(scanner);
                case 5 -> handlePersoneelToewijzen(scanner);
                case 6 -> printVluchtInfo(scanner);
                case 7 -> handlePilootMenu(scanner);
                case 8 -> toewijzenVliegtuig(scanner);
                case 9 -> {
                    System.out.println("Programma wordt afgesloten.");
                    return;
                }
            }
        }
    }


    private static void toonHoofdmenu() {
        System.out.println("\nMenu:");
        System.out.println("1. Passagier beheren");
        System.out.println("2. Vluchten beheren");
        System.out.println("3. Ticket aanmaken");
        System.out.println("4. Boarding");
        System.out.println("5. Personeel toewijzen");
        System.out.println("6. Print vlucht info");
        System.out.println("7. Piloot beheren");
        System.out.println("8. Vliegtuig toewijzen");
        System.out.println("9. Exit");
        System.out.print("Kies een optie: ");
    }

    private static void handlePassagierMenu(Scanner scanner) {
        System.out.println("\n1. Passagier aanmaken");
        System.out.println("2. Passagiers lijst weergeven");
        System.out.print("Kies een optie: ");
        String keuze = scanner.nextLine();

        if (!keuze.matches("[1-2]")) {
            System.out.println("Ongeldige invoer. Kies 1 of 2.");
            return;
        }

        switch (Integer.parseInt(keuze)) {
            case 1 -> Passagier.maakNieuwePassagier(scanner);
            case 2 -> {
                System.out.println("\nLijst van passagiers:");
                for (Passagier passagier : Passagier.passagiers) {
                    System.out.println(passagier);
                }
            }
        }
    }

    private static void handleVluchtMenu(Scanner scanner) {
        System.out.println("\n1. Vlucht aanmaken");
        System.out.println("2. Vluchten lijst weergeven");
        System.out.print("Kies een optie: ");
        String keuze = scanner.nextLine();

        if (!keuze.matches("[1-2]")) {
            System.out.println("Ongeldige invoer. Kies 1 of 2.");
            return;
        }

        switch (Integer.parseInt(keuze)) {
            case 1 -> Vlucht.maakNieuweVlucht(scanner);
            case 2 -> {
                System.out.println("\nLijst van vluchten:");
                for (Vlucht vlucht : Vlucht.getVluchten()) {
                    System.out.println(vlucht);
                }
            }
        }
    }

    private static void boardPassenger(Scanner scanner) {
        System.out.print("\nVoer passagier naam in: ");
        String passagierNaam = scanner.nextLine();
        Passagier passagier = zoekPassagier(passagierNaam);

        if (passagier == null) {
            System.out.println("Passagier niet gevonden.");
            return;
        }

        System.out.print("Voer vluchtcode in: ");
        String vluchtCode = scanner.nextLine();
        Vlucht vlucht = zoekVlucht(vluchtCode);

        if (vlucht == null) {
            System.out.println("Vlucht niet gevonden.");
            return;
        }

        System.out.print("Voer klasse in (economy/business): ");
        String klasse = scanner.nextLine();
        try {
            vlucht.boardPassenger(passagier, klasse);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        System.out.println("Passagier " + passagier.getNaam() + " is ingecheckt voor vlucht " + vlucht.getVluchtcode());
    }

    private static void handlePersoneelToewijzen(Scanner scanner) {
        System.out.println("\n1. Piloot toewijzen");
        System.out.println("2. Crew toewijzen");
        System.out.print("Kies een optie: ");
        String keuze = scanner.nextLine();

        if (!keuze.matches("[1-2]")) {
            System.out.println("Ongeldige invoer. Kies 1 of 2.");
            return;
        }

        switch (Integer.parseInt(keuze)) {
            case 1 -> toewijzenPiloot(scanner);
            case 2 -> toewijzenCrew(scanner);
        }
    }

    private static void printVluchtInfo(Scanner scanner) {
        System.out.print("\nVoer vluchtcode in: ");
        String vluchtCode = scanner.nextLine();
        Vlucht vlucht = zoekVlucht(vluchtCode);

        if (vlucht == null) {
            System.out.println("Vlucht niet gevonden.");
            return;
        }

        System.out.println(vlucht);
    }

    private static Passagier zoekPassagier(String naam) {
        for (Passagier passagier : Passagier.passagiers) {
            if (passagier.getNaam().equalsIgnoreCase(naam)) {
                return passagier;
            }
        }
        return null;
    }

    private static Vlucht zoekVlucht(String vluchtCode) {
        for (Vlucht vlucht : Vlucht.getVluchten()) {
            if (vlucht.getVluchtcode().equalsIgnoreCase(vluchtCode)) {
                return vlucht;
            }
        }
        return null;
    }

    private static void toewijzenPiloot(Scanner scanner) {
        System.out.print("\nVoer piloot naam in: ");
        String pilootNaam = scanner.nextLine();
        Piloot piloot = zoekPiloot(pilootNaam);

        if (piloot == null) {
            System.out.println("Piloot niet gevonden.");
            return;
        }

        System.out.print("Voer vluchtcode in: ");
        String vluchtCode = scanner.nextLine();
        Vlucht vlucht = zoekVlucht(vluchtCode);

        if (vlucht == null) {
            System.out.println("Vlucht niet gevonden.");
            return;
        }

        vlucht.setPiloot(piloot);
        System.out.println("Piloot toegewezen aan vlucht: " + piloot.getNaam());
    }

    private static void toewijzenCrew(Scanner scanner) {
        System.out.print("\nVoer crew naam in: ");
        String crewNaam = scanner.nextLine();
        Crew crew = zoekCrew(crewNaam);

        if (crew == null) {
            System.out.println("Crew niet gevonden.");
            return;
        }

        System.out.print("Voer vluchtcode in: ");
        String vluchtCode = scanner.nextLine();
        Vlucht vlucht = zoekVlucht(vluchtCode);

        if (vlucht == null) {
            System.out.println("Vlucht niet gevonden.");
            return;
        }

        vlucht.setCrew(crew);
        System.out.println("Crew toegewezen aan vlucht: " + crew.getNaam());
    }

    private static Piloot zoekPiloot(String naam) {
        for (Piloot piloot : Piloot.piloten) {
            if (piloot.getNaam().equalsIgnoreCase(naam)) {
                return piloot;
            }
        }
        return null;
    }

    private static Crew zoekCrew(String naam) {
        for (Crew crew : Crew.crews) {
            if (crew.getNaam().equalsIgnoreCase(naam)) {
                return crew;
            }
        }
        return null;
    }

    private static void handlePilootMenu(Scanner scanner) {
        System.out.println("Voer piloot naam in:");
        String pilootNaam = scanner.nextLine();
        if (pilootNaam.isEmpty() || !pilootNaam.matches("[a-zA-Z]+")) {
            System.out.println("Ongeldige naam. Probeer opnieuw.");
            return;
        }

        Piloot piloot = null;
        for (Piloot p : Piloot.piloten) {
            if (p.getNaam().equalsIgnoreCase(pilootNaam)) {
                piloot = p;
                break;
            }
        }
        if (piloot == null) {
            System.out.println("Piloot niet gevonden.");
            return;
        }

        System.out.println("1. Check Benzine.");
        System.out.println("2. Tanken.");
        System.out.println("3. Flight check.");
        String keuzePiloot = scanner.nextLine();
        if (keuzePiloot.isEmpty() || !keuzePiloot.matches("[1-3]")) {
            System.out.println("Ongeldige invoer. Probeer opnieuw.");
            return;
        }

        switch (Integer.parseInt(keuzePiloot)) {
            case 1:
                System.out.println("Geef vliegtuig code in:");
                String vliegtuigCode = scanner.nextLine();
                Vliegtuig vliegtuig = null;
                for (Vliegtuig vt : Vliegtuig.vliegtuigen) {
                    if (vt.getRegistratienummer().equalsIgnoreCase(vliegtuigCode)) {
                        vliegtuig = vt;
                        break;
                    }
                }
                if (vliegtuig == null) {
                    System.out.println("Vliegtuig niet gevonden.");
                    break;
                }
                if (vliegtuig.isBenzineVol()) {
                    System.out.println("Benzine is vol.");
                } else {
                    System.out.println("Benzine is leeg.");
                }
                break;
            case 2:
                System.out.println("Geef vliegtuig code in:");
                String vliegtuigCode2 = scanner.nextLine();
                vliegtuig = null;
                for (Vliegtuig vt : Vliegtuig.vliegtuigen) {
                    if (vt.getRegistratienummer().equalsIgnoreCase(vliegtuigCode2)) {
                        vliegtuig = vt;
                        break;
                    }
                }
                if (vliegtuig == null) {
                    System.out.println("Vliegtuig niet gevonden.");
                    break;
                }
                vliegtuig.vulBenzine();
                System.out.println("Vliegtuig getankt.");
                break;
            case 3:
                System.out.println("Voer vluchtcode in:");
                String vluchtCode = scanner.nextLine();
                Vlucht vlucht = null;
                for (Vlucht v : Vlucht.getVluchten()) {
                    if (v.getVluchtcode().equalsIgnoreCase(vluchtCode)) {
                        vlucht = v;
                        break;
                    }
                }
                if (vlucht == null) {
                    System.out.println("Vlucht niet gevonden.");
                    break;
                }

                // Check fuel
                if (!vlucht.getVliegtuig().isBenzineVol()) {
                    System.out.println("Niet genoeg benzine.");
                    break;
                }

                // Check pilot
                if (vlucht.getPiloot() == null) {
                    System.out.println("Geen piloot toegewezen.");
                    break;
                }

                // Check crew
                if (vlucht.getCrew() == null) {
                    System.out.println("Geen crew toegewezen.");
                    break;
                }

                // Check passengers
                if (Passagier.passagiers.isEmpty()) {
                    System.out.println("Geen passagiers aanwezig.");
                    break;
                }

                System.out.println("Flight check succesvol. Alle vereisten zijn aanwezig.");
                break;
        }
    }

    private static void toewijzenVliegtuig(Scanner scanner) {
        System.out.print("\nVoer vliegtuig code in: ");
        String vliegtuigCode = scanner.nextLine();
        Vliegtuig vliegtuig = zoekVliegtuig(vliegtuigCode);

        if (vliegtuig == null) {
            System.out.println("Vliegtuig niet gevonden.");
            return;
        }

        System.out.print("Voer vluchtcode in: ");
        String vluchtCode = scanner.nextLine();
        Vlucht vlucht = zoekVlucht(vluchtCode);

        if (vlucht == null) {
            System.out.println("Vlucht niet gevonden.");
            return;
        }

        vlucht.setVliegtuig(vliegtuig);
        System.out.println("Vliegtuig toegewezen aan vlucht: " + vliegtuig.getRegistratienummer());
    }

    private static Vliegtuig zoekVliegtuig(String registratienummer) {
        for (Vliegtuig vliegtuig : Vliegtuig.vliegtuigen) {
            if (vliegtuig.getRegistratienummer().equalsIgnoreCase(registratienummer)) {
                return vliegtuig;
            }
        }
        return null;
    }


