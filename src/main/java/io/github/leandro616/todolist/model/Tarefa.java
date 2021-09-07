package io.github.leandro616.todolist.model;

import java.time.LocalDate;

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
@Table(name = "tarefas")
@Data
public class Tarefa {

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   @Column(name = "idtarefa")
   private Integer idTarefa;
   private String descricao;
   private LocalDate dtConclusao;
   private LocalDate dtCriacao;
   private boolean isFinalizada;

   @ManyToOne
   @JoinColumn(name = "id_lista")
   private ListaDeTarefas lista;
}
