package models;

import controller.ClientController;
import domain.Posao;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.AbstractTableModel;

public class TableModelPoslovi extends AbstractTableModel implements Runnable {

    private ArrayList<Posao> lista;
    private String[] kolone = {"Naziv posla"};
    private String parametar = "";

    public TableModelPoslovi() {
        try {
            lista = ClientController.getInstance().getAllPoslovi();
        } catch (Exception ex) {
            Logger.getLogger(TableModelPoslovi.class.getName()).log(Level.SEVERE, null, ex);
            lista = new ArrayList<>();
        }
    }
    
    // Novi konstruktor gde se lista prosleđuje
    public TableModelPoslovi(ArrayList<Posao> lista) {
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
        Posao p = lista.get(row);
        return p.getNazivPosla();
    }

    public Posao getSelectedPosao(int row) {
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
            Logger.getLogger(TableModelPoslovi.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void setParametar(String parametar) {
        this.parametar = parametar;
        refreshTable();
    }

    public void refreshTable() {
        try {
            lista = ClientController.getInstance().getAllPoslovi();
            if (!parametar.isEmpty()) {
                ArrayList<Posao> novaLista = new ArrayList<>();
                for (Posao p : lista) {
                    if (p.getNazivPosla().toLowerCase().contains(parametar.toLowerCase())) {
                        novaLista.add(p);
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
