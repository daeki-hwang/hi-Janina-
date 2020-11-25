package Ex;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
public class MyJavaProject extends JFrame{
	private JLabel[] wholenum;
	public MyJavaProject() {
		super("로또 생성기");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Container c = getContentPane();
		setLayout(new FlowLayout());
		JButton calcon = new JButton("번호 뽑기");
		wholenum = new JLabel[6];
		for(int i=0; i<wholenum.length;i++) 
			wholenum[i] = new JLabel("로또");
		
		calcon.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				for(int i=0;i<wholenum.length;i++) {
					wholenum[i].setText(Integer.toString((int)(Math.random()*45)+1));
					for(int j=0; j<i;j++) {
						if(wholenum[i]==wholenum[j])
							i--;
					}
				}
			}
		});
		for(int i=0;i<wholenum.length;i++)
			c.add(wholenum[i]);
		c.add(calcon);
		setSize(250, 250);
		setVisible(true);
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new MyJavaProject();
	}

}
