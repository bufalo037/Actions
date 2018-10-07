/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author mihai
 * @param <T>
 *
 */


public interface  IsFeed <T extends FeedVisitor>{
    
    /**
     *
     * @param visitor
     * @return retuneaza daca visitorul poate sa aiba acces la feedul respectiv
     * bazat pe informatia pe cae o detine vizitorul
     */
    boolean accepts(T visitor);
}