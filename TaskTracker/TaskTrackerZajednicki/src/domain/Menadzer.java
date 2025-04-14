package domain;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Menadzer extends AbstractDomainObject {
    
    private Long menadzerID;
    private String ime;
    private String prezime;
    private String email;
    private String lozinka;

    public Menadzer(Long menadzerID, String ime, String prezime, String email, String lozinka) {
        this.menadzerID = menadzerID;
        this.ime = ime;
        this.prezime = prezime;
        this.email = email;
        this.lozinka = lozinka;
    }

    public Menadzer() {
    }
    
    @Override
    public String nazivTabele() {
        return " menadzer ";
    }

    @Override
    public String alijas() {
        return " m ";
    }

    @Override
    public String join() {
        return ""; // Nema join-a jer menadžer nema direktne veze s drugim tabelama ovde
    }

    @Override
    public ArrayList<AbstractDomainObject> vratiListu(ResultSet rs) throws SQLException {
        ArrayList<AbstractDomainObject> lista = new ArrayList<>();
        
        while (rs.next()) {
            Menadzer m = new Menadzer(rs.getLong("MenadzerID"),
                                       rs.getString("Ime"),
                                       rs.getString("Prezime"),
                                       rs.getString("Email"),
                                       rs.getString("Lozinka"));
            lista.add(m);
        }
        rs.close();
        return lista;
    }

    @Override
    public String koloneZaInsert() {
        return " (Ime, Prezime, Email, Lozinka) ";
    }

    @Override
    public String uslov() {
        return " MenadzerID = " + menadzerID;
    }

    @Override
    public String vrednostiZaInsert() {
        return "'" + ime + "', '" + prezime + "', '" + email + "', '" + lozinka + "'";
    }

    @Override
    public String vrednostiZaUpdate() {
        return "Ime = '" + ime + "', Prezime = '" + prezime + "', Email = '" + email + "', Lozinka = '" + lozinka + "'";
    }

    @Override
    public String uslovZaSelect() {
        return ""; // Može se koristiti za specifične upite kasnije
    }

    public Long getMenadzerID() {
        return menadzerID;
    }

    public void setMenadzerID(Long menadzerID) {
        this.menadzerID = menadzerID;
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
}