
import java.util.ArrayList;
import java.util.Stack;
import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author mihai
 */
public class Rpn {

    private Rpn() {
   
    }

    static private ArrayList<String> parse(String filter) {
        Pattern pattern;
        ArrayList<String> tokens = new ArrayList<String>(5);
        pattern = Pattern.compile("(\\(|\\)|\\d+\\.?\\d+|\\w+|\\&\\&|\\|\\|)");
        Matcher matcher = pattern.matcher(filter);
        while (matcher.find()) {
            tokens.add(matcher.group());
        }

        return tokens;
    }

    static private int priority(String name) { //am adaugat functia asta ca codul sa fie
//lizibil, ar fi fost oribil daca as mai fi adaugat cateva regular expresions
        switch (name) {
            case "(": {
                return 4; //doar orientativ
            }
            case ")": {
                return 3;
            }
            case "eq": {
                return 2;
            }
            case "ne": {
                return 2;
            }
            case "gt": {
                return 2;
            }
            case "ge": {
                return 2;
            }
            case "lt": {
                return 2;
            }
            case "le": {
                return 2;
            }
            case "&&": {
                return 1;
            }
            case "||": {
                return 1;
            }
            default: {
                return -1;
            }
        }
    }

    /**
    *
    * Face in principiu Shunting-yard algorithm descris din pdf si transforma
    * filtrul intr-o expresie de tipul reversed polish notation
    */
    static TreeMap<Integer,String> make_rpn(String filter) {

        ArrayList<String> tokens = parse(filter);
        TreeMap<Integer,String> output = new TreeMap<Integer,String>();
        int n = tokens.size();
        Stack<String> stiva = new Stack<String>();
        String last_str;
        int p1, p2;
        Integer index = 1;
        
        if (n == 1 && "nil".equals(tokens.get(0))) {
            //inseamna ca filtrul accepta orice feed
            return null;
        }
        for (int i = 0; i < n; i++) {
            String temp = tokens.get(i);
            if (temp.matches("(\\(|\\)|^eq$|^ne$|^gt$|^ge$|^lt$|^le$|\\&\\&|\\|\\|)")) {
                if (stiva.empty() == true) {
                    stiva.push(temp);
                } 
                else 
                {
                    last_str = stiva.peek();
                    p1 = priority(last_str);
                    p2 = priority(temp);
                    if (p2 == 4) {
                        stiva.push(temp);
                        continue;
                    }

                    if (p2 == 3) {
                        while (!"(".equals(stiva.peek())) {
                            output.put(index++,stiva.pop());
                        }
                        stiva.pop();
                        continue;
                    }

                    if (p2 > p1 || p1 == 4) { //este paranteza pe stiva
                        stiva.push(temp);
                    } else {
                        while (p1 >= p2) {

                            output.put(index++,stiva.pop());

                            if (stiva.empty() == false) {
                                last_str = stiva.peek();
                                p1 = priority(last_str);
                                if (p1 == 4) {
                                 //   stiva.pop();
                                    break;
                                }
                            } else {
                                break;
                            }
                        }
                        stiva.push(temp);
                    }
                }

            } else {
                output.put(index++,temp);
            }
        }
        while (stiva.empty() == false) {
            output.put(index++,stiva.pop());
        }

        return output;
    }

}
