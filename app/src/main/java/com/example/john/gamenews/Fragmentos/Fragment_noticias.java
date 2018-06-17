package com.example.john.gamenews.Fragmentos;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;

import com.example.john.gamenews.Adapters.AdapterRecycler;
import com.example.john.gamenews.Interface.GameNewsAPI;
import com.example.john.gamenews.MainActivity;
import com.example.john.gamenews.Object.News;
import com.example.john.gamenews.R;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class Fragment_noticias extends Fragment {
    private OnFragmentInteractionListener mListener;

    private static final String TAG = "NEWS";

    private List<News> listaNews;
    private List<News> aux = null;
    private RecyclerView recyclerView;
    private AdapterRecycler adapter;
    private CheckBox checkBox;
    View view;

    public Fragment_noticias() {
    }

    public static Fragment_noticias newInstance(String param1, String param2) {
        Fragment_noticias fragment = new Fragment_noticias();
        Bundle args = new Bundle();

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_fragment_noticias, container, false);
        checkBox = view.findViewById(R.id.idCheckBox);


        LlenarNoticias(view);

        return view;
    }

    private void LlenarNoticias(final View view) {
        listaNews = new ArrayList<>();

        String baseurl = "http://gamenewsuca.herokuapp.com/";
        Retrofit retrofit = null;
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(baseurl)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }

        GameNewsAPI servicio = retrofit.create(GameNewsAPI.class);
        Call<List<News>> autentic = servicio.signNews("Bearer " + MainActivity.loginUsuario.getToken());

        autentic.enqueue(new Callback<List<News>>() {
            @Override
            public void onResponse(Call<List<News>> call, Response<List<News>> response) {

                aux = response.body();

                String titulo, cuerpo, juego, coverImage, descripcion;

                for (int i = 0; i < aux.size(); i++) {

                    if (aux.get(i).getCoverImage() != null) {

                        if (aux.get(i).getTitle() == null) {
                            titulo = "No hay titulo";
                        } else {
                            titulo = aux.get(i).getTitle();
                        }

                        if (aux.get(i).getBody() == null) {
                            cuerpo = "No hay body";
                        } else {
                            cuerpo = aux.get(i).getBody();
                        }

                        if (aux.get(i).getGame() == null) {
                            juego = "No hay juego";
                        } else {
                            juego = aux.get(i).getGame();
                        }

                        if (aux.get(i).getCoverImage() == null) {
                            coverImage = "No hay imagen";
                        } else {
                            coverImage = aux.get(i).getCoverImage();
                        }

                        if (aux.get(i).getDescription() == null) {
                            descripcion = "No hay descripcion";
                        } else {
                            descripcion = aux.get(i).getDescription();
                        }

                        listaNews.add(new News(aux.get(i).get_id(), titulo, cuerpo, juego, coverImage, descripcion, aux.get(i).getCreatedDate(), aux.get(i).get__v()));
                    }
                }

                recyclerView = view.findViewById(R.id.recyclerNews);
                adapter = new AdapterRecycler(listaNews, getContext());
                GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 2);
                gridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
                    @Override
                    public int getSpanSize(int position) {
                        if (position % 3 == 0) {
                            return 2;
                        } else {
                            return 1;
                        }
                    }
                });
                recyclerView.setLayoutManager(gridLayoutManager);
                recyclerView.setAdapter(adapter);

            }

            @Override
            public void onFailure(Call<List<News>> call, Throwable t) {
                Log.d(TAG, "onFailure" + t.getMessage());
            }
        });
    }

    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }


    public interface OnFragmentInteractionListener {

        void onFragmentInteraction(Uri uri);
    }
}
