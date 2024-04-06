package zerobase.dividend.exception.impl;
import zerobase.dividend.exception.AbstractException;
import org.springframework.http.HttpStatus;
public class AlreadyExistUserException extends AbstractException {
    @Override public int getStatusCode() {return HttpStatus.BAD_REQUEST.value();}
    @Override public String getMessage() {return "이미 있는 사용자";}}