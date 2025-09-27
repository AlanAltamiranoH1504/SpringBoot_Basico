package altamirano.hernandez.app1_springboot_2025.services.cliente;

import altamirano.hernandez.app1_springboot_2025.models.Cliente;
import altamirano.hernandez.app1_springboot_2025.repositories.IClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ImplClienteService implements IClienteService{
    @Autowired
    private IClienteRepository iClienteRepository;

    @Override
    public List<Cliente> findAll() {
        return (List<Cliente>) iClienteRepository.findAll();
    }

    @Override
    public Optional<Cliente> findById(int id) {
        return iClienteRepository.findById(id);
    }

    @Override
    public Optional<Cliente> findByEmail(String email) {
        return iClienteRepository.findByEmail(email);
    }

    @Override
    public Optional<Cliente> findByCellPhone(String cellphone) {
        return iClienteRepository.findByCellPhone(cellphone);
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
