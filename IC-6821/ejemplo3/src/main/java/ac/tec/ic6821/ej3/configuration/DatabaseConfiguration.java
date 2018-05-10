package ac.tec.ic6821.ej3.configuration;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.orm.jpa.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

@Configuration
@EntityScan(basePackages = "ac.tec.ic6821.ej3.model")
@EnableJpaRepositories(basePackages = "ac.tec.ic6821.ej3.data.jpa")
@EnableJpaAuditing
@EnableTransactionManagement
public class DatabaseConfiguration {

    @Value("${ej3.db.usr}")
    private String dbUsr;

    @Value("${ej3.db.pwd}")
    private String dbPwd;

    @Value("${ej3.db.url}")
    private String dbUrl;

    @Value("${ej3.db.cls}")
    private String dbCls;

    @Bean
    public DataSource dataSource() {
        HikariConfig hikariConfig = new HikariConfig();
        hikariConfig.addDataSourceProperty("url", dbUrl);
        hikariConfig.addDataSourceProperty("user", dbUsr);
        hikariConfig.addDataSourceProperty("password", dbPwd);
        hikariConfig.setDataSourceClassName(dbCls);

        return new HikariDataSource(hikariConfig);
    }

}
