package iset.dsi.sqllite;

public class Product {

    Integer Id;
    String Name;
    Integer Quantity;

    public Product()
    {

    }

    public Product(String name, Integer quantity) {

        Name = name;
        Quantity = quantity;
    }
    public Product(Integer id, String name, Integer quantity) {
        Id = id;
        Name = name;
        Quantity = quantity;
    }

    public Integer getQuantity() {
        return Quantity;
    }

    public void setQuantity(Integer quantity) {
        Quantity = quantity;
    }

    public Integer getId() {
        return Id;
    }

    public void setId(Integer id) {
        Id = id;
    }


    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    @Override
    public String toString() {
        return "Product{" +
                "Id=" + Id +
                ", Name='" + Name + '\'' +
                ", Quantity=" + Quantity +
                '}';
    }
}
