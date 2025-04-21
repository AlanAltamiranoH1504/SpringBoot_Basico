package altamirano.hernandez.app1_springboot_2025.controllers;

import altamirano.hernandez.app1_springboot_2025.models.Categoria;
import altamirano.hernandez.app1_springboot_2025.services.Categorias.ImplCategoriaService;
import jakarta.validation.Valid;
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

    //Listado de Categorias
    @GetMapping("/findAll")
    ResponseEntity<?> findAll(){
        Map<String, Object> json = new HashMap<>();
        try {
            json.put("categorias", implCategoriaService.findAll());
        } catch (Exception e) {
            json.put("Error", e.getMessage());
            json.put("stackTrace", e.getStackTrace());
        }
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

    @PostMapping("/delete")
    ResponseEntity<?> deleteCategoria( @RequestBody Categoria categoria){
        Map<String, Object> json = new HashMap<>();
        try {
            implCategoriaService.deleteById(categoria.getId());
            json.put("msg", "Categoria eliminada con exito");
        } catch (Exception e) {
            json.put("Error", e.getMessage());
        }
        return ResponseEntity.status(200).body(json);
    }
}
