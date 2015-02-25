import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class DelUpUser implements ActionListener{

	
	SqlManage sqlmanage = new SqlManage();
	

	private JTextField tusername;
	private JPasswordField tpassword;
	private JTextField tfname;
	private JTextField tlname;
	private JTextField tdep;
	
	private JButton bupdate;
	private JButton bdelete;
	private JButton bcancel;


	public String username;
	public String password;
	public String fname;
	public String lname;
	public String dep;

	String oldpassword ;

	
	
	JFrame frame = new JFrame("Add & Update  Frame");
	static String[] ConnectOptionNames = { "Login", "Cancel" };
	

	public DelUpUser() {
		
		frame.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
			//MainUserFrame.jbt1.setEnabled(true);
			//MainUserFrame.jbt2.setEnabled(true);
			//MainUserFrame.jbt3.setEnabled(true);
			new MainUserFrame();
			}
			});  
		
		Container pane = frame.getContentPane();
		JPanel pane1 = new JPanel();
		pane.setLayout(new BorderLayout());
		pane1.add(new JLabel("Update & Delete User for Admin"));
		JPanel pane2 = new JPanel();
		pane2.setLayout(new GridLayout(5,1));
		pane2.add(new JLabel("  User Name  :"));
		pane2.add(new JLabel("  Password    :"));
		pane2.add(new JLabel("  First Name   :"));
		pane2.add(new JLabel("  Last Name   :"));
		pane2.add(new JLabel("  Department :"));
		JPanel pane3 = new JPanel();
		pane3.setLayout(new GridLayout(5,1));
		tusername=new JTextField(10);
		tpassword=new JPasswordField(10);
		tfname=new JTextField(10);
		tlname=new JTextField(10);
		tdep=new JTextField(10);
		
		pane3.add(tusername);
		pane3.add(tpassword);
		pane3.add(tfname);
		pane3.add(tlname);
		pane3.add(tdep);

		JPanel pane4 = new JPanel();
		pane4.add(new JLabel());
		JPanel pane5 = new JPanel();
		bupdate = new JButton("Add New");
		bupdate.setToolTipText("add new user ");
		bupdate.addActionListener(this);
		bcancel = new JButton("Cancel");
		bcancel.setToolTipText("cancel user");
		bcancel.addActionListener(this);
		pane5.add(bupdate);
		pane5.add(bcancel);
		pane.add("North",pane1);
		pane.add("West",pane2);
		pane.add("Center",pane3);
		pane.add("East",pane4);
		pane.add("South",pane5);
		frame.setSize(300,200);
		frame.setResizable(false);
		frame.setVisible(true);
	}

	public DelUpUser(String[] listuser) {
		String[] tuser;

		
		frame.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
			//MainUserFrame.jbt1.setEnabled(true);
			//MainUserFrame.jbt2.setEnabled(true);
			//MainUserFrame.jbt3.setEnabled(true);
			new MainUserFrame();
			}
			});  
		
		Container pane = frame.getContentPane();
		JPanel pane1 = new JPanel();
		pane.setLayout(new BorderLayout());
		pane1.add(new JLabel("Update & Delete User for Admin"));
		JPanel pane2 = new JPanel();
		pane2.setLayout(new GridLayout(5,1));
		pane2.add(new JLabel("  User Name  :"));
		pane2.add(new JLabel("  Password    :"));
		pane2.add(new JLabel("  First Name   :"));
		pane2.add(new JLabel("  Last Name   :"));
		pane2.add(new JLabel("  Department :"));
		JPanel pane3 = new JPanel();
		pane3.setLayout(new GridLayout(5,1));
		tusername=new JTextField(10);
		tpassword=new JPasswordField(10);
		tfname=new JTextField(10);
		tlname=new JTextField(10);
		tdep=new JTextField(10);
		pane3.add(tusername);
		pane3.add(tpassword);
		pane3.add(tfname);
		pane3.add(tlname);
		pane3.add(tdep);

		for(int i=0;i<listuser.length;i++) {
			System.out.println(listuser[i]);
		}
		
		tusername.setText(listuser[0]);
		tpassword.setText(listuser[1]);
		tfname.setText(listuser[2]);
		tlname.setText(listuser[3]);
		tdep.setText(listuser[4]);
		oldpassword = listuser[1];

		JPanel pane4 = new JPanel();
		pane4.add(new JLabel());
		JPanel pane5 = new JPanel();
		bupdate = new JButton("Update");
		bupdate.setToolTipText("update user");
		bupdate.addActionListener(this);
		bdelete = new JButton("Delete");
		bdelete.setToolTipText("delete user");
		bdelete.addActionListener(this);
		bcancel = new JButton("Cancel");
		bcancel.setToolTipText("cancel user");
		bcancel.addActionListener(this);
		pane5.add(bupdate);
		pane5.add(bdelete);
		pane5.add(bcancel);
		pane.add("North",pane1);
		pane.add("West",pane2);
		pane.add("Center",pane3);
		pane.add("East",pane4);
		pane.add("South",pane5);
		frame.setSize(300,200);
		frame.setResizable(false);
		frame.setVisible(true);
	}



	public void actionPerformed (ActionEvent e)  {
		//System.out.println(e.getActionCommand());
		String label = e.getActionCommand();
		
        	
		if (label.equals("Update")) {
		System.out.println("update");
		this.username = tusername.getText();
		this.password = EnCryptPassword.crypt(tpassword.getText(),2);
		this.fname = tfname.getText();
		this.lname = tlname.getText();
		this.dep = tdep.getText();
		
		if(this.username.equals("") || this.password.equals("") || this.fname.equals("") || this.lname.equals("") || this.dep.equals("")) {
		
		JOptionPane.showMessageDialog(null,"You should fill in blank !!",
				"Warning",JOptionPane.WARNING_MESSAGE);
		System.out.println("update :"+this.username+":"+this.password+":"+this.fname+":"+this.lname+":"+this.dep);
			
		} else { 

		if(Confirm("Are You sure to Update user")) {
		System.out.println("oidpassword :"+this.oldpassword+"  newpassword : "+tpassword.getText());
		
		if(this.oldpassword.equals(tpassword.getText())) {
		
		sqlmanage.SqlUpdate("update user set username= '"+this.username+"', fname='"+this.fname+"', lname='"+this.lname+"', dep='"+this.dep+"'  where username='"+this.username+"'" );
		System.out.println("update :"+this.username+":"+this.oldpassword+":"+this.fname+":"+this.lname+":"+this.dep);
		///MainUserFrame.jbt1.setEnabled(true);
		//MainUserFrame.jbt2.setEnabled(true);
		//MainUserFrame.jbt3.setEnabled(true);
		new MainUserFrame();
		frame.dispose();
       		}else {
		sqlmanage.SqlUpdate("update user set username= '"+this.username+"', password='"+this.password+"', fname='"+this.fname+"', lname='"+this.lname+"', dep='"+this.dep+"'  where username='"+this.username+"'" );
		System.out.println("update :"+this.username+":"+this.password+":"+this.fname+":"+this.lname+":"+this.dep);
		///MainUserFrame.jbt1.setEnabled(true);
		//MainUserFrame.jbt2.setEnabled(true);
		//MainUserFrame.jbt3.setEnabled(true);
		new MainUserFrame();
		frame.dispose(); 
		}//end if check password
		}
		} //end if check blank
	
		} else if (label.equals("Delete")) {
		
		if(Confirm("Are You sure to delete user")) {
		this.username = tusername.getText();
		System.out.println("delete : "+this.username);
		sqlmanage.SqlDelete("delete from user where username = '"+this.username +"' ");
		//MainUserFrame.jbt1.setEnabled(true);
		//MainUserFrame.jbt2.setEnabled(true);
		//MainUserFrame.jbt3.setEnabled(true);
		new MainUserFrame();
		frame.dispose();
		}

	
		} else if (label.equals("Add New")) {
		System.out.println("Add New User");
		this.username= tusername.getText();
		this.password = EnCryptPassword.crypt(tpassword.getText(),2);
		this.fname = tfname.getText();
		this.lname= tlname.getText();
		this.dep = tdep.getText();

		if(this.username.equals("") || this.password.equals("") || this.fname.equals("") || this.lname.equals("") || this.dep.equals("")) {
		JOptionPane.showMessageDialog(null,"You should fill in blank !!",
				"Warning",JOptionPane.WARNING_MESSAGE);
			System.out.println("add new  :"+this.username+":"+this.password+":"+this.fname+":"+this.lname+":"+this.dep);
		} else {
		
		if(Confirm("Are You sure to Add New user")) {
	   	sqlmanage.SqlInsert("insert into user values('"+this.username+"','"+this.password+"','"+this.fname+"','"+this.lname+"','"+this.dep+"')");
		System.out.println("add new :"+this.username+":"+this.password+":"+this.fname+":"+this.lname+":"+this.dep);
		//MainUserFrame.jbt1.setEnabled(true);
		//MainUserFrame.jbt2.setEnabled(true);
		//MainUserFrame.jbt3.setEnabled(true);
		new MainUserFrame();
		frame.dispose();
		}
		}//end if blank

		} else if (label.equals("Cancel")) {
		System.out.println("cancel");
		//MainUserFrame.jbt1.setEnabled(true);
		//MainUserFrame.jbt2.setEnabled(true);
		//MainUserFrame.jbt3.setEnabled(true);
		new MainUserFrame();
		frame.dispose();

		}
		
		
		
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


	public static void main(String args[])  {
		new DelUpUser();
	//String[] list = {"kailo","2222","montree","bungnasang","mis"};
		//new DelUpUser(list);
	
	}
}