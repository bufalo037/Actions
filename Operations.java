
import java.security.InvalidAlgorithmParameterException;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author mihai
 */
public final class Operations {

    private Operations() {
    }
    
    /**
     *
     * @param op operatia care va fi facuta
     * @param opr1 primul argument in forma de string
     * @param opr2 al doilea argument in forma de string
     * @return returneaza valoarea care in cazul de fata e doar boolean pt 
     * operatia efetuata
     * @throws InvalidAlgorithmParameterException
     */
    public static boolean call_operator(String op, String opr1, String opr2)
            throws InvalidAlgorithmParameterException{
        switch(op){
            case"eq":{
                return Operations.eq(opr1, opr2);
            }
            case"ne":{
                return Operations.ne(opr1, opr2);
            }
            case"lt":{
                return Operations.lt(Double.valueOf(opr1), Double.valueOf(opr2));
            }
            case"le":{
                return Operations.le(Double.valueOf(opr1), Double.valueOf(opr2));
            }
            case"gt":{
                return Operations.gt(Double.valueOf(opr1), Double.valueOf(opr2));
            }
            case"ge":{
                return Operations.ge(Double.valueOf(opr1), Double.valueOf(opr2));
            }
             case"&&":{
                return Operations.and(Boolean.valueOf(opr1), Boolean.valueOf(opr2));
            }
              case"||":{
                return Operations.or(Boolean.valueOf(opr1), Boolean.valueOf(opr2));
            }
        }
        throw new InvalidAlgorithmParameterException();
    }
    
    
    
    private static boolean and(boolean x1, boolean x2){
        return ((x1 != x2)?false:x1 == true);
    }
    
    private static boolean or(boolean x1, boolean x2){
        return (x1 != x2)?true:x1 == true;
    }
    
    private static boolean eq(String x1, String x2){
        return x1.equals(x2);
    }
    
    private static boolean ne(String x1, String x2){
        return !x1.equals(x2);
    }
    
    private static boolean gt(double x1, double x2){
        return x1 > x2;
    }
    
    private static boolean ge(double x1, double x2){
        return x1 >= x2;
    }
    
    private static boolean lt(double x1, double x2){
        return x1 < x2;
    }
    
    private static boolean le(double x1, double x2){
        return x1 <= x2;
    }
}
