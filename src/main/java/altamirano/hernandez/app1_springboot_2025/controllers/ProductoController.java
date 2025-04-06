package altamirano.hernandez.app1_springboot_2025.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/productos")
public class ProductoController {

    @GetMapping("/prueba")
    Map<String, Object> pruebas(){
        Map<String, Object> json = new HashMap<>();
        json.put("test", "Funcionando el controlador de producto");
        return json;
    }
}
