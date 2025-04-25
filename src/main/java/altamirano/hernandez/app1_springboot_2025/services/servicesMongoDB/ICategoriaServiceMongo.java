package altamirano.hernandez.app1_springboot_2025.services.servicesMongoDB;

import altamirano.hernandez.app1_springboot_2025.models.mongoDB.Categoria;

import java.util.List;

public interface ICategoriaServiceMongo {
    public abstract List<Categoria> findAll();
    public abstract Categoria findById(String id);
    public abstract void save(Categoria categoria);
    public abstract void deleteById(String id);
}
