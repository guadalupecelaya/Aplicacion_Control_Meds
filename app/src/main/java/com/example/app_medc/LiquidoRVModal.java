package com.example.app_medc;

import android.os.Parcel;
import android.os.Parcelable;

public class LiquidoRVModal implements Parcelable {
    private String fecha;
    private String hora;
    private String evacuacion;
    private String cantidad;
    private String descripcion;
    private String liquidoID;
    private String userUD;

    public String getUserUD() {
        return userUD;
    }

    public void setUserUD(String userUD) {
        this.userUD = userUD;
    }

    public LiquidoRVModal() {
    }

    public String getEvacuacion() {
        return evacuacion;
    }

    public String getLiquidoID() {
        return liquidoID;
    }

    public void setLiquidoID(String liquidoID) {
        this.liquidoID = liquidoID;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getCantidad() {
        return cantidad;
    }

    public void setCantidad(String cantidad) {
        this.cantidad = cantidad;
    }

    public void setEvacuacion(String evacuacion) {
        this.evacuacion = evacuacion;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    protected LiquidoRVModal(Parcel in) {
        fecha = in.readString();
        hora = in.readString();
        evacuacion = in.readString();
        cantidad = in.readString();
        descripcion = in.readString();
        liquidoID = in.readString();
        userUD = in.readString();
    }

    public static final Creator<LiquidoRVModal> CREATOR = new Creator<LiquidoRVModal>() {
        @Override
        public LiquidoRVModal createFromParcel(Parcel in) {
            return new LiquidoRVModal(in);
        }

        @Override
        public LiquidoRVModal[] newArray(int size) {
            return new LiquidoRVModal[size];
        }
    };
    public LiquidoRVModal(String fecha, String hora, String evacuacion,
                               String cantidad, String descripcion, String liquidoID,
                               String userUD) {
        this.fecha = fecha;
        this.hora = hora;
        this.evacuacion = evacuacion;
        this.cantidad = cantidad;
        this.descripcion = descripcion;
        this.liquidoID =liquidoID;
        this.userUD=userUD;

    }

    @Override
    public int describeContents() {
        return 0;
    }


    //userID ??
    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(fecha);
        dest.writeString(hora);
        dest.writeString(evacuacion);
        dest.writeString(cantidad);
        dest.writeString(descripcion);
        dest.writeString(liquidoID);
    }
}