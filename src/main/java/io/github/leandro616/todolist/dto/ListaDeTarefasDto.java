package io.github.leandro616.todolist.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;


@Data
@AllArgsConstructor
public class ListaDeTarefasDto {
   private Integer id;

   @NotBlank(message = "Campo nome obrigatório")
   @Size(max = 100, message = "O nome deve ter no máximo 100 caracteres")
   private String nome;

   public ListaDeTarefasDto() {
      
   }
}
