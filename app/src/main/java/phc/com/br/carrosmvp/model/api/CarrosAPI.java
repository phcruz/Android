package phc.com.br.carrosmvp.model.api;

import java.util.List;

import phc.com.br.carrosmvp.model.domain.Carro;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

/**
 * Created by paulo on 30/05/2018.
 */

public interface CarrosAPI {

    @GET("listarTodosCarros")
    Call<List<Carro>> getCarrosWs();

    @POST("alterar")
    Call<Carro> postAtualizaCarro(@Body Carro carro);
}
