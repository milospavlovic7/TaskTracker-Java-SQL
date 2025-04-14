package so.menadzer;

import db.DBBroker;
import domain.AbstractDomainObject;
import domain.Menadzer;
import so.AbstractSO;

/**
 *
 * @author USER
 */
public class SOGetMenadzer extends AbstractSO {

    private Menadzer menadzer;

    @Override
    protected void validate(AbstractDomainObject ado) throws Exception {
        if (!(ado instanceof Menadzer)) {
            throw new Exception("Prosleđeni objekat nije instanca klase Menadzer!");
        }
    }

    @Override
    protected void execute(AbstractDomainObject ado) throws Exception {
        Menadzer menadzerToGet = (Menadzer)ado;
        
        if (menadzerToGet.getMenadzerID() < Integer.MIN_VALUE || menadzerToGet.getMenadzerID() > Integer.MAX_VALUE) {
            throw new Exception("MenadzerID je van opsega int vrednosti!");
        }
        
        int menadzerID = Math.toIntExact(menadzerToGet.getMenadzerID()) - 1;
        menadzer = (Menadzer) DBBroker.getInstance().select(ado).get(menadzerID); // Get the first Menadzer
    }

    public Menadzer getMenadzer() {       
        return menadzer; // Return the single Menadzer object
    }
}
