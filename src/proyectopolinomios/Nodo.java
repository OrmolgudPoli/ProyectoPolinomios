/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectopolinomios;

/**
 *
 * @author Ormolgud Gonzalez Cardona
 */
public class Nodo 
{
    float coef;
    int exp;
    Nodo liga;

    public Nodo(float coef, int exp) {
        this.coef = coef;
        this.exp = exp;
        liga=null;
    }

    public float getCoef() {
        return coef;
    }

    public void setCoef(float coef) {
        this.coef = coef;
    }

    public int getExp() {
        return exp;
    }

    public void setExp(int exp) {
        this.exp = exp;
    }

    public Nodo getLiga() {
        return liga;
    }

    public void setLiga(Nodo liga) {
        this.liga = liga;
    }
 
}
