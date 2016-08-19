package ch.unibe.ese.team1.test.testData;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ch.unibe.ese.team1.model.Rating;
import ch.unibe.ese.team1.model.User;
import ch.unibe.ese.team1.model.dao.UserDao;
import ch.unibe.ese.team1.model.dao.RatingDao;

/**
 * This inserts some alert test data into the database.
 */
@Service
public class RatingTestDataSaver {

	@Autowired
	RatingDao ratingDao;
	
	@Autowired
	UserDao userDao;
	
	@Transactional
	public void saveTestData() throws Exception {		
		
		User ese = userDao.findByUsername("ese@unibe.ch");
		User jane = userDao.findByUsername("jane@doe.com");
		User oprah = userDao.findByUsername("oprah@winfrey.com");
		User berner = userDao.findByUsername("user@bern.com");
		
		//Ese's ratings
		Rating rating = new Rating();
		rating.setRater(ese);
		rating.setRatee(berner);
		rating.setRating(3);
		ratingDao.save(rating);
		
		rating = new Rating();
		rating.setRater(ese);
		rating.setRatee(oprah);
		rating.setRating(4);
		ratingDao.save(rating);
		
		rating = new Rating();
		rating.setRater(ese);
		rating.setRatee(jane);
		rating.setRating(5);
		ratingDao.save(rating);
		
		//Berner Bär doesn't rate anyone
		rating = new Rating();
		rating.setRater(berner);
		rating.setRatee(ese);
		rating.setRating(0);
		ratingDao.save(rating);
		
		rating = new Rating();
		rating.setRater(berner);
		rating.setRatee(oprah);
		rating.setRating(0);
		ratingDao.save(rating);
		
		rating = new Rating();
		rating.setRater(berner);
		rating.setRatee(jane);
		rating.setRating(0);
		ratingDao.save(rating);
		
		//Oprah loves everyone
		rating = new Rating();
		rating.setRater(oprah);
		rating.setRatee(jane);
		rating.setRating(5);
		ratingDao.save(rating);
		
		rating = new Rating();
		rating.setRater(oprah);
		rating.setRatee(berner);
		rating.setRating(5);
		ratingDao.save(rating);
		
		rating = new Rating();
		rating.setRater(oprah);
		rating.setRatee(ese);
		rating.setRating(5);
		ratingDao.save(rating);
		
		//Jane hasn't invited many people
		rating = new Rating();
		rating.setRater(jane);
		rating.setRatee(berner);
		rating.setRating(2);
		ratingDao.save(rating);
	}
}
