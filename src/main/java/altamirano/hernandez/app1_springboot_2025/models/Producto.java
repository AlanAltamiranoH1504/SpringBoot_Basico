package altamirano.hernandez.app1_springboot_2025.models;

import java.util.Objects;

public class Producto {
    private String nombre;
    private String descripcion;
    private String precio;

    public Producto(){

    }
    public Producto(String nombre, String descripcion, String precio) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precio = precio;
    }

    //Metodos Get y Set
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
        return Objects.equals(nombre, producto.nombre) && Objects.equals(descripcion, producto.descripcion) && Objects.equals(precio, producto.precio);
    }
    @Override
    public int hashCode() {
        return Objects.hash(nombre, descripcion, precio);
    }
}
