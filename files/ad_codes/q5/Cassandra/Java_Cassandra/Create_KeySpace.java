
import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.Session;

public class Create_KeySpace {

   public static void main(String args[]){

      
      Cluster cluster = Cluster.builder().addContactPoint("127.0.0.1").build();
    
      System.out.println("Cluster Created.....");
      //Creating Session object
      Session session = cluster.connect("tp3");
      System.out.println("Session Created.....");
      String q = "insert into emp(emp_id, emp_name) values (1001, 'DIR');";
      session.execute(q);
      System.out.println("Insert Successful.....");
   }
}