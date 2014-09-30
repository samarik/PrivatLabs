/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package diplomdaoandbusiness.swingClient.frame;

import diplomdaoandbusiness.beans.entities.Category;
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
public class CategoryTableModels implements TableModel {

    private final List<Category> list = new ArrayList<>();
    private final String[] columnNames = new String[]{"id", "id родителя", "Наименование", "Описание"};
    private final Class[] columnClasses = new Class[]{Integer.class, Integer.class, String.class, String.class};
    private final List<TableModelListener> listeners = new ArrayList<>();

    @Override
    public int getRowCount() {
        return list.size();
    }

    @Override
    public int getColumnCount() {
        return 4;
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
        Category c = list.get(rowIndex);
        if (columnIndex == 0) {
            return c.getId();
        }
        if (columnIndex == 1) {
            return c.getIdParent();
        }
        if (columnIndex == 2) {
            return c.getName();
        } else {
            return c.getDescription();
        }
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        Category c = list.get(rowIndex);
        if (columnIndex == 0) {
            c.setId((Integer) aValue);
        }
        if (columnIndex == 1) {
            c.setIdParent((Integer) aValue);
        }
        if (columnIndex == 2) {
            c.setName((String) aValue);
        } else {
            c.setDescription((String) aValue);
        }
        fireModelChanged(null);
    }

    public void deleteCategory(int rowIndex) {
        list.remove(list.get(rowIndex));
        fireModelChanged(null);
    }

    public void addCategory(Category c) {
        list.add(c);
        fireModelChanged(null);
    }

    public void setCategory(int rowIndex, Category c) {
        list.set(rowIndex, c);
        fireModelChanged(null);
    }

    public void setCategoryList(List<Category> list) {
        clearCategoryList();
        this.list.addAll(list);
        fireModelChanged(null);
    }

    public void clearCategoryList() {
        this.list.clear();
        fireModelChanged(null);
    }

    public Category getCategory(int rowIndex) {
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
