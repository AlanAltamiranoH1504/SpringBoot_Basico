package altamirano.hernandez.app1_springboot_2025.controllers;

import altamirano.hernandez.app1_springboot_2025.models.Categoria;
import altamirano.hernandez.app1_springboot_2025.services.ImplCategoriaService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/categorias")
public class CategoriaController {

    //Inyectamos Service
    @Autowired
    ImplCategoriaService implCategoriaService;

    //Metodo prueba
    @GetMapping("/prueba")
    ResponseEntity<?> prueba() {
        Map<String, Object> json = new HashMap<>();
        json.put("code", "200");
        json.put("message", "Controlador de categorias funcionando");

        return ResponseEntity.status(200).body(json);
    }

    //Creacion de Categorias
    @PostMapping("/save")
    ResponseEntity<?> saveCategoria(@Valid @RequestBody Categoria categoria, BindingResult bindingResult){
        Map<String, Object> json = new HashMap<>();
        if (bindingResult.hasErrors()){
            Map<String, Object> errores = new HashMap<>();
            bindingResult.getFieldErrors().forEach(error -> {
                errores.put(error.getField(), error.getDefaultMessage());
            });
            return ResponseEntity.status(400).body(errores);
        }else{
            implCategoriaService.save(categoria);
            json.put("msg", "Categoria agregada con exito");
        }
        return ResponseEntity.status(200).body(json);
    }
}
