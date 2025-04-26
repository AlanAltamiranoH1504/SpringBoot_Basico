package altamirano.hernandez.app1_springboot_2025.services.servicesMongoDB;

import altamirano.hernandez.app1_springboot_2025.models.mongoDB.ProductoMongo;

import java.util.List;

public interface IProductoServiceMongo {
    public abstract List<ProductoMongo> findAll();
    public abstract ProductoMongo findById(String id);
    public abstract void save(ProductoMongo producto);
    public abstract void deleteById(String id);
}
