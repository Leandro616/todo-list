package io.github.leandro616.todolist.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "usuarios")
@Data
public class Usuario {

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   @Column(name = "idusuario")
   private Integer idUsuario;

   @Column(length = 100)
   private String nome;

   @Column(length = 150, unique = true)
   private String email;

   @Column(length = 16)
   private String senha;

}
