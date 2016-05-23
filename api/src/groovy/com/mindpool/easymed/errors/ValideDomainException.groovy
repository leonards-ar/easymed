package com.mindpool.easymed.errors

/**
 * Created by federicobarreraoro on 5/23/16.
 */
class DomainValidationException extends RuntimeException {
    def errors;

    public DomainValidationException(errors) {
        this.errors = errors
    }

}
