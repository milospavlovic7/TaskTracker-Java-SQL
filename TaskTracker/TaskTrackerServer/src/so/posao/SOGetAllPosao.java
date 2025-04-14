package so.posao;

import db.DBBroker;
import domain.AbstractDomainObject;
import domain.Posao;
import java.util.ArrayList;
import so.AbstractSO;

public class SOGetAllPosao extends AbstractSO {
    
    private ArrayList<Posao> listaPoslova;

    @Override
    protected void validate(AbstractDomainObject ado) throws Exception {
        if (!(ado instanceof Posao)) {
            throw new Exception("Prosleđeni objekat nije instanca klase Posao!");
        }
    }

    @Override
    protected void execute(AbstractDomainObject ado) throws Exception {
        listaPoslova = (ArrayList<Posao>) (ArrayList<?>) DBBroker.getInstance().select(ado);
    }

    public ArrayList<Posao> getListaPoslova() {
        return listaPoslova;
    }
}
