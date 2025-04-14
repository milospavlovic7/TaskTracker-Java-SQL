package models;

import controller.ClientController;
import domain.Zaposleni;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.AbstractTableModel;

public class TableModelZaposleni extends AbstractTableModel implements Runnable {

    private ArrayList<Zaposleni> lista;
    private String[] kolone = {"Ime i Prezime"};
    private String parametar = "";

    // Postojeći konstruktor za učitavanje svih zaposlenih
    public TableModelZaposleni() {
        try {
            lista = ClientController.getInstance().getAllZaposleni();
        } catch (Exception ex) {
            Logger.getLogger(TableModelZaposleni.class.getName()).log(Level.SEVERE, null, ex);
            lista = new ArrayList<>();
        }
    }
    
    // Novi konstruktor koji prihvata listu zaposlenih (filtriranu listu)
    public TableModelZaposleni(ArrayList<Zaposleni> lista) {
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
        Zaposleni z = lista.get(row);
        return z.getIme() + " " + z.getPrezime();
    }

    public Zaposleni getSelectedZaposleni(int row) {
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
            Logger.getLogger(TableModelZaposleni.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void setParametar(String parametar) {
        this.parametar = parametar;
        refreshTable();
    }

    public void refreshTable() {
        try {
            lista = ClientController.getInstance().getAllZaposleni();
            if (!parametar.isEmpty()) {
                ArrayList<Zaposleni> novaLista = new ArrayList<>();
                for (Zaposleni z : lista) {
                    String punoIme = z.getIme() + " " + z.getPrezime();
                    if (punoIme.toLowerCase().contains(parametar.toLowerCase())) {
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
