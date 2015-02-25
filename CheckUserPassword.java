import java.sql.*;
import javax.swing.*;


public class CheckUserPassword {
	private String username;
	private String password;
	private boolean checkuser=false;
	Connection conn = null;

	public CheckUserPassword(String username,String password) {
		this.username = username;
	 	this.password = password;
		System.out.println(this.username+" : "+this.password);
	try {
		Class.forName( "sun.jdbc.odbc.JdbcOdbcDriver" );
		conn = DriverManager.getConnection("jdbc:odbc:db1","","");
		PreparedStatement pstmt;
		pstmt = conn.prepareStatement("select * from admin where username=? and password=?");
		ResultSet res;
		pstmt.setString(1,this.username);
		pstmt.setString(2,this.password);
		res = pstmt.executeQuery();
		
		
		boolean morereccords = res.next();
		if(!morereccords) {
			JOptionPane.showMessageDialog(null,"Username or Password incorrect !!",
				"Warning",JOptionPane.WARNING_MESSAGE);
			System.out.println("login fails !!");
		}else {
			System.out.println(this.username);
			System.out.println(this.password);
			new MainUserFrame();
			MainMenuFrame.file.setEnabled(false);
		}
		
		res.close();
		pstmt.close();
		conn.close();
		
	}
	catch(SQLException e) {
		e.printStackTrace();
	}
	catch(ClassNotFoundException e) {
		e.printStackTrace();
	}
	}



	//public CheckUserPassword(String username,String password)  {
	//	this.username = username;
	// 	this.password = password;
		//CheckUserPassword();	
	
	//}
	
	public boolean isCheck() {
		System.out.println(checkuser);
		return checkuser;
	}


public static void main(String[] args) {

	new CheckUserPassword("kailo","2222");

	}

}


