package altamirano.hernandez.app1_springboot_2025.models.mongoDB;

import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Objects;

@Document(collection = "productos")
public class ProductoMongo {

    @Id
    private String id;
    @NotBlank(message = "El nombre del producto no puede estar vacio")
    @Size(min = 3, max = 90, message = "El largo del nombre del producto debe ser entre 3 y 90 caracteres")
    private String nombre;
    @NotBlank(message = "El slug del producto no puede estar vacio")
    @Size(min = 3, max = 90, message = "El largo del slug del producto debe ser entre 3 y 90 caracteres")
    private String slug;
    @NotBlank(message = "La descripcion del producto no puede estar vacia")
    @Size(min = 3, max = 90, message = "El largo de la descripcion del producto debe ser entre 3 y 90 caracteres")
    private String descripcion;
    @Positive(message = "El precio del producto debe ser mayor a 0")
    private double precio;
    @Size(max = 100, message = "El largo de la foto del producto debe ser de un maximo de 100 caracteres")
    private String foto;

    //Relacion embebida con Categoria
    private CategoriaMongo categoriaMongo;

    //Constructores
    public ProductoMongo(){}
    public ProductoMongo(String id) {
        this.id = id;
    }
    public ProductoMongo(String nombre, String slug, String descripcion, double precio, String foto){
        this.nombre = nombre;
        this.slug = slug;
        this.descripcion = descripcion;
        this.precio = precio;
        this.foto = foto;
    }
    public ProductoMongo(String id, String nombre, String slug, String descripcion, double precio, String foto){
        this.id = id;
        this.nombre = nombre;
        this.slug = slug;
        this.descripcion = descripcion;
        this.precio = precio;
        this.foto = foto;
    }

    //Metodos Get y Set
    public CategoriaMongo getCategoria() {
        return categoriaMongo;
    }
    public void setCategoria(CategoriaMongo categoriaMongo) {
        this.categoriaMongo = categoriaMongo;
    }
    public @NotBlank(message = "La descripcion del producto no puede estar vacia") @Size(min = 3, max = 90, message = "El largo de la descripcion del producto debe ser entre 3 y 90 caracteres") String getDescripcion() {
        return descripcion;
    }
    public void setDescripcion(@NotBlank(message = "La descripcion del producto no puede estar vacia") @Size(min = 3, max = 90, message = "El largo de la descripcion del producto debe ser entre 3 y 90 caracteres") String descripcion) {
        this.descripcion = descripcion;
    }
    public @Size(max = 100, message = "El largo de la foto del producto debe ser de un maximo de 100 caracteres") String getFoto() {
        return foto;
    }
    public void setFoto(@Size(max = 100, message = "El largo de la foto del producto debe ser de un maximo de 100 caracteres") String foto) {
        this.foto = foto;
    }
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public @NotBlank(message = "El nombre del producto no puede estar vacio") @Size(min = 3, max = 90, message = "El largo del nombre del producto debe ser entre 3 y 90 caracteres") String getNombre() {
        return nombre;
    }
    public void setNombre(@NotBlank(message = "El nombre del producto no puede estar vacio") @Size(min = 3, max = 90, message = "El largo del nombre del producto debe ser entre 3 y 90 caracteres") String nombre) {
        this.nombre = nombre;
    }
    @Positive(message = "El precio del producto debe ser mayor a 0")
    public double getPrecio() {
        return precio;
    }
    public void setPrecio(@Positive(message = "El precio del producto debe ser mayor a 0") double precio) {
        this.precio = precio;
    }
    public @NotBlank(message = "El slug del producto no puede estar vacio") @Size(min = 3, max = 90, message = "El largo del slug del producto debe ser entre 3 y 90 caracteres") String getSlug() {
        return slug;
    }
    public void setSlug(@NotBlank(message = "El slug del producto no puede estar vacio") @Size(min = 3, max = 90, message = "El largo del slug del producto debe ser entre 3 y 90 caracteres") String slug) {
        this.slug = slug;
    }

    //Metodo toString
    @Override
    public String toString() {
        return "Producto{" +
                "categoria=" + categoriaMongo +
                ", id='" + id + '\'' +
                ", nombre='" + nombre + '\'' +
                ", slug='" + slug + '\'' +
                ", descripcion='" + descripcion + '\'' +
                ", precio=" + precio +
                ", foto='" + foto + '\'' +
                '}';
    }

    //Metodo Equals y Hashcode
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProductoMongo producto = (ProductoMongo) o;
        return Double.compare(precio, producto.precio) == 0 && Objects.equals(id, producto.id) && Objects.equals(nombre, producto.nombre) && Objects.equals(slug, producto.slug) && Objects.equals(descripcion, producto.descripcion) && Objects.equals(foto, producto.foto) && Objects.equals(categoriaMongo, producto.categoriaMongo);
    }
    @Override
    public int hashCode() {
        return Objects.hash(id, nombre, slug, descripcion, precio, foto, categoriaMongo);
    }
}
