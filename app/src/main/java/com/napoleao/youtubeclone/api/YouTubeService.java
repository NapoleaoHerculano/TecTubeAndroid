package com.napoleao.youtubeclone.api;

import com.napoleao.youtubeclone.model.Resultado;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface YouTubeService {
    /**
     * Aqui é definido o tipo de requisição que eu desejo fazer
     * junto com os parâmetros. No caso desta API, utilizei os
     * parâmetros abaixo.
     * A anotação @query define quais são os parâmetros da requisição.
     * Eles devem ser escritos igual a documentação.
     */
    @GET("search")
    Call<Resultado> recuperarVideos(
            @Query("part") String part,
            @Query("order") String order,
            @Query("maxResults") String maxResults,
            @Query("key") String key,
            @Query("channelId") String channelId,
            @Query("q") String q

    );
}
