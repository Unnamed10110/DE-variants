/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.objects;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author USER- ACER
 */
public class Link {

    int inicio, fin,longitud;
    List<Integer> ranuras;

    public Link (){
        inicio = -1;
        fin = -1;
        ranuras = new ArrayList<Integer>();
       
    }

    //public Link(int p, int s, int longi, List<Integer> ranuras) {
    public Link(int p, int s, int longi, List<Integer>ranuras) {
        this.inicio = p;
        this.fin = s;
        this.longitud=longi;
        this.ranuras = ranuras;
    }

    public int getInicio() {
        return inicio;
    }

    public void setInicio(int inicio) {
        this.inicio = inicio;
    }

    public int getFin() {
        return fin;
    }
    public int getLongitud() {
        return longitud;
    }
    public void setLongitud(int longi) {
        this.longitud= longi;
    }

    public void setFin(int fin) {
        this.fin = fin;
    }

    public List<Integer> getRanuras() {
        return ranuras;
    }

    public void setRanuras(List<Integer> ranuras) {
        this.ranuras = ranuras;
    }
}

