package io.github.leandro616.todolist.dto;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Data
public class TarefaDto {
   private String descricao;

   @JsonFormat(pattern = "dd/MM/yyyy")
   private LocalDate dtConclusao;
   private Integer idLista;
}
