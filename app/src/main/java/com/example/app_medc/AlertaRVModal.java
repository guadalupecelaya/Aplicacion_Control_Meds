package com.example.app_medc;

import android.os.Parcel;
import android.os.Parcelable;

public class AlertaRVModal implements Parcelable {
    private String hora_inicioA;
    private String fecha_inicioA;
    private String fecha_finalA;
    private String medicamentoA;
    private String enfermedadA;
    private String intervaloA;
    private String alertaID;

    public AlertaRVModal(){

    }


    public AlertaRVModal(String hora_inicioA, String fecha_inicioA, String fecha_finalA, String medicamentoA,
                         String enfermedadA, String intervaloA, String alertaID) {
        this.hora_inicioA = hora_inicioA;
        this.fecha_finalA = fecha_finalA;
        this.fecha_inicioA = fecha_inicioA;
        this.enfermedadA = enfermedadA;
        this.medicamentoA = medicamentoA;
        this.intervaloA = intervaloA;
        this.alertaID = alertaID;
    }

    public String getHora_inicioA() {
        return hora_inicioA;
    }

    public void setHora_inicioA(String hora_inicioA) {
        this.hora_inicioA = hora_inicioA;
    }

    public String getFecha_inicioA() {
        return fecha_inicioA;
    }

    public String getFecha_finalA() {
        return fecha_finalA;
    }

    public void setFecha_finalA(String fecha_finalA) {
        this.fecha_finalA = fecha_finalA;
    }

    public void setFecha_inicioA(String fecha_inicioA) {
        this.fecha_inicioA = fecha_inicioA;
    }

    public String getMedicamentoA() {
        return medicamentoA;
    }

    public void setMedicamentoA(String medicamentoA) {
        this.medicamentoA = medicamentoA;
    }

    public String getEnfermedadA() {
        return enfermedadA;
    }

    public void setEnfermedadA(String enfermedadA) {
        this.enfermedadA = enfermedadA;
    }

    public String getIntervaloA() {
        return intervaloA;
    }

    public void setIntervaloA(String intervaloA) {
        this.intervaloA = intervaloA;
    }

    public String getAlertaID() {
        return alertaID;
    }

    public void setAlertaID(String alertaID) {
        this.alertaID = alertaID;
    }
    protected AlertaRVModal(Parcel in) {
        hora_inicioA = in.readString();
        fecha_inicioA = in.readString();
        fecha_finalA = in.readString();
        enfermedadA = in.readString();
        medicamentoA = in.readString();
        intervaloA = in.readString();
        alertaID = in.readString();
    }

    public static final Creator<AlertaRVModal> CREATOR = new Creator<AlertaRVModal>() {
        @Override
        public AlertaRVModal createFromParcel(Parcel in) {
            return new AlertaRVModal(in);
        }

        @Override
        public AlertaRVModal[] newArray(int size) {
            return new AlertaRVModal[size];
        }
    };


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(hora_inicioA);
        dest.writeString(fecha_finalA);
        dest.writeString(fecha_inicioA);
        dest.writeString(enfermedadA);
        dest.writeString(medicamentoA);
        dest.writeString(intervaloA);
        dest.writeString(alertaID);
    }
}
