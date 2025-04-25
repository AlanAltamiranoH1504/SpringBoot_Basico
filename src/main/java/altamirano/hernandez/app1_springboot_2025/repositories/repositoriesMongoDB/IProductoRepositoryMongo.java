package altamirano.hernandez.app1_springboot_2025.repositories.repositoriesMongoDB;

import altamirano.hernandez.app1_springboot_2025.models.mongoDB.Producto;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface IProductoRepositoryMongo extends MongoRepository<Producto, String> {
}
