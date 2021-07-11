package br.com.latourtec.corporative.address.exception;


import br.com.latourtec.corporative.address.exception.handler.HttpResponseException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class EntityNotFoundException extends HttpResponseException {
	
	public EntityNotFoundException(String message) {
		super(HttpStatus.NOT_FOUND,  message);
	}
	
}
