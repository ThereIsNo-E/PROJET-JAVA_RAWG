package org.main.utils;
import okhttp3.HttpUrl;
import okhttp3.Request;


public class UrlBuilder {
    private final Request.Builder requestBuilder =  new Request.Builder();
    private final HttpUrl.Builder urlBuilder;

    public UrlBuilder(String url) {
        this.urlBuilder = HttpUrl.parse(url).newBuilder();
    }

    public void addSegment(String segment) {
        urlBuilder.addPathSegment(segment);
    }

    public void addParam(String param, String value) {
        urlBuilder.addQueryParameter(param, value);
    }

    public Request build() {
        return requestBuilder.url(urlBuilder.build())
                .get()
                .build();
    }
}
