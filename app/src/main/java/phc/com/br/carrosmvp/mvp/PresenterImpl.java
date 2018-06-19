package phc.com.br.carrosmvp.mvp;

import android.content.Context;
import android.os.Bundle;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import phc.com.br.carrosmvp.model.domain.Carro;

/**
 * Created by paulo on 30/05/2018.
 */

public class PresenterImpl implements MVP.Presenter {

    private MVP.Model model;
    private MVP.View view;
    private List<Carro> carros = new ArrayList<>();

    public PresenterImpl() {
        model = new ModelImpl(this);
    }

    @Override
    public void setView(MVP.View view) {
        this.view = view;
    }

    @Override
    public Context getContext() {
        return (Context) view;
    }

    @Override
    public void retrieveCarros(Bundle savedInstanceState) {
        if (savedInstanceState != null) {
            carros = savedInstanceState.getParcelableArrayList(MVP.View.CARROS_KEY);
            return;
        }
        model.retrieveCarros();
    }

    @Override
    public void updateIsFavoritoCarro(Carro carro) {
        carro.setFavorito(!carro.isFavorito());
        model.updateIsFavoritoCarro(carro);
    }

    @Override
    public void showToast(String mensagem) {
        view.showToast(mensagem);
    }

    @Override
    public void showProgressBar(boolean status) {
        int visibilidade = status ? View.VISIBLE : View.GONE;
        view.showProgressBar(visibilidade);
    }

    @Override
    public void updateListaRecycler(List<Carro> carros) {
        this.carros.clear();
        this.carros.addAll(carros);
        view.updateListaRecycler();
    }

    @Override
    public void updateItemRecycler(Carro carro) {
        for (int i = 0; i < carros.size(); i++) {
            if (carros.get(i).getId() == carro.getId()) {
                carros.get(i).setFavorito(carro.isFavorito());
                view.updateItemRecycler(i);
                break;
            }
        }
    }

    @Override
    public List<Carro> getCarros() {
        return carros;
    }
}
