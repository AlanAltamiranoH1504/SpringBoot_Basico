package altamirano.hernandez.app1_springboot_2025.models;

import jakarta.persistence.*;

import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "clientes")
public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(length = 100, nullable = false)
    private String name;

    @Column(length = 255, nullable = false)
    private String longname;

    @Column(length = 60, unique = true, nullable = false)
    private String email;

    @Column(length = 255, nullable = false)
    private String address;

    @Column(length = 10, unique = true, nullable = false)
    private String cellphone;

    private Date created_at;
    private Date updated_at;

    public Cliente() {
    }

    public Cliente(String name, String longname, String email, String address, String cellphone, Date created_at, Date updated_at) {
        this.name = name;
        this.longname = longname;
        this.email = email;
        this.address = address;
        this.cellphone = cellphone;
        this.created_at = new Date();
        this.updated_at  = new Date();
    }

    public Cliente(int id, String name, String longname, String email, String address, String cellphone) {
        this.id = id;
        this.name = name;
        this.longname = longname;
        this.email = email;
        this.address = address;
        this.cellphone = cellphone;
    }

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

    public String getLongname() {
        return longname;
    }

    public void setLongname(String longname) {
        this.longname = longname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCellphone() {
        return cellphone;
    }

    public void setCellphone(String cellphone) {
        this.cellphone = cellphone;
    }

    @Override
    public String toString() {
        return "Cliente{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", longname='" + longname + '\'' +
                ", email='" + email + '\'' +
                ", address='" + address + '\'' +
                ", cellphone='" + cellphone + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Cliente cliente = (Cliente) o;
        return id == cliente.id && Objects.equals(name, cliente.name) && Objects.equals(longname, cliente.longname) && Objects.equals(email, cliente.email) && Objects.equals(address, cliente.address) && Objects.equals(cellphone, cliente.cellphone);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, longname, email, address, cellphone);
    }
}
