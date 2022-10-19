package study.security.authoritytest.core.controller;

import lombok.RequiredArgsConstructor;
import org.apache.catalina.User;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import study.security.authoritytest.core.domain.Paper;
import study.security.authoritytest.core.service.PaperService;

import java.util.List;

@RestController
@RequestMapping("/paper")
@RequiredArgsConstructor
public class PaperController {

    private final PaperService paperService;

//    @PreAuthorize("isStudent()")
    @GetMapping("/mypapers")
    public List<Paper> myPapers(@AuthenticationPrincipal User user) {
        User user1;
        return paperService.getMyPapers(user.getUsername());
    }

    @GetMapping("/get/{paperId}")
    public Paper getPaper(@AuthenticationPrincipal User user, @PathVariable("paperId") Long paperId) {
        return paperService.getPager(paperId);
    }
}
