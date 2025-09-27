package altamirano.hernandez.app1_springboot_2025.controllers;

import altamirano.hernandez.app1_springboot_2025.models.Cliente;
import altamirano.hernandez.app1_springboot_2025.models.DTO.CreateClienteDTO;
import altamirano.hernandez.app1_springboot_2025.models.DTO.UpdateClienteDTO;
import altamirano.hernandez.app1_springboot_2025.services.cliente.IClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/clientes")
public class ClienteController {
    @Autowired
    private IClienteService iClienteService;

    @GetMapping("")
    public ResponseEntity<?> list_clientes() {
        Map<String, Object> json = new HashMap<>();
        try {
            List<Cliente> clientes = iClienteService.findAll();
            if (clientes.size() <= 0) {
                json.put("status", false);
                json.put("message", "No existen registros de clientes");
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(json);
            }
            json.put("clientes", clientes);
            return ResponseEntity.status(HttpStatus.OK).body(json);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> find_cliente(@PathVariable int id) {
        Map<String, Object> json = new HashMap<>();
        try {
            Optional<Cliente> cliente_to_show = iClienteService.findById(id);
            if (cliente_to_show.isEmpty()) {
                json.put("status", false);
                json.put("message", "Cliente con id " + id + " no encontrado");
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(json);
            }

            json.put("status", true);
            json.put("cliente", cliente_to_show);
            return ResponseEntity.status(HttpStatus.OK).body(json);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

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
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update_cliente(@Validated @RequestBody UpdateClienteDTO updateClienteDTO, BindingResult bindingResult, @PathVariable int id) {
        Map<String, Object> json = new HashMap<>();
        try {
            if (bindingResult.hasErrors()) {
                Map<String, Object> errors = new HashMap<>();
                bindingResult.getFieldErrors().forEach(error -> {
                    errors.put(error.getField(), error.getDefaultMessage());
                });
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors);
            }

            Optional<Cliente> cliente_to_update = iClienteService.findById(id);
            Optional<Cliente> email_in_use = iClienteService.findByEmail(updateClienteDTO.getEmail());
            Optional<Cliente> cellphone_in_use = iClienteService.findByCellPhone(updateClienteDTO.getCellphone());
            //Validacion de email
            if (email_in_use.isPresent() && email_in_use.get().getId() != id) {
                json.put("status", false);
                json.put("message", "El email ya se encuentra en uso");
                return ResponseEntity.status(HttpStatus.CONFLICT).body(json);
            }

            //Validacion de cellphone
            if (cellphone_in_use.isPresent() && cellphone_in_use.get().getId() != id) {
                json.put("status", false);
                json.put("message", "El cellphone ya se encuentra en uso");
                return ResponseEntity.status(HttpStatus.CONFLICT).body(json);
            }

            //Validacio de cliente por id
            if (cliente_to_update.isEmpty()) {
                json.put("status", false);
                json.put("message", "Cliente con id " + id + " no encontrado");
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(json);
            }

            cliente_to_update.get().setName(updateClienteDTO.getName());
            cliente_to_update.get().setLongname(updateClienteDTO.getLongname());
            cliente_to_update.get().setEmail(updateClienteDTO.getEmail());
            cliente_to_update.get().setAddress(updateClienteDTO.getAddress());
            cliente_to_update.get().setCellphone(updateClienteDTO.getCellphone());
            iClienteService.save(cliente_to_update.get());
            json.put("status", true);
            json.put("message", "Cliente actualizado correctamente");
            return ResponseEntity.status(HttpStatus.OK).body(json);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete_cliente(@PathVariable int id) {
        Map<String, Object> json = new HashMap<>();
        try {
            Optional<Cliente> cliente_to_delete = iClienteService.findById(id);
            if (cliente_to_delete.isEmpty()) {
                json.put("status", false);
                json.put("message", "Cliente con id " + id + " no encontrado");
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(json);
            }
            iClienteService.deleteById(id);
            json.put("status", true);
            json.put("message", "Cliente eliminado correctamente");
            return ResponseEntity.status(HttpStatus.OK).body(json);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
