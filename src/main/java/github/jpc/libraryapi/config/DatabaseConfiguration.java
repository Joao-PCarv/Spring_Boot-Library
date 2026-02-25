package github.jpc.libraryapi.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;

@Configuration
public class DatabaseConfiguration {

    @Value("${spring.datasource.url}")
    String url;
    @Value("${spring.datasource.username}")
    String username;
    @Value("${spring.datasource.password}")
    String password;
    @Value("${spring.datasource.driver-class-name}")
    String driver;
    @Value("${spring.datasource.hikari.maximum-pool-size:10}")
    Integer maxPoolSize;

//    @Bean
    public DataSource dataSource(){
        DriverManagerDataSource ds = new DriverManagerDataSource();

        ds.setUrl(url);
        ds.setUsername(username);
        ds.setPassword(password);
        ds.setDriverClassName(driver);

        return ds;
    };

    @Bean
    public DataSource hikariDatasource(){
        HikariConfig config = new HikariConfig();
        config.setJdbcUrl(url);
        config.setUsername(username);
        config.setPassword(password);
        config.setDriverClassName(driver);

        // Esta configuração é opcional, mas pode ser ajustada conforme as necessidades do seu aplicativo
        config.setMaximumPoolSize(maxPoolSize); // Define o número máximo de conexões no pool
        config.setMinimumIdle(1); // Define o número mínimo de conexões ociosas no pool
        config.setPoolName("library-db-poll"); // Define um nome para o pool de conexões
        config.setMaxLifetime(600000); // Define o tempo máximo de vida de uma conexão (em milissegundos) (10 minutos)
        config.setConnectionTimeout(30000); // Define o tempo máximo para obter uma conexão do pool (em milissegundos) (30 segundos)
        config.setConnectionTestQuery("SELECT 1"); // Define uma consulta de teste para verificar a validade das conexões

        return new HikariDataSource(config);

    };
}
