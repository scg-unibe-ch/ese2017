package ch.unibe.ese.team1.test.testData;

import java.util.LinkedList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ch.unibe.ese.team1.controller.service.AdService;
import ch.unibe.ese.team1.model.Ad;
import ch.unibe.ese.team1.model.User;
import ch.unibe.ese.team1.model.dao.UserDao;

/**
 * This inserts some bookmark test data into the database.
 */
@Service
public class BookmarkTestDataSaver{

	@Autowired
	private UserDao userDao;
	@Autowired
	private AdService adService;

	@Transactional
	public void saveTestData() throws Exception {
		User ese = userDao.findByUsername("ese@unibe.ch");
		User jane = userDao.findByUsername("jane@doe.com");
		User bernerBaer = userDao.findByUsername("user@bern.com");
		User oprah = userDao.findByUsername("oprah@winfrey.com");

		// 5 bookmarks for Ese test-user
		LinkedList<Ad> bookmarkedAds = new LinkedList<>();
		bookmarkedAds.add(adService.getAdById(1));
		bookmarkedAds.add(adService.getAdById(3));
		bookmarkedAds.add(adService.getAdById(5));
		bookmarkedAds.add(adService.getAdById(7));
		bookmarkedAds.add(adService.getAdById(8));
		ese.setBookmarkedAds(bookmarkedAds);
		
		userDao.save(ese);

		// 4 bookmarks for Jane Doe
		bookmarkedAds = new LinkedList<>();
		bookmarkedAds.add(adService.getAdById(6));
		bookmarkedAds.add(adService.getAdById(9));
		bookmarkedAds.add(adService.getAdById(10));
		bookmarkedAds.add(adService.getAdById(11));
		jane.setBookmarkedAds(bookmarkedAds);
		userDao.save(jane);

		// 5 bookmarks for user berner bear
		bookmarkedAds = new LinkedList<>();
		bookmarkedAds.add(adService.getAdById(2));
		bookmarkedAds.add(adService.getAdById(4));
		bookmarkedAds.add(adService.getAdById(6));
		bookmarkedAds.add(adService.getAdById(8));
		bookmarkedAds.add(adService.getAdById(12));
		bernerBaer.setBookmarkedAds(bookmarkedAds);
		userDao.save(bernerBaer);

		// 4 bookmarks for Oprah
		bookmarkedAds = new LinkedList<>();
		bookmarkedAds.add(adService.getAdById(2));
		bookmarkedAds.add(adService.getAdById(3));
		bookmarkedAds.add(adService.getAdById(6));
		bookmarkedAds.add(adService.getAdById(12));
		oprah.setBookmarkedAds(bookmarkedAds);
		userDao.save(oprah);
	}

}
