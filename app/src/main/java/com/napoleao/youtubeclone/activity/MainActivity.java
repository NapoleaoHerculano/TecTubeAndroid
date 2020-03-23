package com.napoleao.youtubeclone.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;

import com.miguelcatalan.materialsearchview.MaterialSearchView;
import com.napoleao.youtubeclone.R;
import com.napoleao.youtubeclone.adapter.AdapterVideos;
import com.napoleao.youtubeclone.api.YouTubeService;
import com.napoleao.youtubeclone.helper.RecyclerItemClickListener;
import com.napoleao.youtubeclone.helper.RetrofitConfig;
import com.napoleao.youtubeclone.helper.YouTubeConfig;
import com.napoleao.youtubeclone.model.Item;
import com.napoleao.youtubeclone.model.Resultado;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MainActivity extends AppCompatActivity {

    //Widgets
    private RecyclerView recyclerVideos;
    private MaterialSearchView materialSearchView;

    private List<Item> videos = new ArrayList<>();
    private Resultado resultado;
    private AdapterVideos adapterVideos;
    private Retrofit retrofit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Inicializando componentes de interface
        recyclerVideos = findViewById(R.id.recyclerVideos);
        materialSearchView = findViewById(R.id.searchView);

        //Instanciando Retrofit
        retrofit = RetrofitConfig.getRetrofit();

        //Configurando Toolbar
        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("TecTube");
        setSupportActionBar(toolbar);

        //Requisitando Vídeos
        obterVideos("");

        //Configurando métodos para o SearchView
        materialSearchView.setOnQueryTextListener(new MaterialSearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                obterVideos(query);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });

        materialSearchView.setOnSearchViewListener(new MaterialSearchView.SearchViewListener() {
            @Override
            public void onSearchViewShown() {

            }

            @Override
            public void onSearchViewClosed() {
                obterVideos("");

            }
        });
    }

    /**
     * Faz a requisição à API e recupera uma lista de vídeos.
     * @param pesquisa utlizado para busca no canal, na chamada padrão (onCreate) deve setar "".
     */
    public void obterVideos(String pesquisa){
        String q = pesquisa.replaceAll(" ", "+");

        YouTubeService youTubeService = retrofit.create( YouTubeService.class);
        youTubeService.recuperarVideos(
                "snippet", "date", "20",
                YouTubeConfig.GOOGLE_YOUTUBE_API_KEY, YouTubeConfig.CANAL_ID, q
        ).enqueue(new Callback<Resultado>() {
            @Override
            public void onResponse(Call<Resultado> call, Response<Resultado> response) {
                if( response.isSuccessful()){
                    resultado = response.body();
                    videos = resultado.items;
                    configurarRecyclerView();
                }
            }

            @Override
            public void onFailure(Call<Resultado> call, Throwable t) {

            }
        });
    }

    public void configurarRecyclerView(){
        adapterVideos = new AdapterVideos(videos, this);
        recyclerVideos.setHasFixedSize(true);
        recyclerVideos.setLayoutManager(new LinearLayoutManager(this));
        recyclerVideos.setAdapter(adapterVideos);

        /**
         * Configurando evento de clique no RecyclerView.
         */
        recyclerVideos.addOnItemTouchListener(
                new RecyclerItemClickListener(
                        this,
                        recyclerVideos,
                        new RecyclerItemClickListener.OnItemClickListener() {
                            @Override
                            public void onItemClick(View view, int position) {
                                Item video = videos.get(position);
                                String idVideo = video.id.videoId;

                                Intent intent = new Intent(MainActivity.this, PlayerActivity.class);
                                intent.putExtra("idVideo", idVideo);
                                startActivity(intent);
                            }

                            @Override
                            public void onLongItemClick(View view, int position) {

                            }

                            @Override
                            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                            }
                        }
                )
        );
    }

    /**
     * Configurando o SearchView.
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_main, menu);

        MenuItem item = menu.findItem(R.id.menu_search);
        materialSearchView.setMenuItem(item);
        return true;
    }
}
