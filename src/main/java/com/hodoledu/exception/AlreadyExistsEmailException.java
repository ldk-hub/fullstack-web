package com.hodoledu.exception;

public class AlreadyExistsEmailException extends HodollogException{

    public static  String MESSAGE = "이미 가입된 이메일입니다.";
    public AlreadyExistsEmailException() {
        super(MESSAGE);
    }

    @Override
    public int statusCode() {
        return 400;
    }
}
