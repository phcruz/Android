package phc.com.br.carrosmvp.model.domain;

import android.os.Parcel;
import android.os.Parcelable;

import phc.com.br.carrosmvp.R;

/**
 * Created by paulo on 30/05/2018.
 */

public class Carro implements Parcelable {

    public static final String ID_KEY = "id";
    public static final String IS_FAVORITO_KEY = "is-favorito";

    private Long id;
    private String modelo;
    private String marca;
    private String imagem;
    private boolean isFavorito;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getImagem() {
        return imagem;
    }

    public void setImagem(String imagem) {
        this.imagem = imagem;
    }

    public boolean isFavorito() {
        return isFavorito;
    }

    public void setFavorito(boolean favorito) {
        isFavorito = favorito;
    }

    public int getIsFavoritoIcone() {
        if (isFavorito()) {
            return R.drawable.ic_favorito_marcado;
        }
        return R.drawable.ic_favorito;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(this.id);
        dest.writeString(this.modelo);
        dest.writeString(this.marca);
        dest.writeString(this.imagem);
        dest.writeByte(this.isFavorito ? (byte) 1 : (byte) 0);
    }

    public Carro() {
    }

    protected Carro(Parcel in) {
        this.id = in.readLong();
        this.modelo = in.readString();
        this.marca = in.readString();
        this.imagem = in.readString();
        this.isFavorito = in.readByte() != 0;
    }

    public static final Parcelable.Creator<Carro> CREATOR = new Parcelable.Creator<Carro>() {
        @Override
        public Carro createFromParcel(Parcel source) {
            return new Carro(source);
        }

        @Override
        public Carro[] newArray(int size) {
            return new Carro[size];
        }
    };

    @Override
    public String toString() {
        return "Carro{" +
                "id=" + id +
                ", modelo='" + modelo + '\'' +
                ", marca='" + marca + '\'' +
                ", imagem='" + imagem + '\'' +
                ", isFavorito=" + isFavorito +
                '}';
    }
}
