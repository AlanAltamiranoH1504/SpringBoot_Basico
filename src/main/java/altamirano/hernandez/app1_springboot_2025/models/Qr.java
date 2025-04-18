package altamirano.hernandez.app1_springboot_2025.models;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;

import java.util.Objects;

public class Qr {
    //Atributos
    @NotBlank(message = "El mensaje del QR no puede ser vacio")
    private String text;
    @Positive(message = "La anchura del QR debe ser mayor a 0")
    private int width;
    @Positive(message = "La altura del QR debe ser mayot a 0")
    private int height;

    //Constructores
    public Qr(){

    }
    public Qr(String text, int width, int height) {
        this.text = text;
        this.width = width;
        this.height = height;
    }

    //Metodos GET y SET
    @Positive(message = "La altura del QR debe ser mayot a 0")
    public int getHeight() {
        return height;
    }
    public void setHeight(@Positive(message = "La altura del QR debe ser mayot a 0") int height) {
        this.height = height;
    }
    public @NotBlank(message = "El mensaje del QR no puede ser vacio") String getText() {
        return text;
    }
    public void setText(@NotBlank(message = "El mensaje del QR no puede ser vacio") String text) {
        this.text = text;
    }
    @Positive(message = "La anchura del QR debe ser mayor a 0")
    public int getWidth() {
        return width;
    }
    public void setWidth(@Positive(message = "La anchura del QR debe ser mayor a 0") int width) {
        this.width = width;
    }

    //Metodo toString
    @Override
    public String toString() {
        return "Qr{" +
                "height=" + height +
                ", text='" + text + '\'' +
                ", width=" + width +
                '}';
    }

    //Metodo Equasl y Hashcode
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Qr qr = (Qr) o;
        return width == qr.width && height == qr.height && Objects.equals(text, qr.text);
    }
    @Override
    public int hashCode() {
        return Objects.hash(text, width, height);
    }
}
