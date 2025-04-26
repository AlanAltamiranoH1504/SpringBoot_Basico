package altamirano.hernandez.app1_springboot_2025.repositories.repositoriesMongoDB;

import altamirano.hernandez.app1_springboot_2025.models.mongoDB.CategoriaMongo;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ICategoriaRepositoryMongo extends MongoRepository<CategoriaMongo, String> {

}
