package study.security.authoritytest.core.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import study.security.authoritytest.core.service.SecurityMessageService;

@RestController
@RequiredArgsConstructor
public class HomeController {

    private final SecurityMessageService securityMessageService;

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/greeting")
    public String greeting() {
        return "hello";
    }

    @PreAuthorize("hasRole('USER')")
    @GetMapping("/greeting/{name}")
    public String greeting(@PathVariable("name") String name) {
        return "hello " + securityMessageService.message(name);
    }


}
