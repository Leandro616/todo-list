package io.github.leandro616.todolist.dto;

import lombok.AllArgsConstructor;
import lombok.Data;


@Data
@AllArgsConstructor
public class ListaDeTarefasDto {
   private Integer id;
   private String nome;

   public ListaDeTarefasDto() {
      
   }
}
