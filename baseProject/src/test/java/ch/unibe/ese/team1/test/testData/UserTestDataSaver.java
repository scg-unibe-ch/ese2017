package ch.unibe.ese.team1.test.testData;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ch.unibe.ese.team1.model.Gender;
import ch.unibe.ese.team1.model.User;
import ch.unibe.ese.team1.model.UserPicture;
import ch.unibe.ese.team1.model.UserRole;
import ch.unibe.ese.team1.model.dao.UserDao;

/**
 * This inserts some user test data into the database.
 */
@Service
public class UserTestDataSaver {

	@Autowired
	private UserDao userDao;

	@Transactional
	public void saveTestData() throws Exception {
		// system account
		User system = createUser("System", "1234", "FlatFindr", "Admin",
				"/img/test/system.jpg", Gender.ADMIN);
		system.setAboutMe("We keep you off the streets.");
		userDao.save(system);

		// Main test-user for the assistants (advertiser)
		User ese = createUser("ese@unibe.ch", "ese", "John", "Wayne",
				"/img/test/portrait.jpg", Gender.MALE);
		ese.setAboutMe(getDummyText());
		userDao.save(ese);
		
		// Searcher
		User janeDoe = createUser("jane@doe.com", "password", "Jane", "Doe",
				Gender.FEMALE);
		janeDoe.setAboutMe(getDummyText());
		userDao.save(janeDoe);

		// Another advertiser & searcher
		User bernerBaer = createUser("user@bern.com", "password",
				"Berner", "Bär", Gender.MALE);
		UserPicture picture = new UserPicture();
		picture.setFilePath("/img/test/berner_baer.png");
		picture.setUser(bernerBaer);
		bernerBaer.setPicture(picture);
		bernerBaer.setAboutMe("I am a PhD student and I am Italian. I am 26,"
				+ "I like winter-sports, hiking, traveling and cooking."
				+ "I enjoy spending time with friends, watching movies, "
				+ "going for drinks and organizing dinners. I have lived in Milan,"
				+ "London and Zurich, always in flatshares and i have never had"
				+ "problems with my flatmates.");
		userDao.save(bernerBaer);
		
		// Another advertiser & searcher
		User oprah = createUser("oprah@winfrey.com", "password", "Oprah", "Winfrey",
				"/img/test/oprah.jpg", Gender.FEMALE);
		oprah.setAboutMe(getDummyText());
		userDao.save(oprah);
		
		// Dummy users to be added for Roommates
		User hans = createUser("hans@unibe.ch", "password", "Hans", "DummyOne",
				Gender.MALE);
		hans.setAboutMe("Hello, I am the dummy user Hans for the AdBern. I am living" +
				"at Kramgasse 22 and I am very very happy there.");
		userDao.save(hans);
		
		User mathilda = createUser("mathilda@unibe.ch", "password", "Mathilda",
				"DummyTwo", Gender.FEMALE);
		mathilda.setAboutMe("Hello, I am the dummy user Mathilda for the AdBern. I am living" +
				"at Kramgasse 22 and I am very very happy there.");
		userDao.save(mathilda);
	}

	public User createUser(String email, String password, String firstName,
			String lastName, Gender gender) {
		User user = new User();
		user.setUsername(email);
		user.setPassword(password);
		user.setEmail(email);
		user.setFirstName(firstName);
		user.setLastName(lastName);
		user.setEnabled(true);
		user.setGender(gender);
		Set<UserRole> userRoles = new HashSet<>();
		UserRole role = new UserRole();
		role.setRole("ROLE_USER");
		role.setUser(user);
		userRoles.add(role);
		user.setUserRoles(userRoles);
		return user;
	}

	public User createUser(String email, String password, String firstName,
			String lastName, String picPath, Gender gender) {
		User user = new User();
		user.setUsername(email);
		user.setPassword(password);
		user.setEmail(email);
		user.setFirstName(firstName);
		user.setLastName(lastName);
		user.setEnabled(true);
		user.setGender(gender);
		Set<UserRole> userRoles = new HashSet<>();
		UserRole role = new UserRole();
		UserPicture picture = new UserPicture();
		picture.setUser(user);
		picture.setFilePath(picPath);
		user.setPicture(picture);
		role.setRole("ROLE_USER");
		role.setUser(user);
		userRoles.add(role);
		user.setUserRoles(userRoles);
		return user;
	}

	private String getDummyText() {
		return "I am a Master student from switzerland. I'm 25 years old, "
				+ "my hobbies are summer-sports, hiking, traveling and cooking. "
				+ "I enjoy spending time with friends, watching movies, "
				+ "going for drinks and organizing dinners. I have lived in Fräkmündegg, "
				+ "London and Zurich, always in flatshares and i have never had "
				+ "problems with my flatmates because I am a nice person.";
	}

}
