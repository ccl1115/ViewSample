package com.simon.example.layout;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.simon.example.layout.skin.SkinService;
import com.simon.example.layout.skin.impl.DayTheme;
import com.simon.example.layout.skin.impl.NightTheme;

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
                if (theme.equals(DayTheme.NAME)) {
                    SkinService.applyTheme(getActivity(), NightTheme.NAME);
                } else {
                    SkinService.applyTheme(getActivity(), DayTheme.NAME);
                }
            }
        });
    }
}
