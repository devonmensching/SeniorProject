package com.example.user.seniorseminarproject.net;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.HttpHeaderParser;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

import java.io.UnsupportedEncodingException;
import java.util.Map;

/**
 * This class creates a custom GSON Request.
 * It is used to send the data from the local
 * SQLite tables to the server.
 */

public class CustomVolleyGsonRequest extends Request<Object> {
    private final Gson gson = new Gson();
    private final Class<Object> clazz;
    //private final Map<String, String> headers;
    private final Response.Listener<Object> listener;
    private Map<String,String> params;

    /**
     * Make a GET request and return a parsed object from JSON.
     *
     * @param url URL of the request to make
     * @param clazz Relevant class object, for Gson's reflection
     * @param params
     */
    public CustomVolleyGsonRequest(String url, Class<Object> clazz, Map<String, String> params,
                                   Response.Listener<Object> listener, Response.ErrorListener errorListener) {
        super(Method.POST, url, errorListener);
        this.clazz = clazz;
        // this.headers = headers;
        this.params = params;
        this.listener = listener;
    }

    @Override
    public Map<String, String> getHeaders() throws AuthFailureError {
        return super.getHeaders();
    }

    @Override
    protected void deliverResponse(Object response) {
        listener.onResponse(response);
    }

    @Override
    protected Map<String,String> getParams() throws com.android.volley.AuthFailureError
    {
        return params;
    }

    @Override
    protected Response<Object> parseNetworkResponse(NetworkResponse response) {
        String json="";
        try {
            json = new String(
                    response.data,
                    HttpHeaderParser.parseCharset(response.headers));
            return Response.success(
                    gson.fromJson(json, clazz),
                    HttpHeaderParser.parseCacheHeaders(response));
        } catch (UnsupportedEncodingException e) {
            return Response.error(new ParseError(e));
        } catch (JsonSyntaxException e) {
            return Response.error(new ParseError(e));
        }
    }
}
