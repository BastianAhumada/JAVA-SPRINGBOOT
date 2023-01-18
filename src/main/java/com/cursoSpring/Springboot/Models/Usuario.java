package com.cursoSpring.Springboot.Models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

//Entidad referencial a la base de datos
@Entity
/*@Table es utilizada para indicarle a JPA contra que tabla debe de mapear una entidad,
de esta manera cuando se realice una persistencia, borrado o select de la entidad,
 JPA sabrá contra que tabla de la base de datos deberá interactuar.*/
@Table(name = "usuarios")
public class Usuario {
    /*Getter y setter se hacen  travez de las anotaciones gracias a lombok,
    y colum se refiere a la columna de la tabla que hace referencia JPA*/
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter @Setter @Column(name = "id")
    private Long Id;
    @Getter @Setter @Column(name = "nombre")
    private String Nombre;
    @Getter @Setter @Column(name = "apellido")
    private String Apellido;
    @Getter @Setter @Column(name = "email")
    private String Email;
    @Getter @Setter @Column(name = "telefono")
    private String Telefono;
    @Getter @Setter @Column(name = "contraseña")
    private String Contraseña;
}
