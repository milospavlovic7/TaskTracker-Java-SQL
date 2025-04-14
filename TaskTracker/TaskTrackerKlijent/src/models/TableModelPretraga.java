package models;

import domain.Posao;
import domain.Zadatak;
import domain.Zaposleni;
import java.util.List;
import javax.swing.table.AbstractTableModel;

public class TableModelPretraga extends AbstractTableModel {

    private final String[] columnNames = {"Naziv", "Klasa", "Status"};
    private final List<Object> lista;

    public TableModelPretraga(List<Object> lista) {
        this.lista = lista;
    }

    public List<Object> getLista() {
        return lista;
    }

    @Override
    public int getRowCount() {
        return lista.size();
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public String getColumnName(int column) {
        return columnNames[column];
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Object obj = lista.get(rowIndex);

        if (obj instanceof Zaposleni) {
            Zaposleni zaposleni = (Zaposleni) obj;
            if (columnIndex == 0) {
                return zaposleni.getIme() + " " + zaposleni.getPrezime();
            } else if (columnIndex == 1) {
                return "Zaposleni";
            } else {
                return ""; // Status nije relevantan za zaposlene
            }
        }

        if (obj instanceof Posao) {
            Posao posao = (Posao) obj;
            if (columnIndex == 0) {
                return posao.getNazivPosla();
            } else if (columnIndex == 1) {
                return "Posao";
            } else {
                return ""; // Status nije relevantan za poslove
            }
        }

        if (obj instanceof Zadatak) {
            Zadatak zadatak = (Zadatak) obj;
            if (columnIndex == 0) {
                return zadatak.getNazivZadatka();
            } else if (columnIndex == 1) {
                return "Zadatak";
            } else {
                return zadatak.getStatusZadatka().toString(); // Status ide u treću kolonu
            }
        }

        return null;
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return false; // Tabela nije editabilna
    }
}
