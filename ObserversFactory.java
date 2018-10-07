/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author mihai
 */
public class ObserversFactory {
    
    private static ObserversFactory factory = null;

    private ObserversFactory() {
    }

    static ObserversFactory getFactory() {
        if(factory == null)
            factory = new ObserversFactory();
        return factory;
    }
    
    FeedObserver createObserver(int obs_id, String filter){
        return new FeedObserver(obs_id, filter);
    }
    
    
}
