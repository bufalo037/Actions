/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author mihai
 */
public interface NodeVisitable {

    /**
     *Are menirea de a rezolva arborele pana la nodul curent
     *
     * @return va returna un bool convertit ca string daca este un nod operator
     * si un string care va reprezenta o valoare daca este un nod oprerand
     */
    String accept();
}
