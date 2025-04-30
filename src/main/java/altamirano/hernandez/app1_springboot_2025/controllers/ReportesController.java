package altamirano.hernandez.app1_springboot_2025.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/reportes")
public class ReportesController {

    @GetMapping("/prueba")
    public ResponseEntity<?>prueba() {
        Map<String, Object> json = new HashMap<>();
        json.put("msg", "Funcionando controlador de reportes");
        return ResponseEntity.status(200).body(json);
    }
}
