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
import so.AbstractSO;

/**
 *
 * @author USER
 */
public class SOUpdateZaposleni extends AbstractSO {

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
    }

    @Override
    protected void execute(AbstractDomainObject ado) throws Exception {
        DBBroker.getInstance().update(ado);
    }
}
