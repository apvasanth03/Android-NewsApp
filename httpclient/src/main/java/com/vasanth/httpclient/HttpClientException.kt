package com.vasanth.httpclient

/**
 * Class gives information about the exception occurred during HTTPClientApi call.
 *
 * @author Vasanth
 */
class HttpClientException(errorCode: ErrorCode, httpStatusCode: Int) : Exception() {

    /**
     * ErrorCode Enum represents the kind of exception occurred.
     */
    enum class ErrorCode {

        // No connectivity error.
        NO_CONNECTION_ERROR,

        // Response could not be parsed error.
        PARSE_ERROR,

        // Http error, see HttpStatusCode for more info.
        HTTP_ERROR
    }

    // Properties.
    val errorCode: ErrorCode
    val httpStatusCode: Int

    // Initializer.
    init {
        this.errorCode = errorCode
        this.httpStatusCode = httpStatusCode
    }
}