package br.unicamp.ft.c155041.moracmg.ui.moradias;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class MoradiasViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public MoradiasViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is moradias fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}