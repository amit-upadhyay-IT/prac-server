
import java.util.*;
import javax.jdo.*;
import com.objectdb.Utilities;

public class JDOTest {
	public static void main(String[] args) {
		
		Properties properties = new Properties();
		properties.setProperty("javax.jdo.PersistenceManagerFactoryClass", "com.objectdb.jdo.PMF");
		properties.setProperty("javax.jdo.option.ConnectionURL", "Person.odb");
		PersistenceManagerFactory pmf =JDOHelper.getPersistenceManagerFactory(properties);
		PersistenceManager pm = pmf.getPersistenceManager();
		
		//Create persistent objects
		pm.currentTransaction().begin();
		Person person = new Person(args[0], args[1], Integer.parseInt(args[2]));
		pm.makePersistent(person);
		System.out.println("Successful....");
		
		//Print all persons in the database
		
		System.out.println("All objects....");
		Extent extent = pm.getExtent(Person.class, false);
		Iterator itr = extent.iterator();
		while (itr.hasNext())
		System.out.println(itr.next());
		extent.closeAll();
		
		//First Query display all persons whose age>=35 
		System.out.println("All objects whose age is greater than 35.....");
		Query query = pm.newQuery(Person.class, "this.age >=35");
		Collection result = (Collection)query.execute();
		Iterator itr1 = result.iterator();
		while (itr1.hasNext())
		System.out.println(itr1.next());
		query.close(result);
		
		// Query display all persons whose first  
		System.out.println("All objects whose first name is G....");
		Query query1 = pm.newQuery(Person.class, "this.firstName = 'G'");
		Collection result1 = (Collection)query1.execute();
		Iterator itr2 = result1.iterator();
		while (itr2.hasNext())
		System.out.println(itr2.next());
		query.close(result1);
		
		//Query for sorting
		System.out.println("Soring of all objects by age...");
		Query query2 = pm.newQuery(Person.class, "this.age >= 1");
		query2.setOrdering("this.age ascending");
		Collection result2 = (Collection)query2.execute();
		Iterator itr3 = result2.iterator();
		while (itr3.hasNext())
		System.out.println(itr3.next());
		query.close(result2);
		
		pm.currentTransaction().commit();
				
		
		pm.close();
	}
}