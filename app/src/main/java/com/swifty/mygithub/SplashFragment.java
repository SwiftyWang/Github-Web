package com.swifty.mygithub;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.swifty.mygithub.storage.Pref;

/**
 * Created by swifty on 14/12/2016.
 */

public class SplashFragment extends Fragment {

    private EditText editText;
    private View view;

    public void goAction() {
        if (TextUtils.isEmpty(editText.getText().toString())) {
            Snackbar.make(view, getString(R.string.please_input_username), Snackbar.LENGTH_SHORT).show();
        } else {
            Pref.getInstance().putUserName(editText.getText().toString());
            if (getActivity() instanceof MainActivity) {
                ((MainActivity) getActivity()).addWebFragment();
            }
        }

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        view = inflater.inflate(R.layout.fragment_splash, container, false);
        editText = (EditText) view.findViewById(R.id.user_input);
        view.findViewById(R.id.go).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goAction();
            }
        });
        return view;
    }
}
