package diplomdaoandbusiness.services;

import diplomdaoandbusiness.beans.entities.Category;
import java.util.List;

/**
 *
 * @author Olga
 */
public interface ICategoryServices {

    List<Category> list();

    List<Integer> listCategoryChild(int categoryId);

    void add(Category category);

    void modify(Category category);

}
