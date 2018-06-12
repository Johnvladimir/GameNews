package com.example.john.gamenews.Fragmentos.Fragments_Add_Tabs;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.john.gamenews.Adapters.AdapterRecycler_Player;
import com.example.john.gamenews.Interface.GameNewsAPI;
import com.example.john.gamenews.MainActivity;
import com.example.john.gamenews.Object.Players;
import com.example.john.gamenews.R;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Fragment_Tab_Top_Players extends Fragment {
    private OnFragmentInteractionListener mListener;

    private static final String TAG = "PLAYERS";

    private List<Players> listPlayers;
    private List<Players> aux;
    private RecyclerView recyclerView;
    private AdapterRecycler_Player adapter;
    View view;

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
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_fragment__tab__top__players, container, false);

        llenarPlayers(view);

        return view;
    }

    private void llenarPlayers(final View view) {
        listPlayers = new ArrayList<>();

        String baseurl = "http://gamenewsuca.herokuapp.com/";
        Retrofit retrofit = null;
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(baseurl)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }

        GameNewsAPI servicio = retrofit.create(GameNewsAPI.class);
        Call<List<Players>> autenticar = servicio.signPlayers("Bearer " + MainActivity.loginUsuario.getToken());

        autenticar.enqueue(new Callback<List<Players>>() {
            @Override
            public void onResponse(Call<List<Players>> call, Response<List<Players>> response) {

                aux = response.body();

                String avatar, id, name, biografia, game;

                for (int j = 0; j < aux.size(); j++) {

                    if (aux.get(j).getGame().equals("lol")) {

                        if (aux.get(j).getAvatar() == null) {
                            avatar = "No existe";
                        } else {
                            avatar = aux.get(j).getAvatar();
                        }

                        if (aux.get(j).get_id() == null) {
                            id = "No existe";
                        } else {
                            id = aux.get(j).get_id();
                        }

                        if (aux.get(j).getName() == null) {
                            name = "No existe";
                        } else {
                            name = aux.get(j).getName();
                        }

                        if (aux.get(j).getBiografia() == null) {
                            biografia = "No existe";
                        } else {
                            biografia = aux.get(j).getBiografia();
                        }

                        if (aux.get(j).getGame() == null) {
                            game = "No existe";
                        } else {
                            game = aux.get(j).getGame();
                        }

                        listPlayers.add(new Players(avatar, id, name, biografia, game, aux.get(j).get__v()));
                    }
                }


                recyclerView = view.findViewById(R.id.recyclerPlayers);
                adapter = new AdapterRecycler_Player(listPlayers, getContext());
                recyclerView.setLayoutManager(new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL));
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<List<Players>> call, Throwable t) {
                Log.d(TAG, "onFailure" + t.getMessage());
            }
        });

    }

    //metodo a llenar Players


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
