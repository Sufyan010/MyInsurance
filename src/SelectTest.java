import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

/*
 * PDBORCL =
  (DESCRIPTION =
    (ADDRESS = (PROTOCOL = TCP)(HOST = localhost)(PORT = 1521))
    (CONNECT_DATA =
      (SERVER = DEDICATED)
      (SERVICE_NAME = pdborcl)
    )
  )
  
 */




public class SelectTest {

	public static void main(String[] args) {
		
		try {
			DriverManager.registerDriver(new oracle.jdbc.OracleDriver());
			System.out.println("Driver registered.....");
			Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:ORCL", "hr", "tiger");
			System.out.println("Connected to the database "+conn);
			
		//	Statement statement= conn.createStatement();
		//	System.out.println("Statement created : "+statement);
		//	System.out.println();
		  
			PreparedStatement preparedStatement= conn.prepareStatement("select * from Employees where hire_date between ? and ?");
			System.out.println("Prepared Statement created : "+preparedStatement);
			System.out.println();
			//Scanner scan = new Scanner(System.in);
		    //System.out.println("Enter Job_ID : ");
		   // String v_job = scan.nextLine();
			Scanner scan2 = new Scanner(System.in);
		    System.out.println("Enter starting date : ");
		    String fromDate = scan2.nextLine();
		    preparedStatement.setString(1, fromDate);
		    Scanner scan3 = new Scanner(System.in);
		    System.out.println("Enter ending date   : ");
		    String toDate = scan3.nextLine();
			preparedStatement.setString(2, toDate);
			ResultSet rs= preparedStatement.executeQuery();
			//ResultSet rs = statement.executeQuery("select * from Employees where Job_id='FI_MGR'");
		  //ResultSet rs = statement.executeQuery("select * from emp where job="+"'"+v_job+"'");
			
		//	Scanner scan2 = new Scanner(System.in);
       //     System.out.println("Enter starting date : ");
       //     String fromDate = scan2.nextLine();

       //     Scanner scan3 = new Scanner(System.in);
        //    System.out.println("Enter ending date   : ");
        //    String toDate = scan3.nextLine();

            //ResultSet dateRs = statement.executeQuery("select * from emp where hire_date between '01-Jan-81' and '31-Dec-81'");
           // ResultSet rs = statement.executeQuery("select * from employees where hire_date between '"+fromDate+"' and '"+toDate+"'");
			
			while(rs.next())
			{
				System.out.println("Emp number: "+rs.getInt(1));
				System.out.println("Emp First Name: "+rs.getString(2));
				System.out.println("Emp Last Name: "+rs.getString(3));
				System.out.println("Emp Email: "+rs.getString(4));
				System.out.println("Emp Phone Number: "+rs.getString(5));
				System.out.println("Emp Hire Date: "+rs.getString(6));
				System.out.println("Emp Job_ID: "+rs.getString(7));
				System.out.println("Emp Salary: "+rs.getInt(8));
				System.out.println("Emp Commision Percent: "+rs.getString(9));
				System.out.println("Emp Manager ID: "+rs.getString(10));
				System.out.println("Emp Departement ID: "+rs.getString(11));
				System.out.println("---------------------------------------------");
			}
			
			scan2.close();
			scan3.close();
			rs.close();
			System.out.println("DB resources are closed");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	
	}
}
