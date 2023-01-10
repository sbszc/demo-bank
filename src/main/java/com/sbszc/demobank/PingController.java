package com.sbszc.demobank;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("${root-path.api.v1}")
public class PingController {

    @GetMapping("ping")
    public String ping() {
        return "ping";
    }
}
