package so.posao;

import db.DBBroker;
import domain.AbstractDomainObject;
import domain.Posao;
import so.AbstractSO;

public class SOUpdatePosao extends AbstractSO {

    @Override
    protected void validate(AbstractDomainObject ado) throws Exception {
        if (!(ado instanceof Posao)) {
            throw new Exception("Prosleđeni objekat nije instanca klase Posao!");
        }
    }

    @Override
    protected void execute(AbstractDomainObject ado) throws Exception {
        DBBroker.getInstance().update(ado);
    }
}
