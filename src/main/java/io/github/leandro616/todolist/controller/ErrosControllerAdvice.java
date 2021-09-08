package io.github.leandro616.todolist.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.ResponseStatusException;

import io.github.leandro616.todolist.exception.ApiErros;

@RestControllerAdvice
public class ErrosControllerAdvice {
   
   @ExceptionHandler(MethodArgumentNotValidException.class)
   @ResponseStatus(HttpStatus.BAD_REQUEST)
   public ApiErros validationHandler(MethodArgumentNotValidException e) {

      List<String> mensagens = e.getBindingResult()
         .getAllErrors()
         .stream()
         .map(objectError -> objectError.getDefaultMessage())
         .collect(Collectors.toList());

      return new ApiErros(mensagens);
   }

   @ExceptionHandler(ResponseStatusException.class)
   public ResponseEntity<ApiErros> 
         responseExceptionHandler(ResponseStatusException e) {
            
      ApiErros apiErros = new ApiErros(e.getReason());

      return new ResponseEntity<ApiErros>(apiErros, e.getStatus());
   }


}
