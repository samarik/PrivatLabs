package diplomdaoandbusiness.swingClient.frame;

import diplomdaoandbusiness.beans.entities.Product;
import java.math.BigDecimal;
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
    private final String[] columnNames = new String[]{"id","id производителя","id категории","Наименование","Цена","Описание"};
    private final Class[] columnClasses = new Class[]{Integer.class,Integer.class,Integer.class, String.class, BigDecimal.class, String.class};
    private final List<TableModelListener> listeners = new ArrayList<>();

      @Override
    public int getRowCount() {
        return list.size();
    }

    @Override
    public int getColumnCount() {
        return 6;
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
        } if(columnIndex==1){
            return p.getIdManuf();
        }if(columnIndex==2){
            return p.getIdCateg();
        } if(columnIndex==3){
            return p.getName();
        }if(columnIndex==4){
            return p.getPrice();
        }else{
            return p.getDescription();
        }
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        Product p = list.get(rowIndex);
        if(columnIndex==0){
            p.setId((Integer)aValue);
        } if(columnIndex==1){
            p.setIdManuf((Integer)aValue);
        }if(columnIndex==2){
            p.setIdCateg((Integer)aValue);
        }if(columnIndex==3){
            p.setName((String)aValue);
        }if(columnIndex==4){
            p.setPrice((BigDecimal)aValue);
        }else{
            p.setDescription((String)aValue);
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
