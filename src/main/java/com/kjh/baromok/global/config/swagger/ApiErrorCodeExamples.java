package com.kjh.baromok.global.config.swagger;

import com.kjh.baromok.global.apiPayload.code.BaseErrorCode;
import com.kjh.baromok.global.apiPayload.code.ErrorCode;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface ApiErrorCodeExamples {

    ErrorCode[] value();
}
