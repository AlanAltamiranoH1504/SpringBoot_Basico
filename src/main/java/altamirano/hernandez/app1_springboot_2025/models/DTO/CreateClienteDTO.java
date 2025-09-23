package altamirano.hernandez.app1_springboot_2025.models.DTO;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class CreateClienteDTO {
    @NotNull(message = "El nombre es obligatorio")
    @Size(min = 1, max = 100, message = "El nombre debe tener una longitud maxima de 100 caracteres")
    private String name;

    @NotNull(message = "El nombre es obligatorio")
    @Size(min = 1, max = 255, message = "Los apellidos deben tener una longitud maxima de 255 caracteres")
    private String longname;

    @NotNull(message = "El nombre es obligatorio")
    @Size(min = 1, max = 60, message = "El email debe tener una longitud maxima de 60 caracteres")
    @Email(message = "Formato de email no valido")
    private String email;

    @NotNull(message = "La direccion es obligatoria")
    @Size(min = 1, max = 255, message = "La longitud maxima de la direccion es 255 caracteres")
    private String address;

    @NotNull(message = "El numero de telefono es obligatorio")
    @Size(min = 8, max = 10, message = "El numero debe tener una longitud minima de 8 y maxima de 10")
    private String cellphone;

    public CreateClienteDTO(String name, String longname, String email, String address, String cellphone) {
        this.name = name;
        this.longname = longname;
        this.email = email;
        this.address = address;
        this.cellphone = cellphone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLongname() {
        return longname;
    }

    public void setLongname(String longname) {
        this.longname = longname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCellphone() {
        return cellphone;
    }

    public void setCellphone(String cellphone) {
        this.cellphone = cellphone;
    }
}
