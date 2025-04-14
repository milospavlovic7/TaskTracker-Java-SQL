package so.posao;

import db.DBBroker;
import domain.AbstractDomainObject;
import domain.Posao;
import so.AbstractSO;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class SOAddPosao extends AbstractSO {

    @Override
    protected void validate(AbstractDomainObject ado) throws Exception {
        if (!(ado instanceof Posao)) {
            throw new Exception("Prosleđeni objekat nije instanca klase Posao!");
        }
    }

    @Override
    protected void execute(AbstractDomainObject ado) throws Exception {
        // Ubacujemo posao u bazu i dobijamo generisani ključ (ID)
        PreparedStatement ps = DBBroker.getInstance().insert(ado);
        ResultSet rs = ps.getGeneratedKeys();
        if (rs.next()) {
            Long generatedId = rs.getLong(1);
            ((Posao) ado).setPosaoID(generatedId);
        }
    }
}
