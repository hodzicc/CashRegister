package ba.unsa.etf.rpr.domain;

import java.util.Objects;

public class Products implements Idable{
    private int id;
    private String name;
    private double price;
    private int leftInStock;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getLeftInStock() {
        return leftInStock;
    }

    public void setLeftInStock(int leftInStock) {
        this.leftInStock = leftInStock;
    }

    @Override
    public String toString() {
        return "Products{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", leftInStock=" + leftInStock +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Products)) return false;
        Products products = (Products) o;
        return id == products.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, price, leftInStock);
    }
}
