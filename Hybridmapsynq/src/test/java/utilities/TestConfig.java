package utilities;
public class TestConfig{


	
	//public static String server="smtp.gmail.com";
	public static String server="smtp.rediffmail.com";
	//public static String from = "djbasu2010@gmail.com";
	public static String from = "postallmail@rediffmail.com";
	//public static String password = "pass@123";
	public static String password = "pass@123";
	public static String[] to ={"debojyoti.psi@gmail.com","post_my_mail@yahoo.com"};
	public static String subject = "Test Report";
	
	public static String messageBody ="TestMessage";
	public static String attachmentPath="C:\\workspace\\WebDriver\\error.jpg";
	public static String attachmentName="error.jpg";
	
	
	
	//SQL DATABASE DETAILS
	public static String driver="net.sourceforge.jtds.jdbc.Driver";
	//public static String driver="com.microsoft.sqlserver.jdbc.SQLServerDriver";
	public static String dbConnectionUrl="jdbc:jtds:sqlserver://192.101.44.22;DatabaseName=selenium_practice_sql";
	public static String dbUserName="sa";
	public static String dbPassword="pass!@#123";
	
	
	//MYSQL DATABASE DETAILS
	//public static String mysqldriver="com.mysql.jdbc.Driver";		// Old JDBC Driver for MySQL
	public static String mysqldriver="com.mysql.cj.jdbc.Driver";	// New JDBC Driver for MySQL
	public static String mysqluserName = "root";
	public static String mysqlpassword = "test";
	public static String mysqlurl = "jdbc:mysql://localhost:3306/selenium_practice";
	
	
	
	
	
	
	
	
	
}
