package com.ieslavereda.es.miravereda.Config;

import com.mysql.cj.jdbc.MysqlDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
@Configuration
public class MyDataSource {
    @Bean("MyDataSource")
    public static DataSource getMydataSource(){
        try{
            MysqlDataSource dataSource=new MysqlDataSource();
            dataSource.setUser("proyecto2425");// cambiar a futuro
            dataSource.setPassword("1111");
            dataSource.setUrl("jdbc:mysql://10.249.158.3:3306/miraveredapractica");//cambiar por id de servidor

            return dataSource;
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }

    }
}
