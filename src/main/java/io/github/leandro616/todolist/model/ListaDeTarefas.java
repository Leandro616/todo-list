package io.github.leandro616.todolist.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import lombok.Data;

@Entity
@Table(name = "listas")
@Data
public class ListaDeTarefas {

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   @Column(name = "idlista")
   private Integer idLista;
   
   @Column(length = 100)
   private String nome;

   @ManyToOne
   @JoinColumn(name = "id_usuario")
   private Usuario usuario;

   @Transient
   private List<Tarefa> tarefas;
}
