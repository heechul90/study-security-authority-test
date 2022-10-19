package study.security.authoritytest.config;

import org.springframework.stereotype.Component;

@Component
public class NameCheck {

    public boolean check(String name) {
        return name.equals("heech");
    }
}
