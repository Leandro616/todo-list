package io.github.leandro616.todolist.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.Data;

@Entity
@Table(name = "usuarios")
@Data
public class Usuario {

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   @Column(name = "idusuario")
   private Integer idUsuario;

   @NotBlank(message = "Campo nome obrigatório")
   @Size(max = 100, message = "O nome deve ter no máximo 100 caracteres")
   private String nome;

   @Column(unique = true)
   @Email(message = "Campo email inválido")
   @NotBlank(message = "Campo email obrigatório")
   @Size(max = 150, message = "O email deve ter no máximo 150 caracteres")
   private String email;

   @NotBlank(message = "Campo senha obrigatório")
   @Size(min = 4, max = 16, message = "A senha deve ter entre 4 e 16 caracteres")
   private String senha;

}
