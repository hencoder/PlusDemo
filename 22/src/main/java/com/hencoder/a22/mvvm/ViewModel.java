package com.hencoder.a22.mvvm;

import android.widget.TextView;

import com.hencoder.a22.data.DataCenter;

public class ViewModel {
    TextAttr data1 = new TextAttr();
    TextAttr data2 = new TextAttr();

    ViewModel(ViewBinder binder, TextView text1View, TextView text2View) {
        binder.bind(text1View, data1);
        binder.bind(text2View, data2);
    }

    public void load() {
        String[] data = DataCenter.getData();
        data1.setText(data[0]);
        data2.setText(data[1]);
    }

    static class TextAttr {
        CharSequence text;
        private OnChangeListener listener;

        public void setText(CharSequence text) {
            this.text = text;
            if (listener != null) {
                listener.onChange(text);
            }
        }

        void setOnChangeListener(OnChangeListener listener) {
            this.listener = listener;
        }

        interface OnChangeListener {
            void onChange(CharSequence newText);
        }
    }
}
