package domain;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

public class Posao extends AbstractDomainObject {

    private Long posaoID;
    private String nazivPosla;
    private String opisPosla;
    private Date datumKreiranjaPosla;
    private Menadzer menadzer;

    public Posao(Long posaoID, String nazivPosla, String opisPosla, Date datumKreiranjaPosla, Menadzer menadzer) {
        this.posaoID = posaoID;
        this.nazivPosla = nazivPosla;
        this.opisPosla = opisPosla;
        this.datumKreiranjaPosla = datumKreiranjaPosla;
        this.menadzer = menadzer;
    }

    public Posao() {
    }

    @Override
    public String nazivTabele() {
        return " posao ";
    }

    @Override
    public String alijas() {
        return " p ";
    }

    @Override
    public String join() {
        return " JOIN menadzer m ON p.MenadzerID = m.MenadzerID ";
    }

    @Override
    public ArrayList<AbstractDomainObject> vratiListu(ResultSet rs) throws SQLException {
        ArrayList<AbstractDomainObject> lista = new ArrayList<>();

        while (rs.next()) {
            Menadzer m = new Menadzer();
            m.setMenadzerID(rs.getLong("MenadzerID"));
            m.setIme(rs.getString("Ime"));
            m.setPrezime(rs.getString("Prezime"));

            Posao p = new Posao(
                    rs.getLong("PosaoID"),
                    rs.getString("NazivPosla"),
                    rs.getString("OpisPosla"),
                    rs.getDate("DatumKreiranjaPosla"),
                    m
            );

            lista.add(p);
        }
        rs.close();
        return lista;
    }

    @Override
    public String koloneZaInsert() {
        return " (NazivPosla, OpisPosla, DatumKreiranjaPosla, MenadzerID) ";
    }

    @Override
    public String uslov() {
        return " PosaoID = " + posaoID;
    }

    @Override
    public String vrednostiZaInsert() {
        return "'" + nazivPosla + "', '" + opisPosla + "', '" + new java.sql.Date(datumKreiranjaPosla.getTime()) + "', " +
                menadzer.getMenadzerID();
    }

    @Override
    public String vrednostiZaUpdate() {
        return "NazivPosla = '" + nazivPosla + "', OpisPosla = '" + opisPosla + "', DatumKreiranjaPosla = '" +
                new java.sql.Date(datumKreiranjaPosla.getTime()) + "', MenadzerID = " + menadzer.getMenadzerID();
    }

    @Override
    public String uslovZaSelect() {
        return "";
    }

    public Long getPosaoID() {
        return posaoID;
    }

    public void setPosaoID(Long posaoID) {
        this.posaoID = posaoID;
    }

    public String getNazivPosla() {
        return nazivPosla;
    }

    public void setNazivPosla(String nazivPosla) {
        this.nazivPosla = nazivPosla;
    }

    public String getOpisPosla() {
        return opisPosla;
    }

    public void setOpisPosla(String opisPosla) {
        this.opisPosla = opisPosla;
    }

    public Date getDatumKreiranjaPosla() {
        return datumKreiranjaPosla;
    }

    public void setDatumKreiranjaPosla(Date datumKreiranjaPosla) {
        this.datumKreiranjaPosla = datumKreiranjaPosla;
    }

    public Menadzer getMenadzer() {
        return menadzer;
    }

    public void setMenadzer(Menadzer menadzer) {
        this.menadzer = menadzer;
    }

    @Override
    public String toString() {
        return nazivPosla;
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null || getClass() != obj.getClass())
            return false;
        Posao other = (Posao) obj;
        return posaoID != null && posaoID.equals(other.getPosaoID());
    }

    @Override
    public int hashCode() {
        return posaoID != null ? posaoID.hashCode() : 0;
    }

}
