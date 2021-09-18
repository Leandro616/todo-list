package io.github.leandro616.todolist.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.Data;

@Entity
@Table(name = "listas")
@Data
public class ListaDeTarefas {

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   @Column(name = "idlista")
   private Integer idLista;
   
   @NotBlank(message = "Campo nome obrigatório")
   @Size(max = 100, message = "O nome deve ter no máximo 100 caracteres")
   private String nome;

   private boolean isDefault = false;

   @ManyToOne
   @JoinColumn(name = "id_usuario")
   private Usuario usuario;
}
