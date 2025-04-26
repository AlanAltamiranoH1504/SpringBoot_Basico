package altamirano.hernandez.app1_springboot_2025.controllers.mongoDB;

import altamirano.hernandez.app1_springboot_2025.models.mongoDB.CategoriaMongo;
import altamirano.hernandez.app1_springboot_2025.models.mongoDB.ProductoMongo;
import altamirano.hernandez.app1_springboot_2025.services.servicesMongoDB.ICategoriaServiceMongo;
import altamirano.hernandez.app1_springboot_2025.services.servicesMongoDB.IProductoServiceMongo;
import org.aspectj.lang.annotation.AfterThrowing;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/productos")
public class ProductoControllerMongo {

    @Autowired
    ICategoriaServiceMongo iCategoriaServiceMongo;
    @Autowired
    IProductoServiceMongo iProductoServiceMongo;

    @PostMapping("/save/{id}")
    ResponseEntity<?> save(@PathVariable String id, @RequestBody ProductoMongo productoMongo, BindingResult bindingResult) {
        Map<String, Object> json = new HashMap<>();
        if (bindingResult.hasErrors()) {
            Map<String, Object> errores = new HashMap<>();
            bindingResult.getFieldErrors().forEach(error -> {
                errores.put(error.getField(), error.getDefaultMessage());
            });
            return ResponseEntity.badRequest().body(errores);
        } else {
            try {
                CategoriaMongo categoriaFound = iCategoriaServiceMongo.findById(id);
                productoMongo.setCategoria(categoriaFound);
                iProductoServiceMongo.save(productoMongo);

                json.put("message", "Producto guardado correctamente");
                return ResponseEntity.ok().body(json);
            } catch (Exception e) {
                return ResponseEntity.status(500).body(e.getMessage());
            }
        }
    }
}
