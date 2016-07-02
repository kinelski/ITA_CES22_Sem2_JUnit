public class CalculatorInput {
	private boolean typingNum;
	private int num;
	private Calculator calc;
	
	public CalculatorInput (){
		typingNum = true;
		num = 0;
		calc = new Calculator();
	}
	
	public String digit (int alg){
		if (alg < 0 || alg > 9)
			return calc.getParent();
		
		if (typingNum){
			num = 10*num + alg;
			calc.delete();
			calc.insert(num);
		} else {
			num = alg;
			calc.insert(num);
			typingNum = true;
		}
		
		return calc.getParent();
	}
	
	public String digit (Op op){
		switch (op){
		case SUM: case SUB: case MUL: case DIV: case POW: case OPP: case CLP:
			calc.insert(op);
			typingNum = false;
			break;
		case CLR:
			calc.clear();
			typingNum = true;
			num = 0;
			break;
		case EQL:
                        typingNum = false;
                        try{
                            return Integer.toString(calc.computeAnswer());
                        } catch (ArithmeticException e){
                            calc.clear();
                            return "Erro: divisao por zero.";
                        } catch (Exception e){
                            calc.clear();
                            return "Erro de sintaxe.";
                        }
			
		default:
		}
		
		return calc.getParent();
	}
}