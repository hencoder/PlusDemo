package com.hencoder.a22.mvvm;

import android.text.Editable;
import android.text.TextWatcher;
import android.widget.TextView;

public class ViewBinder {
    void bind(final TextView textView, final ViewModel.TextAttr text) {
        textView.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (!s.equals(text.text)) {
                    text.setText(s);
                }
            }
        });
        text.setOnChangeListener(new ViewModel.TextAttr.OnChangeListener() {
            @Override
            public void onChange(CharSequence newText) {
                if (!newText.equals(textView.getText())) {
                    textView.setText(newText);
                }
            }
        });
    }
}
