package altamirano.hernandez.app1_springboot_2025.services.servicesMongoDB;

import altamirano.hernandez.app1_springboot_2025.models.mongoDB.Categoria;
import altamirano.hernandez.app1_springboot_2025.repositories.repositoriesMongoDB.ICategoriaRepositoryMongo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ImplCategoriaServiceMongo implements ICategoriaServiceMongo {

    //Inyectamos repositorio
    @Autowired
    ICategoriaRepositoryMongo icategoriaRepositoryMongo;

    @Override
    public List<Categoria> findAll() {
        List<Categoria> categorias = new ArrayList<>();
        categorias = icategoriaRepositoryMongo.findAll();
        return categorias;
    }

    @Override
    public Categoria findById(String id) {
        try {
            Categoria categoriaFound = icategoriaRepositoryMongo.findById(id).get();
            return categoriaFound;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void save(Categoria categoria) {
        icategoriaRepositoryMongo.save(categoria);
    }

    @Override
    public void deleteById(String id) {
        try{
            Categoria categoriaFound = icategoriaRepositoryMongo.findById(id).get();
            if (categoriaFound != null){
                icategoriaRepositoryMongo.deleteById(categoriaFound.getId());
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
