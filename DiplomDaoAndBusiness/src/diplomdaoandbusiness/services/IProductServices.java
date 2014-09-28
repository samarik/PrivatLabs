/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package diplomdaoandbusiness.services;

import diplomdaoandbusiness.beans.entities.Product;
import java.util.List;

/**
 *
 * @author Olga
 */
public interface IProductServices {

    List<Product> list();
    
    
    Product getInfo(int productId);

    void add(Product user);

    void delete(int productId);

    void modify(Product product);

}
