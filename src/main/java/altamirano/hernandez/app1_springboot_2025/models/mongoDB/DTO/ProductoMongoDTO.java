package altamirano.hernandez.app1_springboot_2025.models.mongoDB.DTO;

import altamirano.hernandez.app1_springboot_2025.models.mongoDB.ProductoMongo;
import jakarta.validation.constraints.NotBlank;

import java.util.Objects;

public class ProductoMongoDTO {

    @NotBlank(message = "El id del producto no puede ser vacio o null")
    private String id;

    //Constructores
    public ProductoMongoDTO(){

    }
    public ProductoMongoDTO(String id) {
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
        return "ProductoMongoDTO{" +
                "id='" + id + '\'' +
                '}';
    }

    //Equals y Hashcode
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProductoMongoDTO that = (ProductoMongoDTO) o;
        return Objects.equals(id, that.id);
    }
    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
