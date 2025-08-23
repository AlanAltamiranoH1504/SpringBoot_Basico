package altamirano.hernandez.app1_springboot_2025.controllers;

import altamirano.hernandez.app1_springboot_2025.models.DTO.ErrorDTO;
import altamirano.hernandez.app1_springboot_2025.models.Formulario;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/formController")
public class FormController {

    @GetMapping("/form")
    public ResponseEntity<?> formGet() {
        Map<String, Object> json = new HashMap<>();
        try {
            json.put("status", true);
            json.put("message", "Funcionando metodo formGet");
            return ResponseEntity.status(HttpStatus.OK).body(json);
        } catch (Exception e) {
            ErrorDTO error = new ErrorDTO("Erro funcionamiento metodo formGET", e.getMessage(), LocalDateTime.now());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
        }
    }

    @PostMapping("/form")
    public ResponseEntity<?> formPost(
            @RequestParam String username,
            @RequestParam String email,
            @RequestParam String password
    ) {
        Map<String, Object> json = new HashMap<>();
        try {
            json.put("status", true);
            json.put("message", "Funcionando metodo formPost");
            json.put("username", username);
            json.put("email", email);
            json.put("password", password);

            return ResponseEntity.status(HttpStatus.OK).body(json);
        } catch (Exception e) {
            ErrorDTO error = new ErrorDTO("Error funcionamiento metodo formPOST", e.getMessage(), LocalDateTime.now());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
        }
    }

    @PostMapping("/form_with_model")
    public ResponseEntity<?> formPOSTWithModel(@Validated @RequestBody Formulario formulario, BindingResult bindingResult) {
        try {
            Map<String, Object> json = new HashMap<>();
            if (bindingResult.hasErrors()) {
                Map<String, Object> errores = new HashMap<>();
                bindingResult.getFieldErrors().forEach(error -> {
                    errores.put(error.getField(), error.getDefaultMessage());
                });
                return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(errores);
            }

            json.put("status", true);
            json.put("message", "Funcionando metodo formPOST");
            json.put("objectForm", formulario);
            return ResponseEntity.status(HttpStatus.OK).body(json);
        } catch (Exception e) {
            ErrorDTO errorDTO = new ErrorDTO("Error funcionamiento metodo formPOSTWithModel", e.getMessage(), LocalDateTime.now());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorDTO);
        }
    }
}
