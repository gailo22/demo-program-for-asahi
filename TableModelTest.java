import javax.swing.*;
import javax.swing.table.*;
import javax.swing.event.*;
import java.sql.*;
import java.awt.*;
import java.util.*;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.ListSelectionEvent;
import java.lang.String;

class MyListener implements ListSelectionListener {
        
	JTable t;
	MyListener(JTable t) { this.t = t; }
	public void valueChanged(ListSelectionEvent e) {
		if(e.getValueIsAdjusting()) {
		int r = t.getSelectedRow();
		int c = t.getSelectedColumn();
		System.out.println("value of row "+r+" is -->");
		for(int i=0;i<t.getColumnCount();i++) {
		System.out.print(t.getValueAt(r,i)+":");
		TableModelTest.listuser[i]=(String)t.getValueAt(r, i);
		}
		System.out.println("");
		System.out.println("------------");
		}
	}
}

/*class MyAbstractTableModel extends AbstractTableModel {
	Vector headers;
	Vector rows;
	MyAbstractTableModel(Vector headers,Vector rows) {
	
	this.headers = (String[])headers;
	this.rows = (Object[][])rows;
	//String headers[] =  {"id","name","department"};
	//String rows[][] = {{"123","kailo","mis dc"},
	//		{"456","oily","customer"},
	//		{"789","cherry","data"}};
	}

	public int getColumnCount() { return; }
	public int getRowCount() { return; }
	public Object getValueAt(int r , int c) { return; }
	public String getColumnName(int i) { return; }
} */

public class TableModelTest extends JPanel  {
   private Connection connection;
   public static String[] listuser= new String[5];
	TableModelTest() {
	//super("TableModelTest");
//s---------------------------------	

      String url = "jdbc:odbc:db1";  
      String username = "";
      String password = "";

      // Load the driver to allow connection to the database
      try {
         Class.forName( "sun.jdbc.odbc.JdbcOdbcDriver" );

         connection = DriverManager.getConnection( 
            url, username, password );
      } 
      catch ( ClassNotFoundException cnfex ) {
         System.err.println( 
            "Failed to load JDBC/ODBC driver." );
         cnfex.printStackTrace();
         System.exit( 1 );  // terminate program
      }
      catch ( SQLException sqlex ) {
         System.err.println( "Unable to connect" );
         sqlex.printStackTrace();
      }

      getTable();
}


private void getTable()
   {
      Statement statement;
      ResultSet resultSet;
      
      try {
         String query = "SELECT * FROM user";

         statement = connection.createStatement();
         resultSet = statement.executeQuery( query );
         displayResultSet( resultSet );
         statement.close();
      }
      catch ( SQLException sqlex ) {
         sqlex.printStackTrace();
      }
   }

   private void displayResultSet( ResultSet rs )
      throws SQLException
   {
      // position to first record
      boolean moreRecords = rs.next();  

      // If there are no records, display a message
      if ( ! moreRecords ) {
         JOptionPane.showMessageDialog( this, 
            "ResultSet contained no records" );
         //setTitle( "No records to display" );
         return;
      }

      //setTitle( "Authors table from Books" );

      Vector columnHeads = new Vector();
      Vector rows = new Vector();

      try {
         // get column heads
         ResultSetMetaData rsmd = rs.getMetaData();
      
         for ( int i = 1; i <= rsmd.getColumnCount(); ++i ) 
            columnHeads.addElement( rsmd.getColumnName( i ) );

         // get row data
         do {
            rows.addElement( getNextRow( rs, rsmd ) ); 
         } while ( rs.next() );

         // display table with ResultSet contents
      JTable  table = new JTable( rows, columnHeads );

//e------------------------------------------------------------
	
	
	//JTable table = new JTable(new MyAbstractTableModel(rows,columnHeads));
	//table.setCellSelectionEnabled(true);
	table.setPreferredScrollableViewportSize(new Dimension(450, 200));
	ListSelectionModel sm = table.getSelectionModel();
	sm.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
	sm.addListSelectionListener(new MyListener(table));
	//JScrollPane jsp = new JScrollPane(table);
	//getContentPane().add(jsp);
	

//s------------------------------

	}
      catch ( SQLException sqlex ) {
         sqlex.printStackTrace();
      }
   }

private Vector getNextRow( ResultSet rs, 
                              ResultSetMetaData rsmd )
       throws SQLException
   {
      Vector currentRow = new Vector();
      
      for ( int i = 1; i <= rsmd.getColumnCount(); ++i )
         switch( rsmd.getColumnType( i ) ) {
            case Types.VARCHAR:
                  currentRow.addElement( rs.getString( i ) );
               break;
            case Types.INTEGER:
                  currentRow.addElement( 
                     new Long( rs.getLong( i ) ) );
               break;
            default: 
               System.out.println( "Type was: " + 
                  rsmd.getColumnTypeName( i ) );
         }
      
      return currentRow;
   }

 public void shutDown()
   {
      try {
         connection.close();
      }
      catch ( SQLException sqlex ) {
         System.err.println( "Unable to disconnect" );
         sqlex.printStackTrace();
      }
   }

//e------------------------------------------
	
	public static void main(String args[]) {
	TableModelTest frame=new TableModelTest();
		frame.setSize(400,250);
		frame.show();
	}
}
	