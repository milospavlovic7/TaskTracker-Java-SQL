package so.zaposlenja;

import db.DBBroker;
import domain.AbstractDomainObject;
import domain.Zaposlenje;
import so.AbstractSO;

public class SOAddZaposlenje extends AbstractSO {

    @Override
    protected void validate(AbstractDomainObject ado) throws Exception {
        if (!(ado instanceof Zaposlenje)) {
            throw new Exception("Prosleđeni objekat nije instanca klase Zaposlenje!");
        }
    }

    @Override
    protected void execute(AbstractDomainObject ado) throws Exception {
        DBBroker.getInstance().insert(ado);
    }
}
