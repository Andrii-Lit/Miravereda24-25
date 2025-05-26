package com.ieslavereda.es.miravereda.Config;

import com.mysql.cj.jdbc.MysqlDataSource;
import org.springframework.context.annotation.Bean;

import javax.sql.DataSource;

public class MyDataSource {
    @Bean
    public static DataSource getMydataSource(){
        try{
            MysqlDataSource dataSource=new MysqlDataSource();
            dataSource.setURL("jdbc:mysql://10.249.158.3:3306/miraveredapractica");//cambiar por id de servidor
            dataSource.setUser("root");// cambiar a futuro
            dataSource.setPassword("1234");
            return dataSource;
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }

    }
}
