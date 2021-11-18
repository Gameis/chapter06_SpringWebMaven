package spring.conf;

import org.apache.commons.dbcp2.BasicDataSource;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

@Configuration
@PropertySource("classpath:spring/db.properties")
@EnableTransactionManagement
public class SpringConfiguration {

	@Value("${jdbc.driver}")
	private String driverClassName;
	
	@Value("${jdbc.url}")
    private String url;
 
    @Value("${jdbc.username}")
    private String username;
 
    @Value("${jdbc.password}")
    private String password;
    
    @Value("20")
    private int maxTotal;
    
    @Value("3")
    private int maxIdle;
	
    //Connection Pool
    @Bean
    public BasicDataSource dataSource() {
    	BasicDataSource dataSource = new BasicDataSource();   
		dataSource.setDriverClassName(driverClassName);
		dataSource.setUrl(url);
		dataSource.setUsername(username);
		dataSource.setPassword(password);
		dataSource.setMaxTotal(maxTotal);
		dataSource.setMaxIdle(maxIdle);
		
		return dataSource;
    }
    
    @Bean
    public SqlSessionFactory sqlSessionFactory() {
    	SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
    	sqlSessionFactoryBean.setConfigLocation(new ClassPathResource("spring/mybatis-config.xml"));
    	sqlSessionFactoryBean.setDataSource(dataSource());
    	sqlSessionFactoryBean.setMapperLocations(new ClassPathResource("user/dao/userMapper.xml"));
    	
    	try {
			return sqlSessionFactoryBean.getObject();
		} catch (Exception e) {
			return null;
		}
    }
    
    @Bean
    public SqlSession sqlSession() {
    	return new SqlSessionTemplate(sqlSessionFactory());
    }
    
    @Bean
    public DataSourceTransactionManager transactionManager() {
    	return new DataSourceTransactionManager(dataSource());
    }
    //-----------
    
    
}
