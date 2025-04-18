package altamirano.hernandez.app1_springboot_2025.controllers;

import altamirano.hernandez.app1_springboot_2025.models.Email;
import altamirano.hernandez.app1_springboot_2025.services.Emails.EmailService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/mails")
public class EmailController {
    //Inyectamos servicio de emails
    @Autowired
    EmailService emailService;

    //Metodo prueba
    @GetMapping("/prueba")
    public ResponseEntity<?> prueba() {
        Map<String, Object> json = new HashMap<>();
        json.put("status", "200");
        json.put("message", "Controlador de emails funcionando");

        return ResponseEntity.status(200).body(json);
    }

    //Envio email
    @PostMapping("/send-email")
    public ResponseEntity<?> sendEmail(@Valid @RequestBody Email email, BindingResult bindingResult) {
        Map<String, Object> json = new HashMap<>();
        if (bindingResult.hasErrors()) {
            Map<String, Object> errors = new HashMap<>();
            bindingResult.getFieldErrors().forEach(error -> {
                errors.put(error.getField(), error.getDefaultMessage());
                json.put("errores", errors);
                json.put("message", "Bad request");
            });
            return ResponseEntity.status(400).body(json);
        } else {
            emailService.sendSimpleMail(email.getTo(), email.getSubject(), email.getText());
            json.put("message", "Success");
        }
        return ResponseEntity.status(200).body(json);
    }

}
