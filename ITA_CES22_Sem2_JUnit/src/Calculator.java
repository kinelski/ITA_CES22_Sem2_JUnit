import java.util.*;

class Union{
	private boolean isNum;
	private Op op;
	private int num;
	
	public Union (Op op){
		isNum = false;
		this.op = op;
	}
	
	public Union (int num){
		isNum = true;
		this.num = num;
	}
	
	public Op getOp (){
		return op;
	}
	
	public int getNumber (){
		return num;
	}
	
	public boolean isNumber (){
		return isNum;
	}
	
	public String toString (){
		if (isNum) return Integer.toString(num);
		else {
			switch (op){
			case SUM: return "+";
			case SUB: return "-";
			case MUL: return "*";
			case DIV: return "/";
			case POW: return "^";
			case OPP: return "(";
			case CLP: return ")";
			
			default: return "";
			}
		}
	}
}

public class Calculator {
	
	private int ans;
	private LinkedList<Union> parent;
	private LinkedList<Union> polish;
	 
	public Calculator (){
		ans = 0;
		parent = new LinkedList<Union>();
		polish = new LinkedList<Union>();
	}
	 
	public int computeAnswer () throws ArithmeticException, Exception{
		int num1, num2;
		Iterator<Union> it;
		Stack<Integer> num_stack = new Stack<Integer>();
		Union union;
		
		computePolish();
		it = polish.iterator();
		
		while (it.hasNext()){
			union = it.next();
			
			if (union.isNumber())
				num_stack.push(union.getNumber());
			else {
				switch (union.getOp()){
				case SUM:
					num2 = num_stack.pop();
					num1 = num_stack.pop();
					num_stack.push(num1 + num2);
					break;
				case SUB:
					num2 = num_stack.pop();
					num1 = num_stack.pop();
					num_stack.push(num1 - num2);
					break;
				case MUL:
					num2 = num_stack.pop();
					num1 = num_stack.pop();
					num_stack.push(num1 * num2);
					break;
				case DIV:
					num2 = num_stack.pop();
					num1 = num_stack.pop();
                                        
                                        if (num2 == 0)
                                            throw new ArithmeticException();
                                        
					num_stack.push(num1 / num2);
					break;
				case POW:
					num2 = num_stack.pop();
					num1 = num_stack.pop();
                                        
                                        if (num1 == 0 && num2 < 0)
                                            throw new ArithmeticException();
                                        
					num_stack.push((int)Math.pow(num1, num2));
					break;
					
				default:
				}
			}
		}
		
		ans = num_stack.peek();
                parent.clear();
                
		return ans;
	}
	 
	public String getParent (){
		Iterator<Union> it = parent.iterator();
		String s_parent = "";
		 
		while (it.hasNext())
			s_parent += it.next().toString() + " ";
		
		return s_parent;
	}
	 
	public String getPolish () throws Exception{
		computePolish();
		 
		Iterator<Union> it = polish.iterator();
		String s_polish = "";
		 
		while (it.hasNext())
			s_polish += it.next().toString() + " ";
		 
		return s_polish;
	}
	 
	public void clear (){
		parent.clear();
	}
	 
	//Shunting-yard algorithm
	private void computePolish () throws Exception{
		Iterator<Union> it = parent.iterator();
		Stack<Union> op_stack = new Stack<Union>();
		Union union;
		 
		polish.clear();
		 
		while (it.hasNext()){
			union = it.next();
			 
			if (union.isNumber())
				polish.addLast (union);
			else {
				switch (union.getOp()){
				case SUM: case SUB:
					while (!op_stack.empty() &&
							op_stack.peek().getOp() != Op.OPP)
						polish.addLast (op_stack.pop());
					 
					op_stack.push(union);
					break;
					 
				case MUL: case DIV:
					while (!op_stack.empty() &&
							op_stack.peek().getOp() != Op.SUM &&
							op_stack.peek().getOp() != Op.SUB &&
							op_stack.peek().getOp() != Op.OPP)
						polish.addLast(op_stack.pop());
					 
					op_stack.push(union);
					break;
					 
				case POW: case OPP:
					op_stack.push(union);
					break;
					 
				case CLP:
					while (op_stack.peek().getOp() != Op.OPP){
                                            polish.addLast(op_stack.pop());
                                            if (op_stack.isEmpty())
                                                throw new Exception();
                                        }
					 
					op_stack.pop();
					break;
					
				default:
				}
			}
		}
		 
		while (!op_stack.empty()){
                    if (op_stack.peek().getOp() == Op.OPP ||
                            op_stack.peek().getOp() == Op.CLP)
                        throw new Exception();
                    
                    polish.addLast(op_stack.pop());
                }
	}
	 
	public void delete (){
		if (!parent.isEmpty())
			parent.removeLast();
	}
	 
	public void insert (Op op){
		parent.addLast(new Union(op));
	}
	 
	public void insert (int num){
		parent.addLast (new Union(num));
	}
}