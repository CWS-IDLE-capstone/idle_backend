package com.idle.idle_backend.config.jwt.common;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BasicController {

    @GetMapping("/")
    public String home() {
        return "<h1>idle-backend 스웨거 주소: http://awsv4-env.eba-mre2mcnv.ap-northeast-2.elasticbeanstalk.com/swagger-ui/index.html </h1>";
    }
}
