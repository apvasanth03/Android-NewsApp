package com.vasanth.httpclient

/**
 * Interface use to send callback once request completes.
 *
 * @author Vasanth
 */
interface HttpResponseListener<T> {

    /**
     * Gets called on request success.
     *
     * @param response Response.
     */
    fun onSuccessResponse(response: T)

    /**
     * Gets called on request failure.
     *
     * @param exception [HttpClientException] provides information about the error occurred.
     */
    fun onErrorResponse(exception: HttpClientException)
}