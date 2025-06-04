package com.ieslavereda.es.miravereda.Controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.HashMap;
import java.util.Map;
/**
 * @author Andrii,Cristobal,Dario,Leonardo
 * @version 1.0
 *
 */
public abstract class BaseController {
    /**
     *
     * @param e
     * @return
     */
    protected ResponseEntity<?> getResposeError(Exception e) {
            Map<String,String> errors = new HashMap<>();
            errors.put("error",String.valueOf(e.getCause()));
            errors.put("message", e.getMessage());

            return new ResponseEntity<>(errors, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}

