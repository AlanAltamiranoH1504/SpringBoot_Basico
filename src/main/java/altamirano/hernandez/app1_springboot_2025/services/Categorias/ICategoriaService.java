package altamirano.hernandez.app1_springboot_2025.services.Categorias;

import altamirano.hernandez.app1_springboot_2025.models.Categoria;

import java.util.List;

public interface ICategoriaService {
    public abstract List<Categoria> findAll();
    public abstract void save(Categoria categoria);
    public abstract Categoria findById(int id);
    public abstract void deleteById(int id);
}
