package Config;

import com.mysql.cj.jdbc.MysqlDataSource;
import org.springframework.context.annotation.Bean;

import javax.sql.DataSource;

public class MydataSource {
    @Bean
    public static DataSource getMydataSource(){
        try{
            MysqlDataSource dataSource=new MysqlDataSource();
            dataSource.setURL("jdbc:mysql://localhost:3306/mydb");//cambiar por id de servidor
            dataSource.setUser("root");// cambiar a futuro
            dataSource.setPassword("1234");
            return dataSource;
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }

    }
}
