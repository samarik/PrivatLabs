/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package diplomdaoandbusiness.swingClient.frame;

import diplomdaoandbusiness.beans.entities.Category;
import diplomdaoandbusiness.beans.entities.Product;
import java.util.ArrayList;
import java.util.List;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;

/**
 *
 * @author Olga
 */
class ProductTableModels implements TableModel {

    private List<Product> list = new ArrayList<>();
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
        Product p = list.get(rowIndex);
        if(columnIndex==0){
            return p.getId();
        } else{
            return p.getName();
        }
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        Product p = list.get(rowIndex);
        if(columnIndex==0){
            p.setId((Integer)aValue);
        } else{
            p.setName((String)aValue);
        }
        fireModelChanged(null);
    }
    
    public void deleteProduct(int rowIndex){
        list.remove(list.get(rowIndex));
        fireModelChanged(null);
    }
    
    public void addProduct(Product p){
        list.add(p);
        fireModelChanged(null);
    }
    
    public void setProduct(int rowIndex, Product p){
        list.set(rowIndex, p);
        fireModelChanged(null);
    }
    
    public void setManufacturerList(List<Product> list){
        clearProductList();
        this.list.addAll(list);
        fireModelChanged(null);
    }
    
    public void clearProductList(){
        this.list.clear();
        fireModelChanged(null);
    }
    
    public Product getProduct(int rowIndex){
        return list.get(rowIndex);
    }
    
    private void fireModelChanged(TableModelEvent e){
        for(TableModelListener l : listeners){
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

    public void setList(List<Product> list) {
        clearProductList();
        this.list.addAll(list);
        fireModelChanged(null);
    }
    
    

}
