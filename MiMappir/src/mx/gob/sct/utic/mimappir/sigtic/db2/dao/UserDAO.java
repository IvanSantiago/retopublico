package mx.gob.sct.utic.mimappir.sigtic.db2.dao;

import java.util.ArrayList;
import java.util.List;

import mx.gob.sct.utic.mimappir.sigtic.db2.model.USUARIOS;
/**
 * A custom DAO for accessing data from the database.
 * 
 */
public class UserDAO {

	/**
	 * Simulates retrieval of data from a database.
	 */
	public USUARIOS searchDatabase(String username) {
		// Retrieve all users from the database
		List<USUARIOS> users = internalDatabase();

		// Search user based on the parameters
		for (USUARIOS USUARIOS : users) {
			if (USUARIOS.getLOGIN().equals(username) == true) {
				
				// return matching user
				return USUARIOS;
			}
		}

		
		throw new RuntimeException("User does not exist!");
	}

	/**
	 * Our fake database. Here we populate an ArrayList with a dummy list of
	 * users.
	 */
	private List<USUARIOS> internalDatabase() {
		// Dummy database

		// Create a dummy array list
		List<USUARIOS> users = new ArrayList<USUARIOS>();
		USUARIOS user = null;

		// Create a new dummy user
		user = new USUARIOS();
		user.setLOGIN("john");
		// Actual password: admin
		user.setPASS("21232f297a57a5a743894a0e4a801fc3");
		//user.setPassword("admin");
		// Admin user
		user.setNIVEL(1);

		// Add to array list
		users.add(user);

		// Create a new dummy user
		user = new USUARIOS();
		user.setLOGIN("jane");
		// Actual password: user
		user.setPASS("ee11cbb19052e40b07aac0ca060c23ee");
		//user.setPassword("user");
		// Regular user
		user.setNIVEL(2);

		// Add to array list
		users.add(user);

		return users;
	}

}