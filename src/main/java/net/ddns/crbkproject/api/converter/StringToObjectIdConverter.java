package net.ddns.crbkproject.api.converter;

import net.ddns.crbkproject.domain.exception.DomainException;
import org.bson.types.ObjectId;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import static net.ddns.crbkproject.domain.exception.ExceptionCode.INVALID_VALUE_OF_FIELD;

@Component
public class StringToObjectIdConverter implements Converter<String, ObjectId> {

    @Override
    public ObjectId convert(String source) {
        try {
            return new ObjectId(source);
        } catch (Exception e) {
            throw new DomainException(INVALID_VALUE_OF_FIELD, String.format("Invalid format of ObjectId: '%s'", source));
        }
    }
}
