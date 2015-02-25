import java.util.*;
import java.awt.*;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.table.*;


public class LoginDialog
{
    String username, password;

    LoginDialog() {
        getUserandPassword();
    }

    // modal dialog to get user ID and password
    static String[] ConnectOptionNames = { "Login", "Cancel" };
    static String   ConnectTitle = "Checkuser & Password for Admin";

    void getUserandPassword() {
    JPanel      connectionPanel;

 // Create the labels and text fields.
	JLabel     userNameLabel = new JLabel("UserName :   ", JLabel.CENTER);
 	JTextField userNameField = new JTextField(10);
	JLabel     passwordLabel = new JLabel("Password:   ", JLabel.CENTER);
	JTextField passwordField = new JPasswordField(10);
	connectionPanel = new JPanel(false);
	connectionPanel.setLayout(new BoxLayout(connectionPanel,
						BoxLayout.X_AXIS));
	JPanel namePanel = new JPanel(false);
	namePanel.setLayout(new GridLayout(0, 1));
	namePanel.add(userNameLabel);
	namePanel.add(passwordLabel);
	JPanel fieldPanel = new JPanel(false);
	fieldPanel.setLayout(new GridLayout(0, 1));
	fieldPanel.add(userNameField);
	fieldPanel.add(passwordField);
	connectionPanel.add(namePanel);
	connectionPanel.add(fieldPanel);

        // Connect or quit
	if(JOptionPane.showOptionDialog(null, connectionPanel, 
                                        ConnectTitle,
                                        JOptionPane.OK_CANCEL_OPTION, 
                                        JOptionPane.INFORMATION_MESSAGE,
                                        null, ConnectOptionNames, 
                                        ConnectOptionNames[0]) != 0) 
        {
	System.out.println("----cancel---");
	   // System.exit(0);
	}
	else {  
	//	new MainUserFrame() ;
        username = userNameField.getText();
        password = EnCryptPassword.crypt(passwordField.getText(),2);
	new CheckUserPassword(username,password);
	System.out.println(username+":"+password);  }

    }

public static void main(String args[])  {
	
new LoginDialog();

}

}
