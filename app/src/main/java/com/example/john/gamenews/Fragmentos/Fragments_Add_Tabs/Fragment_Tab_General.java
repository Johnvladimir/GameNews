package com.example.john.gamenews.Fragmentos.Fragments_Add_Tabs;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.john.gamenews.Adapters.AdapterRecycler;
import com.example.john.gamenews.Adapters.AdapterRecycler_Player;
import com.example.john.gamenews.Interface.GameNewsAPI;
import com.example.john.gamenews.MainActivity;
import com.example.john.gamenews.Object.News;
import com.example.john.gamenews.Object.Players;
import com.example.john.gamenews.R;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Fragment_Tab_General extends Fragment {

    private OnFragmentInteractionListener mListener;

    private List<News> listGeneral;
    private List<News> aux;
    private RecyclerView recyclerView;
    private AdapterRecycler adapter;
    View view;

    private static String dato;

    public Fragment_Tab_General() {
    }

    public static Fragment_Tab_General newInstance(String param1) {
        Fragment_Tab_General fragment = new Fragment_Tab_General();

        dato = param1;

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_fragment__tab__general, container, false);

        llenarGeneral(view);

        return view;
    }

    private void llenarGeneral(final View view) {
        listGeneral = new ArrayList<>();

        String baseurl = "http://gamenewsuca.herokuapp.com/";
        Retrofit retrofit = null;
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(baseurl)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }

        GameNewsAPI servicio = retrofit.create(GameNewsAPI.class);
        Call<List<News>> autenticar = servicio.signNews("Bearer " + MainActivity.loginUsuario.getToken());

        autenticar.enqueue(new Callback<List<News>>() {
            @Override
            public void onResponse(Call<List<News>> call, Response<List<News>> response) {
                aux = response.body();

                String titulo, cuerpo, juego, coverImage, descripcion, create_date;

                for (int i = 0; i < aux.size(); i++) {

                    if (aux.get(i).getCoverImage() != null && aux.get(i).getGame().equals(dato)) {

                        if (aux.get(i).getTitle() == null) {
                            titulo = "No hay titulo";
                        } else {
                            titulo = aux.get(i).getTitle();
                        }

                        if (aux.get(i).getBody() == null) {
                            cuerpo = "No hay titulo";
                        } else {
                            cuerpo = aux.get(i).getBody();
                        }

                        if (aux.get(i).getGame() == null) {
                            juego = "No hay titulo";
                        } else {
                            juego = aux.get(i).getGame();
                        }

                        if (aux.get(i).getCoverImage() == null) {
                            coverImage = "No hay titulo";
                        } else {
                            coverImage = aux.get(i).getCoverImage();
                        }

                        if (aux.get(i).getDescription() == null) {
                            descripcion = "No hay titulo";
                        } else {
                            descripcion = aux.get(i).getDescription();
                        }

                        if (aux.get(i).getCreatedDate() == null) {
                            create_date = "No hay titulo";
                        } else {
                            create_date = aux.get(i).getCreatedDate();
                        }

                        listGeneral.add(new News(aux.get(i).get_id(), titulo, cuerpo, juego, coverImage, descripcion, create_date, aux.get(i).get__v()));
                    }
                }

                recyclerView = view.findViewById(R.id.recyclerGeneral);
                adapter = new AdapterRecycler(listGeneral, getContext());
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