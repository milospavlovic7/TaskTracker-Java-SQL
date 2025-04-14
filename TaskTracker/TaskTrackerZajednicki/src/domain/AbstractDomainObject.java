package domain;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Apstraktna klasa koja predstavlja osnovu za sve domenske objekte u sistemu TaskTracker.
 * Obezbeđuje metode za generisanje SQL upita i manipulaciju podacima iz baze.
 */
public abstract class AbstractDomainObject implements Serializable {

    public abstract String nazivTabele();
    public abstract String alijas();
    public abstract String join();
    public abstract ArrayList<AbstractDomainObject> vratiListu(ResultSet rs) throws SQLException;
    public abstract String koloneZaInsert();
    public abstract String uslov();
    public abstract String vrednostiZaInsert();
    public abstract String vrednostiZaUpdate();
    public abstract String uslovZaSelect();
    
}