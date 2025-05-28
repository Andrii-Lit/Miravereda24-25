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
            dataSource.setUser("proyecto2425");//userDeBase de Datos
            dataSource.setPassword("1111");
            dataSource.setUrl("jdbc:mysql://172.28.201.195:3306/miravereda2425?noAccessToProcedureBodies=true");//ya esta la ip del servidor

            return dataSource;
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }

    }
}
