package com.mrc.auth.exception;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import jakarta.servlet.http.HttpServletRequest;

@RestControllerAdvice
public class GlobalExceptionHandler {
	
	@ExceptionHandler
	public ResponseEntity<String> handleResourceNotFoundException(ResourceNotFoundException ex) {
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
	}
	
	@ExceptionHandler(RuntimeException.class)
	public ResponseEntity<ApiErrorResponse> handleInvalidCredentialsException(InvalidCredentialsException ex, 
			HttpServletRequest request){
		ApiErrorResponse response = ApiErrorResponse.builder()
				.status(HttpStatus.UNAUTHORIZED.value())
				.error(HttpStatus.UNAUTHORIZED.getReasonPhrase())
				.message(ex.getMessage())
				.path(request.getRequestURI())
				.timesatamp(LocalDateTime.now())
				.build();
		return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
	}
	
	/*
	 * 
@RestControllerAdvice is a Spring annotation that lets you handle errors and exceptions globally for all REST endpoints in your app. 
It combines @ControllerAdvice and @ResponseBody, meaning it automatically turns error details into JSON or XML.
You do not use it on the service layer. 
It belongs strictly at the web/controller level, but it will catch exceptions thrown from your service layer.
Spring MVC ties this annotation to the web request lifecycle and controller execution.


so what is the exception handling flow in this case?
all the exception will travel starting from DB -> Repo class - > service class -> controller 
and it should be handled in controller using global exception handler?


@RestControllerAdvice
public class GlobalExceptionHandler {}

Used on class. that class will be called Global Exception Handler
This annotation tells Spring: "Whenever an exception occurs in any controller, send it here"

@ExceptionHandler(ResourceNotFoundException.class)
Used on method. that method is called handler method
The annotation tells Spring which type of exception this method will handle. here, it will handle: ResourceNotFoundExceptions
@ExceptionHandler, here no exception type is mentioned. So, it will be decided based on the method parameter 

@ExceptionHandler({A.class, B.class, C.class})		// it is possible to have many exception in one handler BUT
Method Parameter Type -> 
Must be equal to 
or a parent of
every exception listed in @ExceptionHandler

-> Exception class in method parameter should be either the same or the parent.
Meaning. Suppose, A -> B -> RuntimeException ( A is subclass of B, B parenet of A and child of RE)

@ExceptionHandler(A.class) then: handler(A ex), handler(B ex) or handler(RuntimeException ex) is fine
@ExceptionHandler(B.class) then: handler(B ex), handler(RuntimeException ex) is fine but handler(A ex) not fine


1. When Spring starts your application, it scans every class annotated with: @RestControllerAdvice
2. For each method, it reads the annotation: @ExceptionHandler(...)
and builds an internal mapping similar to:
ResourceNotFoundException -> handleResourceNotFound()
InvalidCredentialsException -> handleInvalidCredentials()
DuplicateResourceException -> handleDuplicateResource()
Exception -> handleException()
3. Later, whenever an exception is thrown, Spring simply looks up the matching handler.
4. if multiple match, Spring chooses the closest match in the inheritance hierarchy.

A -> B -> RuntimeException -> Exception
if handler for A is not present, it will try to find handler of B
if handler for B is not present, it will try to find handler of RuntimeException
if handler for Runtime exception is not present, it will try to find handler ofs Exception

what if no handler is present in the GlobalExceptionHandler class?
If no @ExceptionHandler matches, Spring falls back to its default exception resolver.
That is why we add @ExceptionHandler(Exception.class) as a safety net. if in case, we missed any exceptions.


io.jsonwebtoken.ExpiredJwtException: JWT expired 123899727 milliseconds ago at 2026-07-30T21:58:33.000Z -> 
	 * 
	 */
	
}
