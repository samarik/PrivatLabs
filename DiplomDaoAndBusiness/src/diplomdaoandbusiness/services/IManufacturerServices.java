/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package diplomdaoandbusiness.services;

import diplomdaoandbusiness.beans.entities.Manufacturer;
import java.util.List;

/**
 *
 * @author Olga
 */
public interface IManufacturerServices {

    List<Manufacturer> list();

    void add(Manufacturer manufacturer);

    void delete(int manufacturerId);

    void modify(Manufacturer manufacturer);
}
