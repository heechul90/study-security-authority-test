package study.security.authoritytest.core.domain;

import lombok.*;

import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class Paper {

    private Long paperId;
    private String title;
    private String tutorId;
    private List<String> studentIds;
    private State state;
}
