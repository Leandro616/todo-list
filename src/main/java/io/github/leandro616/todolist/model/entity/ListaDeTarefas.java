package io.github.leandro616.todolist.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "listas")
@Data
public class ListaDeTarefas {

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   @Column(name = "idlista")
   private Integer id;
   
   private String nome;
   private Boolean padrao = false;

   @ManyToOne
   @JoinColumn(name = "id_usuario")
   private Usuario usuario;
}
