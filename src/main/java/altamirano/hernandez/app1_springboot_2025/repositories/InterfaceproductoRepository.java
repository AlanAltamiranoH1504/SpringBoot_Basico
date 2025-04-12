package altamirano.hernandez.app1_springboot_2025.repositories;

import altamirano.hernandez.app1_springboot_2025.models.Producto;
import org.springframework.data.repository.CrudRepository;

public interface InterfaceproductoRepository extends CrudRepository<Producto, Integer> {
}
