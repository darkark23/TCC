package br.net.iesb.springConfig;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

@Configuration
@PropertySource("classpath:application.properties")
@EnableJpaRepositories(
        basePackages = {"br.net.iesb.repository.transacional","br.net.iesb.service.transacional"},
        entityManagerFactoryRef = "transacionalEntityManager",
        transactionManagerRef = "transacionalTransactionManager")
public class PercistenceTransacionalAutoConfiguration  {

    @Bean
    @Primary
    public LocalContainerEntityManagerFactoryBean transacionalEntityManager() {
        LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(transacionalDataSource());
        em.setPackagesToScan( new String[]{"br.net.iesb.entity.transacional"} );
        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        em.setJpaVendorAdapter(vendorAdapter);
        HashMap<String, String> properties = new HashMap<>();
        properties.put("spring.datasource.data", "transacional.sql");
        properties.put("spring.h2.console.enabled", "true");
        properties.put("spring.h2.console.path", "/h2");
        properties.put("spring.jpa.hibernate.ddl-auto", "create-drop");
        properties.put("spring.jpa.show-sql", "true");
        em.setJpaPropertyMap(properties);
        return em;
    }

    @Bean
    @Primary
    public DataSource transacionalDataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("org.h2.Driver");
        dataSource.setUrl("jdbc:h2:~/transacional;AUTO_SERVER=TRUE");
        dataSource.setUsername("sa");
        dataSource.setPassword("");
        return dataSource;
    }

    @Bean
    @Primary
    public PlatformTransactionManager transacionalTransactionManager() {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(transacionalEntityManager().getObject());
        return transactionManager;
    }

}
