package altamirano.hernandez.app1_springboot_2025.repositories;

import altamirano.hernandez.app1_springboot_2025.models.Producto;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface InterfaceproductoRepository extends CrudRepository<Producto, Integer> {

    @Query("SELECT p FROM Producto p WHERE p.categoria.nombre LIKE :nombreCategoria")
    List<Producto> productosFiltrados(@Param("nombreCategoria") String nombreCategoria);
}
