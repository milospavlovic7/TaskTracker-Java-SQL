/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package so.zaposleni;

import db.DBBroker;
import domain.AbstractDomainObject;
import domain.Zaposleni;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import so.AbstractSO;

/**
 *
 * @author USER
 */
public class SOAddZaposleni extends AbstractSO {

    @Override
    protected void validate(AbstractDomainObject ado) throws Exception {
        if (!(ado instanceof Zaposleni)) {
            throw new Exception("Prosledjeni objekat nije instanca klase Zaposleni!");
        }

        Zaposleni z = (Zaposleni) ado;

        if (z.getIme() == null || z.getIme().isEmpty()) {
            throw new Exception("Ime zaposlenog mora biti uneto!");
        }

        if (z.getPrezime() == null || z.getPrezime().isEmpty()) {
            throw new Exception("Prezime zaposlenog mora biti uneto!");
        }

        ArrayList<Zaposleni> zaposleni = (ArrayList<Zaposleni>) (ArrayList<?>) DBBroker.getInstance().select(ado);

        for (Zaposleni zaposlen : zaposleni) {
            if (zaposlen.getEmail().equals(z.getEmail())) {
                throw new Exception("Vec postoji zaposleni sa tim email-om!");
            }
        }
    }

    @Override
    protected void execute(AbstractDomainObject ado) throws Exception {
        // Ubacujemo Zaposleni u bazu i dobijamo generisani ključ (ID)
        PreparedStatement ps = DBBroker.getInstance().insert(ado);
        ResultSet rs = ps.getGeneratedKeys();
        if (rs.next()) {
            Long generatedId = rs.getLong(1);
            ((Zaposleni) ado).setZaposleniID(generatedId);
        }
    }
}
