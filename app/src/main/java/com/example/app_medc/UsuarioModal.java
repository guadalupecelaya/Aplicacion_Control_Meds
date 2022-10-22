package com.example.app_medc;

import android.os.Parcel;
import android.os.Parcelable;

public class UsuarioModal implements Parcelable {
    private String nombre;
    private String apaterno;
    private String amaterno;
    private String correo;
    private String usuarioID;

    public UsuarioModal(String nombre, String apaterno, String amaterno, String correo, String usuarioID) {
        this.nombre = nombre;
        this.amaterno=amaterno;
        this.apaterno=apaterno;
        this.correo=correo;
        this.usuarioID=usuarioID;
    }

    public UsuarioModal() {

    }

    protected UsuarioModal(Parcel in) {
        nombre = in.readString();
        apaterno = in.readString();
        amaterno = in.readString();
        correo = in.readString();
        usuarioID = in.readString();
    }

    public static final Creator<UsuarioModal> CREATOR = new Creator<UsuarioModal>() {
        @Override
        public UsuarioModal createFromParcel(Parcel in) {
            return new UsuarioModal(in);
        }

        @Override
        public UsuarioModal[] newArray(int size) {
            return new UsuarioModal[size];
        }
    };

    public String getUsuarioID() {
        return usuarioID;
    }

    public void setUsuarioID(String usuarioID) {
        this.usuarioID = usuarioID;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getAmaterno() {
        return amaterno;
    }

    public void setAmaterno(String amaterno) {
        this.amaterno = amaterno;
    }

    public String getApaterno() {
        return apaterno;
    }

    public void setApaterno(String apaterno) {
        this.apaterno = apaterno;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(nombre);
        dest.writeString(apaterno);
        dest.writeString(amaterno);
        dest.writeString(correo);
        dest.writeString(usuarioID);
    }
}
