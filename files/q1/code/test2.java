
import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.Row;
import com.datastax.driver.core.Session;

public class test2 {

   public static void main(String args[]){

      
      Cluster cluster = Cluster.builder().addContactPoint("localhost").build();
    
      System.out.println("Cluster Created.....");
      //Creating Session object
      Session session = cluster.connect("tp1");
      System.out.println("Session Created.....");
   
      String q4 = "select * from emp;";
      ResultSet result = session.execute(q4);
       
     System.out.println("1. Round Robing (Assuning no of partitions 3...... "); 
      for (Row row : result) {
    	  int p;
    	  p=row.getInt("emp_id")%3;
    	  System.out.format("Partion number for record %d is %d \n", row.getInt("emp_id"), p);
    	  
    	  //System.out.format("%d %s\n", row.getInt("emp_id"), row.getString("emp_name") );
    	  }
      
      ResultSet result1 = session.execute(q4);
      System.out.println("2. Range partiotion (assuming empid less than 1100 on disk 0 and remaining on disk1 ...... "); 
      for (Row row1 : result1) {
    	  int p1;
    	  p1=row1.getInt("emp_id");
    	  if(p1<1100) 
    	  System.out.format("Partion number for record %d is 0 \n", row1.getInt("emp_id"));
    	  else    
    	  System.out.format("Partion number for record %d is 1 \n", row1.getInt("emp_id"));
    	   	
    	  //System.out.format("%d %s\n", row.getInt("emp_id"), row.getString("emp_name") );
    	  }
      
      ResultSet result2 = session.execute(q4);
      System.out.println("3. Hash partiotion (assuming h(k)= k mod n ...... "); 
          	  for (Row row2 : result2) {
        	  int p2;
        	  p2=row2.getInt("emp_id")%3;
        	  System.out.format("Partion number for record %d is %d \n", row2.getInt("emp_id"), p2);
    	  //System.out.format("%d %s\n", row.getInt("emp_id"), row.getString("emp_name") );
    	  }
      
      session.close();
      cluster.close();
   }
}