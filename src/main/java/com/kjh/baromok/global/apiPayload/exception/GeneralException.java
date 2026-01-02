package com.kjh.baromok.global.apiPayload.exception;


import com.kjh.baromok.global.apiPayload.code.BaseErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class GeneralException extends RuntimeException{
    private final BaseErrorCode code;
}
