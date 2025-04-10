package altamirano.hernandez.app1_springboot_2025.controllers;

import altamirano.hernandez.app1_springboot_2025.models.Producto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/productos")
public class ProductoController {

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
    public String home(){
        return "producto/home";
    }

    //Save producto en la base de datos
    @PostMapping("/save")
    @ResponseBody
    public Map<String, Object> save(@RequestBody Producto producto){
        Map<String, Object> json = new HashMap<>();
        json.put("estado", "producto recbido");
        json.put("nombr", producto.getNombre());

        return json;
    }
}
