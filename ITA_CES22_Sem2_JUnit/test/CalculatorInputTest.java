import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class CalculatorInputTest {
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @Before
    public void setUp() {
    }

    @Test
    public void testDigit() {
        CalculatorInput ci = new CalculatorInput();
        
        System.out.println("CalculatorInputTest: Testando o metodo testDigit");
        
        //Testa soma
        assertEquals("1", ci.digit(1));
        assertEquals("1 +", ci.digit(Op.SUM));
        assertEquals("1 + 5", ci.digit(5));
        assertEquals("6", ci.digit(Op.EQL));
        
        //Testa subtracao
        assertEquals("3", ci.digit(3));
        assertEquals("32", ci.digit(2));
        assertEquals("32 -", ci.digit(Op.SUB));
        assertEquals("32 - 6", ci.digit(6));
        assertEquals("26", ci.digit(Op.EQL));
        
        //Testa multiplicacao
        assertEquals("2", ci.digit(2));
        assertEquals("2 *", ci.digit(Op.MUL));
        assertEquals("2 * 9", ci.digit(9));
        assertEquals("18", ci.digit(Op.EQL));
        
        //Testa divisao
        assertEquals("2", ci.digit(2));
        assertEquals("28", ci.digit(8));
        assertEquals("28 /", ci.digit(Op.DIV));
        assertEquals("28 / 3", ci.digit(3));
        assertEquals("9", ci.digit(Op.EQL));
        
        //Testa exponenciacao
        assertEquals("4", ci.digit(4));
        assertEquals("4 ^", ci.digit(Op.POW));
        assertEquals("4 ^ 3", ci.digit(3));
        assertEquals("64", ci.digit(Op.EQL));
        
        //Testa precedencia de operacoes
        assertEquals("3", ci.digit(3));
        assertEquals("3 +", ci.digit(Op.SUM));
        assertEquals("3 + 5", ci.digit(5));
        assertEquals("3 + 5 *", ci.digit(Op.MUL));
        assertEquals("3 + 5 * 2", ci.digit(2));
        assertEquals("13", ci.digit(Op.EQL));
        
        //Testa funcionamento dos parenteses
        assertEquals("(", ci.digit(Op.OPP));
        assertEquals("( 7", ci.digit(7));
        assertEquals("( 7 +", ci.digit(Op.SUM));
        assertEquals("( 7 + 2", ci.digit(2));
        assertEquals("( 7 + 2 )", ci.digit(Op.CLP));
        assertEquals("( 7 + 2 ) *", ci.digit(Op.MUL));
        assertEquals("( 7 + 2 ) * 8", ci.digit(8));
        assertEquals("72", ci.digit(Op.EQL));
        
        //Testa funcao clear
        assertEquals("2", ci.digit(2));
        assertEquals("2 +", ci.digit(Op.SUM));
        assertEquals("", ci.digit(Op.CLR));
        
        //Testa erro de divisao por zero
        assertEquals("5", ci.digit(5));
        assertEquals("5 /", ci.digit(Op.DIV));
        assertEquals("5 / 0", ci.digit(0));
        assertEquals("Erro: divisao por zero.", ci.digit(Op.EQL));
        
        //Testa erro de sintaxe.
        assertEquals("(", ci.digit(Op.OPP));
        assertEquals("( 7", ci.digit(7));
        assertEquals("( 7 +", ci.digit(Op.SUM));
        assertEquals("Erro de sintaxe.", ci.digit(Op.EQL));
    }
    
}
