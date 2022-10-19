package study.security.authoritytest.core.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum State {

    PREPARE("출제 중"),
    READY("시험 시작"),
    END("시험 종료");

    private final String name;
}
