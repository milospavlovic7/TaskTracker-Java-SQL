package domain;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

public class Zadatak extends AbstractDomainObject {

    private Long zadatakID;
    private String nazivZadatka;
    private String opisZadatka;
    private Date datumKreiranjaZadatka;
    private StatusZadatka statusZadatka;
    private Menadzer menadzer; // Čuvamo ceo objekat Menadzer

    public Zadatak(Long zadatakID, String nazivZadatka, String opisZadatka, 
                   Date datumKreiranjaZadatka, StatusZadatka statusZadatka, Menadzer menadzer) {
        this.zadatakID = zadatakID;
        this.nazivZadatka = nazivZadatka;
        this.opisZadatka = opisZadatka;
        this.datumKreiranjaZadatka = datumKreiranjaZadatka;
        this.statusZadatka = statusZadatka;
        this.menadzer = menadzer;
    }

    public Zadatak() {
    }

    @Override
    public String nazivTabele() {
        return " zadatak ";
    }

    @Override
    public String alijas() {
        return " zd ";
    }

    @Override
    public String join() {
        return " JOIN menadzer m ON zd.MenadzerID = m.MenadzerID ";
    }

    @Override
    public ArrayList<AbstractDomainObject> vratiListu(ResultSet rs) throws SQLException {
        ArrayList<AbstractDomainObject> lista = new ArrayList<>();

        while (rs.next()) {
            // Kreiramo objekat menadžera koristeći podatke iz ResultSet-a
            Menadzer m = new Menadzer();
            m.setMenadzerID(rs.getLong("MenadzerID"));
            m.setIme(rs.getString("Ime"));
            m.setPrezime(rs.getString("Prezime"));

            Zadatak z = new Zadatak(
                    rs.getLong("ZadatakID"),
                    rs.getString("NazivZadatka"),
                    rs.getString("OpisZadatka"),
                    rs.getDate("DatumKreiranjaZadatka"),
                    StatusZadatka.valueOf(rs.getString("StatusZadatka")),
                    m
            );
            lista.add(z);
        }
        rs.close();
        return lista;
    }

    @Override
    public String koloneZaInsert() {
        return " (NazivZadatka, OpisZadatka, DatumKreiranjaZadatka, StatusZadatka, MenadzerID) ";
    }

    @Override
    public String uslov() {
        return " ZadatakID = " + zadatakID;
    }

    @Override
    public String vrednostiZaInsert() {
        return "'" + nazivZadatka + "', '" + opisZadatka + "', '" + new java.sql.Date(datumKreiranjaZadatka.getTime()) + "', " +
                "'" + statusZadatka.name() + "', " + menadzer.getMenadzerID();
    }

    @Override
    public String vrednostiZaUpdate() {
        return "NazivZadatka = '" + nazivZadatka + "', OpisZadatka = '" + opisZadatka + "', " +
               "DatumKreiranjaZadatka = '" + new java.sql.Date(datumKreiranjaZadatka.getTime()) + "', " +
               "StatusZadatka = '" + statusZadatka.name() + "', MenadzerID = " + menadzer.getMenadzerID();
    }

    @Override
    public String uslovZaSelect() {
        return "";
    }

    // Getteri i setteri
    public Long getZadatakID() {
        return zadatakID;
    }

    public void setZadatakID(Long zadatakID) {
        this.zadatakID = zadatakID;
    }

    public String getNazivZadatka() {
        return nazivZadatka;
    }

    public void setNazivZadatka(String nazivZadatka) {
        this.nazivZadatka = nazivZadatka;
    }

    public String getOpisZadatka() {
        return opisZadatka;
    }

    public void setOpisZadatka(String opisZadatka) {
        this.opisZadatka = opisZadatka;
    }

    public Date getDatumKreiranjaZadatka() {
        return datumKreiranjaZadatka;
    }

    public void setDatumKreiranjaZadatka(Date datumKreiranjaZadatka) {
        this.datumKreiranjaZadatka = datumKreiranjaZadatka;
    }

    public StatusZadatka getStatusZadatka() {
        return statusZadatka;
    }

    public void setStatusZadatka(StatusZadatka statusZadatka) {
        this.statusZadatka = statusZadatka;
    }

    public Menadzer getMenadzer() {
        return menadzer;
    }

    public void setMenadzer(Menadzer menadzer) {
        this.menadzer = menadzer;
    }

    @Override
    public String toString() {
        return nazivZadatka;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Zadatak zadatak = (Zadatak) o;

        return zadatakID != null ? zadatakID.equals(zadatak.zadatakID) : zadatak.zadatakID == null;
    }

    @Override
    public int hashCode() {
        return zadatakID != null ? zadatakID.hashCode() : 0;
    }

}
