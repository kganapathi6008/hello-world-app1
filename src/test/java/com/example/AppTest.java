import org.springframework.core.env.Environment;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.beans.factory.annotation.Autowired;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class AppTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private Environment environment;

    @Test
    void contextLoads() {
        assertThat(restTemplate).isNotNull();
    }

    @Test
    void helloEndpointShouldReturnHelloWorld() {
        // Read context path from application properties
        String contextPath = environment.getProperty("server.servlet.context-path", "");
        String response = this.restTemplate.getForObject("http://localhost:" + port + contextPath + "/", String.class);
        assertThat(response).isEqualTo("Hello, World from Spring Boot!");
    }
}

