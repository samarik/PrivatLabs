package diplomdaoandbusiness.swingClient.frame;

import diplomdaoandbusiness.beans.entities.Manufacturer;
import java.util.ArrayList;
import java.util.List;
import javax.swing.event.TableModelEvent;
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

    @Override
    public int getRowCount() {
        return list.size();
    }

    @Override
    public int getColumnCount() {
        return 2;
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
        return false;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Manufacturer m = list.get(rowIndex);
        if (columnIndex == 0) {
            return m.getId();
        } else {
            return m.getName();
        }
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        Manufacturer m = list.get(rowIndex);
        if (columnIndex == 0) {
            m.setId((Integer) aValue);
        } else {
            m.setName((String) aValue);
        }
        fireModelChanged(null);
    }

    public void deleteManufacturer(int rowIndex) {
        list.remove(list.get(rowIndex));
        fireModelChanged(null);
    }

    public void addManufacturer(Manufacturer m) {
        list.add(m);
        fireModelChanged(null);
    }

    public void setManufacturer(int rowIndex, Manufacturer m) {
        System.out.println(m);
        list.set(rowIndex, m);
        fireModelChanged(null);
    }

    public void setManufacturerList(List<Manufacturer> list) {
        clearManufacturerList();
        this.list.addAll(list);
        fireModelChanged(null);
    }

    public void clearManufacturerList() {
        this.list.clear();
        fireModelChanged(null);
    }

    public Manufacturer getManufacturer(int rowIndex) {
        return list.get(rowIndex);
    }

    private void fireModelChanged(TableModelEvent e) {
        for (TableModelListener l : listeners) {
            l.tableChanged(e);
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
