package ma.projet.my_app.my_fragments;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class mViewModel extends ViewModel {
    private MutableLiveData<String> someText = new MutableLiveData<>();

    public LiveData<String> getSomeText() {
        return someText;
    }

    public void setSomeText(String text) {
        someText.setValue(text);
    }}
