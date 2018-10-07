/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author mihai
 * @param <T>
 */
public interface FeedVisitor <T extends IsFeed> {

    /**
     *
     * @param feed
     * 
     * viziteaza feed-ul dat ca parametru cu scopul de a printa informatia care
     * este prezenta in feed
     */
    void visit(T feed);
    
}
