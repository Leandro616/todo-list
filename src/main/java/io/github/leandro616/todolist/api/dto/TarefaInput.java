package io.github.leandro616.todolist.api.dto;

import java.time.LocalDate;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class TarefaInput {

   @NotBlank(message = "Campo descrição obrigatório")
   @Size(max = 1000, message = "A descrição deve ter no máximo 1000 caracteres")
   private String descricao;

   @JsonFormat(pattern = "dd/MM/yyyy")
   private LocalDate dtConclusao;
}
