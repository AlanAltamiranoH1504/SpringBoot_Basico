package altamirano.hernandez.app1_springboot_2025.controllers.mongoDB;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/categorias/mongo")
public class CategoriaControllerMongo {

    @GetMapping("/prueba")
    ResponseEntity<?> prueba(HttpServletResponse response) {
        Map<String, Object> json = new HashMap<>();
        json.put("code", response.SC_OK);
        json.put("message", "Funcionado controlador de categorias para mongo");
        return ResponseEntity.status(200).body(json);
    }
}
