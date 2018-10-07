
import java.text.DecimalFormat;
import java.util.Collection;
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
public abstract class Observer{
    abstract void update(Feed name);
}

class FeedObserver extends Observer implements FeedVisitor<Feed>{
    
    private int obs_id;
    private String filter;
    private TreeMap <String,Feed> feed;
    static final private Subject subject = Subject.getSubject();
    private TreeMap<Integer,String> rpn = null; //reverse polish notation
    
    public FeedObserver() {
    }

    public FeedObserver(int obs_id, String filter) {
        this.obs_id = obs_id;
        this.filter = filter;
        
        TreeMap <String, Feed> feeds = subject.getFeeds();
        feed = new TreeMap <String,Feed>(subject.new Strcmp());
        Collection<Feed> values = feeds.values();
        Iterator<Feed> iterator = values.iterator();
        Feed x;
        while(iterator.hasNext()){
            x = iterator.next();
            feed.put(x.getName(), x.copy());
        }
    }
    
    @Override
    void update(Feed feedname){
        if(feed.get(feedname.getName()) == null){
                
                feed.put(feedname.getName(), feedname.copy()); 
            /*
            } catch (CloneNotSupportedException ex) {
                Logger.getLogger(FeedObserver.class.getName()).log(Level.SEVERE, null, ex);
            }
            */
            feed.get(feedname.getName()).incNr_modif();
        }
        else
        {
           //il notica cat modificari au avut loc de la ultima verificare(print)
           feed.get(feedname.getName()).incNr_modif();

        }
    }

    public TreeMap<Integer,String> getRpn() {
        return rpn;
    }
    
    
    
    void print(){
        if(rpn == null){
            rpn = Rpn.make_rpn(filter);
        }
        Collection<Feed> feeds = feed.values();
        Iterator<Feed> iter =  feeds.iterator();
        while(iter.hasNext()){
            visit(iter.next());
        }
        
    }
    
    @Override
    public void visit(Feed feed){
        
        DecimalFormat numberFormat = new DecimalFormat("0.00");
        double value  = subject.getFeeds().get(feed.getName()).getValue();
        double old_value = feed.getValue();
        double proc = 0;
        if(feed.getEver_printed()){
            proc = (value-feed.getValue())/feed.getValue() * 100.0;
        }
        
        feed.setValue(value);
        
        if(feed.accepts(this) == true){
            
            feed.setEver_printed(true);
            
            System.out.println("obs " + this.obs_id + ": " + feed.getName() + " " 
            + value + " " + numberFormat.format(proc)
            + "% " + feed.getNr_modif());
            
            feed.setNr_modif_to_0();
        }
        else
        {
            feed.setValue(old_value);
        }
    }

}
