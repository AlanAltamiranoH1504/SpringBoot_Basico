package altamirano.hernandez.app1_springboot_2025.services;

import altamirano.hernandez.app1_springboot_2025.models.Producto;
import altamirano.hernandez.app1_springboot_2025.repositories.InterfaceproductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ImplProductoService implements InterfaceProductoService {
    @Autowired
    InterfaceproductoRepository productoRepository;

    @Override
    public List<Producto> findAll() {
        List<Producto> productos = (List<Producto>) productoRepository.findAll();
        return productos;
    }

    @Override
    public Producto findyById(int id) {
        return null;
    }

    @Override
    public void save(Producto producto) {
        productoRepository.save(producto);
    }

    @Override
    public void update(int id, Producto producto) {

    }

    @Override
    public void delete(Producto producto) {

    }

    @Override
    public void deleteById(int id) {
        productoRepository.deleteById(id);
    }
}
