import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.script.ScriptEngineManager;
import javax.script.ScriptEngine;
import javax.script.ScriptException;

public class Calculator1 implements ActionListener {

    private final JFrame f;
    private final JButton b1,b2,b3,b4,b5,b6,b7,b8,b9,b0,B,C,plus,minus,mul,div,eq,dot;
    private final JTextField tf1,tf2;
    private final JPanel jp;
    private final int height = 600, width = 600;
    private  String pre;

    Calculator1()
    {
        f = new JFrame("Cals");

        tf1 = new JTextField();
        tf1.setHorizontalAlignment(JTextField.RIGHT);
        tf1.setBounds(10,10,width-25,50);
        tf1.setEditable(false);
        tf1.setFont(new Font("Serif", Font.PLAIN, 22));

        tf2 = new JTextField();
        tf2.setHorizontalAlignment(JTextField.RIGHT);
        tf2.setBounds(10,70,width-25,50);
        tf2.setEditable(false);
        tf2.setFont(new Font("Serif", Font.PLAIN, 22));

        B = new JButton("B");
        B.setBounds(10,150,100,40);
        B.setFont(new Font("Serif", Font.PLAIN, 22));

        C = new JButton("C");
        C.setBounds(140,150,100,40);
        C.setFont(new Font("Serif", Font.PLAIN, 22));

        b7 = new JButton("7");       b7.setFont(new Font("Serif", Font.PLAIN, 22));
        b8 = new JButton("8");       b8.setFont(new Font("Serif", Font.PLAIN, 22));
        plus = new JButton("+");     plus.setFont(new Font("Serif", Font.PLAIN, 22));
        b9 = new JButton("9");       b9.setFont(new Font("Serif", Font.PLAIN, 22));
        b4 = new JButton("4");       b4.setFont(new Font("Serif", Font.PLAIN, 22));
        b5 = new JButton("5");       b5.setFont(new Font("Serif", Font.PLAIN, 22));
        minus = new JButton("-");    minus.setFont(new Font("Serif", Font.PLAIN, 22));
        b6 = new JButton("6");       b6.setFont(new Font("Serif", Font.PLAIN, 22));
        b1 = new JButton("1");       b1.setFont(new Font("Serif", Font.PLAIN, 22));
        b2 = new JButton("2");       b2.setFont(new Font("Serif", Font.PLAIN, 22));
        mul = new JButton("*");      mul.setFont(new Font("Serif", Font.PLAIN, 22));
        b3 = new JButton("3");       b3.setFont(new Font("Serif", Font.PLAIN, 22));
        b0 = new JButton("0");       b0.setFont(new Font("Serif", Font.PLAIN, 22));
        dot = new JButton(".");      dot.setFont(new Font("Serif", Font.PLAIN, 22));
        eq  = new JButton("=");      eq.setFont(new Font("Serif", Font.PLAIN, 22));
        div = new JButton("/");      div.setFont(new Font("Serif", Font.PLAIN, 22));


        jp = new JPanel(new GridLayout(4,4,20,20));

        jp.add(b7);  jp.add(b8);  jp.add(b9);  jp.add(plus);
        jp.add(b4);  jp.add(b5);  jp.add(b6);  jp.add(minus);
        jp.add(b1);  jp.add(b2);  jp.add(b3);  jp.add(mul);
        jp.add(b0);  jp.add(dot); jp.add(div); jp.add(eq);

        jp.setBounds(10,220,width-40,280);

        b0.addActionListener(this);    b4.addActionListener(this);    b8.addActionListener(this);
        minus.addActionListener(this); b1.addActionListener(this);    b5.addActionListener(this);
        b9.addActionListener(this);    mul.addActionListener(this);   b2.addActionListener(this);
        b6.addActionListener(this);    dot.addActionListener(this);   eq.addActionListener(this);
        b3.addActionListener(this);    b7.addActionListener(this);    plus.addActionListener(this);
        div.addActionListener(this);   C.addActionListener(this);     B.addActionListener(this);


        f.add(tf1);  f.add(tf2); f.add(B); f.add(C); f.add(jp);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setLayout(null);
        f.setSize(width,height);
        f.setLocationRelativeTo(null);
        f.setResizable(false);
        f.setVisible(true);

    }


    @Override
    public void actionPerformed(ActionEvent e)
    {
        if(tf2.getText().toString().equals("ERROR") | tf2.getText().toString().equals("Infinity")) tf2.setText(null);

        if(e.getSource() == b0)   tf2.setText(tf2.getText().toString() + "0");
        if(e.getSource() == b1)   tf2.setText(tf2.getText().toString() + "1");
        if(e.getSource() == b2)   tf2.setText(tf2.getText().toString() + "2");
        if(e.getSource() == b3)   tf2.setText(tf2.getText().toString() + "3");
        if(e.getSource() == b4)   tf2.setText(tf2.getText().toString() + "4");
        if(e.getSource() == b5)   tf2.setText(tf2.getText().toString() + "5");
        if(e.getSource() == b6)   tf2.setText(tf2.getText().toString() + "6");
        if(e.getSource() == b7)   tf2.setText(tf2.getText().toString() + "7");
        if(e.getSource() == b8)   tf2.setText(tf2.getText().toString() + "8");
        if(e.getSource() == b9)   tf2.setText(tf2.getText().toString() + "9");
        if(e.getSource() == plus) tf2.setText(tf2.getText().toString() + "+");
        if(e.getSource() == minus) tf2.setText(tf2.getText().toString() + "-");
        if(e.getSource() == mul) tf2.setText(tf2.getText().toString() + "*");
        if(e.getSource() == div) tf2.setText(tf2.getText().toString() + "/");

        if(e.getSource() == dot) { if(tf2.getText().toString().indexOf(".")<0)  tf2.setText(tf2.getText().toString() + "."); }

        if(e.getSource() == C)     tf2.setText(null);

        if(e.getSource() == B )   if(tf2.getText().toString().length()!=0) tf2.setText(tf2.getText().toString().substring(0,tf2.getText().toString().length()-1));

        if(e.getSource() == eq)    Evaluate(tf2.getText().toString());
      }


      public static void main(String[] args){
        new Calculator1();
      }

      public void Evaluate(String input)
      {
          ScriptEngineManager mgr = new ScriptEngineManager();
          ScriptEngine engine = mgr.getEngineByName("JavaScript");
          String result = "";

          try
          {
              result =  engine.eval(input).toString();
          } catch (ScriptException e) {
          }


          if(result.equals(""))  tf2.setText("ERROR");
          else          tf2.setText(result);

          tf1.setText(input);

      }
}
