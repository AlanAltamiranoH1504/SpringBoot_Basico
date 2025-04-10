package altamirano.hernandez.app1_springboot_2025.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/views")
public class templatesController {

    @GetMapping("/home")
    public String home(Model model) {
        model.addAttribute("title", "Home Page");
        return "views/home";
    }

    @GetMapping("/atributos")
    public String atributos(Model model) {
        int num1 = 15;
        int num2 = 3;
        int cifra = 12345;
        Date fecha = new Date();
        List<String> paises = new ArrayList<>();
        paises.add("Mexico");
        paises.add("Alemania");
        paises.add("Rusia");

        model.addAttribute("title", "Atributos");
        model.addAttribute("num1", num1);
        model.addAttribute("num2", num2);
        model.addAttribute("cifra", cifra);
        model.addAttribute("fecha", fecha);
        model.addAttribute("paises", paises);
        return "views/atributos";
    }

    @GetMapping("/estaticos")
    public String estaticos(Model model){
        model.addAttribute("title", "Estaticos");
        return "views/estaticos";
    }
}
