package altamirano.hernandez.app1_springboot_2025.controllers;

import altamirano.hernandez.app1_springboot_2025.models.Categoria;
import altamirano.hernandez.app1_springboot_2025.models.DTO.ProductoDTO;
import altamirano.hernandez.app1_springboot_2025.models.Producto;
import altamirano.hernandez.app1_springboot_2025.services.Categorias.ICategoriaService;
import altamirano.hernandez.app1_springboot_2025.services.Productos.InterfaceProductoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/productos")
public class ProductoController {
    @Autowired
    private InterfaceProductoService productoService;
    @Autowired
    private ICategoriaService categoriaService;

    //Variables globales de ambiente
    @Value("${datos.nombre}")
    String nombre;
    @Value("${datos.apellidoPaterno}")
    String apellidoPaterno;
    @Value("${datos.apellidoMaterno}")
    String apellidoMaterno;

    //Inyectamos el objeto Enviroment
    @Autowired
    Environment env;

    @GetMapping("/prueba")
    Map<String, Object> pruebas() {
        Map<String, Object> json = new HashMap<>();
        json.put("test", "Funcionando el controlador de producto");
        return json;
    }

    @GetMapping("/saludo")
    public Map<String, Object> saludo() {
        Map<String, Object> json = new HashMap<>();
        json.put("saludo", "Saludo desde la ruta /saludo");
        return json;
    }

    //Metodo con PathVariable obligatorio
    @GetMapping("/parametros/{id}/{nombre}/{aP}/{aM}")
    public Map<String, Object> pathVariable(@PathVariable int id, @PathVariable String nombre, @PathVariable String aP, @PathVariable String aM) {
        Map<String, Object> json = new HashMap<>();
        json.put("Los datos que se enviaron en el pathVariable", "son los siguientes");
        json.put("id", id);
        json.put("nombre", nombre);
        json.put("Apellido Paterno", aP);
        json.put("Apellido Materno", aM);
        return json;
    }

    //Metodo con RequestParam
    @GetMapping("/requestParams")
    public Map<String, Object> requestParams(@RequestParam int id, @RequestParam String ciudad) {
        Map<String, Object> json = new HashMap<>();
        json.put("id", id);
        json.put("ciudad", ciudad);
        return json;
    }

    //Inyeccion de valores desde el archivo de ambiente
    @GetMapping("/enviroment")
    public Map<String, Object> enviroment() {
        Map<String, Object> json = new HashMap<>();
        json.put("Valores de variable de: ", "ambiente");
        json.put("nombre", nombre);
        json.put("apellidoPaterno", apellidoMaterno);
        json.put("apellidoMaterno", apellidoMaterno);
        return json;
    }

    //Inyeccion de variables a traves de objeto de tipo Enviroment
    @GetMapping("/objetoEnviroment")
    public Map<String, Object> objetoEnviroment() {
        Map<String, Object> json = new HashMap<>();
        String email = env.getProperty("datos.email");
        json.put("email", email);
        return json;
    }

    //Prueba de retorno de vista
    @GetMapping("/home")
    public String home() {
        return "producto/home";
    }

    //Listado de productos
    @GetMapping("/list")
    public Map<String, Object> list() {
        Map<String, Object> json = new HashMap<>();
        try {
            json.put("productos", productoService.findAll());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return json;
    }

    //Save producto en la base de datos
    @PostMapping("/save")
    public Map<String, Object> save(@Valid @RequestBody ProductoDTO productoDTO, BindingResult bindingResult) {
        Map<String, Object> json = new HashMap<>();
        if (bindingResult.hasErrors()) {
            Map<String, Object> errors = new HashMap<>();
            bindingResult.getFieldErrors().forEach(error -> {
                errors.put(error.getField(), error.getDefaultMessage());
            });
            json.put("errors", errors);
        } else {
            Categoria categoriaDB = categoriaService.findById(productoDTO.getCategoriaId());
            if (categoriaDB == null){
                json.put("code", "400");
                json.put("message", "Categoria de productos no encontrada");
            }
            Producto productoGuardar = new Producto(productoDTO.getNombre(), productoDTO.getSlug(), productoDTO.getDescripcion(), productoDTO.getPrecio(), productoDTO.getFoto());
            productoGuardar.setCategoria(categoriaDB);
            productoService.save(productoGuardar);
            json.put("code", "200");
            json.put("message", "Producto agregado correctamente");
        }
        return json;
    }

    //Elimina un producto
    @PostMapping("/delete")
    public Map<String, Object> delete(@RequestBody Producto producto) {
        Map<String, Object> json = new HashMap<>();
        try {
            productoService.deleteById(producto.getId());
            json.put("code", "200");
            json.put("msg", "Producto eliminado correctamente");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return json;
    }

    //Busca el producto a editar
    @PostMapping("/findById")
    public Map<String, Object> findById(@RequestBody Producto producto) {
        Map<String, Object> json = new HashMap<>();
        Producto productoFind = productoService.findyById(producto.getId());
        if (productoFind != null) {
            json.put("code", "200");
            json.put("producto", productoFind);
        } else {
            json.put("code", "404");
            json.put("producto", producto);
            json.put("status", "Recurso no encontrado");
        }
        return json;
    }

    //Actualiza el producto en la base de datos
    @PostMapping("/update")
    public Map<String, Object> update(@Valid @RequestBody Producto producto, BindingResult bindingResult) {
        Map<String, Object> json = new HashMap<>();
        if (bindingResult.hasErrors()) {
            Map<String, Object> errors = new HashMap<>();
            bindingResult.getFieldErrors().forEach(error -> {
                errors.put(error.getField(), error.getDefaultMessage());
                json.put("errors", errors);
                json.put("producto", producto);
            });
        } else {
            productoService.update(producto.getId(), producto);
            json.put("code", "200");
            json.put("status", "Producto actualizado correctamente");
        }
        return json;
    }

    //Upload de Archivos
    @PostMapping("/upload-archivos")
    public Map<String, Object> upload_archivos(@RequestParam("archivo") MultipartFile archivo) {
        Map<String, Object> json = new HashMap<>();
        if (archivo.isEmpty()) {
            json.put("code", "500");
            json.put("status", "El archivo esta vacio");
        }

        //Validamos el tipo de Archivo
        String tipoArchivo = archivo.getContentType();
        if (tipoArchivo == null || !tipoArchivo.equals("image/jpeg") && !tipoArchivo.equals("image/png")) {
            json.put("code", "500");
            json.put("status", "Formato de archivo incorrecto");
            return json;
        }

        try {
            //Fijamos la carpeta destino (creamos en caso de no existir)
            String carpetaDestino = Paths.get("statics/uploads").toAbsolutePath().toString();
            File directorio = new File(carpetaDestino);
            if (!directorio.exists()) {
                directorio.mkdirs();
            }

            //Guardamos el archivo
            String rutaArchivo = carpetaDestino + "/" + archivo.getOriginalFilename();
            archivo.transferTo(new File(rutaArchivo));
            json.put("code", "200");
            json.put("status", "Archivo guardado correctamente");
        } catch (Exception e) {
            json.put("code", "500");
            json.put("status", "Error al guardar el archivo: " + e.getMessage());
        }
        return json;
    }

    @GetMapping("/prueba-reponseEntity")
    public ResponseEntity<?> prueba_responseEntity() {
        Map<String, Object>json = new HashMap<>();
        json.put("mensaje", "prueba correcta");

        return ResponseEntity.status(200).body(json);
    }
}
