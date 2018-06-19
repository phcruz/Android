package phc.com.br.carrosmvp.view;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import phc.com.br.carrosmvp.R;
import phc.com.br.carrosmvp.model.domain.Carro;

/**
 * Created by paulo on 30/05/2018.
 */

public class CarrosAdapter extends RecyclerView.Adapter<CarrosAdapter.CarroViewHolder> {

    private MainActivity activity;
    private List<Carro> carros;


    public CarrosAdapter(MainActivity activity, List<Carro> carros) {
        this.activity = activity;
        this.carros = carros;
    }

    @Override
    public CarroViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.item_carro, parent, false);
        CarroViewHolder viewHolder = new CarroViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(CarroViewHolder holder, int position) {
        holder.setDados(carros.get(position));
    }

    @Override
    public int getItemCount() {
        return carros.size();
    }

    class CarroViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private ImageView ivCarro;
        private ImageView ivFavorito;
        private TextView tvModelo;

        private CarroViewHolder(View itemView) {
            super(itemView);

            ivCarro = (ImageView) itemView.findViewById(R.id.iv_carro);
            ivFavorito = (ImageView) itemView.findViewById(R.id.iv_favorito);
            tvModelo = (TextView) itemView.findViewById(R.id.tv_modelo);

            ivFavorito.setOnClickListener(this);
        }

        private void setDados(Carro carro) {
            Picasso.with(ivCarro.getContext())
                    .load(carro.getImagem())
                    .into(ivCarro);

            tvModelo.setText(carro.getModelo());
            ivFavorito.setImageResource(carro.getIsFavoritoIcone());
        }

        @Override
        public void onClick(View view) {
            activity.updateIsFavoritoCarro(carros.get(getAdapterPosition()));
        }
    }
}
