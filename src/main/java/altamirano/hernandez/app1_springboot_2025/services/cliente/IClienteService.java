package altamirano.hernandez.app1_springboot_2025.services.cliente;

import altamirano.hernandez.app1_springboot_2025.models.Cliente;

import java.util.List;

public interface IClienteService {
    public abstract List<Cliente> findAll();
    public abstract Cliente findById(int id);
    public abstract Cliente save(Cliente cliente);
    public abstract void deleteById(int id);
}
