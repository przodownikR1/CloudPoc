package pl.java.scalatech.web.test;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/one")
public class OneController {
    @GetMapping("/")
    String getOne() {
        return "one";
    }

}
