package Ex;
import javax.swing.*;
public class Ex01 extends JFrame{
	public Ex01() {
		setTitle("메뉴 만들기");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		createMenu();
		setSize(300, 300);
		setVisible(true);
	}
	
	private void createMenu() {
		JMenuBar bar = new JMenuBar();
		JMenu menu = new JMenu("보기");
		
		menu.add(new JMenuItem("화면확대"));
		menu.add(new JMenuItem("쪽윤곽"));
		
		bar.add(new JMenu("파일"));
		bar.add(new JMenu("편집"));
		bar.add(menu);
		bar.add(new JMenu("입력"));
		
		setJMenuBar(bar);
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new Ex01();
	}

}
