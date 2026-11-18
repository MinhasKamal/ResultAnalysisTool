/**
 * Developers: Minhas Kamal(BSSE-0509)
 * Date: -Jan-2013
 * Program: Scientific Calculator
**/

package com.resultAnalysisTool.tools.dataAnalyse.analyseDataBase.addNewColumn.operation;

import com.resultAnalysisTool.util.message.Message;


public class AdvancedCalculatorOperation {
	
	//constructor
	public AdvancedCalculatorOperation() {
		super();
	}
	
	/**
	 * Interfacing method															#****IM****#
	**/
	public String operation(String operand1, String operand2, String operator){
		
		if(operator.equals("+")) return sum(operand1, operand2);
		else if(operator.equals("-")) return substract(operand1, operand2);
		else if(operator.equals("x")) return multiply(operand1, operand2);
		else if(operator.equals("/")) return divide(operand1, operand2);
		else if(operator.equals("^")) return power(operand1, operand2);
		else if(operator.equals("C")) return nCr(operand1, operand2);
		else if(operator.equals("P")) return nPr(operand1, operand2);
		else if(operator.equals("mod")) return modulus(operand1, operand2);
		else return "";
	}
	public String operation(String operand, String operator){
		if(operator.equals("sqrt")) return sqrt(operand);
		else if(operator.equals("cbrt")) return cbrt(operand);
		else if(operator.equals("fact")) return factorial(operand);
		else if(operator.equals("sqre")) return sqre(operand);
		else if(operator.equals("cube")) return cube(operand);
		else if(operator.equals("sin")) return sine(operand);
		else if(operator.equals("asin")) return asine(operand);
		else if(operator.equals("sinh")) return sineH(operand);
		else if(operator.equals("cos")) return cosine(operand);
		else if(operator.equals("acos")) return acosine(operand);
		else if(operator.equals("cosh")) return cosineH(operand);
		else if(operator.equals("tan")) return tangent(operand);
		else if(operator.equals("atan")) return atangent(operand);
		else if(operator.equals("tanh")) return tangentH(operand);
		else if(operator.equals("log")) return logarithm10(operand);
		else if(operator.equals("ln")) return logarithmE(operand);

		else return "";
	}
	//End of Interfacing method														#_____IM_____#
	
	
	/**
	 * Simple operational methods													#******S*****#
	**/
	private String sum(String operand1, String operand2){
		double a=0;
		double b=0;
		
		try{
			a=Double.parseDouble(operand1);
			b=Double.parseDouble(operand2);
		}catch(Exception e){
			new Message("Math Error! \n   Input correctly.", 420);
		}
		
		return ""+(a+b);
	}
	private String substract(String operand1, String operand2){
		double a=0;
		double b=0;
		
		try{
			a=Double.parseDouble(operand1);
			b=Double.parseDouble(operand2);
		}catch(Exception e){
			new Message("Math Error! \n   Input correctly.", 420);
		}
		
		return ""+(a-b);
	}
	private String multiply(String operand1, String operand2){
		double a=0;
		double b=0;
		
		try{
			a=Double.parseDouble(operand1);
			b=Double.parseDouble(operand2);
		}catch(Exception e){
			new Message("Math Error! \n   Input correctly.", 420);
		}
		
		return ""+(a*b);
	}
	private String divide(String operand1, String operand2){
		String r="0";
		double a=0;
		double b=1;
		
		try{
			a=Double.parseDouble(operand1);
			b=Double.parseDouble(operand2);
		}catch(Exception e){
			new Message("Math Error! \n   Input correctly.", 420);
		}
		
		if(b!=0){
			r= ""+(a/b);
		}else{
			new Message("Cannot divide by Zero!", 420);
		}
		
		return r;
	}
	private String power(String operand1, String operand2){
		String r="0";
		double a=0;
		double b=0;
		
		try{
			a=Double.parseDouble(operand1);
			b=Double.parseDouble(operand2);
			
			r=""+Math.pow(a, b);
		}catch(Exception e){
			new Message("Math Error! \n   Input correctly.", 420);
			return "";
		}
		
		return r;
	}
		
	
	private String sqrt(String operand){
		double a=0;
		
		try{
			a=Double.parseDouble(operand);
			
			if(a>=0){
				return ""+Math.sqrt(a);
			}else{
				new Message("There is no square-root of \n   negative number!", 420);
				return "";
			}
		}catch(Exception e){
			new Message("Wrong Input!", 420);
			return "";
		}
	}
	//End of Simple operational methods 									#______S______#
	
	/**
	 * complex operational methods											#******C******#
	**/
	
	private String nPr(String operand1, String operand2){
		String r="";
		double a=0;
		double b=0;
		
		try{
			a=Double.parseDouble(operand1);
			b=Double.parseDouble(operand2);
			
			if(a<0 || b<0 || a<b){
				new Message("Math error!", 420);
			}else{
				r = divide( factorial(operand1), factorial( substract(operand1, operand2) ) );
			}
		}catch(Exception e){
			new Message("Math error!", 420);
		}
		
		return r;
	}
	
	private String nCr(String operand1, String operand2){
		String r="";
		double a=0;
		double b=0;
		
		try{
			a=Double.parseDouble(operand1);
			b=Double.parseDouble(operand2);
			
			if(a<0 || b<0 || a<b){
				new Message("Math error!", 420);
			}else{
				r = divide(factorial(operand1), multiply( (factorial( substract(operand1, operand2) )), factorial(operand2) ));
			}
		}catch(Exception e){
			new Message("Math error!", 420);
		}
		
		return r;
	}
	
	private String modulus(String operand1, String operand2){
		double a=0;
		double b=0;
		
		try{
			a=Double.parseDouble(operand1);
			b=Double.parseDouble(operand2);
		}catch(Exception e){
			new Message("Math Error! \n   Input correctly.", 420);
		}
		
		return ""+(a%b);
	}
	
	private String sqre(String operand){
		double a=0;
		
		try{
			a=Double.parseDouble(operand);
			
			return ""+ a*a;
		}catch(Exception e){
			new Message("Wrong Input!", 420);
			return "";
		}
	}
	
	private String cube(String operand){
		double a=0;
		
		try{
			a=Double.parseDouble(operand);
			
			return ""+ a*a*a;
		}catch(Exception e){
			new Message("Wrong Input!", 420);
			return "";
		}
	}
	
	private String cbrt(String operand){
		double a=0;
		
		try{
			a=Double.parseDouble(operand);
			
			return ""+Math.cbrt(a);
		}catch(Exception e){
			new Message("Wrong Input!", 420);
			return "";
		}
	}
	
	private String factorial(String operand){
		String r="1";
		
		long i=1;
		
		try{
			double a=Double.parseDouble(operand);
			
			for(int j=1; j<=a; j++){
				i = i*j;
			}
		}catch(Exception e){
			new Message("Math Error!", 420);
		}
		
		r=""+i;
		
		return r;
	}
	
	//																		***
	private String sine(String operand){
		String r="";
		
		try{
			double a=Double.parseDouble(operand);
			
			r="" + Math.sin((a * Math.PI)/180);
			
		}catch(Exception e){
			new Message("Math Error!", 420);
		}
		
		return r;
	}
	private String asine(String operand){
		String r="";
		
		try{
			double a=Double.parseDouble(operand);
			
			r="" + (Math.asin(a)*180)/Math.PI;
			
		}catch(Exception e){
			new Message("Math Error!", 420);
		}
		
		return r;
	}
	
	private String cosine(String operand){
		String r="";
		
		try{
			double a=Double.parseDouble(operand);
			
			r="" + Math.cos((a * Math.PI)/180);
			
		}catch(Exception e){
			new Message("Math Error!", 420);
		}
		
		return r;
	}
	private String acosine(String operand){
		String r="";
		
		try{
			double a=Double.parseDouble(operand);
			
			r="" + (Math.acos(a)*180)/Math.PI;
			
		}catch(Exception e){
			new Message("Math Error!", 420);
		}
		
		return r;
	}
	
	private String tangent(String operand){
		String r="";
		
		try{
			double a=Double.parseDouble(operand);
			
			r="" + Math.tan((a * Math.PI)/180);
			
		}catch(Exception e){
			new Message("Math Error!", 420);
		}
		
		return r;
	}
	private String atangent(String operand){
		String r="";
		
		try{
			double a=Double.parseDouble(operand);
			
			r="" + (Math.atan(a)*180)/Math.PI;
			
		}catch(Exception e){
			new Message("Math Error!", 420);
		}
		
		return r;
	}
	
	private String sineH(String operand){
		String r="";
		
		try{
			double a=Double.parseDouble(operand);
			
			r="" + Math.sinh((a * Math.PI)/180);
			
		}catch(Exception e){
			new Message("Math Error!", 420);
		}
		
		return r;
	}
	
	private String cosineH(String operand){
		String r="";
		
		try{
			double a=Double.parseDouble(operand);
			
			r="" + Math.cosh((a * Math.PI)/180);
			
		}catch(Exception e){
			new Message("Math Error!", 420);
		}
		
		return r;
	}
	
	private String tangentH(String operand){
		String r="";
		
		try{
			double a=Double.parseDouble(operand);
			
			r="" + Math.tanh((a * Math.PI)/180);
			
		}catch(Exception e){
			new Message("Math Error!", 420);
		}
		
		return r;
	}
	
	
	//																			***
	private String logarithm10(String operand){
		String r="";
		
		try{
			double a=Double.parseDouble(operand);
			
			r="" + Math.log10(a);
			
		}catch(Exception e){
			new Message("Math Error!", 420);
		}
		
		return r;
	}
	
	private String logarithmE(String operand){
		String r="";
		
		try{
			double a=Double.parseDouble(operand);
			
			r="" + Math.log(a);
			
		}catch(Exception e){
			new Message("Math Error!", 420);
		}
		
		return r;
	}
	//End of complex operational methods										#_______C_______#
	
	/*///test main method
	public static void main(String[] args) {
		System.out.println(new AdvancedCalculatorOperation().asine("30"));
	}
	/**/
}



