package net.ddns.crbkproject.domain.exception;

public class DomainException extends RuntimeException {
    private final ExceptionCode code;

    public DomainException(ExceptionCode code, Object... variables) {
        super(String.format(code.detailsPattern(), variables));
        this.code = code;
    }

    public ExceptionCode code() {
        return code;
    }
}
