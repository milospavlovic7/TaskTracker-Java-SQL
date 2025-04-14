package so.pripadnostZadatkaPoslu;

import db.DBBroker;
import domain.AbstractDomainObject;
import domain.PripadnostZadatkaPoslu;
import so.AbstractSO;

public class SODeletePripadnostZadatkaPoslu extends AbstractSO {

    @Override
    protected void validate(AbstractDomainObject ado) throws Exception {
        if (!(ado instanceof PripadnostZadatkaPoslu)) {
            throw new Exception("Prosleđeni objekat nije instanca klase PripadnostZadatkaPoslu!");
        }
    }

    @Override
    protected void execute(AbstractDomainObject ado) throws Exception {
        DBBroker.getInstance().delete(ado);
    }
}
