package com.example.app_medc;

import android.os.Parcel;
import android.os.Parcelable;

public class EnfermedadesRVModal implements Parcelable {
    //EditText nom_enf, fecha, sintom, precauciones, medicamentos;
    private String nombreEnfermedado;
    private String fechaDiagnostico;
    private String sintomas;
    private String precauciones;
    private String medicamentosComunes;
    private String enfermedadesID;

    public EnfermedadesRVModal() {
    }

    protected EnfermedadesRVModal(Parcel in) {
        nombreEnfermedado = in.readString();
        fechaDiagnostico = in.readString();
        sintomas = in.readString();
        precauciones = in.readString();
        medicamentosComunes = in.readString();
        enfermedadesID = in.readString();
    }

    public static final Creator<EnfermedadesRVModal> CREATOR = new Creator<EnfermedadesRVModal>() {
        @Override
        public EnfermedadesRVModal createFromParcel(Parcel in) {
            return new EnfermedadesRVModal(in);
        }

        @Override
        public EnfermedadesRVModal[] newArray(int size) {
            return new EnfermedadesRVModal[size];
        }
    };

    public String getNombreEnfermedado() {
        return nombreEnfermedado;
    }

    public void setNombreEnfermedado(String nombreEnfermedado) {
        this.nombreEnfermedado = nombreEnfermedado;
    }

    public String getFechaDiagnostico() {
        return fechaDiagnostico;
    }

    public void setFechaDiagnostico(String fechaDiagnostico) {
        this.fechaDiagnostico = fechaDiagnostico;
    }

    public String getSintomas() {
        return sintomas;
    }

    public void setSintomas(String sintomas) {
        this.sintomas = sintomas;
    }

    public String getPrecauciones() {
        return precauciones;
    }

    public void setPrecauciones(String precauciones) {
        this.precauciones = precauciones;
    }

    public String getMedicamentosComunes() {
        return medicamentosComunes;
    }

    public void setMedicamentosComunes(String medicamentosComunes) {
        this.medicamentosComunes = medicamentosComunes;
    }

    public String getEnfermedadesID() {
        return enfermedadesID;
    }

    public void setEnfermedadesID(String enfermedadesID) {
        this.enfermedadesID = enfermedadesID;
    }

    public EnfermedadesRVModal(String nombreEnfermedado, String fechaDiagnostico, String sintomas, String precauciones,
                               String medicamentosComunes, String enfermedadesID) {
        this.nombreEnfermedado = nombreEnfermedado;
        this.fechaDiagnostico = fechaDiagnostico;
        this.sintomas = fechaDiagnostico;
        this.precauciones = sintomas;
        this.medicamentosComunes = medicamentosComunes;
        this.enfermedadesID = enfermedadesID;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(nombreEnfermedado);
        dest.writeString(fechaDiagnostico);
        dest.writeString(sintomas);
        dest.writeString(precauciones);
        dest.writeString(medicamentosComunes);
        dest.writeString(enfermedadesID);
    }
}
