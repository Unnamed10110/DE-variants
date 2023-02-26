/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.objects;

/**
 *
 * @author USER- ACER
 */
public class demand {
    private float id;
    private int origen, destino, FS;
    public demand(float id,int o, int d, int fs){
        this.id=id;
        this.origen=o;
        this.destino=d;
        this.FS=fs;
    }
    public int getOrigen(){
        return origen;
    }
    public int getDestino(){
        return destino;
    }
    public int getFS(){
        return FS;
    }
    
    public float getID(){
        return id;
    }
    public void setID(float i){
        this.id=i;
    }
    
    public void setOrigen(int o){
        this.origen=o;
    }
    public void setDestino(int d){
        this.destino=d;
    }
    public void setFS(int fs){
        this.FS=fs;
    }
    
    
}
