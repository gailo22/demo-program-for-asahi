import java.sql.*;

public class  SqlManage { 

		public String listuser = new String();

		private Connection conn = null;
		private PreparedStatement pstmt;
		
	public SqlManage() {
		
		try
		{
			Class.forName( "sun.jdbc.odbc.JdbcOdbcDriver" );
			conn=DriverManager.getConnection("jdbc:odbc:db1","","");	
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		catch (ClassNotFoundException  e)
		{
			e.printStackTrace();
		}
		//SqlClose();
	}

	public void SqlSelect(String sqlcommand) {
		try
		{
			pstmt = conn.prepareStatement(sqlcommand);
			ResultSet res ;
			res=pstmt.executeQuery();
			while (res.next())
			{   System.out.print(res.getString("username"));
				System.out.println(res.getString("password"));
			}
			SqlClose();
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		//SqlClose();
	}

	public void SqlUpdate(String sqlcommand) {
		try
		{
			pstmt = conn.prepareStatement(sqlcommand);
			pstmt.executeUpdate();
			SqlClose();
		
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
			//SqlClose();

	}

	public void SqlDelete(String sqlcommand) {
		try
		{
			pstmt = conn.prepareStatement(sqlcommand);
			pstmt.executeUpdate();
			SqlClose();
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		//SqlClose();
	}

	public void SqlInsert(String sqlcommand) {
		try
		{
			pstmt = conn.prepareStatement(sqlcommand);
			pstmt.executeUpdate();
			SqlClose();
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
	//SqlClose();
	}

	public void SqlClose() {
		try
		{
			pstmt.close();
			conn.close();
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		
	}

public static void main(String[] args) {
	SqlManage sqlmanage= new SqlManage();
	//sqlmanage.SqlClose();
	//sqlmanage.SqlUpdate("update user set password = '1111' where username= 'kailo' ");
	//sqlmanage.SqlDelete("delete from user where username='kailo' ");
	//sqlmanage.SqlInsert("insert into user values('kailo','2222','montree','bungnasang','mis')");
	//sqlmanage.SqlSelect("select * from user");
	
}

}


