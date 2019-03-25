package com.vasanth.httpclient.urlconnection

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import com.google.gson.Gson
import com.google.gson.JsonParseException
import com.vasanth.httpclient.HttpClient
import com.vasanth.httpclient.HttpClientException
import com.vasanth.httpclient.HttpResponseListener
import java.io.*
import java.net.URL
import javax.inject.Inject
import javax.inject.Singleton
import javax.net.ssl.HttpsURLConnection


/**
 * HttpClient implementation using Android HttpUrlConnection.
 *
 * @author Vasanth
 */
@Singleton
class UrlConnectionHttpClientImpl @Inject constructor(private val appContext: Context, private val gson: Gson) :
    HttpClient {

    // HttpClient Methods.
    override fun <T> getRequest(
        url: String,
        headers: Map<String, String>?,
        classOfT: Class<T>,
        listener: HttpResponseListener<T>
    ) {
        if (isNetworkConnectionAvailable()) {
            try {
                val response = downloadUrl(urlString = url, headers = headers, classOfT = classOfT)
                listener.onSuccessResponse(response)
            } catch (httpClientExp: HttpClientException) {
                listener.onErrorResponse(httpClientExp)
            }
        } else {
            listener.onErrorResponse(
                HttpClientException(
                    errorCode = HttpClientException.ErrorCode.NO_CONNECTION_ERROR,
                    httpStatusCode = -1
                )
            )
        }

    }

    // Private Methods.

    /**
     * Used to check if network is connected or not.
     */
    private fun isNetworkConnectionAvailable(): Boolean {
        val cm = appContext.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetwork: NetworkInfo? = cm.activeNetworkInfo
        val isConnected: Boolean = activeNetwork?.isConnected == true
        return isConnected
    }

    /**
     * Given a URL, sets up a connection and gets the HTTP response body from the server.
     * If the network request is successful, it returns the response body in an object of the specified class. Otherwise,
     * it will throw an HttpClientException.
     */
    @Throws(HttpClientException::class)
    private fun <T> downloadUrl(urlString: String, headers: Map<String, String>?, classOfT: Class<T>): T {
        var connection: HttpsURLConnection? = null

        try {
            val url = URL(urlString)
            connection = (url.openConnection() as HttpsURLConnection)
            if (headers != null) {
                for ((k, v) in headers) {
                    connection.setRequestProperty(k, v)
                }
            }
            connection.doInput = true
            connection.requestMethod = "GET"
            connection.connect()

            val responseCode = connection.responseCode
            if (responseCode != HttpsURLConnection.HTTP_OK) {
                throw HttpClientException(
                    errorCode = HttpClientException.ErrorCode.HTTP_ERROR,
                    httpStatusCode = responseCode
                )
            }

            val responseJson = readStream(stream = connection.inputStream)
            val response = parseResponse<T>(response = responseJson, classOfT = classOfT)
            return response
        } catch (httpClientExp: HttpClientException) {
            throw httpClientExp
        } catch (e: Exception) {
            val responseCode = connection?.responseCode ?: -1
            throw HttpClientException(
                errorCode = HttpClientException.ErrorCode.HTTP_ERROR,
                httpStatusCode = responseCode
            )
        }
    }

    /**
     * Converts the contents of an InputStream to a String.
     */
    @Throws(IOException::class, UnsupportedEncodingException::class)
    fun readStream(stream: InputStream, maxReadSize: Int): String {
        val reader: Reader? = InputStreamReader(stream, "UTF-8")
        val rawBuffer = CharArray(maxReadSize)
        val buffer = StringBuffer()
        var readSize: Int = reader?.read(rawBuffer) ?: -1
        var maxReadBytes = maxReadSize
        while (readSize != -1 && maxReadBytes > 0) {
            if (readSize > maxReadBytes) {
                readSize = maxReadBytes
            }
            buffer.append(rawBuffer, 0, readSize)
            maxReadBytes -= readSize
            readSize = reader?.read(rawBuffer) ?: -1
        }
        return buffer.toString()
    }

    /**
     * Converts the contents of an InputStream to a String.
     */
    @Throws(IOException::class)
    fun readStream(stream: InputStream): String {
        val stringBuilder = StringBuilder()
        var line: String? = null

        val bufferedReader = BufferedReader(InputStreamReader(stream, "UTF-8"))
        line = bufferedReader.readLine()
        while (line != null) {
            stringBuilder.append(line)
            line = bufferedReader.readLine()
        }

        return stringBuilder.toString()
    }

    /**
     * Parses the given Json response into an object of the specified class.
     */
    @Throws(HttpClientException::class)
    private fun <T> parseResponse(response: String, classOfT: Class<T>): T {
        try {
            val parsedResponse = gson.fromJson(response, classOfT)
            return parsedResponse
        } catch (e: JsonParseException) {
            throw HttpClientException(errorCode = HttpClientException.ErrorCode.PARSE_ERROR, httpStatusCode = -1)
        }

    }


}