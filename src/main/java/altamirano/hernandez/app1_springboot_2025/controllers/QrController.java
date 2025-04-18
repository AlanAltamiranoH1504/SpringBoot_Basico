package altamirano.hernandez.app1_springboot_2025.controllers;

import altamirano.hernandez.app1_springboot_2025.models.Qr;
import altamirano.hernandez.app1_springboot_2025.services.QRs.QrService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/qrs")
public class QrController {
    //Inyectamos servicio de generacion de QR
    @Autowired
    QrService qrService;

    @GetMapping("/prueba")
    public ResponseEntity<?> prueba(){
        Map<String, Object> json = new HashMap<>();
        json.put("code", "200");
        json.put("message", "Controlador de QRs funcionando");

        return ResponseEntity.status(200).body(json);
    }

    @PostMapping("/generador-qrs")
    public ResponseEntity<?> generadorQRs(@Valid @RequestBody Qr qr, BindingResult bindingResult){
        Map<String, Object> json = new HashMap<>();
        if (bindingResult.hasErrors()){
            Map<String, Object> errores = new HashMap<>();
            bindingResult.getFieldErrors().forEach(error ->{
                errores.put(error.getField(), error.getDefaultMessage());
            });
            return ResponseEntity.status(400).body(errores);
        }else{
            try{
                //Fijamos carpeta destino
                String carpetaDestino = Paths.get("statics/qrs").toAbsolutePath().toString();
                File directorio = new File(carpetaDestino);
                if (!directorio.exists()){
                    directorio.mkdir();
                }

                //Guardaamos el QR
                String nombreArchivo = "qr_" + System.currentTimeMillis() + ".png";
                File archivoQr = new File(carpetaDestino, nombreArchivo);
                BufferedImage qrImagen = QrService.generatedQRCodeImage(qr.getText(), qr.getWidth(), qr.getHeight());
                ImageIO.write(qrImagen, "png", archivoQr);
                json.put("message", "Codigo QR generado correctamente");
            } catch (Exception e) {
                json.put("code", "400");
                json.put("message", e.getMessage());
            }
        }
        return ResponseEntity.status(200).body(json);
    }
}
