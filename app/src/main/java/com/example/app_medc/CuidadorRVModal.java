package com.example.app_medc;

import android.os.Parcel;
import android.os.Parcelable;

public class CuidadorRVModal implements Parcelable {
    private String nombreC;
    private String apaternoC;
    private String amaternoC;
    private String edadC;
    private String telefonoC;
    private String parentescoC;
    private String cuidadorID;

    public CuidadorRVModal(){

    }

    public String getCuidadorID() {
        return cuidadorID;
    }

    public void setCuidadorID(String cuidadorID) {
        this.cuidadorID = cuidadorID;
    }

    public String getParentescoC() {
        return parentescoC;
    }

    public void setParentescoC(String parentescoC) {
        this.parentescoC = parentescoC;
    }

    public String getTelefonoC() {
        return telefonoC;
    }

    public void setTelefonoC(String telefonoC) {
        this.telefonoC = telefonoC;
    }

    public String getEdadC() {
        return edadC;
    }

    public void setEdadC(String edadC) {
        this.edadC = edadC;
    }

    public String getAmaternoC() {
        return amaternoC;
    }

    public void setAmaternoC(String amaternoC) {
        this.amaternoC = amaternoC;
    }

    public String getNombreC() {
        return nombreC;
    }

    public void setNombreC(String nombreC) {
        this.nombreC = nombreC;
    }

    public String getApaternoC() {
        return apaternoC;
    }

    public void setApaternoC(String apaternoC) {
        this.apaternoC = apaternoC;
    }

    public CuidadorRVModal(String nombreC, String apaternoC, String amaternoC, String edadC,
                           String telefonoC, String parentescoC, String cuidadorID) {
        this.nombreC = nombreC;
        this.apaternoC = apaternoC;
        this.amaternoC = amaternoC;
        this.edadC = edadC;
        this.telefonoC = telefonoC;
        this.parentescoC = parentescoC;
        this.cuidadorID = cuidadorID;
    }

    protected CuidadorRVModal(Parcel in) {
        nombreC = in.readString();
        apaternoC = in.readString();
        amaternoC = in.readString();
        edadC = in.readString();
        telefonoC = in.readString();
        parentescoC = in.readString();
        cuidadorID = in.readString();
    }

    public static final Creator<CuidadorRVModal> CREATOR = new Creator<CuidadorRVModal>() {
        @Override
        public CuidadorRVModal createFromParcel(Parcel in) {
            return new CuidadorRVModal(in);
        }

        @Override
        public CuidadorRVModal[] newArray(int size) {
            return new CuidadorRVModal[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(nombreC);
        dest.writeString(apaternoC);
        dest.writeString(amaternoC);
        dest.writeString(edadC);
        dest.writeString(telefonoC);
        dest.writeString(parentescoC);
        dest.writeString(cuidadorID);
    }
}
