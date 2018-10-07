
import java.security.InvalidAlgorithmParameterException;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author mihai
 */
public abstract class Node implements NodeVisitable{
    
    protected Node left,right;
    protected String value;
    protected Node() {
    }

    protected Node(Node left, Node right, String value) {
        this.left = left;
        this.right = right;
        this.value = value;
    }
    
    
}

class OperatorNode extends Node implements NodeVisitor{

    private OperatorNode() {
    }

    public OperatorNode(Node left, Node right, String value) {
        super(left, right,value);
    }
    
    @Override
    public String accept() {
        try {
            return String.valueOf(Operations.call_operator(value, visit(left), visit(right)));
            
        } catch (InvalidAlgorithmParameterException ex) {
            Logger.getLogger(OperatorNode.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    @Override
    public String visit(NodeVisitable x){
        return x.accept();
    }
    
    
}

class OperandNode extends Node{

    public OperandNode() {
       
    }

    public OperandNode(String value) {
        super(null, null, value);
       
    }
    
    @Override
    public String accept() {
        return value;
    }
    
}