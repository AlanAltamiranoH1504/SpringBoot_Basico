package altamirano.hernandez.app1_springboot_2025.services.servicesMongoDB;

import altamirano.hernandez.app1_springboot_2025.models.mongoDB.CategoriaMongo;

import java.util.List;

public interface ICategoriaServiceMongo {
    public abstract List<CategoriaMongo> findAll();
    public abstract CategoriaMongo findById(String id);
    public abstract void save(CategoriaMongo categoria);
    public abstract void deleteById(String id);
}
