package altamirano.hernandez.app1_springboot_2025.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.util.Objects;

@Entity
@Table(name = "categorias")
public class Categoria {
    //Atributos
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @NotBlank(message = "El nombre de la categoria no puede estar vacio")
    @Size(min = 3, max = 60, message = "El nombre de la categoria debe ser entre 3 y 60 caracteres")
    private String nombre;
    @NotBlank(message = "El slug de la categoria no puede estar vacio")
    @Size(min = 3, max = 60, message = "El slug de la categoria debe tener un tamaño de 3 a 60 caracteres")
    private String slug;

    //Constrcutores
    public Categoria(){

    }
    public Categoria(int id) {
        this.id = id;
    }
    public Categoria(int id, String  nombre, String slug){
        this.id = id;
        this.nombre =  nombre;
        this.slug = slug;
    }

    //Metodos Get y Set
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public @NotBlank(message = "El slug de la categoria no puede estar vacio") @Size(min = 3, max = 60, message = "El slug de la categoria debe tener un tamaño de 3 a 60 caracteres") String getSlug() {
        return slug;
    }
    public void setSlug(@NotBlank(message = "El slug de la categoria no puede estar vacio") @Size(min = 3, max = 60, message = "El slug de la categoria debe tener un tamaño de 3 a 60 caracteres") String slug) {
        this.slug = slug;
    }

    //Metodo toString
    @Override
    public String toString() {
        return "Categoria{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", slug='" + slug + '\'' +
                '}';
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Categoria categoria = (Categoria) o;
        return id == categoria.id && Objects.equals(nombre, categoria.nombre) && Objects.equals(slug, categoria.slug);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nombre, slug);
    }
}
