package Ex;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
class Victory extends JDialog{
	private JLabel victorytext = new JLabel("you won!");
	private JButton okButton = new JButton("close");
	public Victory(JFrame frame, String title) {
		super(frame, title);
		setLayout(new FlowLayout());
		add(victorytext);
		add(okButton);
		setSize(200, 100);
		setLocation(500, 500);
		
		okButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
			}
		});
	}
}

class Defeat extends JDialog{
	private int dialogflag = 2;
	private JLabel defeattext = new JLabel("play again?");
	private JButton yes = new JButton("yes"), no = new JButton("no");
	public Defeat(JFrame frame, String title) {
		super(frame, title);
		setLayout(new FlowLayout());
		yes.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				turntoyes();
				setVisible(false);
			}
		});
		
		no.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				turntono();
				setVisible(false);
				System.exit(0);
			}
		});
		add(defeattext);
		add(yes);
		add(no);
		setSize(150, 100);
		setLocation(500, 500);
	}
	public int getFlag() {
		return dialogflag;
	}
	
	public void turntoyes() {
		dialogflag = 1;
	}
	
	public void turntono() {
		dialogflag  = 0;
	}
	
	public void setDialogFlag() {
		dialogflag = 2;
	}
}

class MyLabel extends JLabel{
	private int barSize;
	private int maxBarSize;
	
	public MyLabel(int num) {
		barSize = num;
		maxBarSize = num;
	}
	
	public int getbarSize() {
		return barSize;
	}
	
	public void setbarSize(int NowBarSize) {
		barSize = NowBarSize;
	}
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.setColor(Color.GREEN);
		int width = (int)((double)(this.getWidth())/maxBarSize*barSize);
		g.fillRect(0, 0, width, this.getHeight());
	}
	
	synchronized public void consume() {
		if(barSize==0) {
			try {
				wait();
			}catch(InterruptedException e) {
				return;
			}
		}
		barSize--;
		repaint();
		notify();
	}
}

class ConsumerThread extends Thread{
	private MyLabel bar;
	private boolean flag = true; 
	public ConsumerThread(MyLabel bar) {
		this.bar = bar;
	}
	
	public void finish() {
		flag = false;
	}
	
	public int getNowBarSize() {
		int num = bar.getbarSize();
		return num;
	}
	
	@Override
	public void run() {
		while(true) {
			try {
				sleep(500);
				if(flag==false)
					return;
				bar.consume();
			}catch(InterruptedException e) {
				return;
			}
		}
	}
}

public class OnlyMyJavaProject extends JFrame{
	private Victory vt;
	private Defeat df;
	private int nowBarSize;
	private int rest = 7;
	private MyLabel bar = new MyLabel(300);
	private ConsumerThread th;
	private JLabel area = new JLabel();
	private JLabel other = new JLabel();
	private Panel1 p1 = new Panel1();
	private Panel2 p2 = new Panel2();
	JLabel test = new JLabel("O");
	private int [][] answer = {{95,298},{127,102},{462,467},{245,126},{315,238},{360,218},{439,236}, {1000, 1000}};
	public OnlyMyJavaProject() {
		super("find the differences");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(null);
		Container c = getContentPane();
		
		vt = new Victory(this, "Victory");
		df = new Defeat(this, "Defeat...");
		
		p1.setSize(500, 500);
		p1.setLocation(0, 0);
		p1.addMouseListener(new MyMouseAdapter());
		p2.setSize(500, 500);
		p2.setLocation(500, 0);
		p2.addMouseListener(new MyNewMouseAdapter());
		
		bar.setBackground(Color.RED);
		bar.setOpaque(true);
		bar.setLocation(250, 600);
		bar.setSize(400, 20);
		
		c.add(p1);
		c.add(p2);
		c.add(bar);
		setSize(1020, 1000);
		setVisible(true);
		
		th = new ConsumerThread(bar);
		th.start();
		while(true) {
			nowBarSize = th.getNowBarSize();
			System.out.println(nowBarSize);
			if(nowBarSize==0) {
				df.setVisible(true);
				if(df.getFlag()==1) {
					nowBarSize = 300;
					int barSize = nowBarSize;
					bar.setbarSize(barSize);
					df.setDialogFlag();
					ConsumerThread tr = new ConsumerThread(bar);
					tr.start();
				}
				else if(df.getFlag()==0){
					break;
				}
			}
		}
	}
	private class Panel1 extends JPanel{
		Panel1(){
			area.setVisible(false);
		}
		public void paintComponent(Graphics g) {
			ImageIcon backimg = new ImageIcon("images/left.jpg");
			Image spiderman = backimg.getImage();
			Image newspiderman = spiderman.getScaledInstance(500, 500, Image.SCALE_SMOOTH);
			ImageIcon reback = new ImageIcon(newspiderman);
			g.drawImage(reback.getImage(), 0, 0, null);
		}
	}
	private class Panel2 extends JPanel{
		Panel2(){
			other.setVisible(false);
		}
		public void paintComponent(Graphics g) {
			ImageIcon backimg = new ImageIcon("images/right.jpg");
			Image spiderman = backimg.getImage();
			Image newspiderman = spiderman.getScaledInstance(500, 500, Image.SCALE_SMOOTH);
			ImageIcon reback = new ImageIcon(newspiderman);
			g.drawImage(reback.getImage(), 0, 0, null);
		}
	}
	class MyMouseAdapter extends MouseAdapter{
		public void mousePressed(MouseEvent e) {
			int x = e.getX();
			int y = e.getY();
			for(int i=0; i<answer.length;i++) {
				int pox = answer[i][0];
				int poy = answer[i][1];
				if(Math.abs(x-pox)<10&&Math.abs(y-poy)<10) {
					JLabel test = new JLabel("O");
					test.setSize(30, 30);
					test.setFont(new Font("Ariel", Font.ITALIC, 30));
					test.setForeground(Color.RED);
					test.setLocation(pox-12, poy-12);
					test.setVisible(true);
					p1.add(test, new Integer(1));
					rest--;
					if(rest==0) {
						th.finish();
						vt.setVisible(true);
					}
					while(i<answer.length) {
						answer[i][0] = answer[i+1][0];
						answer[i][1] = answer[i+1][1];
						i++;
					}
					break;
				}else {
					area.setText("X");
					area.setForeground(Color.RED);
					area.setSize(50, 40);
					area.setVisible(true);
					area.setLocation(x, y-5);
					area.setVisible(true);
					p1.add(area, new Integer(0));
				}
			}
		}
	}
	class MyNewMouseAdapter extends MouseAdapter{
		public void mousePressed(MouseEvent e) {
			int x = e.getX();
			int y = e.getY();
			for(int i=0; i<answer.length;i++) {
				int pox = answer[i][0];
				int poy = answer[i][1];
				if(Math.abs(x-pox)<10&&Math.abs(y-poy)<10) {
					JLabel test = new JLabel("O");
					test.setSize(30, 30);
					test.setFont(new Font("Ariel", Font.ITALIC, 30));
					test.setForeground(Color.RED);
					test.setLocation(pox-12, poy-12);
					test.setVisible(true);
					p2.add(test, new Integer(1));
					rest--;
					if(rest==0){
						th.finish();
						vt.setVisible(true);
					}
					while(i<answer.length) {
						answer[i][0] = answer[i+1][0];
						answer[i][1] = answer[i+1][1];
						i++;
					}
					break;
				}else {
					other.setText("X");
					other.setForeground(Color.RED);
					other.setSize(50, 40);
					other.setVisible(true);
					other.setLocation(x, y-5);
					other.setVisible(true);
					p2.add(other, new Integer(0));
				}
			}
		}
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new OnlyMyJavaProject();
	}

}
