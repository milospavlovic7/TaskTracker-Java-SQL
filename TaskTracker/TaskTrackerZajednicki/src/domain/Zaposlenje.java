package domain;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

public class Zaposlenje extends AbstractDomainObject {

    private Long zaposleniID;
    private Long posaoID;
    private Date datumPocetkaZap;
    private Date datumZavrsetkaZap;

    // Konstruktor sa svim parametrima
    public Zaposlenje(Long zaposleniID, Long posaoID, Date datumPocetkaZap, Date datumZavrsetkaZap) {
        this.zaposleniID = zaposleniID;
        this.posaoID = posaoID;
        this.datumPocetkaZap = datumPocetkaZap;
        this.datumZavrsetkaZap = datumZavrsetkaZap;
    }

    // Default konstruktor
    public Zaposlenje() {
    }

    @Override
    public String nazivTabele() {
        return " zaposlenje ";
    }

    @Override
    public String alijas() {
        return " znj ";
    }

    @Override
    public String join() {
        return " JOIN zaposleni zap ON znj.ZaposleniID = zap.ZaposleniID " +
               "JOIN posao p ON znj.PosaoID = p.PosaoID ";
    }

    @Override
    public ArrayList<AbstractDomainObject> vratiListu(ResultSet rs) throws SQLException {
        ArrayList<AbstractDomainObject> lista = new ArrayList<>();

        while (rs.next()) {
            Zaposlenje z = new Zaposlenje(rs.getLong("ZaposleniID"),
                                          rs.getLong("PosaoID"),
                                          rs.getDate("DatumPocetkaZap"),
                                          rs.getDate("DatumZavrsetkaZap"));
            lista.add(z);
        }
        rs.close();
        return lista;
    }

    @Override
    public String koloneZaInsert() {
        return " (ZaposleniID, PosaoID, DatumPocetkaZap, DatumZavrsetkaZap) ";
    }

    @Override
    public String uslov() {
        return " ZaposleniID = " + zaposleniID + " AND PosaoID = " + posaoID;
    }

    @Override
    public String vrednostiZaInsert() {
        String datumZavrsetkaStr = (datumZavrsetkaZap != null)
            ? "'" + new java.sql.Date(datumZavrsetkaZap.getTime()) + "'"
            : "NULL";

        return zaposleniID + ", " + posaoID + ", '" + new java.sql.Date(datumPocetkaZap.getTime()) + "', " + datumZavrsetkaStr;
    }


    @Override
    public String vrednostiZaUpdate() {
        String datumZavrsetkaStr = (datumZavrsetkaZap != null)
            ? "'" + new java.sql.Date(datumZavrsetkaZap.getTime()) + "'"
            : "NULL";

        return "DatumPocetkaZap = '" + new java.sql.Date(datumPocetkaZap.getTime()) + "', " +
               "DatumZavrsetkaZap = " + datumZavrsetkaStr;
    }


    @Override
    public String uslovZaSelect() {
        return "";
    }

    // Getters i Setters
    public Long getZaposleniID() {
        return zaposleniID;
    }

    public void setZaposleniID(Long zaposleniID) {
        this.zaposleniID = zaposleniID;
    }

    public Long getPosaoID() {
        return posaoID;
    }

    public void setPosaoID(Long posaoID) {
        this.posaoID = posaoID;
    }

    public Date getDatumPocetkaZap() {
        return datumPocetkaZap;
    }

    public void setDatumPocetkaZap(Date datumPocetkaZap) {
        this.datumPocetkaZap = datumPocetkaZap;
    }

    public Date getDatumZavrsetkaZap() {
        return datumZavrsetkaZap;
    }

    public void setDatumZavrsetkaZap(Date datumZavrsetkaZap) {
        this.datumZavrsetkaZap = datumZavrsetkaZap;
    }

    @Override
    public String toString() {
        return "ZaposleniID: " + zaposleniID + ", PosaoID: " + posaoID + ", DatumPocetkaZap: " + datumPocetkaZap +
               ", DatumZavrsetkaZap: " + datumZavrsetkaZap;
    }
}
