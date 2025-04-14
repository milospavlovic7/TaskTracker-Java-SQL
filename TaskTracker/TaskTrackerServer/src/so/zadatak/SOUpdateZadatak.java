package so.zadatak;

import db.DBBroker;
import domain.AbstractDomainObject;
import domain.Zadatak;
import so.AbstractSO;

public class SOUpdateZadatak extends AbstractSO {

    @Override
    protected void validate(AbstractDomainObject ado) throws Exception {
        if (!(ado instanceof Zadatak)) {
            throw new Exception("Prosleđeni objekat nije instanca klase Zadatak!");
        }
    }

    @Override
    protected void execute(AbstractDomainObject ado) throws Exception {
        DBBroker.getInstance().update(ado);
    }
}
