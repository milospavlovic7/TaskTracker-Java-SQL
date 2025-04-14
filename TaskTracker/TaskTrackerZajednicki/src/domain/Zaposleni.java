package domain;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Zaposleni extends AbstractDomainObject {

    private Long zaposleniID;
    private String ime;
    private String prezime;
    private String telefon;
    private String email;
    private String lozinka;
    private Menadzer menadzer; 

    public Zaposleni(Long zaposleniID, String ime, String prezime, String telefon, 
                     String email, String lozinka, Menadzer menadzer) {
        this.zaposleniID = zaposleniID;
        this.ime = ime;
        this.prezime = prezime;
        this.telefon = telefon;
        this.email = email;
        this.lozinka = lozinka;
        this.menadzer = menadzer;
    }

    public Zaposleni() {
    }

    @Override
    public String nazivTabele() {
        return " zaposleni ";
    }

    @Override
    public String alijas() {
        return " zap ";
    }

    @Override
    public String join() {
        return " JOIN menadzer m ON zap.MenadzerID = m.MenadzerID ";
    }

    @Override
    public ArrayList<AbstractDomainObject> vratiListu(ResultSet rs) throws SQLException {
        ArrayList<AbstractDomainObject> lista = new ArrayList<>();
        while (rs.next()) {
            // Kreiramo objekat menadžera koristeći podatke iz ResultSet-a
            Menadzer m = new Menadzer();
            m.setMenadzerID(rs.getLong("MenadzerID"));
            m.setIme(rs.getString("Ime")); // Dobija ime menadžera
            m.setPrezime(rs.getString("Prezime")); // Dobija prezime menadžera

            Zaposleni z = new Zaposleni(
                    rs.getLong("ZaposleniID"),
                    rs.getString("Ime"),
                    rs.getString("Prezime"),
                    rs.getString("Telefon"),
                    rs.getString("Email"),
                    rs.getString("Lozinka"),
                    m // Dodeljujemo menadžera zaposlenom
            );
            lista.add(z);
        }
        rs.close();
        return lista;
    }


    @Override
    public String koloneZaInsert() {
        return " (Ime, Prezime, Email, Telefon, Lozinka, MenadzerID) ";
    }

    @Override
    public String uslov() {
        return " ZaposleniID = " + zaposleniID;
    }

    @Override
    public String vrednostiZaInsert() {
        // Menadžer se čuva preko njegovog ID-a
        return "'" + ime + "', '" + prezime + "', '" + email + "', '" + telefon + "', '" + lozinka + "', " + menadzer.getMenadzerID();
    }

    @Override
    public String vrednostiZaUpdate() {
        return " Ime = '" + ime + "', Prezime = '" + prezime + "', Email = '" + email + "', " +
               "Telefon = '" + telefon + "', Lozinka = '" + lozinka + "', MenadzerID = " + menadzer.getMenadzerID();
    }

    @Override
    public String uslovZaSelect() {
        return "";
    }

    // Getteri i setteri
    public Long getZaposleniID() {
        return zaposleniID;
    }

    public void setZaposleniID(Long zaposleniID) {
        this.zaposleniID = zaposleniID;
    }

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public String getPrezime() {
        return prezime;
    }

    public void setPrezime(String prezime) {
        this.prezime = prezime;
    }

    public String getTelefon() {
        return telefon;
    }

    public void setTelefon(String telefon) {
        this.telefon = telefon;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLozinka() {
        return lozinka;
    }

    public void setLozinka(String lozinka) {
        this.lozinka = lozinka;
    }

    public Menadzer getMenadzer() {
        return menadzer;
    }

    public void setMenadzer(Menadzer menadzer) {
        this.menadzer = menadzer;
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Zaposleni other = (Zaposleni) obj;
        return this.getZaposleniID() != null && this.getZaposleniID().equals(other.getZaposleniID());
    }

    @Override
    public int hashCode() {
        return getZaposleniID() != null ? getZaposleniID().hashCode() : 0;
    }


    @Override
    public String toString() {
        return ime + " " + prezime;
    }
}
