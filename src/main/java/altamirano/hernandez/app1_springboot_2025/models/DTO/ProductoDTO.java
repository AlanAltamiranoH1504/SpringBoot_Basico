package altamirano.hernandez.app1_springboot_2025.models.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

public class ProductoDTO {
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
    private int categoriaId;

    //Constructores
    public ProductoDTO() {

    }
    public ProductoDTO(int categoriaId, String descripcion, String foto, String nombre, String precio, String slug) {
        this.categoriaId = categoriaId;
        this.descripcion = descripcion;
        this.foto = foto;
        this.nombre = nombre;
        this.precio = precio;
        this.slug = slug;
    }

    //Get y Set
    public int getCategoriaId() {
        return categoriaId;
    }
    public void setCategoriaId(int categoriaId) {
        this.categoriaId = categoriaId;
    }
    public @NotBlank(message = "La descripcion del producto no puede estar vacia") @Size(min = 5, max = 90, message = "La descripcion del producto debe ser mayor a 5 y menor o igual a 90 caracteres") String getDescripcion() {
        return descripcion;
    }
    public void setDescripcion(@NotBlank(message = "La descripcion del producto no puede estar vacia") @Size(min = 5, max = 90, message = "La descripcion del producto debe ser mayor a 5 y menor o igual a 90 caracteres") String descripcion) {
        this.descripcion = descripcion;
    }
    public String getFoto() {
        return foto;
    }
    public void setFoto(String foto) {
        this.foto = foto;
    }
    public @NotBlank(message = "El nombre del producto no puede estar vacio") @Size(min = 5, max = 90, message = "El nombre del producto debe ser mayor a 5 y menor o igual a 90 caracteres") String getNombre() {
        return nombre;
    }
    public void setNombre(@NotBlank(message = "El nombre del producto no puede estar vacio") @Size(min = 5, max = 90, message = "El nombre del producto debe ser mayor a 5 y menor o igual a 90 caracteres") String nombre) {
        this.nombre = nombre;
    }
    public @Positive(message = "El precio debe ser mayor a $0.00") String getPrecio() {
        return precio;
    }
    public void setPrecio(@Positive(message = "El precio debe ser mayor a $0.00") String precio) {
        this.precio = precio;
    }
    public @NotBlank(message = "El slug del producto no puede estar vacio") @Size(min = 3, max = 90, message = "El slug del producto debe ser de un tamaño entre 3 y 90 caracteres") String getSlug() {
        return slug;
    }
    public void setSlug(@NotBlank(message = "El slug del producto no puede estar vacio") @Size(min = 3, max = 90, message = "El slug del producto debe ser de un tamaño entre 3 y 90 caracteres") String slug) {
        this.slug = slug;
    }
}
