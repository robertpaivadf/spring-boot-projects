package io.github.robertpaivadf.rest.controller;

import io.github.robertpaivadf.exception.PedidoNEException;
import io.github.robertpaivadf.exception.RegraNegocioException;
import io.github.robertpaivadf.rest.ApiErrors;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

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

}
