package altamirano.hernandez.app1_springboot_2025.models.mongoDB.DTO;

import jakarta.validation.constraints.NotBlank;

import java.util.Objects;

public class CategoriaMongoDTO {
    @NotBlank(message = "El id de la categoria no puede ser vacio")
    private String id;

    //Constrcutores de la clase
    public CategoriaMongoDTO(){
    }
    public CategoriaMongoDTO(String id){
        this.id = id;
    }

    //Metodos GET y SET
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }

    //toString
    @Override
    public String toString() {
        return "CategoriaMongoDTO{" +
                "id='" + id + '\'' +
                '}';
    }

    //Equals y Hashcode
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CategoriaMongoDTO that = (CategoriaMongoDTO) o;
        return Objects.equals(id, that.id);
    }
    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
