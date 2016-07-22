package com.bluetooth.perk.fragmenttest;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.Toast;

/**
 * Created by perk on 2016/7/19.
 */
public class TitleFragment extends Fragment {
    private ImageButton mButton;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.title_fragment, container, false);
        mButton = (ImageButton) view.findViewById(R.id.id_title_left_btn);
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getActivity(), "i am an ImageButton in TitleFragment ! ", Toast.LENGTH_SHORT).show();
            }
        });
        return view;
    }
}
