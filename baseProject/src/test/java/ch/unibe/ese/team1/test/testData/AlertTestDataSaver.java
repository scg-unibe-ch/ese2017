package ch.unibe.ese.team1.test.testData;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ch.unibe.ese.team1.model.Alert;
import ch.unibe.ese.team1.model.User;
import ch.unibe.ese.team1.model.dao.AlertDao;
import ch.unibe.ese.team1.model.dao.UserDao;

/**
 * This inserts some alert test data into the database.
 */
@Service
public class AlertTestDataSaver {

	@Autowired
	private AlertDao alertDao;
	
	@Autowired
	private UserDao userDao;


	@Transactional
	public void saveTestData() throws Exception {
		User ese = userDao.findByUsername("ese@unibe.ch");
		User jane = userDao.findByUsername("jane@doe.com");

		// 2 Alerts for the ese test-user
		Alert alert = new Alert();
		alert.setUser(ese);
		alert.setBothRoomAndStudio(true);
		alert.setRoom(true);
		alert.setStudio(true);
		alert.setCity("Bern");
		alert.setZipcode(3000);
		alert.setPrice(1500);
		alert.setRadius(30);
		alertDao.save(alert);
		
		alert = new Alert();
		alert.setUser(ese);
		alert.setBothRoomAndStudio(false);
		alert.setRoom(true);
		alert.setStudio(false);
		alert.setCity("Zürich");
		alert.setZipcode(8000);
		alert.setPrice(1000);
		alert.setRadius(25);
		alertDao.save(alert);
		
		
		// One alert for Jane Doe
		alert = new Alert();
		alert.setUser(jane);
		alert.setBothRoomAndStudio(false);
		alert.setRoom(false);
		alert.setStudio(true);
		alert.setCity("Luzern");
		alert.setZipcode(6003);
		alert.setPrice(900);
		alert.setRadius(22);
		alertDao.save(alert);
	}

}
