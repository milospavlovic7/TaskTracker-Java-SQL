package so.zaposlenja;

import db.DBBroker;
import domain.AbstractDomainObject;
import domain.Zaposlenje;
import java.util.ArrayList;
import so.AbstractSO;

public class SOGetAllZaposlenje extends AbstractSO {

    private ArrayList<Zaposlenje> listaZaposlenja;

    @Override
    protected void validate(AbstractDomainObject ado) throws Exception {
        if (!(ado instanceof Zaposlenje)) {
            throw new Exception("Prosleđeni objekat nije instanca klase Zaposlenje!");
        }
    }

    @Override
    protected void execute(AbstractDomainObject ado) throws Exception {
        listaZaposlenja = (ArrayList<Zaposlenje>) (ArrayList<?>) DBBroker.getInstance().select(ado);
    }

    public ArrayList<Zaposlenje> getListaZaposlenja() {
        return listaZaposlenja;
    }
}
