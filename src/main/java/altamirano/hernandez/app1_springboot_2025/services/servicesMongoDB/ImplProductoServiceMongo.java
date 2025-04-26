package altamirano.hernandez.app1_springboot_2025.services.servicesMongoDB;

import altamirano.hernandez.app1_springboot_2025.models.mongoDB.ProductoMongo;
import altamirano.hernandez.app1_springboot_2025.repositories.repositoriesMongoDB.IProductoRepositoryMongo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ImplProductoServiceMongo implements IProductoServiceMongo {

    //Inyectamos repositorio
    @Autowired
    IProductoRepositoryMongo productoRepositoryMongo;

    @Override
    public List<ProductoMongo> findAll() {
        List<ProductoMongo> productos = new ArrayList<>();
        productos = productoRepositoryMongo.findAll();
        return productos;
    }

    @Override
    public ProductoMongo findById(String id) {
        ProductoMongo productoFound = productoRepositoryMongo.findById(id).get();
        return productoFound;
    }

    @Override
    public void save(ProductoMongo producto) {
        productoRepositoryMongo.save(producto);
    }

    @Override
    public void deleteById(String id) {
        ProductoMongo productoFound = productoRepositoryMongo.findById(id).get();
        if (productoFound != null){
            productoRepositoryMongo.deleteById(productoFound.getId());
        }
    }
}
