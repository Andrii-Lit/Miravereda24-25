package com.ieslavereda.es.miravereda.Model;


import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Actor {
    private String dni;
    private String nombre;
    private String apellidos;
}
