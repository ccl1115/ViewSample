package com.simon.example.layout;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.simon.example.layout.skin.SkinService;
import com.simon.example.layout.skin.impl.DaySkin;
import com.simon.example.layout.skin.impl.NightSkin;

/**
 * @author simon
 */
public class SkinSampleFragment extends SampleDetailFragment {

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        Button button = (Button) view.findViewById(R.id.toggle);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String theme = SkinService.getTheme();
                if (theme.equals(DaySkin.NAME)) {
                    SkinService.applySkin(getActivity(), NightSkin.NAME);
                } else {
                    SkinService.applySkin(getActivity(), DaySkin.NAME);
                }
            }
        });
    }
}
