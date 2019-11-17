package br.unicamp.ft.c155041.moracmg.ui.buscas;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class BuscasViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public BuscasViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is slideshow fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}