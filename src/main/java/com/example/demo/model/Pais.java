package com.example.demo.model;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Objects;


@Document
@JsonIgnoreProperties(ignoreUnknown = true)  //Ignora las demás propiedades del json de la apiweb
public class Pais extends Base implements Serializable {

    @JsonProperty("callingCodes") //Asi se llama en realidad la propiedad del json, hay que respetar su nombre
    @Transient
    private int[] callingCodes;
    private Integer codigoPais;

    @JsonProperty("name")
    private String nombrePais;

    @JsonProperty("capital")
    private String capitalPais;

    private String region;

    @JsonProperty("population")
    private int poblacion;

    @JsonProperty("latlng")
    @Transient
    private double[] latlng;
    private double latitud;
    private double longitud;
public Pais(){} //Si me olvido de este no me crea la entidad

    public Pais(Pais copia) {
        this.id=copia.id;
        this.callingCodes=copia.callingCodes;
        this.latlng=copia.latlng;
        this.codigoPais = copia.codigoPais;
        this.nombrePais = copia.nombrePais;
        this.capitalPais = copia.capitalPais;
        this.region = copia.region;
        this.poblacion = copia.poblacion;
        this.latitud = copia.latitud;
        this.longitud = copia.longitud;
    }

    public int getCodigoPais() {
        return codigoPais;
    }

    public void setCodigoPais(Integer codigoPais) {
        this.codigoPais = codigoPais;

    }


    public String getNombrePais() {
        return nombrePais;
    }

    public void setNombrePais(String nombrePais) {
        this.nombrePais = nombrePais;
    }

    public String getCapitalPais() {
        return capitalPais;
    }

    public void setCapitalPais(String capitalPais) {
        this.capitalPais = capitalPais;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public int getPoblacion() {
        return poblacion;
    }

    public void setPoblacion(int poblacion) {
        this.poblacion = poblacion;
    }

    public double getLatitud() {
        return latitud;
    }

    public void setLatitud(double latitud) {
        this.latitud = latitud;
    }

    public double getLongitud() {
        return longitud;
    }

    public void setLongitud(double longitud) {
        this.longitud = longitud;
    }

    public double[] getLatlng() {
        return latlng;
    }

    public void setLatlng(double[] latlng) {
        this.latlng = latlng;
    }

    public int[] getCallingCodes() {
        return callingCodes;
    }

    public void setCallingCodes(int[] callingCodes) {
        this.callingCodes = callingCodes;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Pais)) return false;
        Pais pais = (Pais) o;
        return getPoblacion() == pais.getPoblacion() &&
                Double.compare(pais.getLatitud(), getLatitud()) == 0 &&
                Double.compare(pais.getLongitud(), getLongitud()) == 0 &&
                Objects.equals(getCodigoPais(), pais.getCodigoPais()) &&
                Objects.equals(getNombrePais(), pais.getNombrePais()) &&
                Objects.equals(getCapitalPais(), pais.getCapitalPais()) &&
                Objects.equals(getRegion(), pais.getRegion());

    }

    @Override
    public int hashCode() {
        int result = Objects.hash(getCodigoPais(), getNombrePais(), getCapitalPais(), getRegion(), getPoblacion(), getLatitud(), getLongitud());
        result = 31 * result + Arrays.hashCode(getCallingCodes());
        result = 31 * result + Arrays.hashCode(getLatlng());
        return result;
    }

    @Override
    public String toString() {
        return "Pais{" +
                "callingCodes=" + Arrays.toString(callingCodes) +
                ", codigoPais=" + codigoPais +
                ", nombrePais='" + nombrePais + '\'' +
                ", capitalPais='" + capitalPais + '\'' +
                ", region='" + region + '\'' +
                ", poblacion=" + poblacion +
                ", latlng=" + Arrays.toString(latlng) +
                ", latitud=" + latitud +
                ", longitud=" + longitud +
                ", id='" + id + '\'' +
                ", date=" + date +
                '}';
    }
}
