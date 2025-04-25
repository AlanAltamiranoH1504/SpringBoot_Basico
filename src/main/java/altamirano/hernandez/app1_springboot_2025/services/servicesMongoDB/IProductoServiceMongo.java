package altamirano.hernandez.app1_springboot_2025.services.servicesMongoDB;

import altamirano.hernandez.app1_springboot_2025.models.mongoDB.Producto;

import java.util.List;

public interface IProductoServiceMongo {
    public abstract List<Producto> findAll();
    public abstract Producto findById(String id);
    public abstract void save(Producto producto);
    public abstract void deleteById(String id);
}
