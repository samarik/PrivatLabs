/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package diplomdaoandbusiness.swingClient;

import diplomdaoandbusiness.beans.entities.Manufacturer;
import java.util.ArrayList;
import java.util.List;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;

/**
 *
 * @author Olga
 */
public class ManufactureTableModels implements TableModel {

    private final List<Manufacturer> list = new ArrayList<>();
    private final String[] columnNames = new String[]{"id", "Наименование"};
    private final Class[] columnClasses = new Class[]{Integer.class, String.class};
    private final List<TableModelListener> listeners = new ArrayList<>();

    public void setList(List<Manufacturer> list) {
        this.list.clear();
        this.list.addAll(list);
        for (TableModelListener l : listeners) {
            l.tableChanged(null);
        }
    }

    public Manufacturer getManufacturer(int index) {
        return list.get(index);
    }

    @Override
    public int getRowCount() {
        return list.size();
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public String getColumnName(int columnIndex) {
        return columnNames[columnIndex];
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        return columnClasses[columnIndex];
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return true;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Manufacturer manufacturer = list.get(rowIndex);
        if (columnIndex == 0) {
            return manufacturer.getId();
        } else {
            return manufacturer.getName();
        }

    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        Manufacturer manufacture = list.get(rowIndex);
        if (columnIndex == 0) {
            manufacture.setId((int) aValue);
        } else {
            manufacture.setName((String) aValue);
        }

    }

    @Override
    public void addTableModelListener(TableModelListener l) {
        listeners.add(l);
    }

    @Override
    public void removeTableModelListener(TableModelListener l) {
        listeners.remove(l);
    }

}
