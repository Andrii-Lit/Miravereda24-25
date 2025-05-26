package com.ieslavereda.es.miravereda.Controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.HashMap;
import java.util.Map;

public abstract class BaseController {
        protected ResponseEntity<?> getResposeError(Exception e) {
            Map<String,String> errors = new HashMap<>();
            errors.put("error",String.valueOf(e.getCause()));
            errors.put("message", e.getMessage());

            return new ResponseEntity<>(errors, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

