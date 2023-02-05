package ba.unsa.etf.rpr.domain;

import java.util.Objects;

/**
 * list of all employees that can log in the app
 * @author Amna Hodzic
 */
public class Employees implements Idable{

    private int id;
    private String username;
    private String password;
    private String name;
    private boolean admin;

    public Employees(){

    }
    public Employees(int id, String name, String username, String password, boolean admin){
        this.id=id;
        this.name=name;
        this.username=username;
        this.password=password;
        this.admin=admin;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isAdmin() {
        return admin;
    }

    public void setAdmin(boolean admin) {
        this.admin = admin;
    }

    @Override
    public String toString() {
        return "Employees{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", name='" + name + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Employees)) return false;
        Employees employees = (Employees) o;
        return id == employees.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id,username,password,name,admin);
    }
}
