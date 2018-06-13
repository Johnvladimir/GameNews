package com.example.john.gamenews.Fragmentos;

import android.content.Context;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.john.gamenews.Adapters.AdapterTabLayout;
import com.example.john.gamenews.Clases.Utilidades_Tab;
import com.example.john.gamenews.Fragmentos.Fragments_Add_Tabs.Fragment_Tab_General;
import com.example.john.gamenews.Fragmentos.Fragments_Add_Tabs.Fragment_Tab_Image;
import com.example.john.gamenews.Fragmentos.Fragments_Add_Tabs.Fragment_Tab_Top_Players;
import com.example.john.gamenews.R;

public class Fragment_Tab_lol extends Fragment {


    private OnFragmentInteractionListener mListener;

    private AppBarLayout appBar;
    private TabLayout pestañas;
    private ViewPager viewPager;
    View view;

    private static String dato;

    public Fragment_Tab_lol() {
    }

    public static Fragment_Tab_lol newInstance(String param1) {
        Fragment_Tab_lol fragment = new Fragment_Tab_lol();

        dato = param1;

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_fragment__tab_lol, container, false);

        if (Utilidades_Tab.rotacion == 0) {
            View parent = (View) container.getParent();

            if (appBar == null) {
                appBar = parent.findViewById(R.id.appBar);
                pestañas = new TabLayout(getActivity());
                pestañas.setTabTextColors(Color.parseColor("#FFFFFF"), Color.parseColor("#FFFFFF"));
                appBar.addView(pestañas);

                viewPager = view.findViewById(R.id.viewPager);

                llenarViewPager(viewPager);
                viewPager.addOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
                    @Override
                    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                        super.onPageScrolled(position, positionOffset, positionOffsetPixels);
                    }
                });
                pestañas.setupWithViewPager(viewPager);
            }

            pestañas.setTabGravity(TabLayout.GRAVITY_FILL);

        } else {
            Utilidades_Tab.rotacion = 1;
        }

        return view;
    }

    private void llenarViewPager(ViewPager viewPager) {
        AdapterTabLayout adapter = new AdapterTabLayout(getFragmentManager());

        adapter.addFragment(new Fragment_Tab_General().newInstance(dato), "General");
        adapter.addFragment(new Fragment_Tab_Top_Players().newInstance(dato), "Top Players");
        adapter.addFragment(new Fragment_Tab_Image().newInstance(dato), "Images");

        viewPager.setAdapter(adapter);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (Utilidades_Tab.rotacion == 0) {
            appBar.removeView(pestañas);
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
