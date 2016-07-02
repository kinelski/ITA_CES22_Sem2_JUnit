
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextField;

public class CalculatorGraphic {
    private CalculatorInput ci;
    private JFrame frame;
    private JTextField text;
    
    public final int BUTTON_W = 50;
    public final int BUTTON_H = 30;
    public final int WIDTH = 256;
    public final int HEIGHT = 179;
    public final int TEXT_W = WIDTH;
    public final int TEXT_H = 30;
    
    public CalculatorGraphic(){
        ci = new CalculatorInput();
        frame = new JFrame();
        text = new JTextField();
        
        int cont = 0;
        
        JButton[] button_numbers = new JButton[10];
        for (int i=0; i<10; i++)
            button_numbers[i] = new JButton(Integer.toString(i));
        
        JButton[] button_op = new JButton[10];
        button_op[0] = new JButton("+");
        button_op[1] = new JButton("-");
        button_op[2] = new JButton("*");
        button_op[3] = new JButton("/");
        button_op[4] = new JButton("^");
        button_op[5] = new JButton("(");
        button_op[6] = new JButton(")");
        button_op[7] = new JButton("<");
        button_op[8] = new JButton("C");
        button_op[9] = new JButton("=");
        
        text.setBounds(0, 0, TEXT_W, TEXT_H);
        
        button_numbers[7].setBounds(0, TEXT_H, BUTTON_W, BUTTON_H);
        button_numbers[8].setBounds(BUTTON_W, TEXT_H, BUTTON_W, BUTTON_H);
        button_numbers[9].setBounds(2*BUTTON_W, TEXT_H, BUTTON_W, BUTTON_H);
        button_numbers[4].setBounds(0, TEXT_H+BUTTON_H, BUTTON_W, BUTTON_H);
        button_numbers[5].setBounds(BUTTON_W, TEXT_H+BUTTON_H, BUTTON_W, BUTTON_H);
        button_numbers[6].setBounds(2*BUTTON_W, TEXT_H+BUTTON_H, BUTTON_W, BUTTON_H);
        button_numbers[1].setBounds(0, TEXT_H+2*BUTTON_H, BUTTON_W, BUTTON_H);
        button_numbers[2].setBounds(BUTTON_W, TEXT_H+2*BUTTON_H, BUTTON_W, BUTTON_H);
        button_numbers[3].setBounds(2*BUTTON_W, TEXT_H+2*BUTTON_H, BUTTON_W, BUTTON_H);
        button_numbers[0].setBounds(BUTTON_W, TEXT_H+3*BUTTON_H, BUTTON_W, BUTTON_H);
        
        button_op[0].setBounds(3*BUTTON_W, TEXT_H, BUTTON_W, BUTTON_H);
        button_op[1].setBounds(3*BUTTON_W, TEXT_H+BUTTON_H, BUTTON_W, BUTTON_H);
        button_op[2].setBounds(4*BUTTON_W, TEXT_H, BUTTON_W, BUTTON_H);
        button_op[3].setBounds(4*BUTTON_W, TEXT_H+BUTTON_H, BUTTON_W, BUTTON_H);
        button_op[4].setBounds(4*BUTTON_W, TEXT_H+2*BUTTON_H, BUTTON_W, BUTTON_H);
        button_op[5].setBounds(0, TEXT_H+3*BUTTON_H, BUTTON_W, BUTTON_H);
        button_op[6].setBounds(2*BUTTON_W, TEXT_H+3*BUTTON_H, BUTTON_W, BUTTON_H);
        button_op[7].setBounds(4*BUTTON_W, TEXT_H+3*BUTTON_H, BUTTON_W, BUTTON_H);
        button_op[8].setBounds(3*BUTTON_W, TEXT_H+3*BUTTON_H, BUTTON_W, BUTTON_H);
        button_op[9].setBounds(3*BUTTON_W, TEXT_H+2*BUTTON_H, BUTTON_W, BUTTON_H);
        
        for (int i=0; i<10; i++)
            button_numbers[i].addActionListener (new ActionListener(){
                public void actionPerformed(ActionEvent e) {
                    JButton button = (JButton)e.getSource();
                    String new_text = ci.digit(Integer.parseInt(button.getText()));
                    text.setText(new_text);
                }
            });
        
        for (int i=0; i<10; i++)
            button_op[i].addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent e){
                    JButton button = (JButton)e.getSource();
                    String new_text, label = button.getText();
                    
                    if (label == "+")
                        new_text = ci.digit(Op.SUM);
                    else if (label == "-")
                        new_text = ci.digit(Op.SUB);
                    else if (label == "*")
                        new_text = ci.digit(Op.MUL);
                    else if (label == "/")
                        new_text = ci.digit(Op.DIV);
                    else if (label == "^")
                        new_text = ci.digit(Op.POW);
                    else if (label == "(")
                        new_text = ci.digit(Op.OPP);
                    else if (label == ")")
                        new_text = ci.digit(Op.CLP);
                    else if (label == "=")
                        new_text = ci.digit(Op.EQL);
                    else if (label == "C")
                        new_text = ci.digit(Op.CLR);
                    else
                        new_text = ci.digit(Op.DEL);
                    
                    text.setText(new_text);
                }
            });
        
        text.setEditable(false);
        text.setSize(TEXT_W, TEXT_H);
        
        frame.add(text);
        for (int i=0; i<10; i++){
            frame.add(button_numbers[i]);
            if (i!=7) frame.add(button_op[i]);
        }
        
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);
        frame.setSize(WIDTH, HEIGHT);
        frame.setResizable(false);
        frame.setVisible(true);
    }
}
