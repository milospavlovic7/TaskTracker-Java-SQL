package so.posao;

import db.DBBroker;
import domain.AbstractDomainObject;
import domain.Posao;
import so.AbstractSO;

public class SOGetPosao extends AbstractSO {
    
    private Posao posao;

    @Override
    protected void validate(AbstractDomainObject ado) throws Exception {
        if (!(ado instanceof Posao)) {
            throw new Exception("Prosleđeni objekat nije instanca klase Posao!");
        }
    }

    @Override
    protected void execute(AbstractDomainObject ado) throws Exception {
        posao = (Posao) DBBroker.getInstance().select(ado).get(0);
    }

    public Posao getPosao() {
        return posao;
    }
}
