
import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.Row;
import com.datastax.driver.core.Session;

public class test {

   public static void main(String args[]){

      
      Cluster cluster = Cluster.builder().addContactPoint("localhost").build();
    
      System.out.println("Cluster Created.....");
      //Creating Session object
      Session session = cluster.connect("tp1");
      System.out.println("Session Created.....");
      String q1 = "insert into emp(emp_id, emp_name) values (1004, 'REG');";
      String q2 = "update emp set emp_name = 'DIRECTOR' where emp_id=1001;";
      String q3 = "delete from emp where emp_id=1004";
      String q4 = "select * from emp;";
      session.execute(q1);
      System.out.println("Insert Successful.....");
      session.execute(q2);
      System.out.println("update successful");
      session.execute(q3);
      System.out.println("Delete Successful......");
      ResultSet result = session.execute(q4);
      //System.out.println(result.all());
      
      for (Row row : result) {
    	  System.out.format("%d %s\n", row.getInt("emp_id"), row.getString("emp_name") );
    	  }
      session.close();
      cluster.close();
   }
}