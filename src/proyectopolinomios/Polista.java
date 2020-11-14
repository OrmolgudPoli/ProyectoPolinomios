package proyectopolinomios;

/**
 *
 * @author Ormolgud Gonzalez Cardona
 */

import javax.swing.JOptionPane;

public class Polista 
{
   Nodo cab;
   
   //Método constructor
   public Polista()
   {
       cab = null;
   }
   
   //Método para obtener la cabeza de la lista
   public Nodo getCab()
   {
       return(cab);
   }
   
   //Método para mostrar
    public String mostrar()
    {
        Nodo q = cab;
        String salida = "<html>";
           while(q != null)
            {
                System.out.println(q.getCoef());
                if(q.getCoef() > 0 && q != cab)
                {
                    salida = salida + " + ";
                }
                salida = salida + q.getCoef()+ "X<sup>" + String.valueOf((int)q.getExp()) + "</sup>";
                q = q.getLiga();
            }
        salida = salida + "</html>";
        return(salida);
    }
    
    /*Método para almacenar un término en un polinomio */
    public void almacenarTerm(float coef, int exp )
    {
        if(coef != 0 || exp < 0)
        {
            Nodo x, ant = null, p = cab;

            while( p != null && p.getExp() > exp )
            {
                ant = p;
                p = p.getLiga();
            }
            if( p != null && p.getExp() == exp)
            {
                JOptionPane.showMessageDialog(null, "Ya existe un termino con ese exponente");
            }
            else
            {
                x = new Nodo(coef, exp);
                x.setLiga(p);

                if(p == cab)
                {
                    cab=x;
                }
                else
                {
                    ant.setLiga(x);
                }
            }
        }else{
           // JOptionPane.showMessageDialog(null, "Coeficiente o exponenete no válido");
        }    
    }
        
    //Métoddo para ingresar los terminos
    public void ingresarTerminos()
    {
        float coef;
        int exp;
        String resp;
        
        resp = JOptionPane.showInputDialog("Desea ingresar un termino? S/N");
        while(resp.equalsIgnoreCase("S"))
        {
            coef=Float.parseFloat(JOptionPane.showInputDialog("Ingrese el coeficiente"));
            exp=Integer.parseInt(JOptionPane.showInputDialog("Ingrese el exponente"));
            this.almacenarTerm(coef,exp);
            resp = JOptionPane.showInputDialog("Desea ingresar un termino? S/N");
        }
    }
    
    /*Método para evaluar el polinomio en listas*/
    public float evaluar(float x )
    {
        float Resultado = 0;
        Nodo p=cab;
        while (p!=null)
        {
            Resultado= Resultado+p.getCoef()*(float) Math.pow(x,p.getExp()); 
            p=p.getLiga();
        }
        return(Resultado);
    }
        
    /*Método para insertar un término en un polinomio en listas*/ 
    public void insertarTerm(float coef, int exp )
    {
        Nodo x, ant = null, p = cab;
        while (p != null && p.getExp() > exp ) // busca la posición del nodo a insertar
        {
           ant = p;
           p = p.getLiga();
        }
        if ( p != null && p.getExp() == exp)
        {
            if((p.getCoef() + coef) != 0)
            {
               p.setCoef(p.getCoef() + coef);
            }
            else
            {    
                if(p == cab)
                {
                    cab = cab.getLiga();
                }
                else
                {
                    ant.setLiga(p.getLiga());
                }
                 //Delete(p)
            }
        }
        else
        {
           x=new Nodo(coef,exp);
           x.setLiga(p);
           if(p == cab)
           {
               cab=x;
           }
           else
           {
               ant.setLiga(x);
           }
        }
    }
    
    /*Método para sumar dos polinomios en Listas*/
    public Polista sumar(Polista B)
    {
        Nodo p=cab, q=B.getCab();
        Polista  R= new Polista();
        while( p!=null && q!=null)
        {
            if(p.getExp() == q.getExp() )
            {
                if(p.getCoef()+q.getCoef()!=0)
                {
                    R.insertarTerm( (p.getCoef()+q.getCoef()), p.getExp() );
                }
                p=p.getLiga();
                q=q.getLiga();
            }
            else
            {
                if(p.getExp()>q.getExp())
                {
                    R.insertarTerm(p.getCoef(), p.getExp());
                    p=p.getLiga();
                }
                else
                {
                    R.insertarTerm(q.getCoef(), q.getExp());
                    q=q.getLiga();
                }
            }
        }

        while(p!=null) 
        {
            R.insertarTerm(p.getCoef(), p.getExp());
            p=p.getLiga();
        }
        while(q!=null) 
        {
            R.insertarTerm(q.getCoef(), q.getExp());
            q=q.getLiga();
        }
        return(R);
    }

    /*Método para restar dos polinomios en Listas*/
    public Polista restar (Polista B)
    {
        Nodo p = cab, q = B.getCab();
        Polista  R = new Polista();
        while( p != null && q != null)
        {
            if(p.getExp() == q.getExp() )
            {
                if(p.getCoef() - q.getCoef() != 0)
                {
                    R.insertarTerm( (p.getCoef()- q.getCoef()), p.getExp() );
                }
                p = p.getLiga();
                q = q.getLiga();
            }
            else
            {
                if(p.getExp() > q.getExp())
                {
                    R.insertarTerm(p.getCoef(), p.getExp());
                    p = p.getLiga();
                }
                else
                {
                    R.insertarTerm(-q.getCoef(), q.getExp());
                    q = q.getLiga();
                }
            }
         }
        
        while(p != null) 
        {
            R.insertarTerm(p.getCoef(), p.getExp());
            p = p.getLiga();
        }
        while(q != null) 
        {
            R.insertarTerm(-q.getCoef(), q.getExp());
            q = q.getLiga();
        }
        return(R);
    }
    
    /*Método para multiplicar dos polinomios en Listas*/
    public Polista multiplicar(Polista B)
    {
        Nodo p=cab, q=B.getCab();
        Polista  R= new Polista();
        while(q!=null)
        {
            p=cab;
            while(p!=null)
            {
                R.insertarTerm((p.getCoef()*q.getCoef()), (p.getExp() + q.getExp()));

                p=p.getLiga();
            }        
            q=q.getLiga();
        }
        return( R);
    }
        
    //Dividir dos polinomios
    public Polista dividir(Polista B){
        Nodo p = getCab(), q = B.getCab(), z;
        int expt, expA;
        float coeft, coeA;
        if (p == null || q == null) {
            System.out.println("Uno de los polinomios está vacío");
            JOptionPane.showMessageDialog(null,"Uno de los polinomios está vacío");
            return null;
        }else{
            if (cab.getExp() >= q.getExp()) {
                Polista copia = this.hacerCopia();
                Polista R = new Polista();
                p = copia.getCab();
                while(p != null && q != null && (p.getExp() >= q.getExp())){
                    expt = p.getExp() - q.getExp();
                    coeft = p.getCoef() / q.getCoef();
                    R.insertarTerm(coeft, expt);
                    z = B.getCab();
                    while(z != null){
                        expA = expt + z.getExp();
                        coeA = coeft * -z.getCoef();
                        copia.insertarTerm(coeA, expA);
                        z = z.getLiga();
                    }
                    p = copia.getCab();
                    q = B.getCab();
                }
                return R;
            }else{
                System.out.println("No se pueden dividir los polinomios");
                JOptionPane.showMessageDialog(null,"No se pueden dividir los polinomios");
                return null;
            }
        }
    }

    //Hace una copia de la lista ligada del polinomio
    public Polista hacerCopia(){
        Polista R = new Polista();
        Nodo x = getCab();
        while(x != null){
            R.insertarTerm(x.getCoef(), x.getExp());
            x = x.getLiga();
        }
        return R;
    }
    
    public Polvf2 dividirPliF1(Polvf1 x){
        Nodo p = getCab();
        int expt, expA, z;
        float coeft, coeA;
        if (p == null || x.getDato(1) == 0) {
            System.out.println("Uno de los polinomios está vacío");
            JOptionPane.showMessageDialog(null,"Uno de los polinomios está vacío");
            return null;
        }else{
            if (p.getExp() > x.getDato(0)) {
                Polista copia = this.hacerCopia();
                Polvf2 R = new Polvf2(0);
                //p = copia.getCab();
                int i = 1;
                while(p != null && i < x.vec.length && (p.getExp() >= x.getDato(0))){  //valida cab no se anulo y i(contador) menor a largo del vector
                    expt = p.getExp() - ((int)x.getDato(0) + 1 - i);
                    coeft = p.getCoef() / x.getDato(1);
                    R.insertarTerm(coeft, expt);
                    z = 1;
                    while(z < x.vec.length){ 
                        coeA = coeft * -x.getDato(z);
                        expA = expt + (int)x.getDato(0) + 1 - z;
                        copia.insertarTerm(coeA, expA);
                        System.out.println("coef " + coeA);
                        System.out.println("exp " + expA);
                        z++;
                    }
                    p = copia.getCab();
                }
                return R;
            }else{
                System.out.println("No se pueden dividir los polinomios");
                JOptionPane.showMessageDialog(null,"No se pueden dividir los polinomios");
                return null;
            }
        }
    }
    
    // multiplicar polf1 y polista para devolverlo en polf2
    public Polvf2 multiplicarPolF1(Polvf1 x)
    {  
        Nodo p = getCab();
        Polvf2 R = new Polvf2(0);
        int expt; //guarda el cálculo de exponente
        float coeft; //guarda el cálculo de coeficiente
        while(p != null)
        {
            for (int i = 1; i < x.vec.length; i++)
            {
                expt = p.getExp() + ((int)x.getDato(0) + 1 - i);
                coeft = p.getCoef() * x.getDato(i);
                R.insertarTerm(coeft, expt); 
            }
            p = p.getLiga(); 
        }
        return( R);                   
    }
    
    //Elimina el primer término del polinomio
    public void eliminarTerm()
    {
        if (cab != null)
        {
            cab = cab.getLiga();
        }
    }
    
    //Compara dos polinomios
    public void comparar(Polista B){
        boolean flag = true;
        Nodo first = this.cab;
        Nodo second = B.cab;        
        if (first == null || second == null){ //Si alguno de los dos está vacío
            flag = false;
            System.out.println("Uno de los polinomios está vacío");
        }else{
            while (flag == true){
                if(first == null && second == null){                                                //Si ambos llegan al final de lectura iguales
                    break;                                                                          //Sale del while
                }else if(first == null || second == null){                                          //Si uno de los dos llega al final de lectura
                    flag = false;
                }else if(first.getCoef() != second.getCoef() || first.getExp()!= second.getExp()){  //Si coeficientes o exponentes son diferentes
                    flag = false;
                }else{                                                                              //Si son iguales
                    first = first.getLiga();
                    second = second.getLiga();
                }
            }
        }
        if(flag == true){
            System.out.println("Los polinomios son iguales");
        }else{
            System.out.println("Los polinomios son diferentes");
        }
    }
}


