package sgaw.playground.com.swapiapp.data;

import android.content.Context;
import android.graphics.Movie;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import retrofit.Call;
import retrofit.Callback;
import retrofit.GsonConverterFactory;
import retrofit.Response;
import retrofit.Retrofit;
import retrofit.http.GET;
import retrofit.http.Path;
import retrofit.http.Query;

/**
 * Created by shirleygaw on 13/12/15.
 */
public class SWAPIApi {
    private static final String BASE_URL = "http://swapi.co";

    interface SWAPIService {
        @GET("/api/people/")
        Call<Universe.SwapiResponse> listCharacters(@Query("page") int page);
    }

    // BUGUB(sgaw): This probably can be removed, but I don't really understand GSON
    // converters that well.
    public interface MovieCharactersCallback {
        /** Successful HTTP response. */
        void onResponse(List<MovieCharacter> characters);

        /** Invoked when a network or unexpected exception occurred during the HTTP request. */
        void onFailure(Throwable t);
    }

    private static SWAPIApi sSingleton = null;
    private final SWAPIService mService;

    private SWAPIApi(SWAPIService service) {
        mService = service;
    }

    public static SWAPIApi get() {
        if (sSingleton == null) {
            sSingleton = new SWAPIApi(getService());
        }
        return sSingleton;
    }

    public void getMovieCharacters(int page, final MovieCharactersCallback movieCharactersCallback) {
        Call<Universe.SwapiResponse> asynccall = mService.listCharacters(page);
        asynccall.enqueue(new Callback<Universe.SwapiResponse>(){
            @Override
            public void onResponse(Response<Universe.SwapiResponse> response, Retrofit retrofit) {
                List<MovieCharacter> results = new ArrayList<MovieCharacter>(
                        response.body().getMovieCharacters().length);
                for (MovieCharacter movieCharacter: response.body().getMovieCharacters()) {
                    results.add(movieCharacter);
                }
                movieCharactersCallback.onResponse(results);
            }

            @Override
            public void onFailure(Throwable t) {
                movieCharactersCallback.onFailure(t);
            }
        });
    }

    private static SWAPIService getService() {
        Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        return retrofit.create(SWAPIService.class);
    }
}
