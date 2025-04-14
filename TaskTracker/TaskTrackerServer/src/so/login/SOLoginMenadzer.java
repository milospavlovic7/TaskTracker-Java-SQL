package so.login;

import db.DBBroker;
import domain.AbstractDomainObject;
import domain.Menadzer;
import java.util.ArrayList;
import so.AbstractSO;

public class SOLoginMenadzer extends AbstractSO {
    
    private Menadzer menadzer;

    @Override
    protected void validate(AbstractDomainObject ado) throws Exception {
        if (!(ado instanceof Menadzer)) {
            throw new Exception("Prosleđeni objekat nije instanca klase Menadzer!");
        }
    }

    @Override
    protected void execute(AbstractDomainObject ado) throws Exception {
        Menadzer m = (Menadzer) ado;
        
        ArrayList<Menadzer> menadzeri =
                (ArrayList<Menadzer>) (ArrayList<?>) DBBroker.getInstance().select(m);

        for (Menadzer men : menadzeri) {
            System.out.println("Proveravam menadžera: " + men.getIme() + " " + men.getPrezime() +
                               ", Email: " + men.getEmail() + ", Lozinka: " + men.getLozinka());
            if (men.getEmail().equals(m.getEmail()) && men.getLozinka().equals(m.getLozinka())) {
                this.menadzer = men;
                System.out.println("Menadžer pronađen!");
                return;
            }
        }

        throw new Exception("Ne postoji menadžer sa tim kredencijalima.");
    }

    public Menadzer getMenadzer() {
        return menadzer;
    }
}
