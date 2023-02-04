package ba.unsa.etf.rpr.domain;

import java.util.Objects;

/**
 * list of all products and their quantity on a single receipt
 * @author Amna Hodzic
 */
public class Receipts implements Idable{

    private int idR;
    private int idP;
    private int Quantity;
    private double LineTotal;

    private String name;
    private double unitPrice;

    public double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getIdR() {
        return idR;
    }

    public void setIdR(int idR) {
        this.idR = idR;
    }

    public int getQuantity() {
        return Quantity;
    }

    public void setQuantity(int quantity) {
        Quantity = quantity;
    }

    public int getIdP() {
        return idP;
    }

    public void setIdP(int idP) {
        this.idP = idP;
    }

    public double getLineTotal() {
        return LineTotal;
    }

    public void setLineTotal(double lineTotal) {
        LineTotal = lineTotal;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Receipts)) return false;
        Receipts receipts = (Receipts) o;
        return idR == receipts.idR && idP == receipts.idP && Quantity == receipts.Quantity && Double.compare(receipts.LineTotal, LineTotal) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(idR, idP, Quantity, LineTotal);
    }

    @Override
    public String toString() {
        return "Receipts{" +
                "idR=" + idR +
                ", idP=" + idP +
                ", Quantity=" + Quantity +
                ", LineTotal=" + LineTotal +
                '}';
    }

    @Override
    public void setId(int id) {
        idR=id;
    }

    @Override
    public int getId() {
        return idR;
    }
}
