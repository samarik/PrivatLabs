package diplomdaoandbusiness.beans.entities;

import diplomdaoandbusiness.beans.base.CustomBean;
import java.awt.Image;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 *
 * @author Olga
 */
public class Product extends CustomBean<Integer> implements Serializable {

    private int idManuf;
    private int idCateg;
    private BigDecimal price;
    private String description;
    private Image image;
    private boolean flSkl;

    public int getIdManuf() {
        return idManuf;
    }

    public void setIdManuf(int idManuf) {
        this.idManuf = idManuf;
    }

    public int getIdCateg() {
        return idCateg;
    }

    public void setIdCateg(int idCateg) {
        this.idCateg = idCateg;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
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

    public boolean getFlSkl() {
        return flSkl;
    }

    public void setFlSkl(boolean flSkl) {
        this.flSkl = flSkl;
    }

    @Override
    public String toString() {
        return "Product{" + "idManuf=" + idManuf + "; " + "idCateg=" + idCateg + "; " + "price=" + price + "; " + "description=" + description + "; " + super.toString() + '}';
    }

}
