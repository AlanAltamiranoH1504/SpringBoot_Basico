package altamirano.hernandez.app1_springboot_2025.controllers;

import altamirano.hernandez.app1_springboot_2025.models.Cliente;
import altamirano.hernandez.app1_springboot_2025.models.DTO.CreateClienteDTO;
import altamirano.hernandez.app1_springboot_2025.services.cliente.IClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/clientes")
public class ClienteController {
    @Autowired
    private IClienteService iClienteService;

    @PostMapping("")
    public ResponseEntity<?> save_cliente(@Validated @RequestBody CreateClienteDTO createClienteDTO, BindingResult bindingResult) {
        Map<String, Object> json = new HashMap<>();
        try {
            if (bindingResult.hasErrors()) {
                Map<String, Object> errors = new HashMap<>();
                bindingResult.getFieldErrors().forEach(error -> {
                    errors.put(error.getField(), error.getDefaultMessage());
                });
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors);
            }

            Cliente cliente_to_save = new Cliente(createClienteDTO.getName(), createClienteDTO.getLongname(), createClienteDTO.getEmail(), createClienteDTO.getAddress(), createClienteDTO.getCellphone(), new Date(), new Date());
            var cliente = iClienteService.save(cliente_to_save);
            json.put("status", true);
            json.put("message", "Cliente creado correctamente");
            return ResponseEntity.status(HttpStatus.CREATED).body(json);
        }
        catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
