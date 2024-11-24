package com.example.vendasredessociais;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

public class InicioFragment extends Fragment {
    /*
     *@author:<Fabiola Rodrigues dos Santos / RA: 1110482313011>
     */

    private View view;

    public InicioFragment() {
        super();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_inicio, container, false);
        return view;
    }
}