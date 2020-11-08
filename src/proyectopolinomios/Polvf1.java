package proyectopolinomios;

/**
 *
 * @author Ormolgud Gonzalez Cardona
 */

import javax.swing.JOptionPane;

public class Polvf1 
{
    int n;
    float vec[];
    
    //Método constructor
    public Polvf1(int grado)
    {
        n = grado + 2;
        vec = new float[n];
        vec[0] = grado;
    }
    
    //Método para obtener un dato
    public float getDato(int pos)
    {
        return(vec[pos]);
    }
    
    //Método para asignar un dato al vector
    public void setDato(float dato,int pos)
    {
        vec[pos]=dato;
    }
    
    //Método para retornar el tamaño del vector
    public int getTam() {
        return n;
    }
    
    //Método para mostrar
    public String mostrar()
    {
        String salida = "<html>";
        int k;
        for(k = 1; k < vec[0] + 2; k++)
        {
            if(vec[k] != 0)
            {
		if(vec[k] > 0 && k > 1)
                {
                    salida = salida + "+";
                }
               
             salida = salida + vec[k] + "X<sup>" + String.valueOf((int)vec[0] + 1 - k) + "</sup>";
            }
        }
        salida = salida + "</html>";
        
        return(salida);
        //JOptionPane.showMessageDialog(null,"Datos del polinomios\n"+salida);
    }
    
    //método para almacenar un término
    public void almacenarTerm(float coef, int exp)
    {
        int pos;
        if(exp >= 0 && exp <= vec[0])
        {
            pos = (int)vec[0] + 1 - exp;
            if(vec[pos] == 0)
            {
                vec[pos] = coef;
            }
            else
            {
                JOptionPane.showMessageDialog(null, "Ya existe un término con ese exponente");
            }
        }
        else
        {
            JOptionPane.showMessageDialog(null, "El exponente no corresponde al polinomio");
        }
    }
    
    //Métoddo para ingresar los terminos
    public void ingresarTerminos()
    {
        float coef;
        int exp;
        String resp;
        
        resp = JOptionPane.showInputDialog("Desea ingresar un término? S/N");
        while(resp.equalsIgnoreCase("S"))
        {
           coef = Float.parseFloat(JOptionPane.showInputDialog("Ingrese el coeficiente"));
           exp = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el exponente"));
           this.almacenarTerm(coef, exp);  // almacena el coeficiente y exponente ingresado 
           resp = JOptionPane.showInputDialog("Desea ingresar otro término? S/N");
           if(!resp.equalsIgnoreCase("S") && vec[1] == 0)
           {
               resp = "s";
               JOptionPane.showMessageDialog(null, "Debe ingresar un termino con el grado del polinomio");
           }
        }
    }
    
    //Reduce el tamaño de vector si hay ceros en los primeros términos del polinomio
    public void ajustar(){
        int k = 1, j = (int)vec[0];
        while(vec[k] == 0){//Halla el grado del nuevo polinomio
            j = j - 1;
            k++;
        }
        Polvf1 R = new Polvf1(j);
        for (int i = 1; i < R.vec.length; i++) {//copia los datos útiles al nuevo vector
            R.vec[i] = vec[(int)vec[0] + 1 - ((int)R.vec[0] + 1 - i)];
        }
        n = j;
        vec = R.vec;//Asigna el nuevo vector al objeto original
    }    
    
    //Copia el vector en otro con grado superior
    public void redimensionar(int exp){
        Polvf1 R = new Polvf1(exp);
        for(int i = 1; i < this.vec.length; i++){
            R.vec[(int)R.vec[0] + 1 - ((int)vec[0] + 1 - i)] = vec[i];
        }
        n = R.n;
        vec = R.vec;
    }
    
    //Método para insertar un dato en el polinomio
    public void insertarTerm(float coef, int exp)
    { 
        int pos;
        if(exp < 0 || coef == 0)
        {
            JOptionPane.showMessageDialog(null,"Algún término no es válido");
        }
        else
        {
            if(exp <= vec[0])
            {
                pos = (int)vec[0] + 1 - exp;
                vec[pos] = vec[pos] + coef;
            }
            else
            {
                this.redimensionar(exp);
                vec[1] = coef;
            }
            this.ajustar();
        }
    }    
    
    //Método para eleminar un dato en el polinomio
    public void eliminarTerm()
    {
        if(vec[1] > 0){
            n = n--;
            vec[1] = 0;
            this.ajustar();
        }        
    }
    
    
    /*Método para sumar dos polinomios en vector forma 1*/
    public Polvf1 sumar(Polvf1 B)
    {
        int k = 1, j = 1, my, expA, expB, posR;
        if(vec[0] > B.getDato(0))
        {
            my = (int)vec[0];
        }
        else
        {
            my = (int)B.getDato(0);
        }
        Polvf1 R = new Polvf1(my);

        while(k < vec[0]+2 && j < B.getDato(0) + 2)
        {
            expA = (int)vec[0] + 1 - k;
            expB = (int)B.getDato(0) + 1 - j;

            if(expA == expB )
            {
                posR = (int)R.getDato(0) + 1 - expA;
                R.setDato((vec[k] + B.getDato(j)), posR );
                k = k + 1;
                j = j + 1;
            }
            else
            {
                if(expA > expB )
                {
                    posR = (int)R.getDato(0) + 1 - expA;
                    R.setDato(vec[k], posR);
                    k = k + 1;
                }
                else
                {
                    posR = (int)R.getDato(0) + 1 - expB;
                    R.setDato(B.getDato(j), posR);
                    j = j + 1;
                }
            }
        }
        R.ajustar();
        return(R);
    }
   
   /*Método para restar dos polinomios en vector forma 1*/
   public Polvf1 Restar (Polvf1 B)
   {
        int k = 1, j = 1, my, expA, expB, posR;
        if(vec[0] > B.getDato(0))
        {
            my = (int)vec[0];
        }
        else
        {
            my = (int)B.getDato(0);
        }
        Polvf1 R = new Polvf1(my);

        while(k < vec[0] + 2 && j < B.getDato(0) + 2)
        {
            expA = (int)vec[0] + 1 - k;
            expB = (int)B.getDato(0) + 1 - j;

            if(expA == expB)
            {
                posR = (int)R.getDato(0) + 1 - expA;
                R.setDato( (vec[k] - B.getDato(j)), posR);
                k = k + 1;
                j = j + 1;
            }
            else
            {
                if(expA > expB )
                {
                    posR = (int)R.getDato(0) + 1 - expA;
                    R.setDato(vec[k], posR);
                    k = k + 1;
                }
                else
                {
                    posR = (int)R.getDato(0) + 1 - expB;
                    R.setDato(B.getDato(j), posR);
                    j = j + 1;
                }
            }
        }
        R.ajustar();
        return( R);
    }
   
    /*Método para multiplicar dos polinomios en vector forma 1*/
    public Polvf1 multiplicar(Polvf1 B)
    {
        int k, j, expA, expB, expR, posR;
        float coefR;
        Polvf1 R = new Polvf1((int)vec[0] + (int)B.getDato(0));

        for(j = 1; j < (B.getDato(0) + 2); j++)
        {
            expB = (int)B.getDato(0) + 1 - j;

            for(k = 1; k < vec[0]+2; k++)
            {
                expA = (int)vec[0] + 1 - k;
                expR = expA + expB;
                coefR = vec[k] * B.getDato(j);
                posR = (int)R.getDato(0) + 1 - expR;
                R.setDato(((int)R.getDato(posR) + coefR), posR);
            }
        }
        return(R);
    }

    /*Método para hacer una copia de un objeto polvf1*/
    public Polvf1 hacercopia( )
    {
        int k;
        Polvf1 copia = new Polvf1((int)vec[0]);

        for(k = 1; k < vec[0] + 2 ; k++)
        {
            copia.setDato(vec[k], k);
        }
   
        return(copia);
    }

    /*Método para dividir dos polinomios */
    public Polvf1 dividir(Polvf1 B)
    {
        int k, expt, posR, expA, posA;
        float coet, coeA;

        Polvf1 copia = this.hacercopia();  // hacer copia de A
        Polvf1 R = new Polvf1((int)vec[0] - (int)B.getDato(0));

        while(copia.getDato(0) >= B.getDato(0) )
        {
            System.out.println("ENTRO AL WHILE");
            expt = (int)copia.getDato(0) - (int)B.getDato(0); // exponete t
            coet = copia.getDato(1) / B.getDato(1);
            posR = (int)R.getDato(0) + 1 - expt;
            R.setDato(coet, posR);
            for(k = 1; k < B.getDato(0) + 2 ; k++)
            {
                expA =  expt + (int)B.getDato(0) + 1 - k;
                coeA = coet * B.getDato(k);
                posA = (int)copia.getDato(0) + 1 - expA;
                copia.setDato((copia.getDato(posA) - coeA), posA);
            }
            copia.ajustar();
        }
        return(R);
    }
    
    /*Método para evaluar un polinomio en vector forma 1*/
    public float evaluar(float x)
    {
        float resultado = 0;
        for(int k = 1; k < vec[0] + 2 ; k++) 
        {
            resultado = resultado + vec[k] * (float)Math.pow(x, (vec[0] + 1 - k));
        }
        return(resultado);
    }

    public Polista sumarF1F2(Polvf2 x){
        Polista R = new Polista();
        int j = 1;
        for(int i = 1; i < vec.length; i++){ //Recorre el vector F1
            if(j < x.vec.length){ //Si hay términos en F2
                if((int)vec[0] + 1 - i > x.vec[j]){//Si el termino de F1 > F2
                    R.almacenarTerm(vec[i], (int) vec[0] + 1 - i);//Inserta término de F1
                }else if((int)vec[0] + 1 - i == x.vec[j]){//Si el termino de F1 == F2
                    R.almacenarTerm(vec[i] + x.vec[j + 1], (int) vec[0] + 1 - i);//Suma ambos términos de F1 y F2
                    j = j + 2;
                }else{
                    R.almacenarTerm(x.vec[j + 1], (int) x.vec[j]);//Inserta término de F2
                    i--;
                    j = j + 2;
                }
            }else{ //Si solo quedan término de F1
                R.almacenarTerm(vec[i], (int) vec[0] + 1 - i);  //Inserta término de F1
            }
        }
        while((j < x.vec.length)){ //Termina de sumar si quedan términos en el segundo sumando
            R.almacenarTerm(x.vec[j + 1], (int) x.vec[j]);//Inserta término de F2
                j = j + 2;
        }
        return R;
    }

    public boolean compF1F2(Polvf2 B)
    {
        int j = 1, k = 1;
        boolean R = true;
        
        if(this.vec[0] != B.getDato(1))
            R = false;
        
        while (j < this.vec[0] + 2 && k < B.getDato(0) * 2 + 1 && R == true) // validar el largo de cada arreglo
        {
            if (this.vec[j] != B.getDato(k + 1) || this.vec[0] + 1 - j != B.getDato(k)) { //Si coef es igual || exp no es igual
                R = false;
            } else {
                j = j + 1;
                k = k + 2;    
            }
        }
        return R;
    }
    
    //compara dos polinomios
    public void comparar(Polvf1 B){
        boolean flag = true;
        if(this.vec[0] != B.vec[0]){
            flag = false;
        }else{
            for (int i = 1; i < this.getTam(); i++) {
                if(this.vec[i] != B.vec[i]){
                    flag = false;
                }
            }
        }
        if(flag == true){
            System.out.println("Los polinomios son iguales");
            JOptionPane.showMessageDialog(null,"Los polinomios son iguales");
        }else{
            System.out.println("Los polinomios son diferentes");
            JOptionPane.showMessageDialog(null, "Los polinomios son diferentes");
        }
    }
    
}
