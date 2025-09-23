package altamirano.hernandez.app1_springboot_2025.repositories;

import altamirano.hernandez.app1_springboot_2025.models.Cliente;
import org.springframework.data.repository.CrudRepository;

public interface IClienteRepository extends CrudRepository<Cliente, Integer> {
}
