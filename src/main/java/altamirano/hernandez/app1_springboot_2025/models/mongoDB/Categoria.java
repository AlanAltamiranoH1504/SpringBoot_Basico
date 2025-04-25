package altamirano.hernandez.app1_springboot_2025.models.mongoDB;

import jakarta.persistence.Id;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Objects;
import java.util.concurrent.CancellationException;

@Document(collection = "categorias")
public class Categoria {
    @Id
    private String id;
    @NotBlank(message = "El nombre de la categoria no puede estar vacio")
    @Size(min = 3, max = 60, message = "El nombre de la categoria deber ser de entre 3 y 60 caracteres")
    private String nombre;
    @NotBlank(message = "El slug de la categoria no puede ser estar vacio")
    @Size(min = 3, max = 60, message = "El slug de la categoria debe ser de entre 3 y 60 caracteres")
    private String slug;

    //Constructores
    public Categoria() {}
    public Categoria(String id) {
        this.id = id;
    }
    public Categoria(String nombre, String slug){
        this.nombre = nombre;
        this.slug = slug;
    }
    public Categoria(String id, String nombre, String slug){
        this.id = id;
        this.nombre = nombre;
        this.slug = slug;
    }

    //Metodos GET y SET
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public @NotBlank(message = "El nombre de la categoria no puede estar vacio") @Size(min = 3, max = 60, message = "El nombre de la categoria deber ser de entre 3 y 60 caracteres") String getNombre() {
        return nombre;
    }
    public void setNombre(@NotBlank(message = "El nombre de la categoria no puede estar vacio") @Size(min = 3, max = 60, message = "El nombre de la categoria deber ser de entre 3 y 60 caracteres") String nombre) {
        this.nombre = nombre;
    }
    public @NotBlank(message = "El slug de la categoria no puede ser estar vacio") @Size(min = 3, max = 60, message = "El slug de la categoria debe ser de entre 3 y 60 caracteres") String getSlug() {
        return slug;
    }
    public void setSlug(@NotBlank(message = "El slug de la categoria no puede ser estar vacio") @Size(min = 3, max = 60, message = "El slug de la categoria debe ser de entre 3 y 60 caracteres") String slug) {
        this.slug = slug;
    }

    //Metodo toString
    @Override
    public String toString() {
        return "Categoria{" +
                "id='" + id + '\'' +
                ", nombre='" + nombre + '\'' +
                ", slug='" + slug + '\'' +
                '}';
    }

    //Metodo Equals y Hashcode
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Categoria categoria = (Categoria) o;
        return Objects.equals(id, categoria.id) && Objects.equals(nombre, categoria.nombre) && Objects.equals(slug, categoria.slug);
    }
    @Override
    public int hashCode() {
        return Objects.hash(id, nombre, slug);
    }
}
