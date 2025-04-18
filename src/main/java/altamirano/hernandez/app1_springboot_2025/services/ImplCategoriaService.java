package altamirano.hernandez.app1_springboot_2025.services;

import altamirano.hernandez.app1_springboot_2025.models.Categoria;
import altamirano.hernandez.app1_springboot_2025.repositories.ICategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ImplCategoriaService implements ICategoriaService {
    @Autowired
    ICategoriaRepository iCategoriaRepository;

    @Override
    public List<Categoria> findAll() {
        List<Categoria> categorias = (List<Categoria>) iCategoriaRepository.findAll();
        return categorias;
    }

    @Override
    public void save(Categoria categoria) {
        iCategoriaRepository.save(categoria);
    }

    @Override
    public Categoria findById(int id) {
        Categoria categoria = iCategoriaRepository.findById(id).get();
        return categoria;
    }

    @Override
    public void deleteById(int id) {
        Categoria busqueda = iCategoriaRepository.findById(id).get();
        if (busqueda != null){
            iCategoriaRepository.deleteById(busqueda.getId());
        }else{
            System.out.println("No se encontro la categoria");
        }
    }
}
