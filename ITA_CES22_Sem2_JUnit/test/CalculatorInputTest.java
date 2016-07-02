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
        
        assertEquals("1", ci.digit(1));
        assertEquals("1 +", ci.digit(Op.SUM));
        assertEquals("1 + 5", ci.digit(5));
        assertEquals("6", ci.digit(Op.EQL));
        
        assertEquals("3", ci.digit(3));
        assertEquals("32", ci.digit(2));
        assertEquals("32 -", ci.digit(Op.SUB));
        assertEquals("32 - 6", ci.digit(6));
        assertEquals("26", ci.digit(Op.EQL));
        
        assertEquals("2", ci.digit(2));
        assertEquals("2 *", ci.digit(Op.MUL));
        assertEquals("2 * 9", ci.digit(9));
        assertEquals("18", ci.digit(Op.EQL));
        
        assertEquals("2", ci.digit(2));
        assertEquals("28", ci.digit(8));
        assertEquals("28 /", ci.digit(Op.DIV));
        assertEquals("28 / 3", ci.digit(3));
        assertEquals("9", ci.digit(Op.EQL));
    }
    
}
