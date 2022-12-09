package com.example.app_medc;

import android.os.Parcel;
import android.os.Parcelable;

public class MedidasRVModal implements Parcelable {
    private String nombreMedicamento;
    private String viaAdministracion;
    private String cantida;
    private String farmaceuticaFabricante;
    private String descripcionUso;
    private String medicamentoID;
    private String userUD;

    public String getUserUD() {
        return userUD;
    }

    public void setUserUD(String userUD) {
        this.userUD = userUD;
    }

    public MedidasRVModal() {
    }

    protected MedidasRVModal(Parcel in) {
        nombreMedicamento = in.readString();
        viaAdministracion = in.readString();
        cantida = in.readString();
        farmaceuticaFabricante = in.readString();
        descripcionUso = in.readString();
        medicamentoID = in.readString();
        userUD = in.readString();
    }

    public static final Creator<MedicamentosRVModal> CREATOR = new Creator<MedicamentosRVModal>() {
        @Override
        public MedicamentosRVModal createFromParcel(Parcel in) {
            return new MedicamentosRVModal(in);
        }

        @Override
        public MedicamentosRVModal[] newArray(int size) {
            return new MedicamentosRVModal[size];
        }
    };

    public String getNombreMedicamento() {
        return nombreMedicamento;
    }

    public void setNombreMedicamento(String nombreMedicamento) {
        this.nombreMedicamento = nombreMedicamento;
    }

    public String getViaAdministracion() {
        return viaAdministracion;
    }

    public void setViaAdministracion(String viaAdministracion) {
        this.viaAdministracion = viaAdministracion;
    }

    public String getCantida() {
        return cantida;
    }

    public void setCantida(String cantida) {
        this.cantida = cantida;
    }

    public String getFarmaceuticaFabricante() {
        return farmaceuticaFabricante;
    }

    public void setFarmaceuticaFabricante(String farmaceuticaFabricante) {
        this.farmaceuticaFabricante = farmaceuticaFabricante;
    }

    public String getDescripcionUso() {
        return descripcionUso;
    }

    public void setDescripcionUso(String descripcionUso) {
        this.descripcionUso = descripcionUso;
    }

    public String getMedicamentoID() {
        return medicamentoID;
    }

    public void setMedicamentoID(String medicamentoID) {
        this.medicamentoID = medicamentoID;
    }

    public MedidasRVModal(String nombreMedicamento, String viaAdministracion, String cantida,
                               String farmaceuticaFabricante, String descripcionUso, String medicamentoID,
                               String userUD) {
        this.nombreMedicamento = nombreMedicamento;
        this.viaAdministracion = viaAdministracion;
        this.cantida = cantida;
        this.farmaceuticaFabricante = farmaceuticaFabricante;
        this.descripcionUso = descripcionUso;
        this.medicamentoID =medicamentoID;
        this.userUD=userUD;

    }

    @Override
    public int describeContents() {
        return 0;
    }


    //userID ??
    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(nombreMedicamento);
        dest.writeString(viaAdministracion);
        dest.writeString(cantida);
        dest.writeString(farmaceuticaFabricante);
        dest.writeString(descripcionUso);
        dest.writeString(medicamentoID);
    }
}



