
import java.sql.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.ListSelectionEvent;
import java.lang.String;

public class MainUserPanel extends JPanel {
   private Connection connection;
   private JTable table;

   private boolean ALLOW_ROW_SELECTION = true;
   
   public static String[] listuser= new String[5];
    
   public MainUserPanel() 
   {   
      // The URL specifying the Books database to which 
      // this program connects using JDBC to connect to a
      // Microsoft ODBC database.
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
         table = new JTable( rows, columnHeads );
 
	table.setPreferredScrollableViewportSize(new Dimension(450, 200));
	table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
	 if (ALLOW_ROW_SELECTION) { // true by default
            ListSelectionModel rowSM = table.getSelectionModel();
            rowSM.addListSelectionListener(new ListSelectionListener() {
                public void valueChanged(ListSelectionEvent e) {
                    //Ignore extra messages.
                    if (e.getValueIsAdjusting()) return;
                    
                    ListSelectionModel lsm = (ListSelectionModel)e.getSource();
                    if (lsm.isSelectionEmpty()) {
                        System.out.println("No rows are selected.");
                    } else {
                        int selectedRow = lsm.getMinSelectionIndex();
                        System.out.println("Row " + selectedRow
                                           + " is now selected.");
                    }
                }
            });
	
        } else {
            table.setRowSelectionAllowed(false);
        }

            table.addMouseListener(new MouseAdapter() {
                public void mouseClicked(MouseEvent e) {
                     MainUserFrame.jbt2.setEnabled(true);			
                     printDebugData(table);
                }
            });
  
       JScrollPane scroller = new JScrollPane( table );
         //getContentPane().
	add(scroller, BorderLayout.CENTER );
         validate();
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


private void printDebugData(JTable table) {
        int numRows = table.getRowCount();
        int numCols = table.getColumnCount();
        javax.swing.table.TableModel model = table.getModel();
	int rowselected = table.getSelectedRow();

	

        System.out.println("Value of data: ");
            System.out.print("    row " + rowselected + ":");
            for (int j=0; j < numCols; j++) {
                System.out.print("  " + model.getValueAt(rowselected, j));
		listuser[j]=(String)model.getValueAt(rowselected, j);
            }
            System.out.println();
        System.out.println("--------------------------");
    }

public boolean isCellEditable(int row, int col) {    
   
  	return false;   
	}

public static void main( String args[] ) 
   {
      JFrame  frame = new JFrame("User Main Frame");
      final MainUserPanel app = new MainUserPanel();
      Container pane = frame.getContentPane();
      JPanel pane1=new JPanel();
      pane1.add(new JButton("Add New User"));
      pane1.add(new JButton(" Update "));
      pane1.add(new JButton("Cancel"));
      JPanel pane2 = new JPanel();
      pane2.setLayout(new BorderLayout());
      pane2.add("North",pane1);
      pane2.add("Center",app);
      		
      
      pane.add(pane2);
      frame.pack();
      frame.setVisible(true);

      frame.addWindowListener( 
         new WindowAdapter() {
            public void windowClosing( WindowEvent e ) 
            {  
               app.shutDown();
               System.exit( 0 );
            }
         }
      );
   }
}
