package study.security.authoritytest.core.controller;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import study.security.authoritytest.core.domain.Paper;
import study.security.authoritytest.core.domain.State;
import study.security.authoritytest.core.service.PaperService;

import java.net.URI;
import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class PaperControllerTest {

    @LocalServerPort
    int port;

    TestRestTemplate client;

    @Autowired
    PaperService paperService;

    private Paper paper1 = Paper.builder()
            .paperId(1L)
            .title("시험지1")
            .tutorId("tutor1")
            .studentIds(List.of("user1"))
            .state(State.PREPARE)
            .build();

    private Paper paper2 = Paper.builder()
            .paperId(2L)
            .title("시험지2")
            .tutorId("tutor1")
            .studentIds(List.of("user2"))
            .state(State.PREPARE)
            .build();

    @Test
    @DisplayName("user1이 시험지 리스트를 조회한다")
    void test_01() {
        paperService.setPaper(paper1);

        client = new TestRestTemplate("user1", "1234");
        ResponseEntity<List<Paper>> response = client.exchange("http://localhost:" + port + "/paper/mypapers", HttpMethod.GET, null, new ParameterizedTypeReference<List<Paper>>() {
        });

        assertThat(response.getStatusCodeValue()).isEqualTo(200);
        System.out.println("response.getBody() = " + response.getBody());

    }


}