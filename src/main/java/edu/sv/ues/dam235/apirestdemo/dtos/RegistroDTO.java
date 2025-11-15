package edu.sv.ues.dam235.apirestdemo.dtos;

import lombok.Data;

@Data
public class RegistroDTO {
    private String name;
    private String lastName;
    private String email;
    private String password;
}
