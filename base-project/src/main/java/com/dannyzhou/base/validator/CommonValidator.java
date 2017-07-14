package com.dannyzhou.base.validator;

import com.dannyzhou.base.exception.ValidatorException;

/**
 * Created by danny on 2017/6/11.
 */
public class CommonValidator {

    public static final void validateOpenId(String value) {
        validateSqlInject(value);

        if (value.length() > 32 || value.length() < 26) {
            throw new ValidatorException();
        }
    }

    public static void validateToken(String value) {
        validateSqlInject(value);

        if (value.length() < 10) {
            throw new ValidatorException();
        }

    }

    private static void validateSqlInject(String value) {
        if (value.contains("(")) {
            throw new ValidatorException();
        }

        if (value.contains(")")) {
            throw new ValidatorException();
        }

        if (value.toUpperCase().contains("AND")) {
            throw new ValidatorException();
        }

        if (value.contains(";")) {
            throw new ValidatorException();
        }

        if (value.contains("1=1")) {
            throw new ValidatorException();
        }
    }
}
