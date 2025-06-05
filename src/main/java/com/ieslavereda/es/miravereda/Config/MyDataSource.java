package com.ieslavereda.es.miravereda.Config;

import com.mysql.cj.jdbc.MysqlDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

/**
 * Clase de configuración para definir el origen de datos (DataSource)
 * utilizado en la aplicación para conectarse a la base de datos MySQL.
 *
 * <p>Esta clase configura un bean de tipo {@link DataSource} usando
 * {@link MysqlDataSource} con los parámetros necesarios para
 * establecer la conexión a la base de datos.</p>
 *
 * @author Andrii, Cristobal, Dario, Leonardo, Ivan
 * @version 1.0
 */
@Configuration
public class MyDataSource {

    /**
     * Crea y configura un bean de tipo {@link DataSource} que se conectará
     * a la base de datos MySQL especificada.
     *
     * @return un objeto {@link DataSource} configurado, o {@code null} si ocurre una excepción.
     */
    @Bean("MyDataSource")
    public static DataSource getMydataSource() {
        try {
            // Se crea una nueva instancia del origen de datos MySQL
            MysqlDataSource dataSource = new MysqlDataSource();

            // Se establece el nombre de usuario de la base de datos
            dataSource.setUser("proyecto2425");

            // Se establece la contraseña del usuario de la base de datos
            dataSource.setPassword("1111");

            // Se establece la URL de conexión a la base de datos, incluyendo IP, puerto y nombre de la base de datos
            dataSource.setUrl("jdbc:mysql://172.28.201.195:3306/miravereda2425?noAccessToProcedureBodies=true");

            return dataSource;
        } catch (Exception e) {
            // Imprime la traza de la excepción y retorna null en caso de error
            e.printStackTrace();
            return null;
        }
    }
}