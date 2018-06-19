package phc.com.br.carrosmvp.view;

import android.os.Bundle;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.Toolbar;
import android.widget.Toast;

import java.util.ArrayList;

import phc.com.br.carrosmvp.R;
import phc.com.br.carrosmvp.model.domain.Carro;
import phc.com.br.carrosmvp.mvp.MVP;
import phc.com.br.carrosmvp.mvp.PresenterImpl;

public class MainActivity extends AppCompatActivity implements MVP.View {

    private CarrosAdapter adapter;
    private static MVP.Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        if (presenter == null) {
            presenter = new PresenterImpl();
        }
        presenter.setView(this);
        presenter.retrieveCarros(savedInstanceState);
    }

    @Override
    protected void onStart() {
        super.onStart();

        RecyclerView rvCarros = (RecyclerView) findViewById(R.id.rv_carros);
        rvCarros.setHasFixedSize(true);

        StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(2, RecyclerView.VERTICAL);
        rvCarros.setLayoutManager(layoutManager);

        adapter = new CarrosAdapter(this, presenter.getCarros());
        rvCarros.setAdapter(adapter);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        outState.putParcelableArrayList(CARROS_KEY, (ArrayList<? extends Parcelable>) presenter.getCarros());
        super.onSaveInstanceState(outState);
    }

    public void updateIsFavoritoCarro(Carro carro) {
        presenter.updateIsFavoritoCarro(carro);
    }

    public void updateListaRecycler() {
        adapter.notifyDataSetChanged();
    }

    public void updateItemRecycler(int posicao) {
        adapter.notifyItemChanged(posicao);
    }

    public void showToast(String mensagem) {
        Toast.makeText(this, mensagem, Toast.LENGTH_SHORT).show();
    }

    public void showProgressBar(int visibilidade) {
        findViewById(R.id.pb_loading).setVisibility(visibilidade);
    }
}
