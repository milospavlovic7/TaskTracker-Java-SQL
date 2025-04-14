package domain;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

public class PripadnostZadatkaPoslu extends AbstractDomainObject {

    private Long posaoID;
    private Long zadatakID;
    private Date datumPocetkaPrip;
    private Date datumZavrsetkaPrip;

    // Konstruktor sa svim parametrima
    public PripadnostZadatkaPoslu(Long posaoID, Long zadatakID, Date datumPocetkaPrip, Date datumZavrsetkaPrip) {
        this.posaoID = posaoID;
        this.zadatakID = zadatakID;
        this.datumPocetkaPrip = datumPocetkaPrip;
        this.datumZavrsetkaPrip = datumZavrsetkaPrip;
    }

    // Default konstruktor
    public PripadnostZadatkaPoslu() {
    }

    @Override
    public String nazivTabele() {
        return " pripadnostzadatkaposlu";
    }

    @Override
    public String alijas() {
        return " pr ";
    }

    @Override
    public String join() {
        return "JOIN posao ps ON pr.PosaoID = ps.PosaoID " +
               "JOIN zadatak zad ON pr.ZadatakID = zad.ZadatakID";
    }


    @Override
    public ArrayList<AbstractDomainObject> vratiListu(ResultSet rs) throws SQLException {
        ArrayList<AbstractDomainObject> lista = new ArrayList<>();

        while (rs.next()) {
            PripadnostZadatkaPoslu p = new PripadnostZadatkaPoslu(rs.getLong("PosaoID"),
                                                                rs.getLong("ZadatakID"),
                                                                rs.getDate("DatumPocetkaPrip"),
                                                                rs.getDate("DatumZavrsetkaPrip"));
            lista.add(p);
        }
        rs.close();
        return lista;
    }

    @Override
    public String koloneZaInsert() {
        return " (PosaoID, ZadatakID, DatumPocetkaPrip, DatumZavrsetkaPrip) ";
    }

    @Override
    public String uslov() {
        return " PosaoID = " + posaoID + " AND ZadatakID = " + zadatakID;
    }

    @Override
    public String vrednostiZaInsert() {
        String datumPocetka;
        if (datumPocetkaPrip != null) {
            datumPocetka = "'" + new java.sql.Date(datumPocetkaPrip.getTime()) + "'";
        } else {
            datumPocetka = "null";
        }

        String datumZavrsetka;
        if (datumZavrsetkaPrip != null) {
            datumZavrsetka = "'" + new java.sql.Date(datumZavrsetkaPrip.getTime()) + "'";
        } else {
            datumZavrsetka = "null";
        }

        return posaoID + ", " + zadatakID + ", " + datumPocetka + ", " + datumZavrsetka;
    }


    @Override
    public String vrednostiZaUpdate() {
        return "DatumPocetkaPrip = '" + new java.sql.Date(datumPocetkaPrip.getTime()) + "', " +
               "DatumZavrsetkaPrip = '" + new java.sql.Date(datumZavrsetkaPrip.getTime()) + "'";
    }

    @Override
    public String uslovZaSelect() {
        return "";
    }

    // Getters i Setters
    public Long getPosaoID() {
        return posaoID;
    }

    public void setPosaoID(Long posaoID) {
        this.posaoID = posaoID;
    }

    public Long getZadatakID() {
        return zadatakID;
    }

    public void setZadatakID(Long zadatakID) {
        this.zadatakID = zadatakID;
    }

    public Date getDatumPocetkaPrip() {
        return datumPocetkaPrip;
    }

    public void setDatumPocetkaPrip(Date datumPocetkaPrip) {
        this.datumPocetkaPrip = datumPocetkaPrip;
    }

    public Date getDatumZavrsetkaPrip() {
        return datumZavrsetkaPrip;
    }

    public void setDatumZavrsetkaPrip(Date datumZavrsetkaPrip) {
        this.datumZavrsetkaPrip = datumZavrsetkaPrip;
    }

    @Override
    public String toString() {
        return "PosaoID: " + posaoID + ", ZadatakID: " + zadatakID + ", DatumPocetkaPrip: " + datumPocetkaPrip +
               ", DatumZavrsetkaPrip: " + datumZavrsetkaPrip;
    }
}
