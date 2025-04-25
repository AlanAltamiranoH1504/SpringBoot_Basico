package altamirano.hernandez.app1_springboot_2025.repositories.repositoriesMongoDB;

import altamirano.hernandez.app1_springboot_2025.models.mongoDB.Categoria;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ICategoriaRepository extends MongoRepository<Categoria, String> {

}
