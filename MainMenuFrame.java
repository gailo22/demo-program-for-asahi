import javax.swing.*;
import java.awt.event.*;
import java.awt.*;

public class MainMenuFrame extends JFrame implements ActionListener {
	public static JMenu file;
	public void actionPerformed (ActionEvent e)  {
		System.out.println(e.getActionCommand());
		String label = e.getActionCommand();
           	if (label.equals("Login")) {
             	new LoginDialog();
          	 } else if (label.equals("Exit")) {
		System.exit(0);
		}
		
	}

	public MainMenuFrame() {
		super("Main MENU FRAME ");
		JMenuBar jmb = new JMenuBar();
		file = new JMenu("  MENU");
		JMenuItem item;
		file.add(item = new JMenuItem("Login", new ImageIcon("dukeWaveRed.gif")));
		item.addActionListener(this);
		file.addSeparator();
		file.add(item =new JMenuItem("Exit"));
		item.addActionListener(this);
		jmb.add(file);
		setJMenuBar(jmb);
		JPanel pane = new JPanel(new BorderLayout());
		JPanel pane1 = new JPanel();
		//pane.setLayout(new BorderLayout());
		JLabel pic = new JLabel(new ImageIcon("Rabbit.gif"));
		JLabel label = new JLabel("Click MENU for Start");
		pane1.add(label);
		pane.add("North",pane1);
		pane.add("Center",pic);
		setContentPane(pane);
		setSize(400,250);
		setVisible(true);
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
			});

	}

	public static void main(String args[]) {
		 new MainMenuFrame();
		
	}
}
