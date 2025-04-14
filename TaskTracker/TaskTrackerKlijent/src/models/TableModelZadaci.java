package models;

import controller.ClientController;
import domain.Zadatak;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.AbstractTableModel;

public class TableModelZadaci extends AbstractTableModel implements Runnable {

    private ArrayList<Zadatak> lista;
    private String[] kolone = {"Naziv zadatka", "Status"};
    private String parametar = "";

    // Postojeći konstruktor – učitava sve zadatke
    public TableModelZadaci() {
        try {
            lista = ClientController.getInstance().getAllZadaci();
        } catch (Exception ex) {
            Logger.getLogger(TableModelZadaci.class.getName()).log(Level.SEVERE, null, ex);
            lista = new ArrayList<>();
        }
    }
    
    // Novi konstruktor gde se lista prosleđuje
    public TableModelZadaci(ArrayList<Zadatak> lista) {
        this.lista = lista;
    }

    @Override
    public int getRowCount() {
        return lista.size();
    }

    @Override
    public int getColumnCount() {
        return kolone.length;
    }

    @Override
    public String getColumnName(int column) {
        return kolone[column];
    }

    @Override
    public Object getValueAt(int row, int column) {
        Zadatak z = lista.get(row);
        switch (column) {
            case 0:
                return z.getNazivZadatka();
            case 1:
                return z.getStatusZadatka();
            default:
                return null;
        }
    }

    public Zadatak getSelectedZadatak(int row) {
        return lista.get(row);
    }

    @Override
    public void run() {
        try {
            while (!Thread.currentThread().isInterrupted()) {
                Thread.sleep(10000);
                refreshTable();
            }
        } catch (InterruptedException ex) {
            Logger.getLogger(TableModelZadaci.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void setParametar(String parametar) {
        this.parametar = parametar;
        refreshTable();
    }

    public void refreshTable() {
        try {
            lista = ClientController.getInstance().getAllZadaci();
            if (!parametar.isEmpty()) {
                ArrayList<Zadatak> novaLista = new ArrayList<>();
                for (Zadatak z : lista) {
                    if (z.getNazivZadatka().toLowerCase().contains(parametar.toLowerCase())) {
                        novaLista.add(z);
                    }
                }
                lista = novaLista;
            }
            fireTableDataChanged();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
