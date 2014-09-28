package diplomdaoandbusiness.beans.base;

import java.io.Serializable;

/**
 * Базовый класс для ентити бинов. В дальнейшем бины можно будет однотипно
 * обрабатывать.
 *
 * @param <T> тип ключа
 */
public class CustomBean<T> implements Serializable {

    private T id;
    private String name;

    /**
     * Конструктор.
     */
    public CustomBean() {
    }

    /**
     * Конструктор.
     *
     * @param id ключ
     * @param name описание
     */
    public CustomBean(T id, String name) {
        this.id = id;
        this.name = name;
    }

    /**
     * Получить ключ.
     *
     * @return ключ
     */
    public T getId() {
        return id;
    }

    /**
     * Назначить ключ.
     *
     * @param id ключ
     */
    public void setId(T id) {
        this.id = id;
    }

    /**
     * Получить описание.
     *
     * @return описание
     */
    public String getName() {
        return name;
    }

    /**
     * Назначить описание.
     *
     * @param name описание
     */
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "CustomBean{" + "id=" + id + ", name=" + name + '}';
    }

}
