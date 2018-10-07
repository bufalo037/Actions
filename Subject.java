
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.TreeMap;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author mihai
 */
public class Subject {
    
    private static Subject  subject = null;
    private final HashMap <Integer,FeedObserver> observers;
    private final TreeMap <String,Feed> feeds;
    private final ObserversFactory factory = ObserversFactory.getFactory();
    
    class Strcmp implements Comparator<String>{

        @Override
        public int compare(String o1, String o2) {
            int min,i;
            
            min  = Integer.min(o1.length(),o2.length());
            for(i = 0;i < min; i++){
                char x,y;
                x = o1.charAt(i);
                y = o2.charAt(i);
                
                if(x != y)
                {
                    if(Character.getNumericValue(x) < Character.getNumericValue(y))
                        return -1;
                    else
                        return 1;
                }
            }
            return 0;
        }
        
    }

    
    private Subject() {
        observers = new HashMap<Integer,FeedObserver>();
        feeds = new TreeMap <String,Feed> (this.new Strcmp());
        
    }
    
    TreeMap<String, Feed> getFeeds() {
        return feeds;
    }

    static Subject getSubject() {
        if(subject == null){
            subject = new Subject();
        }
        return subject;
    }

    HashMap<Integer, FeedObserver> getObservers() {
        return observers;
    }
    
    
    void addObserver(int obs_id, String filter){
        observers.put(obs_id, factory.createObserver(obs_id, filter));
    }
    
    //posibile probleme cu remove
    void deleteObserver(int obs_id){
        
        if(observers.remove(obs_id) == null){
            throw new RuntimeException(); //ca sa vad de merg remove
            
        }
    }
    
    void addFeed(String name, double value){
        Feed x =  feeds.get(name);
        if(x == null){
            x = new Feed(value, name);
            feeds.put(name, x);
        }
        else
        {
            x.setValue(value);
        }
        
        notifyAllObservers(x);
    }
    
    private void notifyAllObservers(Feed name){
        Iterator<FeedObserver> iter = observers.values().iterator();
        while(iter.hasNext()){
            iter.next().update(name);
        }
    }

}
