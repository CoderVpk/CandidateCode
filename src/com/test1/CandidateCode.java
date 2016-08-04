package com.test1;

import java.util.ArrayList;
import java.util.Stack;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

public class CandidateCode {
	static ArrayList<String[]> valsets = new ArrayList<String[]>();
	public static String iteration(String input1) throws ScriptException
    {
        ArrayList vars ;
		String infix;
		vars = new ArrayList();
		Stack stack = new Stack();
        int n = input1.length();
        for(int i=n-1;i>=0;i--){
        	char c = input1.charAt(i);
        	//System.out.println(c);
        	if(Character.isLowerCase(c)){
        		if(!vars.contains(c)){
        			vars.add(c);
        			
        		}

        		stack.push(""+c);
        	}
        	else if((""+c).equals("N")){
        		stack.push("(!"+stack.pop()+")");
        	}
        	else if((""+c).equals("A")){
        		stack.push("("+stack.pop()+"&&"+stack.pop()+")");
        	}
        	else if((""+c).equals("O")){
        		stack.push("("+stack.pop()+"||"+stack.pop()+")");
        	}
        	else if((""+c).equals("F")){
        		stack.push("("+stack.pop()+"=="+stack.pop()+")");
        	}
        	else if((""+c).equals("I")){
        		stack.push("((!"+stack.pop()+")||"+stack.pop()+")");
        	}
        	else{
        		return "invalid";
        	}
        	
        }
        ScriptEngineManager mgr = new ScriptEngineManager();
        ScriptEngine engine = mgr.getEngineByName("JavaScript");
        infix = (String) stack.pop();
        if(!stack.isEmpty()){
        	return "invalid";
        }
//        String userVar[] = {"p = 1"};
//        for (String s : userVar) {
//            engine.eval(s);
//        }
        //System.out.println(infix);
        
        
//        String[] set1 = {"0","1"};
//        String[] set2 = {"0","1"};
//        String[] set3 = {"0","1"};
//        String[][] sets = {set1, set2, set3};
        String[][] sets = new String[vars.size()][2];
        int j = 0;
        for(Object var:vars){
        	String[] set = {"0","1"};
        	sets[j] = set;
        	j++;
        }
 
        ArrayList<String[]> valsets = printCombinations(sets, 0, "");
        for(String[] val:valsets){
        	String userVar[] = new String[val.length];
        	for(int i=0;i<val.length;i++){
        		userVar[i] = vars.get(i) + "=" + val[i];
        	}
        	for (String s : userVar) {
              engine.eval(s);
        	}
        	//System.out.println(engine.eval(infix));
        	if((boolean)engine.eval(infix) == false){
        		return "No";
        	}
        }
        
        return "Yes";
    }
	
	private static ArrayList<String[]> printCombinations(String[][] sets, int n, String prefix){
        if(n >= sets.length){
        	String vals = prefix.substring(0,prefix.length()-1);
            //System.out.println("{"+vals+"}");
            valsets.add(vals.split(","));
            return valsets;
        }
        for(String s : sets[n]){
            printCombinations(sets, n+1, prefix+s+",");
        }
        return valsets;
    }
}
