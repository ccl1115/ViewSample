package com.simon.example.layout;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.simon.example.layout.view.NoUpdateLayout;

import java.util.Date;

/**
 * @author Simon Yu
 */
public class NoUpdateFragment extends SampleDetailFragment implements View.OnClickListener {
    private NoUpdateLayout mNoUpdateLayout;
    private Button mSetText;
    private Button mUpdateParent;

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        mNoUpdateLayout = (NoUpdateLayout) view.findViewById(R.id.no_update_layout);
        mSetText = (Button) view.findViewById(android.R.id.button1);
        mUpdateParent = (Button) view.findViewById(android.R.id.button2);

        mSetText.setOnClickListener(this);
        mUpdateParent.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        final int id = view.getId();
        switch (id) {
            case android.R.id.button1:
                mNoUpdateLayout.setText("Update at " + String.valueOf(new Date().getTime()));
                break;
            case android.R.id.button2:
                mNoUpdateLayout.requestLayout();
                mNoUpdateLayout.invalidate();
                break;
        }
    }
}
