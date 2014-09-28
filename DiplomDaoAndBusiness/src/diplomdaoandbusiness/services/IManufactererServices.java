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
public interface IManufactererServices {

    List<Manufacturer> list();

    void add(Manufacturer user);

    void delete(int manufacturerId);
}
