import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

class MainUserFrame extends JFrame implements ActionListener{

	//MainUserPanel mainuser = new MainUserPanel();
	//TableModelTest mainuser = new TableModelTest();
	String[] luser = MainUserPanel.listuser;
	public static JButton jbt1;
	public static JButton jbt2;
	public static JButton jbt3;
	MainUserPanel app = new MainUserPanel();
	JPanel jp = new JPanel();
	JPanel jp1 = new JPanel();

public void actionPerformed (ActionEvent e)  {
		System.out.println(e.getActionCommand());
		String label = e.getActionCommand();
           	if (label.equals("New User")) {
             		new DelUpUser();
			//jbt1.setEnabled(false);
			//jbt2.setEnabled(false);
			//jbt3.setEnabled(false);
			dispose();
          	 } else if (label.equals(" Update ")) {
			new DelUpUser(luser);
			//jbt1.setEnabled(false);
			//jbt2.setEnabled(false);
			//jbt3.setEnabled(false);
			dispose();
		} else if (label.equals(" Cancel ")) {
		
		if(Confirm("Are You sure to Logout !!")){
		MainMenuFrame.file.setEnabled(true);
		dispose(); }
		

		}
		
	}

public MainUserFrame() {
		super("User Main Frame");
		Container pane= getContentPane();
		
		//final TableModelTest app = new TableModelTest();
		
		jp.setLayout(new BorderLayout());
		
	    	jbt1=new JButton("New User");
		jbt1.setToolTipText("Add New User");
		jbt1.addActionListener(this);
		jbt2=new JButton(" Update ");
		jbt2.setToolTipText("Update User");
		jbt2.setEnabled(false);
		jbt2.addActionListener(this);
		jbt3=new JButton(" Cancel ");
		jbt3.setToolTipText("Cancel");
		jbt3.addActionListener(this);
		jp1.add(jbt1);
		jp1.add(jbt2);
		jp1.add(jbt3);
		jp1.setBorder(BorderFactory.createTitledBorder("User Menu "));
		jp.add("North",jp1);
		jp.add("Center",app);
		pane.add(jp);
		//setContentPane(jp);

		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
			JOptionPane.showMessageDialog(new JFrame(),"Window Closing now!!"
				,"closing dialog",JOptionPane.WARNING_MESSAGE);
			MainMenuFrame.file.setEnabled(true); 
			
				
			}
			});  
		setSize(500,400);
		setVisible(true);
	}
	void printTest(String kailo) {
		System.out.println(kailo);
		}

	public boolean Confirm(String msg) {
		//String msg;
		boolean check=false;
		int r = JOptionPane.showConfirmDialog(new JFrame(),msg,"confirm",JOptionPane.YES_NO_OPTION);
		switch(r) {
			case JOptionPane.YES_OPTION: check=true;
						break;
			case JOptionPane.NO_OPTION: check=false;break;
			//case JOptionPane.CANCEL_OPTION: check=false;
			//			break;
			}
		return check;
	}


	public static void main(String args[]) {
	new MainUserFrame();
		

	}

}

