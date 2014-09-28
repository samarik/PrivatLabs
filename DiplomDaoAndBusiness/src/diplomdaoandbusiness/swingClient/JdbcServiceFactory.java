/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package diplomdaoandbusiness.swingClient;

import diplomdaoandbusiness.business.CategoryJdbcComponent;
import diplomdaoandbusiness.business.ManufacturerJdbcComponent;
import diplomdaoandbusiness.business.ProductJdbcComponent;
import diplomdaoandbusiness.services.ICategoryServices;
import diplomdaoandbusiness.services.IManufacturerServices;
import diplomdaoandbusiness.services.IProductServices;
import diplomdaoandbusiness.util.ConnectionFactory;
import diplomdaoandbusiness.util.DaoConfig;
import org.apache.derby.jdbc.EmbeddedConnectionPoolDataSource;


public class JdbcServiceFactory implements IServiceFactory{
    
    private ICategoryServices categoryService;
    private IManufacturerServices manufacturerService;
    private IProductServices productService;

    public JdbcServiceFactory() {
        EmbeddedConnectionPoolDataSource ds = new EmbeddedConnectionPoolDataSource();
        //ds.setCreateDatabase("create");
        //ds.setDatabaseName("mydb0");
        DaoConfig.init(new ConnectionFactory(ds));
        categoryService = new CategoryJdbcComponent();
        manufacturerService = new ManufacturerJdbcComponent();
        productService = new ProductJdbcComponent();
    }
    
    

    @Override
    public ICategoryServices getCategoryService() {
        return categoryService;
    }

    @Override
    public IProductServices getProductService() {
        return productService;
    }
    
    @Override
    public IManufacturerServices getManufacturerService() {
        return manufacturerService;
    }

}
