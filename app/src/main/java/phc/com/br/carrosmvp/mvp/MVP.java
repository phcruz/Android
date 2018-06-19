package phc.com.br.carrosmvp.mvp;

import android.content.Context;
import android.os.Bundle;

import java.util.List;

import phc.com.br.carrosmvp.model.domain.Carro;

/**
 * Created by paulo on 30/05/2018.
 */

public interface MVP {

    interface Model {
        public void retrieveCarros();

        public void updateIsFavoritoCarro(Carro carro);
    }

    interface Presenter {
        public void retrieveCarros(Bundle savedInstanceState);

        public void updateIsFavoritoCarro(Carro carro);

        public void showToast(String mensagem);

        public void showProgressBar(boolean status);

        public void setView(MVP.View view);

        public Context getContext();

        public void updateListaRecycler(List<Carro> carros);

        public void updateItemRecycler(Carro carro);

        public List<Carro> getCarros();
    }

    interface View {
        String CARROS_KEY = "carros";

        public void showToast(String mensagem);

        public void showProgressBar(int visibilidade);

        public void updateListaRecycler();

        public void updateItemRecycler(int posicao);
    }

}
