package Ex;
import javax.swing.*;
public class Ex01 extends JFrame{
	public Ex01() {
		setTitle("�޴� �����");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		createMenu();
		setSize(300, 300);
		setVisible(true);
	}
	
	private void createMenu() {
		JMenuBar bar = new JMenuBar();
		JMenu menu = new JMenu("����");
		
		menu.add(new JMenuItem("ȭ��Ȯ��"));
		menu.add(new JMenuItem("������"));
		
		bar.add(new JMenu("����"));
		bar.add(new JMenu("����"));
		bar.add(menu);
		bar.add(new JMenu("�Է�"));
		
		setJMenuBar(bar);
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new Ex01();
	}

}
