package so.zaposleni;

import db.DBBroker;
import domain.AbstractDomainObject;
import domain.Zaposleni;
import java.util.ArrayList;
import so.AbstractSO;

/**
 * SOGetAllZaposleni - sistemska operacija za dobijanje svih zaposlenih.
 */
public class SOGetAllZaposleni extends AbstractSO {
    
    private ArrayList<Zaposleni> listaZaposlenih;

    @Override
    protected void validate(AbstractDomainObject ado) throws Exception {
        // Validacija da objekat mora biti instanca klase Zaposleni
        if (!(ado instanceof Zaposleni)) {
            throw new Exception("Prosledjeni objekat nije instanca klase Zaposleni!");
        }
    }

    @Override
    protected void execute(AbstractDomainObject ado) throws Exception {
        // Dohvatanje liste zaposlenih iz baze
        listaZaposlenih = (ArrayList<Zaposleni>) (ArrayList<?>) DBBroker.getInstance().select(ado);
        
        // Ako nema zaposlenih, baciti izuzetak
        if (listaZaposlenih.isEmpty()) {
            throw new Exception("Nema zaposlenih u bazi!");
        }
    }

    // Metoda koja vraća listu zaposlenih
    public ArrayList<Zaposleni> getListaZaposlenih() {
        return listaZaposlenih;
    }
}
