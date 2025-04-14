/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package so.zadatak;

import db.DBBroker;
import domain.AbstractDomainObject;
import domain.Zadatak;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import so.AbstractSO;

/**
 * Sistemska operacija za dodavanje novog zadatka u bazu.
 */
public class SOAddZadatak extends AbstractSO {

    @Override
    protected void validate(AbstractDomainObject ado) throws Exception {
        if (!(ado instanceof Zadatak)) {
            throw new Exception("Prosledjeni objekat nije instanca klase Zadatak!");
        }
    }

    @Override
    protected void execute(AbstractDomainObject ado) throws Exception {
        // Ubacujemo zadatak u bazu i dobijamo generisani ključ (ID)
        PreparedStatement ps = DBBroker.getInstance().insert(ado);
        ResultSet rs = ps.getGeneratedKeys();
        if (rs.next()) {
            Long generatedId = rs.getLong(1);
            ((Zadatak) ado).setZadatakID(generatedId);
        }
    }
}
