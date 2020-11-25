package Ex;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

class MyCalculator extends JDialog{
	private JTextField num1 = new JTextField(10), num2 = new JTextField(10);
	private JButton plus = new JButton("Add");
	
	public MyCalculator(JFrame frame, String title) {
		super(frame, title, true);
		setLayout(new FlowLayout());
		add(new JLabel("두 수를 더합니다."));
		add(num1);
		add(num2);
		add(plus);
		setSize(180, 250);
		
		plus.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
			}
		});
	}
	public int getPlus() {
		if(num1.getText().length()==0&&num2.getText().length()==0) return 0;
		else {
			int x = Integer.parseInt(num1.getText());
			int y = Integer.parseInt(num2.getText());
			return x+y;
		}
	}
}

public class Ex06 extends JFrame{
	private MyCalculator calc;
	public Ex06() {
		super("다이얼로그 만들기");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(new FlowLayout());
		JButton c = new JButton("calculate");
		JLabel result = new JLabel("계산결과 출력");
		result.setOpaque(true);
		result.setBackground(Color.green);
		
		calc = new MyCalculator(this, "Calculation Dialog");
		
		c.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				calc.setVisible(true);
				int num = calc.getPlus();
				result.setText(Integer.toString(num));
			}
		});
		getContentPane().add(c);
		getContentPane().add(result);
		setSize(250, 200);
		setVisible(true);
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new Ex06();
	}

}
