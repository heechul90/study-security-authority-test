package study.security.authoritytest.controller;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class HomeControllerTest {

    @LocalServerPort int port;

    TestRestTemplate client;

    @Test
    @DisplayName("greeting 메시지를 불러온다")
    void test_01() {
        client = new TestRestTemplate("user", "1234");
        ResponseEntity<String> response = client.getForEntity("http://localhost:" + port + "/greeting", String.class);
        assertThat(response.getBody()).isEqualTo("hello");
    }

    @Test
    @DisplayName("greeting 메지지와 name 을 불러온다")
    void test_02() {
        client = new TestRestTemplate("user", "1234");
        ResponseEntity<String> response = client.getForEntity("http://localhost:" + port + "/greeting/heech", String.class);
        assertThat(response.getBody()).isEqualTo("hello heech");
    }

}