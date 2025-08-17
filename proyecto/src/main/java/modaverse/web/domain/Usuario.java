/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modaverse.web.domain;

import jakarta.persistence.*;
import java.io.Serializable;
import lombok.Data;

@Data
@Entity
@Table(name = "Usuario") 
public class Usuario implements Serializable { 
    
    private static final long serialVersionUID = 1L; 

    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY) 
    @Column(name = "UsuarioID")  
    private Long idUsuario;

    private String nombre;
    private String apellidos;

    @Column(name = "Correo")
    private String username;   // Se mapea con la columna Correo

    @Column(name = "Contrasena")
    private String password;   // Se mapea con la columna Contrasena

    private String rol;
}
