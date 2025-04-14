package so.zaposleni;

import db.DBBroker;
import domain.AbstractDomainObject;
import domain.Zaposleni;
import so.AbstractSO;

/**
 *
 * @author USER
 */
public class SOGetZaposleni extends AbstractSO {

    private Zaposleni zaposleni;

    @Override
    protected void validate(AbstractDomainObject ado) throws Exception {
        if (!(ado instanceof Zaposleni)) {
            throw new Exception("Prosleđeni objekat nije instanca klase Zaposleni!");
        }
    }

    @Override
    protected void execute(AbstractDomainObject ado) throws Exception {
        Zaposleni zaposleniToGet = (Zaposleni)ado;
        
        if (zaposleniToGet.getZaposleniID() < Integer.MIN_VALUE || zaposleniToGet.getZaposleniID() > Integer.MAX_VALUE) {
            throw new Exception("ZaposleniID je van opsega int vrednosti!");
        }
        
        int zaposleniID = Math.toIntExact(zaposleniToGet.getZaposleniID()) - 1;
        zaposleni = (Zaposleni) DBBroker.getInstance().select(ado).get(zaposleniID); 
    }

    public Zaposleni getZaposleni() {
        return zaposleni; // Return the single Zaposleni object
    }
}
