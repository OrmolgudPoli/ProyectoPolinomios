package proyectopolinomios;

/**
 *
 * @author Ormolgud Gonzalez Cardona
 */

import javax.swing.JOptionPane;
public class ProyectoPolinomios {

    private static Polista polr; 
    private static Polvf1 f1r;
    private static Polvf2 f2r;
    
    public static void main(String[] args) 
    { 
        menuppal();
    }
    
    //método para el menu principal //Repositorio actualizado
    public static void menuppal()
    {
        String menu = "***MENU***\n1- Polinomios vector f1\n2- Polinomios vector f2\n3- Polinomios listas ligadas\n4- combinados\n0- Salir";
        int opcion;
        do
        {
            opcion = Integer.parseInt(JOptionPane.showInputDialog(menu));
            switch(opcion)
            {
                case 1: menupolvf1();
                    break;
                    
                case 2: menupolvf2();
                    break;
                    
                case 3: menupolista();
                    break;
                    
                case 4: combinados();
                    break;
                    
                case 0: System.exit(0);
                    break;                
            }
        }while(opcion != 0);       
    }
    
    //método para el menu polinomio vector forma 1
    public static void menupolvf1()
    {
        Polvf1 A, B, C;
        int grado, opcion;
        float x;
        String menu = "***MENU***\n1- Mostrar\n2- Evaluar\n3- Sumar\n4- Multiplicar\n5- Dividir\n6- Restar\n7- Eliminar primer término\n8- Comparar\n0- Salir\nDigite la opcion";
        grado = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el grado del primer polinomio"));
        A = new Polvf1(grado);
        A.ingresarTerminos();
        grado = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el grado del segundo polinomio"));
        B = new Polvf1(grado);
        B.ingresarTerminos();
        do
        {
            opcion = Integer.parseInt(JOptionPane.showInputDialog(menu));
            switch(opcion)
            {
                case 1:
                    JOptionPane.showMessageDialog(null, "Datos del polinomio 1\n" + A.mostrar() + "\nDatos del polinomio 2\n" + B.mostrar());
                    break;

                case 2:
                    x = Float.parseFloat(JOptionPane.showInputDialog("Ingrese el valor de la varaible x"));
                    JOptionPane.showMessageDialog(null,"El resultado de evaluar el polinomio con x= "+x+"\nes " + A.evaluar(x));
                    break;

                case 3:     
                    C = A.sumar(B);
                    JOptionPane.showMessageDialog(null, "Datos del polinomio 1\n" + A.mostrar()
                                                                            + "\nDatos del polinomio 2\n" + B.mostrar()
                                                                           + "\nDatos del polinomio suma\n" + C.mostrar());
                    break;

                case 4:    
                    C=A.multiplicar(B);
                    JOptionPane.showMessageDialog(null, "Datos del polinomio 1\n" + A.mostrar()
                                                                            + "\nDatos del polinomio 2\n" + B.mostrar()
                                                                           +"\nDatos del polinomio multiplicado\n" + C.mostrar());
                    break;

                case 5:                     
                    if(A.getDato(0) >= B.getDato(0))
                    {
                        System.out.println("entró a 5");
                        C = A.dividir(B);
                        JOptionPane.showMessageDialog(null, "Datos del polinomio 1\n" + A.mostrar()
                                                    + "\nDatos del polinomio 2\n" + B.mostrar()
                                                   + "\nDatos del polinomio dividido\n" + C.mostrar());
                    }
                    else
                    {
                        JOptionPane.showMessageDialog(null, "No se puede dividir");
                    }
                    break;

                case 6:     
                    C = A.Restar(B);
                    JOptionPane.showMessageDialog(null, "Datos del polinomio 1\n" + A.mostrar()
                                                            + "\nDatos del polinomio 2\n" + B.mostrar()
                                                           + "\nDatos del polinomio resta\n" + C.mostrar());
                    break;
                
                case 7:     
                    A.eliminarTerm();
                    JOptionPane.showMessageDialog(null, "Datos del polinomio 1\n" + A.mostrar());
                    break;
                case 8:     
                    A.comparar(B);
                    break;
                case 0: 
                    System.exit(0);
            }
        }while(opcion != 0);
    }
    
    //método para el menu polinomio vector forma 2
    public static void menupolvf2()
    {
        Polvf2 A, B, C;
        int canterm, opcion;
        float x;
        String menu = "***MENÚ***\n1- Mostrar\n2- Evaluar\n3- Sumar\n4- Multiplicar\n5- Dividir\n6- Restar\n7- Eliminar Termino\n0- Salir\nDigite la opción";
        canterm = Integer.parseInt(JOptionPane.showInputDialog("Cuántos términos tiene el polinomio?"));
        A = new Polvf2(canterm);
        A.ingresarTerminos(canterm);

        canterm = Integer.parseInt(JOptionPane.showInputDialog("Cuántos términos tiene el polinomio?"));
        B = new Polvf2(canterm);
        B.ingresarTerminos(canterm);
        do
        {
            opcion=Integer.parseInt(JOptionPane.showInputDialog(menu));
            switch(opcion)
            {
                case 1:
                    JOptionPane.showMessageDialog(null, "Datos del polinomio 1\n" + A.mostrar() + "\nDatos del polinomio 2\n" + B.mostrar());
                    break;
                    
                case 2:
                    x = Float.parseFloat(JOptionPane.showInputDialog("Ingrese el valor de la varaible x"));
                    JOptionPane.showMessageDialog(null,"El resultado de evaluar el polinomio con x= " + x + "\nes " + A.evaluar(x));
                    break;
                    
                case 3:     
                    C = A.sumar(B);
                    JOptionPane.showMessageDialog(null, "Datos del polinomio 1\n" + A.mostrar()
                                                                            + "\nDatos del polinomio 2\n" + B.mostrar()
                                                                           + "\nDatos del polinomio suma\n" + C.mostrar());
                    break;
                    
                case 4:    
                    C=A.multiplicar(B);
                    JOptionPane.showMessageDialog(null, "Datos del polinomio 1\n" + A.mostrar()
                                                                            + "\nDatos del polinomio 2\n" + B.mostrar()
                                                                           + "\nDatos del polinomio multiplicado\n" + C.mostrar());
                    break;

                case 5: 
                    if(A.getDato(1)>=B.getDato(1))
                    {
                        C = A.dividir(B);
                        JOptionPane.showMessageDialog(null,"Datos del polinomio 1\n" + A.mostrar()
                                                    + "\nDatos del polinomio 2\n" + B.mostrar()
                                                   + "\nDatos del polinomio división\n" + C.mostrar());
                    }
                    else
                    {
                        JOptionPane.showMessageDialog(null, "No se puede dividir");
                    }
                    break;
                    
                case 6:     
                    C = A.restar(B);
                    JOptionPane.showMessageDialog(null, "Datos del polinomio 1\n" + A.mostrar()
                                                            + "\nDatos del polinomio 2\n" + B.mostrar()
                                                           + "\nDatos del polinomio resta\n" + C.mostrar());
                    break;
                case 7:     
                    A.eliminarTerm();
                    JOptionPane.showMessageDialog(null, "Datos del polinomio 1\n" + A.mostrar());
                    break;
                case 0: System.exit(0);

            }
        }while(opcion != 0); 
    }
    
    //método para el menú polinomio en listas ligadas
    public static void menupolista()
    {
        String menu3 = "***MENU***\n1- Mostrar\n2- Evaluar\n3- Sumar\n4- Multiplicar\n5- Dividir\n6- Restar\n0- Salir\nDigite la opcion";
        float x;
        int opcion;
        Polista A, B, C;
        A = new Polista();
        A.ingresarTerminos();
        B = new Polista();
        B.ingresarTerminos();
        do
        {
            opcion=Integer.parseInt(JOptionPane.showInputDialog(menu3));
            switch(opcion)
            {
                case 1:
                    JOptionPane.showMessageDialog(null, "Datos del polinomio 1\n" + A.mostrar() + "\nDatos del polinomio 2\n" + B.mostrar());
                    break;
                    
                case 2:
                    x = Float.parseFloat(JOptionPane.showInputDialog("Ingrese el valor de la varaible x"));
                    JOptionPane.showMessageDialog(null,"El resultado de evaluar el polinomio con x= " + x + "\nes " + A.evaluar(x));
                    break;
                    
                case 3:     
                    C = A.sumar(B);
                    JOptionPane.showMessageDialog(null, "Datos del polinomio 1\n" + A.mostrar()
                                                    + "\nDatos del polinomio 2\n" + B.mostrar()
                                                   + "\nDatos del polinomio suma\n" + C.mostrar());
                    break;
                    
                case 4:    
                    C = A.multiplicar(B);
                    JOptionPane.showMessageDialog(null, "Datos del polinomio 1\n" + A.mostrar()
                                                    + "\nDatos del polinomio 2\n" + B.mostrar()
                                                   + "\nDatos del polinomio multiplicado\n" + C.mostrar());
                    break;

                case 5: 
                    C = A.dividir(B);
                    JOptionPane.showMessageDialog(null,"Datos del polinomio 1\n" + A.mostrar()
                                                        +"\nDatos del polinomio 2\n" + B.mostrar()
                                                       +"\nDatos del polinomio divide\n" + C.mostrar());
                    break;
                        
                case 6:     
                    C = A.restar (B);
                    JOptionPane.showMessageDialog(null,"Datos del polinomio 1\n" + A.mostrar()
                                                    + "\nDatos del polinomio 2\n" + B.mostrar()
                                                   + "\nDatos del polinomio resta\n" + C.mostrar());
                    break;
                    
                case 0: System.exit(0);
            }
        }while(opcion!=0); 
    }
    
    public static void combinados()
    {
        String menu = "***MENU***\n1- Ingresar polvf1\n2- Ingresar polvf2\n3- Ingresar Polista\n4- polista = polvf1+polvf2\n5- polvf2 = polista/polvf1\n6- polvf1 = polvf2/polista\n7- polvf2 = polista*polvf1\n8- comparar polvf1 con polvf2\n0- Salir";
        int opcion;
        do
        {
            opcion = Integer.parseInt(JOptionPane.showInputDialog(menu));
            switch(opcion)
            {
                case 1:
                    int grado;                    
                    grado = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el grado del primer polinomio"));
                    f1r = new Polvf1(grado);
                    f1r.ingresarTerminos();
                    break;
                    
                case 2:
                    int canterm; 
                    canterm = Integer.parseInt(JOptionPane.showInputDialog("Cuantos términos tiene el polinomio?"));
                    f2r = new Polvf2(canterm);
                    f2r.ingresarTerminos(canterm);
                    break;
                    
                case 3:
                    polr = new Polista();
                    polr.ingresarTerminos();
                    break;
                    
                case 4:                   
                    polr = f1r.sumarF1F2(f2r);               
                    JOptionPane.showMessageDialog(null, "Datos del polinomio 1\n" + f1r.mostrar()
                                                                        + "\nDatos del polinomio 2\n" + f2r.mostrar()
                                                                       + "\nDatos del polinomio polista = polvf1+polvf2\n" + polr.mostrar());
                    break;
                    
                case 5: 
                    f2r = polr.dividirPliF1(f1r);
                    JOptionPane.showMessageDialog(null, "Datos del polinomio 1\n" + polr.mostrar()
                                                                        + "\nDatos del polinomio 2\n" + f1r.mostrar()
                                                                       + "\nDatos del polinomio polvf2 = polista/polvf1\n" + f2r.mostrar());
                    break;
                    
                case 6:
                    f1r = f2r.dividirF2Pol(polr);
                    JOptionPane.showMessageDialog(null, "Datos del polinomio 1 f2\n" + f2r.mostrar()
                                                                        + "\nDatos del polinomio 2 polista\n" + polr.mostrar()
                                                                       + "\nDatos del polinomio polvf1 = polvf2/polista\n"+f1r.mostrar());
                    break;
                    
                case 7:
                    f2r = polr.multiplicarPolF1(f1r);
                    JOptionPane.showMessageDialog(null, "Datos del polinomio 1\n" + polr.mostrar()
                                                                        + "\nDatos del polinomio 2\n" + f1r.mostrar()
                                                                       + "\nDatos del polinomio polvf2 = polista*polvf1\n" + f2r.mostrar());
                    break;
                    
                case 8:
                    boolean result;
                    result = f1r.compF1F2(f2r); 
                    if(result == true)
                    {
                        JOptionPane.showMessageDialog(null, "Datos del polinomio 1\n" + f1r.mostrar()
                                                                        + "\nDatos del polinomio 2\n" + f2r.mostrar()
                                                                       + "\nLos Polinomios son iguales");                        
                    }else
                            JOptionPane.showMessageDialog(null, "Datos del polinomio 1\n" + f1r.mostrar()
                                                                        + "\nDatos del polinomio 2\n" + f2r.mostrar()
                                                                       + "\nLos Polinomios no son iguales");
                    break;
                    
                case 0: 
                    System.exit(0);
                    break;
            }
        }while(opcion!=0);
    }
}