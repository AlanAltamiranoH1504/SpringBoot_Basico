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
    @NotBlank(message = "El slug del producto no puede estar vacio")
    @Size(min = 3, max = 90, message = "El slug del producto debe ser de un tamaño entre 3 y 90 caracteres")
    private String slug;
    @NotBlank(message = "La descripcion del producto no puede estar vacia")
    @Size(min = 5, max = 90, message = "La descripcion del producto debe ser mayor a 5 y menor o igual a 90 caracteres")
    private String descripcion;
    @Positive(message = "El precio debe ser mayor a $0.00")
    private String precio;

    private String foto;

    //Asociacion con Categoria (Un producto tiene un Categoria)
    @OneToOne
    @JoinColumn(name = "categoria_id")
    Categoria categoria;

    public Producto(){

    }

    public Producto(int id){
        this.id = id;
    }

    public Producto(String nombre, String slug, String descripcion, String precio, String foto) {
        this.nombre = nombre;
        this.slug = slug;
        this.descripcion = descripcion;
        this.precio = precio;
        this.foto = foto;
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
    public Categoria getCategoria() {
        return categoria;
    }
    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }
    public String getFoto() {
        return foto;
    }
    public void setFoto(String foto) {
        this.foto = foto;
    }
    public @NotBlank(message = "El slug del producto no puede estar vacio") @Size(min = 3, max = 90, message = "El slug del producto debe ser de un tamaño entre 3 y 90 caracteres") String getSlug() {
        return slug;
    }
    public void setSlug(@NotBlank(message = "El slug del producto no puede estar vacio") @Size(min = 3, max = 90, message = "El slug del producto debe ser de un tamaño entre 3 y 90 caracteres") String slug) {
        this.slug = slug;
    }

    //Metodo toString
    @Override
    public String toString() {
        return "Producto{" +
                "categoria=" + categoria +
                ", id=" + id +
                ", nombre='" + nombre + '\'' +
                ", slug='" + slug + '\'' +
                ", descripcion='" + descripcion + '\'' +
                ", precio='" + precio + '\'' +
                ", foto='" + foto + '\'' +
                '}';
    }


    //Metodo Equals y Hashcode
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Producto producto = (Producto) o;
        return id == producto.id && Objects.equals(nombre, producto.nombre) && Objects.equals(slug, producto.slug) && Objects.equals(descripcion, producto.descripcion) && Objects.equals(precio, producto.precio) && Objects.equals(foto, producto.foto) && Objects.equals(categoria, producto.categoria);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nombre, slug, descripcion, precio, foto, categoria);
    }
}
