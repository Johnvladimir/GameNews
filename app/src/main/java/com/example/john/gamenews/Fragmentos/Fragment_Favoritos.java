package com.example.john.gamenews.Fragmentos;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;

import com.example.john.gamenews.Fragmentos.Fragment_noticias;
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

public class Fragment_Favoritos extends Fragment {

    private static String id;
    private static final String TAG = "FAVORITOS";

    private List<News> listaFavs;
    private List<News> aux = null;
    private RecyclerView recyclerView;
    private AdapterRecycler adapter;
    private Fragment_noticias news;
    View view;
    private CheckBox checkBox;

    private OnFragmentInteractionListener mListener;

    public Fragment_Favoritos() {
    }

    public static Fragment_Favoritos newInstance(String param1) {
        Fragment_Favoritos fragment = new Fragment_Favoritos();

        id = param1;

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        news = new Fragment_noticias();

        view = inflater.inflate(R.layout.fragment_fragment__favoritos, container, false);
        checkBox = view.findViewById(R.id.idCheckBox);

        return view;
    }

    private void LlenarFavs(final View view, String id) {
        listaFavs = new ArrayList<>();

        String baseurl = "http://gamenewsuca.herokuapp.com/";
        Retrofit retrofit = null;
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(baseurl)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }

        GameNewsAPI servicio = retrofit.create(GameNewsAPI.class);
        Call<String> autentic = servicio.addFav("Bearer " + MainActivity.loginUsuario.getToken(), id);

        autentic.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {

            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {

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
