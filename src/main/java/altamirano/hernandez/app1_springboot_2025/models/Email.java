package altamirano.hernandez.app1_springboot_2025.models;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.util.Objects;

public class Email {
    @NotBlank(message = "El distinatario del correo no puede ir vacio")
    @Size(min = 5, max = 50)
    public String to;
    @NotBlank(message = "El asunto del correo no puede ir vacio")
    @Size(min = 5, max = 100, message = "El asuento del correo debe ser de 5 a 100 caracteres")
    public String subject;
    @NotBlank(message = "Debes agregar un mensaje al correo")
    @Size(min = 10, max = 500, message = "El tamaño del mensaje es de 10 a 500 caracteres")
    public String text;

    //Constructores
    public Email(){
    }
    public Email(String to, String subject, String text){
        this.to = to;
        this.subject = subject;
        this.text = text;
    }

    //Metodos Get y Set
    public @NotBlank(message = "El asunto del correo no puede ir vacio") String getSubject() {
        return subject;
    }
    public void setSubject(@NotBlank(message = "El asunto del correo no puede ir vacio") String subject) {
        this.subject = subject;
    }
    public @NotBlank(message = "Debes agregar un mensaje al correo") @Size(min = 10, max = 500) String getText() {
        return text;
    }
    public void setText(@NotBlank(message = "Debes agregar un mensaje al correo") @Size(min = 10, max = 500) String text) {
        this.text = text;
    }
    public @NotBlank(message = "El distinatario del correo no puede ir vacio") String getTo() {
        return to;
    }
    public void setTo(@NotBlank(message = "El distinatario del correo no puede ir vacio") String to) {
        this.to = to;
    }

    //Metodo toString
    @Override
    public String toString() {
        return "Email{" +
                "subject='" + subject + '\'' +
                ", to='" + to + '\'' +
                ", text='" + text + '\'' +
                '}';
    }

    //Metodos equals y hashcode
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Email email = (Email) o;
        return Objects.equals(to, email.to) && Objects.equals(subject, email.subject) && Objects.equals(text, email.text);
    }
    @Override
    public int hashCode() {
        return Objects.hash(to, subject, text);
    }
}
