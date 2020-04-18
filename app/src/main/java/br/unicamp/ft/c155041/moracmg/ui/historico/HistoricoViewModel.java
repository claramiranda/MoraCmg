package br.unicamp.ft.c155041.moracmg.ui.historico;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class HistoricoViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public HistoricoViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("Esse é o seu histórico de aplicações, todas as vagas em que você se aplicou aparecerão aqui.");
    }

    public LiveData<String> getText() {
        return mText;
    }
}