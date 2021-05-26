package com.murali.graphql.scalar;

import graphql.language.StringValue;
import graphql.schema.Coercing;
import graphql.schema.CoercingParseLiteralException;
import graphql.schema.CoercingParseValueException;
import graphql.schema.CoercingSerializeException;
import graphql.schema.GraphQLScalarType;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

@Configuration
public class DateScalarConfiguration {

    @Bean
    public GraphQLScalarType dateScalar() {
        return GraphQLScalarType.newScalar()
            .name("Date")
            .description("Java LocalDate as scalar.")
            .coercing(new Coercing<LocalDate, String>() {
                @Override
                public String serialize(final Object dataFetcherResult) {
                    if (dataFetcherResult instanceof LocalDate) {
                        return dataFetcherResult.toString();
                    } else {
                        throw new CoercingSerializeException("Expected a LocalDate object");
                    }
                }

                @Override
                public LocalDate parseValue(final Object input) {
                    try {
                        if (input instanceof String) {
                            DateTimeFormatter formatter = DateTimeFormatter.ISO_DATE_TIME;
                            return LocalDate.parse((String) input, formatter);
                        } else {
                            throw new CoercingParseValueException("Expected a String");
                        }
                    } catch (DateTimeParseException e) {
                        throw new CoercingParseValueException(String.format("Not a valid date: '%s'.", input), e
                        );
                    }
                }

                @Override
                public LocalDate parseLiteral(final Object input) {
                    if (input instanceof StringValue) {
                        try {
                            DateTimeFormatter formatter = DateTimeFormatter.ISO_DATE_TIME;
                            return LocalDate.parse(((StringValue) input).getValue(), formatter);
                        } catch (DateTimeParseException e) {
                            throw new CoercingParseLiteralException(e);
                        }
                    } else {
                        throw new CoercingParseLiteralException("Expected a StringValue.");
                    }
                }
            }).build();
    }
}
