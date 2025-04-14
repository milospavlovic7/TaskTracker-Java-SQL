package so.pripadnostZadatkaPoslu;

import db.DBBroker;
import domain.AbstractDomainObject;
import domain.PripadnostZadatkaPoslu;
import java.util.ArrayList;
import so.AbstractSO;

public class SOGetAllPripadnostZadatkaPoslu extends AbstractSO {

    private ArrayList<PripadnostZadatkaPoslu> listaPripadnostiZP;

    @Override
    protected void validate(AbstractDomainObject ado) throws Exception {
        if (!(ado instanceof PripadnostZadatkaPoslu)) {
            throw new Exception("Prosleđeni objekat nije instanca klase PripadnostZadatkaPoslu!");
        }
    }

    @Override
    protected void execute(AbstractDomainObject ado) throws Exception {
        // Metoda select može vratiti ArrayList<AbstractDomainObject>, pa vršimo cast
        listaPripadnostiZP = (ArrayList<PripadnostZadatkaPoslu>) (ArrayList<?>) DBBroker.getInstance().select(ado);
    }

    public ArrayList<PripadnostZadatkaPoslu> getListaPripadnostiZP() {
        return listaPripadnostiZP;
    }
}
