package pl.rp.ws;

import javax.sql.DataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import pl.rp.ws.controller.TestController;

@Configuration
@ComponentScan("pl.rp.ws")
public class RootApplicationContextConfig {

   @Bean
   public DataSource dataSource() {
	   
	   TestController.traceCounter(TestController.InsertType.IN, "RootApplicationContextConfig dataSource()");
	   EmbeddedDatabaseBuilder builder = new EmbeddedDatabaseBuilder();
	   EmbeddedDatabase db = builder
			   //.setType(EmbeddedDatabaseType.HSQL)
			   .setType(EmbeddedDatabaseType.H2)
			   .addScript("db/sql/create-table.sql")
			   .addScript("db/sql/insert-data.sql")
			   .build();
      TestController.traceCounter(TestController.InsertType.OUT, "RootApplicationContextConfig dataSource()");
     
      return db;
   }
  
   @Bean
   public NamedParameterJdbcTemplate getJdbcTemplate() {
	   
	   TestController.traceCounter(TestController.InsertType.INOUT, "RootApplicationContextConfig getJdbcTemplate()");
	   return new NamedParameterJdbcTemplate(dataSource());
   }
}
