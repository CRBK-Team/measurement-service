package net.ddns.crbkproject.infrastructure.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static net.ddns.crbkproject.domain.model.common.ExceptionCode.INTERNAL_SERVER;
import static net.ddns.crbkproject.domain.model.common.ExceptionCode.INVALID_VALUE_OF_FIELD;
import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

@ControllerAdvice
class ExceptionHandling {
    private static final Logger LOG = LoggerFactory.getLogger(ExceptionHandling.class);

    @ExceptionHandler({DomainException.class})
    public ResponseEntity<ExceptionError> handleDomainExceptions(HttpServletRequest request, DomainException exception) {
        LOG.error("DomainException", exception);

        return response(
                request.getRequestURI(),
                List.of(new ErrorDetails(exception)),
                exception.code().httpStatus());
    }

    @ExceptionHandler({Exception.class})
    public ResponseEntity<ExceptionError> handleInternalExceptions(HttpServletRequest request, Exception exception) {
        LOG.error("Exception", exception);

        return response(
                request.getRequestURI(),
                List.of(new ErrorDetails(Objects.toString(INTERNAL_SERVER.code()), INTERNAL_SERVER.detailsPattern())),
                INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler({MethodArgumentNotValidException.class})
    public ResponseEntity<ExceptionError> handleMethodArgumentNotValidExceptions(HttpServletRequest request, MethodArgumentNotValidException exception) {
        LOG.error("MethodArgumentNotValidException", exception);

        List<ErrorDetails> errors = exception.getBindingResult().getFieldErrors().stream()
                .map(fieldError -> new ErrorDetails(INVALID_VALUE_OF_FIELD.code(),
                        exception.getBindingResult().getFieldError().getField() + " " + exception.getBindingResult().getFieldError().getDefaultMessage()))
                .collect(Collectors.toList());

        return response(request.getRequestURI(), errors, BAD_REQUEST);
    }

    @ExceptionHandler({MethodArgumentTypeMismatchException.class})
    public ResponseEntity<ExceptionError> handleMethodArgumentTypeMismatchExceptions(HttpServletRequest request, MethodArgumentTypeMismatchException exception) {
        ErrorDetails error = new ErrorDetails(INVALID_VALUE_OF_FIELD.code(), exception.getCause().getCause().getMessage());

        LOG.warn("{}: {}", error.getCode(), error.getMessage());

        return response(request.getRequestURI(), List.of(error), BAD_REQUEST);
    }

    private static ResponseEntity<ExceptionError> response(String path, List<ErrorDetails> errorDetails, HttpStatus status) {
        return new ResponseEntity<>(new ExceptionError(path, errorDetails), status);
    }
}
