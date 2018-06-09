package com.example.john.gamenews.Fragmentos;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.john.gamenews.Adapters.AdapterRecycler;
import com.example.john.gamenews.Helpers.RetrofitUser;
import com.example.john.gamenews.Interface.GameNewsAPI;
import com.example.john.gamenews.MainActivity;
import com.example.john.gamenews.Object.News;
import com.example.john.gamenews.R;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class Fragment_noticias extends Fragment {
    private OnFragmentInteractionListener mListener;

    private static final String TAG = "NEWS";

    ArrayList<News> listaNews;
    RecyclerView recyclerView;
    AdapterRecycler adapter;

    private GameNewsAPI servicio;

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

    private void LlenarNoticias(final View view) {
        listaNews = new ArrayList<>();

        servicio = RetrofitUser.getRetrofitInstance().create(GameNewsAPI.class);

        Call<List<News>> autentic = servicio.signNews("Bearer " + MainActivity.loginUsuario.getToken());
        autentic.enqueue(new Callback<List<News>>() {

            @Override
            public void onResponse(Call<List<News>> call, Response<List<News>> response) {

                if (response.isSuccessful()) {

                    List<News> noticias = response.body();

                    for (int i = 0; i < noticias.size(); i++) {
                        News n = noticias.get(i);
                        Log.d(TAG, "Noticia" + n.getTitle());
                    }

                     /*   for (News nuevo : noticias) {
                            listaNews.add(nuevo);
                        }
                    */

                    recyclerView = view.findViewById(R.id.recyclerNews);
                    adapter = new AdapterRecycler(listaNews, getContext());
                    recyclerView.setAdapter(adapter);
                    recyclerView.setHasFixedSize(true);
                    recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

                } else {
                    Log.d(TAG, "onResponse: " + response.errorBody());
                }
            }

            @Override
            public void onFailure(Call<List<News>> call, Throwable t) {
                Log.d(TAG, "onFailure" + t.getMessage());
            }
        });
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_fragment_noticias, container, false);

        LlenarNoticias(view);

        return view;
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
