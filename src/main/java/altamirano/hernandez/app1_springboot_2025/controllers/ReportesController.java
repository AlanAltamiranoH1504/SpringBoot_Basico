package altamirano.hernandez.app1_springboot_2025.controllers;

import altamirano.hernandez.app1_springboot_2025.models.Producto;
import altamirano.hernandez.app1_springboot_2025.services.Productos.InterfaceProductoService;
import altamirano.hernandez.app1_springboot_2025.services.Reportes.PdfService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/reportes")
public class ReportesController {

    //Servicio de productos
    @Autowired
    InterfaceProductoService iProductoService;
    @Autowired
    PdfService pdfService;

    @GetMapping("/prueba")
    public ResponseEntity<?>prueba() {
        Map<String, Object> json = new HashMap<>();
        json.put("msg", "Funcionando controlador de reportes");
        return ResponseEntity.status(200).body(json);
    }

    @GetMapping("/pdf")
    public ResponseEntity<?> reportePDF(){
        List<Producto> productos = iProductoService.findAll();
        byte[] pdf = pdfService.createPdf("Reporte de Productos", productos);
        return ResponseEntity.status(200)
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=documento.pdf")
                .contentType(MediaType.APPLICATION_PDF)
                .body(pdf);
    }
}
