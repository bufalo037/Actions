/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author mihai
 */
public interface NodeVisitor  {
    
    /**
     * Se foloseste ca metoda de recursivitate prin arbore, deoarece functia
     * accept a nodurilor operatori apeleaza aceasta metoda pe copiii ei, generand
     * astfel recursiv un raspuns al acelei functii pana cand se intalnesc frunzele
     * din arbore, care nu mai au o metoda de vizitare si vor returna prin intermediul
     * functiei accept valoarea pe care o stoca nodul.
     * 
     * @param node
     * @return returneaza valarea unui nod dupa ce este aplicat pe el accept
     * 
     */
    String visit(NodeVisitable node);
}
