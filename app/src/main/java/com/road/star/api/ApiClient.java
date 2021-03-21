package com.road.star.api;

import com.facebook.stetho.okhttp3.StethoInterceptor;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.road.star.BuildConfig;

import java.util.concurrent.TimeUnit;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLSession;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;



public class ApiClient {

    private static Retrofit retrofit = null;

    public static Retrofit getClient() {

        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

        if (retrofit == null) {

            OkHttpClient client = null;

            if (BuildConfig.debug) {
                HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
                logging.setLevel(HttpLoggingInterceptor.Level.HEADERS);
                logging.setLevel(HttpLoggingInterceptor.Level.BODY);

                client = new OkHttpClient.Builder()
                        .writeTimeout(1000, TimeUnit.SECONDS).readTimeout(1000, TimeUnit.SECONDS)
                        .connectTimeout(1000, TimeUnit.SECONDS)
                        .addInterceptor(logging)
                        .addNetworkInterceptor(new StethoInterceptor())
                        .hostnameVerifier(new HostnameVerifier() {
                            @Override
                            public boolean verify(String hostname, SSLSession session) {
                                HostnameVerifier hv = HttpsURLConnection.getDefaultHostnameVerifier();
                                return hv.verify("roadstartrips.com", session);

                            }
                        }).build();

            } else {
                HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
                logging.setLevel(HttpLoggingInterceptor.Level.HEADERS);
                logging.setLevel(HttpLoggingInterceptor.Level.BODY);

                client = new OkHttpClient.Builder()
                        .writeTimeout(1000, TimeUnit.SECONDS).readTimeout(1000, TimeUnit.SECONDS)
                        .connectTimeout(1000, TimeUnit.SECONDS)
                        .addInterceptor(logging)
                        .hostnameVerifier(new HostnameVerifier() {
                            @Override
                            public boolean verify(String hostname, SSLSession session) {
                                HostnameVerifier hv = HttpsURLConnection.getDefaultHostnameVerifier();
                                return hv.verify("roadstartrips.com", session);

                            }
                        }).build();

            }

            retrofit = new Retrofit.Builder()
                    .baseUrl(BuildConfig.apiUrl)
                    .client(client)
                    .addConverterFactory(ScalarsConverterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .build();
        }

        return retrofit;
    }
}
