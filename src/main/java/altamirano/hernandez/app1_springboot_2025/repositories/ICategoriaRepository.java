package altamirano.hernandez.app1_springboot_2025.repositories;

import altamirano.hernandez.app1_springboot_2025.models.Categoria;
import org.springframework.data.repository.CrudRepository;

public interface ICategoriaRepository extends CrudRepository<Categoria, Integer> {
}
