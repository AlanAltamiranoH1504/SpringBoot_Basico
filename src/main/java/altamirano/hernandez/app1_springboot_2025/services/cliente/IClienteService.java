package altamirano.hernandez.app1_springboot_2025.services.cliente;

import altamirano.hernandez.app1_springboot_2025.models.Cliente;

import java.util.List;
import java.util.Optional;

public interface IClienteService {
    public abstract List<Cliente> findAll();
    public abstract Optional<Cliente> findById(int id);
    public abstract Optional<Cliente> findByEmail(String email);
    public abstract Optional<Cliente> findByCellPhone(String cellphone);
    public abstract Cliente save(Cliente cliente);
    public abstract void deleteById(int id);
}
