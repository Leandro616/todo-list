package io.github.leandro616.todolist.api.dto;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class ListaDeTarefasDto {
   private Integer id;
   private String nome;
   private Boolean padrao;
}
