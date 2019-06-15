package teilar.cs4414202.security.jwt;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import teilar.cs4414202.exception.InvalidJwtAuthenticationException;

@ControllerAdvice("teilar.cs4414202.security")
public class CustomRestExceptionHandler extends ResponseEntityExceptionHandler {
	 @ExceptionHandler({InvalidJwtAuthenticationException.class,AuthenticationException.class })
	    @ResponseStatus(value = HttpStatus.UNAUTHORIZED)
	    @ResponseBody
	    public String handleAuthenticationException(Exception ex) {
		 	return ex.getMessage();
		 
	 }
	
}