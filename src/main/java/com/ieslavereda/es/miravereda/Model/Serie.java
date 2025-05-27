package com.ieslavereda.es.miravereda.Model;



import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Service;

import java.sql.Date;
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Serie extends Contenido {
    private Date disponibilidad;
}

