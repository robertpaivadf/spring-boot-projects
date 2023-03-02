package io.github.robertpaivadf.rest.controller;

import io.github.robertpaivadf.exception.PedidoNEException;
import io.github.robertpaivadf.exception.RegraNegocioException;
import io.github.robertpaivadf.rest.ApiErrors;
import net.bytebuddy.build.RepeatedAnnotationPlugin;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice //Fazendo tratamentos com ExceptionHandlers
public class ApplicationControllerAdvice {

        @ExceptionHandler (RegraNegocioException.class) // vai marcar esse metodo para ser um tratador de erros
        @ResponseStatus(HttpStatus.BAD_REQUEST)
        public ApiErrors handlerRegraNegocioException(RegraNegocioException ex){
            String mensagemErro = ex.getMessage();
            return new ApiErrors(mensagemErro);
        }

        @ExceptionHandler (PedidoNEException.class)
        @ResponseStatus(HttpStatus.NOT_FOUND)
        public ApiErrors handlerPedidoNFException(RegraNegocioException ex){
            return new ApiErrors(ex.getMessage());
        }


        @ExceptionHandler (MethodArgumentNotValidException.class)
        @ResponseStatus(HttpStatus.BAD_REQUEST)
        public ApiErrors handleMethodNotValidException(MethodArgumentNotValidException ex){
            List<String> errors = ex.getBindingResult().getAllErrors()
                    .stream()
                    .map(erro -> erro.getDefaultMessage())
                    .collect(Collectors.toList());
            return new ApiErrors(errors);
        }


}
