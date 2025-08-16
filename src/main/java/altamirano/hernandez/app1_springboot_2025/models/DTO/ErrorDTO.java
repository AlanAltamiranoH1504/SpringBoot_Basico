package altamirano.hernandez.app1_springboot_2025.models.DTO;

import java.time.LocalDateTime;

public class ErrorDTO {
    private String message;
    private String error;
    private LocalDateTime hora;

    public ErrorDTO(String message, String error, LocalDateTime hora) {
        this.message = message;
        this.error = error;
        this.hora = hora;
    }
}
