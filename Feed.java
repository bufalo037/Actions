
import java.util.Collection;
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
public class Feed implements IsFeed<FeedObserver>{
    private int nr_modif = 0; // va avea sens doar dupa ce va fi clonat
    private boolean accepted = false; //daca ultimul atemt de printare a reusit
    private boolean ever_printed = false; //daca a fost printat vreodata
    private double value;
    private String name;

    Feed() {
    }

    Feed(double value, String name) {
        this.value = value;
        this.name = name;
    }

    int getNr_modif() {
        return nr_modif;
    }
    
    void incNr_modif(){
        this.nr_modif++;
    }
    
    void setNr_modif_to_0(){
        this.nr_modif = 0;
    }

    double getValue() {
        return value;
    }

    void setValue(double value) {
        this.value = value;
    }

    boolean getEver_printed(){
        return ever_printed;
    }

    void setEver_printed(boolean ever_printed) {
        this.ever_printed = ever_printed;
    }
    
 
    String getName() {
        return name;
    }

    Feed copy(){
        return new Feed(this.value,this.name);
    }
    
    void print(String str){
        System.out.println(str);
    }
    
    
    /**
     *
     * @param visitor este observerul care vrea sa printeze feedul acesta
     * @return true daca rpn-ul visitorului este valid pentru informatle feedului
     */
    @Override
    public boolean accepts(FeedObserver visitor){
        
        int n, i = 1,j ,indx1=0, indx2=0;
        String str1 = null, str2 = null;
        
        if(accepted == true && nr_modif == 0){
            return true;
        }
        
        TreeMap<Integer,String> rpn; 
        HashMap<Integer,Node> tree = new HashMap<Integer,Node>();
        Node node1, node2;
        try{
            
            Collection<String> values = visitor.getRpn().values();
            Iterator<String> iterator = values.iterator();
            rpn = new TreeMap<Integer,String>(); 
            while(iterator.hasNext()){
                rpn.put(i++, iterator.next());
            }
            
        }
        catch(NullPointerException ex){
            rpn = null;
        }
        if(rpn == null){
            this.accepted = true;
            return true;
        }
        
        n = rpn.size();
        
        
        for(i = 1;i <= n;i++){
        //stiu ca merge sa fac asa deoarece stiu convenia pe care am facut-o cand
//am creat rpn-u si anume ca este ordonat in ordinea fireasca a numerelor naturale
            if("value".equals(rpn.get(i))){
                rpn.put(i,String.valueOf(value));
            }
            if("name".equals(rpn.get(i))){
                rpn.put(i,name);
            }
        }
        
        for(i = 1; i <= n;i++){
            if(rpn.get(i).matches("(^eq$|^ne$|^gt$|^ge$|^lt$|^le$|\\&\\&|\\|\\|)")){
                for(j = i-1;j >=1;j--){
                    if(!rpn.get(j).isEmpty()){
                        indx2 = j;
                        rpn.put(j, "");
                        break;
                    }
                }
                for(j--;j >=1;j--){
                    if(!rpn.get(j).isEmpty()){
                        indx1 = j;
                        rpn.put(j, "");
                        break;
                    }
                }   
               
                tree.put(i, new OperatorNode(tree.get(indx1), tree.get(indx2), rpn.get(i)));
                
            }
            else
            {
                tree.put(i, new OperandNode(rpn.get(i)));
            }
                
        }
        
        accepted = Boolean.valueOf(tree.get(i-1).accept());
        
        return accepted;
    }

}
