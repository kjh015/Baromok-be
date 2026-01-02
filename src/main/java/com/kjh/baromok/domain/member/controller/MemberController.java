package com.kjh.baromok.domain.member.controller;

import com.kjh.baromok.global.apiPayload.code.ErrorCode;
import com.kjh.baromok.global.config.swagger.ApiErrorCodeExamples;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/member")
public class MemberController {
    @GetMapping("/test")
    @ApiErrorCodeExamples({ErrorCode.BAD_REQUEST, ErrorCode.FORBIDDEN, ErrorCode.NOT_FOUND})
    public String test() {
        return "Hello, World!2";
    }
}
