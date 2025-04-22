package altamirano.hernandez.app1_springboot_2025.models.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.util.Objects;

public class CategoriaDTO {

    //Atributos
    @NotBlank(message = "El nombre de la categoria no puede ser vacio")
    @Size(min = 3, max = 60, message = "El nombre ser de entre 3 y 60 caractres")
    private String nombre;

    //Constructores
    public CategoriaDTO(){

    }
    public CategoriaDTO(String nombre) {
        this.nombre = nombre;
    }

    //Get y Set
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    //ToString
    @Override
    public String toString() {
        return "CategoriaDTO{" +
                "nombre='" + nombre + '\'' +
                '}';
    }

    //Equals y Hashcode
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CategoriaDTO that = (CategoriaDTO) o;
        return Objects.equals(nombre, that.nombre);
    }
    @Override
    public int hashCode() {
        return Objects.hashCode(nombre);
    }
}
