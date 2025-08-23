package altamirano.hernandez.app1_springboot_2025.controllers;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/horarioController")
public class HorarioController {

    @GetMapping("/horarioAtencion")
    public ResponseEntity<?> horarioAtencion() {
        Map<String, Object> json = new HashMap<>();
        json.put("status", true);
        json.put("message", "Aun se encuentra en horario de atencion");
        return ResponseEntity.status(HttpStatus.OK).body(json);
    }
}
