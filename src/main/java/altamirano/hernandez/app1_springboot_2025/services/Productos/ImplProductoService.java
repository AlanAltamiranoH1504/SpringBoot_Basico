package altamirano.hernandez.app1_springboot_2025.services.Productos;

import altamirano.hernandez.app1_springboot_2025.models.Producto;
import altamirano.hernandez.app1_springboot_2025.repositories.InterfaceproductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.client.RestTemplateAutoConfiguration;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ImplProductoService implements InterfaceProductoService {
    @Autowired
    InterfaceproductoRepository productoRepository;
    @Autowired
    private RestTemplateAutoConfiguration restTemplateAutoConfiguration;

    @Override
    public List<Producto> findAll() {
        List<Producto> productos = (List<Producto>) productoRepository.findAll();
        return productos;
    }

    @Override
    public Producto findyById(int id) {
        Producto producto = productoRepository.findById(id).get();
        return producto;
    }

    @Override
    public void save(Producto producto) {
        productoRepository.save(producto);
    }

    @Override
    public void update(int id, Producto producto) {
        Optional<Producto> productoActualizar = productoRepository.findById(id);
        productoActualizar.get().setNombre(producto.getNombre());
        productoActualizar.get().setDescripcion(producto.getDescripcion());
        productoActualizar.get().setPrecio(producto.getPrecio());
        productoRepository.save(productoActualizar.get());
    }

    @Override
    public void delete(Producto producto) {

    }

    @Override
    public void deleteById(int id) {
        productoRepository.deleteById(id);
    }

    @Override
    public List<Producto> productosListados(String nombre) {
        List<Producto> productoList = productoRepository.productosFiltrados("%" + nombre + "%");
        return productoList;
    }

}
