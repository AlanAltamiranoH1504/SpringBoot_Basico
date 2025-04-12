package altamirano.hernandez.app1_springboot_2025.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

import java.util.Objects;

@Entity
@Table(name = "productos")
public class Producto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotBlank(message = "El nombre del producto no puede estar vacio")
    @Size(min = 5, max = 90, message = "El nombre del producto debe ser mayor a 5 y menor o igual a 90 caracteres")
    private String nombre;
    @NotBlank(message = "La descripcion del producto no puede estar vacia")
    @Size(min = 5, max = 90, message = "La descripcion del producto debe ser mayor a 5 y menor o igual a 90 caracteres")
    private String descripcion;
    @Positive(message = "El precio debe ser mayor a $0.00")
    private String precio;

    public Producto(){

    }

    public Producto(int id){
        this.id = id;
    }

    public Producto(String nombre, String descripcion, String precio) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precio = precio;
    }

    //Metodos Get y Set
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getDescripcion() {
        return descripcion;
    }
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public String getPrecio() {
        return precio;
    }
    public void setPrecio(String precio) {
        this.precio = precio;
    }

    //Metodo toString
    @Override
    public String toString() {
        return "Producto{" +
                "descripcion='" + descripcion + '\'' +
                ", id=" + id +
                ", nombre='" + nombre + '\'' +
                ", precio='" + precio + '\'' +
                '}';
    }

    //Metodo Equals y Hashcode
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Producto producto = (Producto) o;
        return id == producto.id && Objects.equals(nombre, producto.nombre) && Objects.equals(descripcion, producto.descripcion) && Objects.equals(precio, producto.precio);
    }
    @Override
    public int hashCode() {
        return Objects.hash(id, nombre, descripcion, precio);
    }
}
