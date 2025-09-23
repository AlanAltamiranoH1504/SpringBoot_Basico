package altamirano.hernandez.app1_springboot_2025.services.cliente;

import altamirano.hernandez.app1_springboot_2025.models.Cliente;
import altamirano.hernandez.app1_springboot_2025.repositories.IClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ImplClienteService implements IClienteService{
    @Autowired
    private IClienteRepository iClienteRepository;

    @Override
    public List<Cliente> findAll() {
        return (List<Cliente>) iClienteRepository.findAll();
    }

    @Override
    public Cliente findById(int id) {
        return iClienteRepository.findById(id).get();
    }

    @Override
    public Cliente save(Cliente cliente) {
        return iClienteRepository.save(cliente);
    }

    @Override
    public void deleteById(int id) {
        iClienteRepository.deleteById(id);
    }
}
