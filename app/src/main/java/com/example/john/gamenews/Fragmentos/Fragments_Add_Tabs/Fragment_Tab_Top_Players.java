package com.example.john.gamenews.Fragmentos.Fragments_Add_Tabs;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.john.gamenews.Adapters.AdapterRecycler_Player;
import com.example.john.gamenews.Object.Players;
import com.example.john.gamenews.R;

import java.util.ArrayList;


public class Fragment_Tab_Top_Players extends Fragment {
    private OnFragmentInteractionListener mListener;

    ArrayList<Players> listPlayers;
    RecyclerView recyclerView;
    AdapterRecycler_Player adapter;

    public Fragment_Tab_Top_Players() {
    }

    //metodo generado
    public static Fragment_Tab_Top_Players newInstance(String param1, String param2) {
        Fragment_Tab_Top_Players fragment = new Fragment_Tab_Top_Players();
        Bundle args = new Bundle();

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        cargarPlayers();
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_fragment__tab__top__players, container, false);

        recyclerView = view.findViewById(R.id.recyclerPlayers);
        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL));
        adapter = new AdapterRecycler_Player(listPlayers, getContext());
        recyclerView.setAdapter(adapter);

        return view;
    }

    private void cargarPlayers() {
        listPlayers = new ArrayList<>();

        listPlayers.add(new Players(1, "Player 1", "Subtitulo 1", "", ""));
        listPlayers.add(new Players(1, "Player 2", "Subtitulo 2", "", ""));
        listPlayers.add(new Players(1, "Player 3", "Subtitulo 3", "", ""));
        listPlayers.add(new Players(1, "Player 4", "Subtitulo 4", "", ""));
        listPlayers.add(new Players(1, "Player 5", "Subtitulo 5", "", ""));
    }

    //metodo generado
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    //metodo generado
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

    //metodo generado
    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    //metodo generado
    public interface OnFragmentInteractionListener {
        void onFragmentInteraction(Uri uri);
    }
}
