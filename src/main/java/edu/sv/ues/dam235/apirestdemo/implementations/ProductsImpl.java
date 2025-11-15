package edu.sv.ues.dam235.apirestdemo.implementations;

import edu.sv.ues.dam235.apirestdemo.dtos.ProductsDTO;
import edu.sv.ues.dam235.apirestdemo.dtos.CrearProductoDTO;
import edu.sv.ues.dam235.apirestdemo.entities.Producto;
import edu.sv.ues.dam235.apirestdemo.repositories.ProductsRepository;
import edu.sv.ues.dam235.apirestdemo.services.ProductServices;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class ProductsImpl implements ProductServices {
    private final ProductsRepository productsRepository;

    private ProductsImpl(ProductsRepository productsRepository) {
        this.productsRepository = productsRepository;
    }

    @Override
    public List<ProductsDTO> getAllProducts() {
        List<ProductsDTO> result = new ArrayList<>();
        List<Producto> items = this.productsRepository.findAll();
        for (Producto item : items) {
            result.add(new ProductsDTO(item.getCode(), item.getName(),
                    item.isStatus()));
        }
        return result;
    }

    @Override
    public ProductsDTO crearProducto(CrearProductoDTO productoDTO) {
        try {
            Producto nuevoProducto = new Producto();
            nuevoProducto.setName(productoDTO.getName());
            nuevoProducto.setStatus(productoDTO.isStatus());

            Producto productoGuardado = this.productsRepository.save(nuevoProducto);

            return new ProductsDTO(
                    productoGuardado.getCode(),
                    productoGuardado.getName(),
                    productoGuardado.isStatus()
            );
        } catch (Exception e) {
            log.error("Error al crear producto: {}", e.getMessage());
            throw new RuntimeException("Error al crear el producto");
        }
    }
}