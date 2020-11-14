//Clase polvf2
package proyectopolinomios;

/**
 *
 * @author Ormolgud Gonzalez Cardona
 */
import javax.swing.JOptionPane;

public class Polvf2 
{
    int n;
    float vec[];
   
    //Método constructor
    public Polvf2(int canterm)
    {
        n = canterm * 2 + 1;
        vec = new float[n];
        vec[0] = canterm;
    }
    
    /*Método para obtener un dato */
    public float getDato(int pos )
    {
        return(vec[pos]);
    }
   
    /*Método para asignar un dato */
    public void setDato(float dato, int pos )
    {
        vec[pos] = dato;
    }
    
    //Método get tamaño
    public int getTam()
    {
        return(n);
    }
    
   //Método para mostrar el polinomio
    public String mostrar()
    {
        String salida = "<html>";
        for(int k = 1; k < this.getTam(); k += 2)
        {
            if(vec[k + 1] > 0 && k > 1) //Si el coeficiente es positivo y no es el primer término
            {
                salida = salida + " + ";
            }
          salida = salida + vec[k + 1] + "X<sup>" + String.valueOf((int)vec[k]) + "</sup>";
        }
        salida = salida + "</html>";
        return(salida);
    }
    
    /*Método para evaluar el polinomio en vector forma 2*/
    public float evaluar(float x )
    {
       int k;
       float Resultado = 0;
       for( k=1; k < vec[0]*2 +1; k+=2) 
       {
            Resultado= Resultado + vec[k+1] * (float)Math.pow(x, vec[k]);
       }
        return(Resultado);
    }

    /*Método para ingresar todos los términos de un polinomio en Polvf2 */
    public void ingresarTerminos(int canterm)
    {
        int k, exp;
        float coef;
        for(k=1;k<=canterm; k++)
        {
            coef = Float.parseFloat(JOptionPane.showInputDialog( "Ingrese coeficiente"));
            exp = Integer.parseInt(JOptionPane.showInputDialog("Ingrese exponente"));
            this.almacenarTerm(coef, exp);
        }
    }
      
   /*Método almacenar un término en un polinomio en vector forma 2*/
    public void almacenarTerm(float coef, int exp )
    {
        int  k =1, j;
        while((k < vec[0] *2 +1 ) &&( vec[k] >exp )&&( vec[k+1]!=0))
        {
            k = k +2;
        }
        if ((k< vec[0]* 2+1) && (vec[ k] == exp) &&( vec[k+1]!=0))
        {
            JOptionPane.showMessageDialog(null,"Ya existe un término con el mismo exponente");
        }
        else
        {
            for(j = (int)vec[0]*2-1 ; j > k; j--) 
            {
                vec[j+1] =vec[j-1];
            }
            vec[k] = exp;
            vec[k + 1] = coef;
        }
    }
    
    /*Método para multiplicar dos polinomios en vector forma 2*/
    public Polvf2 multiplicar(Polvf2 B)
    {
        int k, j, expA, expB, expR;
        float coefR;
        Polvf2 R = new Polvf2(0);
        for(j = 1; j < (this.getDato(0) * 2 + 1); j+=2)
        {
            expA = (int)this.getDato(j);
            for(k = 1; k < (B.vec[0] * 2 + 1); k+=2)
            {
                expB = (int)B.vec[k];
                expR = expA + expB;
                coefR = vec[j + 1] * B.getDato(k + 1);
                R.insertarTerm(coefR, expR);
            }
        }
        return(R);
    }
        
    /*Método para dividir dos polinomios en vector forma 2*/
    public Polvf2 dividir (Polvf2 B){
        Polvf2 cociente = new Polvf2(0);
        Polvf2 aux;
        Polvf2 residuo;
        int pos = 2;
        residuo = this.copy();

        while (residuo.getDato(1) >= B.getDato(1)) {            
            aux = new Polvf2(0);
            cociente.insertarTerm(residuo.vec[2] / B.vec[2], (int)residuo.vec[1] - (int)B.vec[1]);
            
            for(int j = 2; j < B.vec.length; j = j + 2)  //Multiplica el término del cociente por el dividendo
            {
                aux.insertarTerm(-cociente.vec[pos] * B.vec[j], (int)cociente.vec[pos - 1] + (int)B.vec[j - 1]);
            }
            residuo = residuo.sumar(aux);
            residuo.ajustar();
            pos = pos + 2;
        }
        cociente.ajustar();
        return(cociente);
    }
    
    // hacer copia
    public Polvf2 copy()
    {
        Polvf2 R = new Polvf2(this.getTam());
        for (int i = 0; i < this.vec.length; i++) {
            R.setDato(this.vec[i], i);
        }
        return R;
    }

    //Reduce el tamaño de vector si hay ceros en los primeros términos del polinomio
    public void ajustar(){
        int j = 0;
        for(int k = 2; k < vec.length; k = k + 2){//Cuenta los términos con coeficiente cero
            if (vec[k] == 0)
                j++;
        }
        if(j != 0){
            Polvf2 R = new Polvf2((this.getTam() - 1)/2 - (j));//Crea el objeto polinomio con el nuevo número de términos
            int p = (this.getTam() - 1)/2 - (j);
            System.out.println(p);
            int k = 1;
            for (int i = 2; i < vec.length; i = i + 2) {//copia los datos útiles al nuevo vector
                if(vec[i] != 0){
                    R.vec[k] = vec[i - 1];
                    R.vec[k + 1] = vec[i];
                    k = k + 2;
                }
            }
            this.n = R.n;
            this.vec = R.vec;//Asigna el nuevo vector al objeto original
        }
    }
    
    /*Método para insertar un término en un polinomio en vector forma 2*/
    public void insertarTerm(float coef, int exp )
    {
        if(coef != 0)
        {
           int  k = 1, j;

            while( (k < vec[0] * 2 + 1 ) &&( vec[k] > exp ) && (vec[k + 1] != 0))
            {
                k = k + 2;
            }
            if (( k < vec[0] * 2 + 1) && (vec[k] == exp))
            {
                if((vec[k + 1] + coef) != 0)
                {
                    vec[k + 1] = vec[k + 1] + coef;
                }
                else
                {
                    for(j = k; j<(vec[0]*2 - 1 ); j+=2) 
                    {
                        vec[j] =vec[j +2];
                        vec[j+1] = vec[j +3];
                    }
                    vec[0] = vec[ 0] - 1;
                }
            }
            else
            {
                if(vec[0] * 2 + 1 == n)
                {
                    redimensionarVector();
                }

                for(j = ((int)vec[0] * 2 - 1); j >= k; j--) 
                {
                     vec[j + 1] = vec[j - 1];
                }
                vec[k] = exp;
                vec[k + 1] = coef;
                vec[0] = vec[0] + 1;
            }
        }
    }
    
    /*Método para eliminar el primer término en un polinomio en vector forma 2*/
    public void eliminarTerm()
    {
        if (n >= 3)
        {
            this.vec[1] = 0;
            this.vec[2] = 0;
            this.ajustar();          
        }
    }
    
    /*Método para redimensionar un vector forma 2*/
    public void redimensionarVector()
    {
        int k;
        n = n + 2;
        float aux[ ] = new float[n];
        for(k = 0; k < (int)(vec[0] * 2 + 1); k++) 
        {
            aux[k] = vec[k] ;
        }
        vec = aux;
    }
    
    /*Método para sumar dos polinomios  EN VECTOR FORMA 2*/
    public Polvf2 sumar(Polvf2 B)
    {
        int k=1, j=1, expA, expB;
        float coeA, coeB;
        Polvf2 R= new Polvf2(0) ;
        while( (k < vec[0]*2+1 ) &&( j< B.getDato(0)*2+1) )
        {
            expA= (int)vec[k];
            expB= (int)B.getDato(j);
            coeA= vec[k +1];
            coeB=B.getDato(j+1);
            if(expA == expB )
            {
                if((coeA + coeB)!=0)
                {
                    R.insertarTerm( (coeA+coeB), expA );
                }
                k = k +2;
                j = j + 2;
            }
           else
            {
                if(expA>expB )
                {
                    R.insertarTerm(coeA, expA );
                    k = k +2;
                }
                else
                {
                    R.insertarTerm(coeB, expB );
                    j = j +2;
                }
            }
        }

    while(k < vec[0]*2+1 )
    {
        R.insertarTerm(vec[k+1],(int) vec[k] );
        k=k+2;
    }
    while(j< B.getDato(0)*2+1)
    {
        R.insertarTerm(B.getDato(j + 1), (int)B.getDato(j) );
        j=j+2;
    }
    return( R);
    }
/*Método para restar dos polinomios  EN VECTOR FORMA 2*/
    public Polvf2 restar(Polvf2 B)
    {
        int k = 1, j = 1, expA, expB;
        float coeA, coeB;
        Polvf2 R = new Polvf2(0);
        while( (k < vec[0] * 2 + 1 ) && ( j < B.getDato(0) * 2 + 1) )
        {
            expA = (int)vec[k];
            expB = (int)B.getDato(j);
            coeA = vec[k + 1];
            coeB = -B.getDato(j + 1);
            if(expA == expB )
            {
                if((coeA + coeB) != 0)
                {
                    R.insertarTerm((coeA + coeB), expA);
                }
                k = k + 2;
                j = j + 2;
            }
           else
            {
                if(expA > expB)
                {
                    R.insertarTerm(coeA, expA);
                    k = k + 2;
                }
                else
                {
                    R.insertarTerm(coeB, expB);
                    j = j + 2;
                }
            }
        }

        while(k < vec[0]*2+1 )
        {
            R.insertarTerm(vec[k+1],(int) vec[k] );
            k=k+2;
        }
        while(j< B.getDato(0)*2+1)
        {
            R.insertarTerm(B.getDato(j + 1), (int)B.getDato(j) );
            j=j+2;
        }
        return( R);
    }
        
    // metodo para dividil un polinomion en f2/polista y retornanrlo en un f1
    public Polvf1 dividirF2Pol(Polista B)
    { 
        Polvf1 R = new Polvf1(0);  
        Nodo z;
        Polvf2 aux;
        Polvf2 residuo;
        int pos = 1;
        residuo = this.copy();
        while (residuo.getDato(1) >= B.cab.getExp()) {            
            aux = new Polvf2(0);
            R.insertarTerm(residuo.vec[2] / B.cab.getCoef(), (int)residuo.vec[1] - (int)B.cab.getExp());

            System.out.print("R ");
            for (int i = 0; i < R.vec.length; i++) {
                System.out.print(R.vec[i] + " ");
            }
            System.out.println("");
            System.out.println("pos " + pos);

            z = B.getCab();
            while(z != null)  //Multiplica el término del cociente por todos en el dividendo
            {
                aux.insertarTerm(-R.vec[pos] * z.getCoef(), (int)R.vec[0] + 1 - pos + (int)z.getExp());
                z = z.getLiga();

                System.out.print("AUX ");
                for (int i = 0; i < aux.vec.length; i++) {
                    System.out.print(aux.vec[i] + " ");
                }
                System.out.println("");

            }
            residuo = residuo.sumar(aux);
            residuo.ajustar();
            pos++;
        }
        R.ajustar();
        return(R);
    }   
}



