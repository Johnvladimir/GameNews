package com.example.john.gamenews.Fragmentos;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.john.gamenews.Adapters.AdapterRecycler;
import com.example.john.gamenews.Object.News;
import com.example.john.gamenews.R;

import java.util.ArrayList;


public class Fragment_noticias extends Fragment {

    ArrayList<News> listaNews;
    RecyclerView recyclerView;
    AdapterRecycler adapter;

    private OnFragmentInteractionListener mListener;

    public Fragment_noticias() {
    }

    public static Fragment_noticias newInstance(String param1, String param2) {
        Fragment_noticias fragment = new Fragment_noticias();
        Bundle args = new Bundle();

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        cargarNews();

        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_fragment_noticias, container, false);

        recyclerView = view.findViewById(R.id.recyclerNews);
        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(1,StaggeredGridLayoutManager.VERTICAL));

        adapter = new AdapterRecycler(listaNews, getContext());
        recyclerView.setAdapter(adapter);

        return view;
    }

    private void cargarNews() {
        listaNews = new ArrayList<>();

        listaNews.add(new News(" ", "Descripcion1"," "," ", " ", "Descripcion2", " ",1));
        listaNews.add(new News(" ", "Descripcion10"," "," ", " ", "Descripcion20", " ",1));
        listaNews.add(new News(" ", "Descripcion100"," "," ", " ", "Descripcion200", " ",1));
        listaNews.add(new News(" ", "Descripcion1000"," "," ", " ", "Descripcion2000", " ",1));
        listaNews.add(new News(" ", "Descripcion10000"," "," ", " ", "Descripcion20000", " ",1));
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
