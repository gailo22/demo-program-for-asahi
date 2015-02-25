import java.io.*;

public class EnCryptPassword {
public static String password;

 static String crypt(String input,int offset) {
        StringBuffer sb = new StringBuffer();
        for (int n=0;n<input.length(); n++) {
            sb.append((char)(
               offset+input.charAt(n)));    
        }
        return sb.toString();
    }

public static void main(String[] args) {
	//password = Crypt.crypt("hahaha96", 2);
       // System.out.println("Password encyrpted as " + password);

}

}