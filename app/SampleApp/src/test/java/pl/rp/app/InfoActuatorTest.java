package pl.rp.app;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import pl.rp.app.controller.TestController;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class InfoActuatorTest {
	
	@Autowired
	private WebApplicationContext webApplicationContext;
	
	private MockMvc mockmvc;
	
	@Before
	public void setUp() {
		
		TestController.traceCounter(1, "InfoActuatorTest setUp()");
		
		mockmvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
		
		TestController.traceCounter(2, "InfoActuatorTest setUp()");
	}
	
	@Test
	public void actuatorTest() throws Exception {
		
		TestController.traceCounter(1, "InfoActuatorTest actuatorTest()");
		
		mockmvc.perform(get("/manage/info")).andExpect(status().isOk());
		
		TestController.traceCounter(2, "InfoActuatorTest actuatorTest()");
	}
}
