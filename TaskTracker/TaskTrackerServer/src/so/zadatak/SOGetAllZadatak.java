package so.zadatak;

import db.DBBroker;
import domain.AbstractDomainObject;
import domain.Zadatak;
import java.util.ArrayList;
import so.AbstractSO;

public class SOGetAllZadatak extends AbstractSO {
    
    private ArrayList<Zadatak> listaZadataka;

    @Override
    protected void validate(AbstractDomainObject ado) throws Exception {
        if (!(ado instanceof Zadatak)) {
            throw new Exception("Prosleđeni objekat nije instanca klase Zadatak!");
        }
    }

    @Override
    protected void execute(AbstractDomainObject ado) throws Exception {
        listaZadataka = (ArrayList<Zadatak>) (ArrayList<?>) DBBroker.getInstance().select(ado);
    }

    public ArrayList<Zadatak> getListaZadataka() {
        return listaZadataka;
    }
}
