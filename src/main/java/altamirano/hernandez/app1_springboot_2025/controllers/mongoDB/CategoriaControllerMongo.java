package altamirano.hernandez.app1_springboot_2025.controllers.mongoDB;

import altamirano.hernandez.app1_springboot_2025.models.mongoDB.CategoriaMongo;
import altamirano.hernandez.app1_springboot_2025.models.mongoDB.DTO.CategoriaMongoDTO;
import altamirano.hernandez.app1_springboot_2025.services.servicesMongoDB.ICategoriaServiceMongo;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/categorias/mongo")
public class CategoriaControllerMongo {

    //Inyectamos el servicio
    @Autowired
    ICategoriaServiceMongo categoriaServiceMongo;

    @GetMapping("/list")
    ResponseEntity<?> listarCategorias() {
        Map<String, Object> json = new HashMap<>();
        try {
            json.put("categorias", categoriaServiceMongo.findAll());
            return ResponseEntity.status(200).body(json);
        }catch (Exception e) {
            return ResponseEntity.status(500).body(e.getMessage());
        }
    }

    @GetMapping("/prueba")
    ResponseEntity<?> prueba(HttpServletResponse response) {
        Map<String, Object> json = new HashMap<>();
        json.put("code", response.SC_OK);
        json.put("message", "Funcionado controlador de categorias para mongo");
        return ResponseEntity.status(200).body(json);
    }

    @PostMapping("/save")
    ResponseEntity<?> save(@Valid @RequestBody CategoriaMongo categoriaMongo, BindingResult bindingResult){
        Map<String, Object> json = new HashMap<>();
        if (bindingResult.hasErrors()){
            Map<String, Object> errores = new HashMap<>();
            bindingResult.getFieldErrors().forEach(error -> {
                errores.put(error.getField(), error.getDefaultMessage());
            });
            return ResponseEntity.badRequest().body(errores);
        }else {
            try {
                categoriaServiceMongo.save(categoriaMongo);
                json.put("message", "Categoria guardada correctamente!");
                return ResponseEntity.status(201).body(json);
            } catch (Exception e) {
                return ResponseEntity.status(500).body(e.getMessage());
            }
        }
    }

    @DeleteMapping("/delete")
    ResponseEntity<?> delete(@Valid @RequestBody CategoriaMongoDTO categoriaMongoDTO, BindingResult bindingResult){
        Map<String, Object> json = new HashMap<>();

        if (bindingResult.hasErrors()){
            Map<String, Object>errores = new HashMap<>();
            bindingResult.getFieldErrors().forEach(error -> {
                errores.put(error.getField(), error.getDefaultMessage());
            });
            return ResponseEntity.badRequest().body(errores);
        }else{
            try {
                categoriaServiceMongo.deleteById(categoriaMongoDTO.getId());
                json.put("message", "Categoria eliminada correctamente!");
                return ResponseEntity.status(200).body(json);
            } catch (Exception e) {
                return ResponseEntity.status(500).body(e.getMessage());
            }
        }
    }
}
