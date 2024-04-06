package zerobase.dividend.exception.impl;
import zerobase.dividend.exception.AbstractException;
import org.springframework.http.HttpStatus;
public class NoCompanyException extends AbstractException {
    @Override public int getStatusCode() {return HttpStatus.BAD_REQUEST.value();}
    @Override public String getMessage() {return "없는 회사명";}}