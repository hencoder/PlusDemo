package com.hencoder.a22.mvp;

import com.hencoder.a22.data.DataCenter;

public class Presenter {
    private IView iView;

    public Presenter(IView iView) {
        this.iView = iView;
    }

    public void load() {
        String[] data = DataCenter.getData();
        iView.showData(data[0], data[1]);
    }

    public interface IView {
        void showData(String data1, String data2);
    }
}
