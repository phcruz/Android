package phc.com.br.carrosmvp.mvp;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;

import phc.com.br.carrosmvp.model.api.CarrosAPI;
import phc.com.br.carrosmvp.model.domain.Carro;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by paulo on 30/05/2018.
 */

public class ModelImpl implements MVP.Model {

    private final String URL_BASE = "http://192.168.25.187:8080/ws-carros-mvp/carro/";
    private Retrofit retrofit;
    private MVP.Presenter presenter;

    public ModelImpl(MVP.Presenter presenter) {
        Gson gson = new GsonBuilder().setLenient().create();
        Retrofit.Builder builder = new Retrofit.Builder();
        builder.baseUrl(URL_BASE);
        builder.addConverterFactory(GsonConverterFactory.create(gson));
        retrofit = builder.build();

        this.presenter = presenter;
    }


    @Override
    public void retrieveCarros() {
        presenter.showProgressBar(true);
        CarrosAPI carrosApi = retrofit.create(CarrosAPI.class);

        carrosApi.getCarrosWs().enqueue(new Callback<List<Carro>>() {
            @Override
            public void onResponse(Call<List<Carro>> call, Response<List<Carro>> response) {
                List<Carro> carros = response.body();

                presenter.updateListaRecycler(carros);
                presenter.showProgressBar(false);
            }

            @Override
            public void onFailure(Call<List<Carro>> call, Throwable t) {
                presenter.showToast("Ocorreu um erro: " + t.getMessage());
                presenter.showProgressBar(false);
            }
        });
    }

    @Override
    public void updateIsFavoritoCarro(Carro carro) {
        presenter.showProgressBar(true);
        CarrosAPI carrosApi = retrofit.create(CarrosAPI.class);

        carrosApi.postAtualizaCarro(carro).enqueue(new Callback<Carro>() {
            @Override
            public void onResponse(Call<Carro> call, Response<Carro> response) {
                Carro carro = response.body();
                presenter.updateItemRecycler(carro);
            }

            @Override
            public void onFailure(Call<Carro> call, Throwable t) {
                presenter.showToast("Ocorreu um erro: " + t.getMessage());
                presenter.showProgressBar(false);
            }
        });
    }
}
