package httpout;

import static org.junit.Assert.*;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.anything;
import static org.springframework.test.web.client.response.MockRestResponseCreators.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.web.client.RestTemplate;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = HttpoutApplication.class)
public class HttpoutApplicationTests {

	@Autowired
	RequestProducer producer;
	
	@Autowired
	RestTemplate template;
	
	private MockRestServiceServer mockServer;
	
	@Before
	public void createMockServer(){
		mockServer = MockRestServiceServer.createServer(template);
	}
	
	@Test
	public void contextLoads() {
	}
	
	@Test
	public void testSuccessfulRequest(){
		
		mockServer.expect(anything()).andRespond(withSuccess("Hi there.", MediaType.TEXT_PLAIN));
		
		assertEquals("Hi there.", producer.createRequest("Hello"));
		
		mockServer.verify();
	}
	
	@Test
	public void testErrorRequest(){
		
		mockServer.expect(anything()).andRespond(withUnauthorizedRequest());
		
		assertEquals("<401 Unauthorized,{}>", producer.createRequest("Hello"));
		
		mockServer.verify();
	}

}
