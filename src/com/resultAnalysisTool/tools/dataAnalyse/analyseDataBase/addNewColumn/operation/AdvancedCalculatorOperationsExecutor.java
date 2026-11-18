package com.resultAnalysisTool.tools.dataAnalyse.analyseDataBase.addNewColumn.operation;

import java.util.Stack;

import com.resultAnalysisTool.util.message.Message;

public class AdvancedCalculatorOperationsExecutor {
	
	
	public String infixEvaluation(String string){
		//necessary object
		AdvancedCalculatorOperation aCOperation= new AdvancedCalculatorOperation();
		
		String result="";	//the main result
		
		//Finding number of spaces in a string
		int numberOfElements=charNumber(string, ' ');
		
		try{				//may occur math error
		
			//initializing attributes of the element
			Element[] element=new Element[numberOfElements+1];	//element array
			int charFrom=0, charTo=0;
			for(int i=0; i<numberOfElements; i++){
				String s="";     //string property
			    boolean o=false;      //operator-true, operand-false
			    
			    //cutting sub strings
			    int j=charFrom;
			    while(string.charAt(j)!=' '){
			    	j++;
			    }
			    charTo=j;
			    s=string.substring(charFrom, charTo);
			    charFrom=charTo+1;
			    
			    
			    if(s.equals("+") || s.equals("-") || s.equals("x") || s.equals("/") || s.equals("^") ||	//when an operator is found
		    		 s.equals("sqrt") || s.equals("(") || s.equals(")") || s.equals("P") || s.equals("C") || s.equals("mod") ||
		    		 s.equals("log") || s.equals("ln") || s.equals("fact") || s.equals("sin") || s.equals("sinh") || 
		    		 s.equals("asin") || s.equals("cbrt") || s.equals("cos") || s.equals("cosh") || s.equals("acos") ||
		    		 s.equals("cube") || s.equals("tan") || s.equals("tanh") || s.equals("atan") || s.equals("sqre")){ 
			    	
		            o=true;
		        }else{		//when operand
		            o=false;
		        }
			    
			    element[i]=new Element(s, o);
			    
			    /*///test print
			    System.out.println(s+" "+o);
			    /**/
			}
			element[numberOfElements]=new Element(")", true);
			
			//Stack 
			Stack<String> operand=new Stack<>();    //postfix form of the input
			Stack<String> operator=new Stack<>(); 	//temporarily stores operators
	
			operator.push("(");	//indicates the end
			
			/**
			 * Starts
			**/
			for(int i=0; !operator.empty(); i++){ //evaluation
		        if(!element[i].isOperator()){	//when operand is found
		            operand.push(element[i].getString());
		        }
		        else{
		            if(element[i].getString().equals("(")){
		                operator.push("(");
		            }
		            else if(element[i].getString().equals(")")){
		                while(operator.lastElement()!="("){
		                	if(operator.lastElement().equals("+") || operator.lastElement().equals("-") ||
		                			operator.lastElement().equals("x") ||operator.lastElement().equals("/") ||
		                			operator.lastElement().equals("P") ||operator.lastElement().equals("C") ||
		                			operator.lastElement().equals("^") || operator.lastElement().equals("mod")){
			                    String b = (String) operand.pop();   //taking first two elements of the stack
			                    String a = (String) operand.pop();
			                    
			                    String oprt = operator.pop();   //taking the top the operator of stack
		
			                    String r = aCOperation.operation(a, b, oprt);  //result after the operation of a & b
			                    operand.push(r);   //pushing the result in the operand stack
		                	}else{
		                		String a = (String) operand.pop();   //taking first element of the stack
			                    
			                    String oprt = operator.pop();   //taking the top the operator of stack
			                    
			                    String r = aCOperation.operation(a, oprt);  //result after the operation of a
			                    operand.push(r);   //pushing the result in the operand stack
		                	}
		                }
		                operator.pop();   //pops the "("
		            }
		            else{
		                int opertPrecedence = operatorPrecedence(element[i].getString());
	
		                while(operator.lastElement()!="("){
		                    int stackPrecedence = operatorPrecedence(operator.lastElement());
	
		                    if(stackPrecedence <= opertPrecedence){
		                    	break;
		                    }
		                    else{
		                    	if(operator.lastElement().equals("+") || operator.lastElement().equals("-") ||
			                			operator.lastElement().equals("x") ||operator.lastElement().equals("/") ||
			                			operator.lastElement().equals("P") ||operator.lastElement().equals("C") ||
			                			operator.lastElement().equals("^") || operator.lastElement().equals("mod")){
		                    		String b = (String) operand.pop();   //taking first two elements of the stack
		                    		String a = (String) operand.pop();
				                    
				                    String oprt = operator.pop();   //taking the top the operator of stack
			
				                    String r = aCOperation.operation(a, b, oprt);  //result after the operation of a & b
				                    operand.push(r);   //pushing the result in the operand stack
			                	}else{
			                		String a = (String) operand.pop();   //taking first element of the stack
				                    
				                    String oprt = operator.pop();   //taking the top the operator of stack
				                    
				                    String r = aCOperation.operation(a, oprt);  //result after the operation of a
				                    operand.push(r);   //pushing the result in the operand stack
			                	}
		                    }
		                }
		                operator.push(element[i].getString());
		            }
		        }
	
		        if(i>element.length) {
		            new Message("Math error!", 420);
		            break;
		        }
		    }
			//Ends
			
			result=(String) operand.pop();
			
			if(!operator.isEmpty() || !operand.isEmpty()){
				result="";
				new Message("Wrong input!",420);
			}
		}catch(Exception e){
			new Message("Math Error!\n   Invalid input!", 420);
		}
		
		return result;
	}
	
	
	//returns specific number of a char in a string
	private int charNumber(String string, char ch){
		int num=0;
		int length=string.length();

		for(int i=0; i<length; i++){
			if(string.charAt(i)==ch) num++;
		}
		
		return num;
	}
	
	//returns number depending on the operators
	private int operatorPrecedence(String str){
		int i=0;
		
		if(str.equals("+")) i=1;
		else if(str.equals("-")) i=2;
		else if(str.equals("x")) i=3;
		else if(str.equals("/")) i=4;
		else if(str.equals("mod")) i=5;		
		else if(str.equals("^")) i=6;
		else if(str.equals("!")) i=7;
		else if(str.equals("P") || str.equals("C")) i=8;
		else i=9;
		
		return i;
	}
	
	
	/*///test main method
	public static void main(String[] args) {
		System.out.println(new AdvancedCalculatorOperationsExecutor().infixEvaluation("1.099 + 223 - 12 x 2 "));
	}
	/**/
}
