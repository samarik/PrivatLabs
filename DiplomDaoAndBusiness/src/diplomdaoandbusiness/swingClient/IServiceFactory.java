/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package diplomdaoandbusiness.swingClient;

import diplomdaoandbusiness.services.IManufacturerServices;
import diplomdaoandbusiness.services.IProductServices;

public interface IServiceFactory {
    public IManufacturerServices getManufacturerService();
    public IProductServices getProductService();
}
