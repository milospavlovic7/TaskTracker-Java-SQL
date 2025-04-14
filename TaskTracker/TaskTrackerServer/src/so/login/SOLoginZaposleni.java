package so.login;

import db.DBBroker;
import domain.AbstractDomainObject;
import domain.Zaposleni;
import java.util.ArrayList;
import so.AbstractSO;

public class SOLoginZaposleni extends AbstractSO {
    
    private Zaposleni zaposleni;

    @Override
    protected void validate(AbstractDomainObject ado) throws Exception {
        if (!(ado instanceof Zaposleni)) {
            throw new Exception("Prosleđeni objekat nije instanca klase Zaposleni!");
        }
    }

    @Override
    protected void execute(AbstractDomainObject ado) throws Exception {
        Zaposleni z = (Zaposleni) ado;
        
        ArrayList<Zaposleni> zaposleniList =
                (ArrayList<Zaposleni>) (ArrayList<?>) DBBroker.getInstance().select(z);

        for (Zaposleni zap : zaposleniList) {
            if (zap.getEmail().equals(z.getEmail()) &&
                zap.getLozinka().equals(z.getLozinka())) {
                this.zaposleni = zap;
                return;
            }
        }

        throw new Exception("Ne postoji zaposleni sa tim kredencijalima.");
    }

    public Zaposleni getZaposleni() {
        return zaposleni;
    }
}
