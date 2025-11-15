package edu.sv.ues.dam235.apirestdemo.entities;

import jakarta.persistence.*;
import lombok.Data;
@Data
@Entity
@Table(name = "Producto")
public class Producto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Code")
    private Integer code;
    @Column(name = "Name")
    private String name;
    @Column(name = "Status")
    private boolean status;
}
