/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wad.profile;
import org.springframework.context.annotation.*;
import org.springframework.boot.context.properties.ConfigurationProperties;
import javax.sql.DataSource;
import org.springframework.boot.jdbc.DataSourceBuilder;

/**
 *
 * @author Harri
 */
@Configuration
@Profile("production")
public class ProductionProfile {
    
    @Bean
    @ConfigurationProperties(prefix = "spring.datasource")
    public DataSource dataSource() {
        return DataSourceBuilder.create().build();
    }
}
