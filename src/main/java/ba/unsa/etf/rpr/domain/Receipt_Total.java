package ba.unsa.etf.rpr.domain;

import java.util.Date;
import java.util.Objects;

public class Receipt_Total {

    int id;
    double total;
    Date date;
    int Eid;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getEid() {
        return Eid;
    }

    public void setEid(int eid) {
        Eid = eid;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Receipt_Total)) return false;
        Receipt_Total that = (Receipt_Total) o;
        return id == that.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id,total,date,Eid);
    }

    @Override
    public String toString() {
        return "Receipt_Total{" +
                "id=" + id +
                ", total=" + total +
                ", date=" + date +
                ", Eid=" + Eid +
                '}';
    }
}
