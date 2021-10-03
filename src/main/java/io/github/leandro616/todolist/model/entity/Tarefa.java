package io.github.leandro616.todolist.model.entity;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "tarefas")
public class Tarefa {

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   @Column(name = "idtarefa")
   private Integer id;

   private String descricao;
   private LocalDate dtConclusao;
   private LocalDate dtCriacao;
   private Boolean finalizada;

   @ManyToOne
   @JoinColumn(name = "id_lista")
   private ListaDeTarefas lista;

   @PrePersist
   public void prePersist() {
      setDtCriacao(LocalDate.now());
      setFinalizada(false);
   }
}
