package altamirano.hernandez.app1_springboot_2025.controllers.mongoDB;

import altamirano.hernandez.app1_springboot_2025.models.mongoDB.CategoriaMongo;
import altamirano.hernandez.app1_springboot_2025.models.mongoDB.DTO.ProductoMongoDTO;
import altamirano.hernandez.app1_springboot_2025.models.mongoDB.ProductoMongo;
import altamirano.hernandez.app1_springboot_2025.services.servicesMongoDB.ICategoriaServiceMongo;
import altamirano.hernandez.app1_springboot_2025.services.servicesMongoDB.IProductoServiceMongo;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/productos")
public class ProductoControllerMongo {

    @Autowired
    ICategoriaServiceMongo iCategoriaServiceMongo;
    @Autowired
    IProductoServiceMongo iProductoServiceMongo;

    @GetMapping("/listProductosMongo")
    ResponseEntity<?> listProductos() {
        Map<String, Object> json = new HashMap<>();
        List<ProductoMongo> productos = new ArrayList<>();
        productos = iProductoServiceMongo.findAll();
        return ResponseEntity.ok().body(productos);
    }

    @PostMapping("/save/{id}")
    ResponseEntity<?> save(@PathVariable String id, @Valid @RequestBody ProductoMongo productoMongo, BindingResult bindingResult) {
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

    @PutMapping("/updateProductoMongo/{id}")
    ResponseEntity<?> updateProductoMongo(@PathVariable String id, @Valid @RequestBody ProductoMongo productoMongo, BindingResult bindingResult){
        Map<String, Object> json = new HashMap<>();
        if (bindingResult.hasErrors()){
            Map<String, Object> errores = new HashMap<>();
            bindingResult.getFieldErrors().forEach(error -> {
                errores.put(error.getField(), error.getDefaultMessage());
            });
            return ResponseEntity.badRequest().body(errores);
        }else{
            try {
                ProductoMongo productoFound = iProductoServiceMongo.findById(id);
                if (productoFound != null){
                    CategoriaMongo categoriaProducto = productoFound.getCategoria();
                    productoFound.setNombre(productoMongo.getNombre());
                    productoFound.setSlug(productoMongo.getSlug());
                    productoFound.setDescripcion(productoMongo.getDescripcion());
                    productoFound.setPrecio(productoMongo.getPrecio());
                    productoFound.setCategoria(categoriaProducto);
                    iProductoServiceMongo.save(productoFound);
                    json.put("message", "Producto actualizado correctamente");
                    return ResponseEntity.ok().body(json);
                } else{
                    return ResponseEntity.status(404).body("Produco no encontrado");
                }
            } catch (Exception e){
                return ResponseEntity.status(500).body(e.getMessage());
            }
        }

    }

    @DeleteMapping("/deleteProductoMongo")
    ResponseEntity<?> deleteProductoMongo(@Valid @RequestBody ProductoMongoDTO productoMongoDTO, BindingResult bindingResult) {
        Map<String, Object> json = new HashMap<>();
        if (bindingResult.hasErrors()) {
            Map<String, Object> errores = new HashMap<>();
            bindingResult.getFieldErrors().forEach(error -> {
                errores.put(error.getField(), error.getDefaultMessage());
            });
            return ResponseEntity.badRequest().body(errores);
        } else {
            try {
                iProductoServiceMongo.deleteById(productoMongoDTO.getId());
                json.put("message", "Producto eliminado correctamente");
                return ResponseEntity.ok().body(json);
            } catch (Exception e) {
                Map<String, Object> errorServidor = new HashMap<>();
                errorServidor.put("error", e.getMessage());
                errorServidor.put("cause", e.getCause());
                return ResponseEntity.status(500).body(errorServidor);
            }
        }
    }
}
