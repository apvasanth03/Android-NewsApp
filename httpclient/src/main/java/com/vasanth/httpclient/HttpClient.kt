package com.vasanth.httpclient

/**
 * Interface defines Common HTTP Methods.
 *
 * Hence we need to implement this interface using some HTTP client (like Volley, Retrofit etc) and
 * provide functionality for the HTTP methods.
 *
 * Inside our application we will only refer this interface for any HTTP calls there by abstracting
 * the third party HTTP client implementation.
 *
 * @author Vasanth
 */
interface HttpClient {

    /**
     * Used to make "HTTP GET REQUEST" & get response using callback in UI Thread.
     *
     * @param url         Request Url.
     * @param headers     Request headers.
     * @param listener    Listener used to get callback on request completes.
     * @param <T>         Class to which response has to be parsed.
     */
    fun <T> getRequest(
        url: String,
        headers: Map<String, String>?,
        classOfT: Class<T>,
        listener: HttpResponseListener<T>
    )

}