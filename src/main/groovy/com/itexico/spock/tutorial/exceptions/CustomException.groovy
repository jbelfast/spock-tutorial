package com.itexico.spock.tutorial.exceptions

/**
 * Created by Juan Malacrida on 02-Aug-16.
 */
class CustomException extends RuntimeException {
    CustomException() {
        super()
    }

    CustomException(String message) {
        super(message)
    }

    CustomException(String message, Throwable cause) {
        super(message, cause)
    }

    CustomException(Throwable cause) {
        super(cause)
    }
}
