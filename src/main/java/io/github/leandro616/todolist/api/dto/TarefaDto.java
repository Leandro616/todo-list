package io.github.leandro616.todolist.api.dto;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Data
public class TarefaDto {

   private Integer id;
   private String descricao;

   @JsonFormat(pattern = "dd/MM/yyyy")
   private LocalDate dtConclusao;

   @JsonFormat(pattern = "dd/MM/yyyy")
   private LocalDate dtCriacao;

   private boolean finalizada;

   private ListaDeTarefasDto lista;
}
