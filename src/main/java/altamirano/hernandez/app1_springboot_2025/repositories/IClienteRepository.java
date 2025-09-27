package altamirano.hernandez.app1_springboot_2025.repositories;

import altamirano.hernandez.app1_springboot_2025.models.Cliente;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface IClienteRepository extends CrudRepository<Cliente, Integer> {

    @Query("SELECT c FROM Cliente c WHERE c.email =:email")
    Optional<Cliente> findByEmail(String email);

    @Query("SELECT c FROM  Cliente c WHERE c.cellphone =:cellphone")
    Optional<Cliente> findByCellPhone(String cellphone);
}
