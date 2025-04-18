package altamirano.hernandez.app1_springboot_2025.services.Productos;

import altamirano.hernandez.app1_springboot_2025.models.Producto;

import java.util.List;

public interface InterfaceProductoService {
    public abstract List<Producto> findAll();
    public abstract Producto findyById(int id);
    public abstract void save(Producto producto);
    public abstract void update(int id, Producto producto);
    public abstract void delete(Producto producto);
    public abstract void deleteById(int id);
}
