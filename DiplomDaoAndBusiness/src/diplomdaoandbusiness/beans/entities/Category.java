package diplomdaoandbusiness.beans.entities;

import diplomdaoandbusiness.beans.base.CustomBean;
import java.awt.Image;
import java.io.Serializable;

/**
 *
 * @author Olga
 */
public class Category extends CustomBean<Integer> implements Serializable {

    private int idParent;
    private String description;
    private Image image;

    public int getIdParent() {
        return idParent;
    }

    public void setIdParent(int idParent) {
        this.idParent = idParent;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }
    
    @Override
    public String toString() {
        return "Category{" + "idParent=" + idParent + "; " + "description=" + description + "; " + super.toString() + '}';
    }

}
