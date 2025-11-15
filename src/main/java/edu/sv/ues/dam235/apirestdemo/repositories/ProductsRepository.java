package edu.sv.ues.dam235.apirestdemo.repositories;

import edu.sv.ues.dam235.apirestdemo.entities.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
public interface ProductsRepository extends JpaRepository<Producto, Integer> {
}
