
import java.util.Scanner;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author mihai
 */
public class Tema2 {

    /**
     *
     * @param args nu are nici un efect
     * 
     * In main se face citirea de la stdin. Programul incepe doar cand citeste 
     * begin singur pe o linie, iar dupa executa comenzile pana cand citeste din
     * nou end.
     */
    public static void main(String[] args) {
        // TODO code application logic here
        
        
        
        String instruction;
        String[] commands;
        Subject subject = Subject.getSubject();
        try (Scanner scan = new Scanner(System.in)) {
            while(scan.hasNext()){
                instruction = scan.nextLine();
                if("begin".equals(instruction)){
                    break;
                }
            }
            while(scan.hasNext()){
                int n,i;
                instruction = scan.nextLine();
                if("end".equals(instruction)){
                    break;
                }
                commands = instruction.split(" ", 3);
                
                switch(commands[0]){
                    case"create_obs":{
                        subject.addObserver(Integer.parseInt(commands[1]) , commands[2]);
                        break;
                    }
                    case"delete_obs":{
                        subject.deleteObserver(Integer.parseInt(commands[1]));
                        break;
                    }
                    case"print":{
                        subject.getObservers().get(Integer.parseInt(commands[1])).print();
                        break;
                    }
                    case"feed":{
                        subject.addFeed(commands[1], Double.parseDouble(commands[2]));
                        
                    }
                    
                }
            }
        }
    }
    
}
