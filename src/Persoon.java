public class Persoon {

    private String naam;
    private String voornaam;
    private int leeftijd;
    private String adres;

    public Persoon(String naam, String voornaam, int leeftijd, String adres) {
        this.naam = naam;
        this.voornaam = voornaam;
        this.leeftijd = leeftijd;
        this.adres = adres;
    }

    public String getNaam() {
        return naam;
    }

    public void setNaam(String naam) {
        this.naam = naam;
    }

    public String getVoornaam() {
        return voornaam;
    }

    public void setVoornaam(String voornaam) {
        this.voornaam = voornaam;
    }

    public int getLeeftijd() {
        return leeftijd;
    }

    public void setLeeftijd(int leeftijd) {
        this.leeftijd = leeftijd;
    }

    public String getAdres() {
        return adres;
    }

    public void setAdres(String adres) {
        this.adres = adres;
    }

    @Override
    public String toString() {
        return naam + " " + voornaam + " (" + leeftijd + ") - " + adres;
    }
}
