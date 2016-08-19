package ch.unibe.ese.team1.test.testData;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ch.unibe.ese.team1.model.Ad;
import ch.unibe.ese.team1.model.User;
import ch.unibe.ese.team1.model.Visit;
import ch.unibe.ese.team1.model.dao.AdDao;
import ch.unibe.ese.team1.model.dao.UserDao;
import ch.unibe.ese.team1.model.dao.VisitDao;

/**
 * This inserts some visits test data into the database.
 */
@Service
public class VisitTestDataSaver{

	private User testerMuster;
	private User bernerBaer;
	private User janeDoe;
	private User oprah;

	private Ad ad1;
	private Ad ad2;
	private Ad ad3;
	private Ad ad4;
	private Ad ad5;
	private Ad ad6;
	private Ad ad7;
	private Ad ad8;
	private Ad ad9;
	private Ad ad10;
	private Ad ad11;
	private Ad ad12;

	@Autowired
	private UserDao userDao;

	@Autowired
	private AdDao adDao;

	@Autowired
	private VisitDao visitDao;

	@Transactional
	public void saveTestData() throws Exception {
		// load users
		bernerBaer = userDao.findByUsername("user@bern.com");
		testerMuster = userDao.findByUsername("ese@unibe.ch");
		janeDoe = userDao.findByUsername("jane@doe.com");
		oprah = userDao.findByUsername("oprah@winfrey.com");

		// load ads
		ad1 = adDao.findOne(1L);
		ad2 = adDao.findOne(2L);
		ad3 = adDao.findOne(3L);
		ad4 = adDao.findOne(4L);
		ad5 = adDao.findOne(5L);
		ad6 = adDao.findOne(6L);
		ad7 = adDao.findOne(7L);
		ad8 = adDao.findOne(8L);
		ad9 = adDao.findOne(9L);
		ad10 = adDao.findOne(10L);
		ad11 = adDao.findOne(11L);
		ad12 = adDao.findOne(12L);

		Visit visit;
		List<User> searchers;
		DateFormat dateFormat = new SimpleDateFormat("HH:mm dd.MM.yyyy");

		// Visits for Ad 1
		visit = new Visit();
		visit.setAd(ad1);
		searchers = new LinkedList<>();
		searchers.add(testerMuster);
		visit.setSearchers(searchers);
		visit.setStartTimestamp(dateFormat.parse("14:00 26.12.2014"));
		visit.setEndTimestamp(dateFormat.parse("16:00 26.12.2014"));
		visitDao.save(visit);

		visit = new Visit();
		visit.setAd(ad1);
		searchers = new LinkedList<>();
		searchers.add(testerMuster);
		visit.setSearchers(searchers);
		visit.setStartTimestamp(dateFormat.parse("08:00 22.12.2014"));
		visit.setEndTimestamp(dateFormat.parse("11:00 22.12.2014"));
		visitDao.save(visit);

		visit = new Visit();
		visit.setAd(ad1);
		searchers = new LinkedList<>();
		searchers.add(testerMuster);
		visit.setSearchers(searchers);
		visit.setStartTimestamp(dateFormat.parse("14:00 23.12.2014"));
		visit.setEndTimestamp(dateFormat.parse("17:00 23.12.2014"));
		visitDao.save(visit);

		// Visits for Ad 2
		visit = new Visit();
		visit.setAd(ad2);
		searchers = new LinkedList<>();
		searchers.add(bernerBaer);
		visit.setSearchers(searchers);
		visit.setStartTimestamp(dateFormat.parse("09:00 20.12.2014"));
		visit.setEndTimestamp(dateFormat.parse("11:00 20.12.2014"));
		visitDao.save(visit);

		visit = new Visit();
		visit.setAd(ad2);
		visit.setSearchers(new LinkedList<User>());
		visit.setStartTimestamp(dateFormat.parse("12:00 20.12.2014"));
		visit.setEndTimestamp(dateFormat.parse("14:00 20.12.2014"));
		visitDao.save(visit);

		// Visits for Ad 3
		visit = new Visit();
		visit.setAd(ad3);
		searchers = new LinkedList<>();
		searchers.add(testerMuster);
		visit.setSearchers(searchers);
		visit.setStartTimestamp(dateFormat.parse("10:00 21.12.2014"));
		visit.setEndTimestamp(dateFormat.parse("11:00 21.12.2014"));
		visitDao.save(visit);

		visit = new Visit();
		visit.setAd(ad3);
		searchers = new LinkedList<>();
		searchers.add(testerMuster);
		visit.setSearchers(searchers);
		visit.setStartTimestamp(dateFormat.parse("08:00 22.12.2014"));
		visit.setEndTimestamp(dateFormat.parse("11:00 22.12.2014"));
		visitDao.save(visit);

		visit = new Visit();
		visit.setAd(ad3);
		searchers = new LinkedList<>();
		searchers.add(testerMuster);
		visit.setSearchers(searchers);
		visit.setStartTimestamp(dateFormat.parse("14:00 23.12.2014"));
		visit.setEndTimestamp(dateFormat.parse("16:00 23.12.2014"));
		visitDao.save(visit);

		// Visits for Ad 4
		visit = new Visit();
		visit.setAd(ad4);
		searchers = new LinkedList<>();
		searchers.add(bernerBaer);
		visit.setSearchers(searchers);
		visit.setStartTimestamp(dateFormat.parse("10:00 21.12.2014"));
		visit.setEndTimestamp(dateFormat.parse("11:00 21.12.2014"));
		visitDao.save(visit);

		visit = new Visit();
		visit.setAd(ad4);
		searchers = new LinkedList<>();
		searchers.add(bernerBaer);
		visit.setSearchers(searchers);
		visit.setStartTimestamp(dateFormat.parse("08:00 22.12.2014"));
		visit.setEndTimestamp(dateFormat.parse("11:00 22.12.2014"));
		visitDao.save(visit);

		visit = new Visit();
		visit.setAd(ad4);
		searchers = new LinkedList<>();
		searchers.add(bernerBaer);
		visit.setSearchers(searchers);
		visit.setStartTimestamp(dateFormat.parse("14:00 26.12.2014"));
		visit.setEndTimestamp(dateFormat.parse("16:00 26.12.2014"));
		visitDao.save(visit);

		visit = new Visit();
		visit.setAd(ad4);
		visit.setSearchers(new LinkedList<>());
		visit.setStartTimestamp(dateFormat.parse("18:30 27.12.2014"));
		visit.setEndTimestamp(dateFormat.parse("20:00 27.12.2014"));
		visitDao.save(visit);

		// Visits for Ad 5
		visit = new Visit();
		visit.setAd(ad5);
		searchers = new LinkedList<>();
		searchers.add(janeDoe);
		visit.setSearchers(searchers);
		visit.setStartTimestamp(dateFormat.parse("10:00 12.12.2014"));
		visit.setEndTimestamp(dateFormat.parse("11:00 12.12.2014"));
		visitDao.save(visit);

		visit = new Visit();
		visit.setAd(ad5);
		searchers = new LinkedList<>();
		searchers.add(testerMuster);
		visit.setSearchers(searchers);
		visit.setStartTimestamp(dateFormat.parse("13:00 13.12.2014"));
		visit.setEndTimestamp(dateFormat.parse("14:00 13.12.2014"));
		visitDao.save(visit);

		visit = new Visit();
		visit.setAd(ad5);
		searchers = new LinkedList<>();
		searchers.add(testerMuster);
		visit.setSearchers(searchers);
		visit.setStartTimestamp(dateFormat.parse("19:00 15.12.2014"));
		visit.setEndTimestamp(dateFormat.parse("21:00 15.12.2014"));
		visitDao.save(visit);

		visit = new Visit();
		visit.setAd(ad5);
		searchers = new LinkedList<>();
		searchers.add(oprah);
		visit.setSearchers(searchers);
		visit.setStartTimestamp(dateFormat.parse("08:00 16.12.2014"));
		visit.setEndTimestamp(dateFormat.parse("11:00 16.12.2014"));
		visitDao.save(visit);

		visit = new Visit();
		visit.setAd(ad5);
		visit.setSearchers(new LinkedList<>());
		visit.setStartTimestamp(dateFormat.parse("13:00 19.12.2014"));
		visit.setEndTimestamp(dateFormat.parse("15:30 19.12.2014"));
		visitDao.save(visit);

		// Visits for Ad 6
		visit = new Visit();
		visit.setAd(ad6);
		searchers = new LinkedList<>();
		searchers.add(janeDoe);
		searchers.add(bernerBaer);
		visit.setSearchers(searchers);
		visit.setStartTimestamp(dateFormat.parse("10:00 21.12.2014"));
		visit.setEndTimestamp(dateFormat.parse("11:00 21.12.2014"));
		visitDao.save(visit);

		visit = new Visit();
		visit.setAd(ad6);
		searchers = new LinkedList<>();
		searchers.add(bernerBaer);
		visit.setSearchers(searchers);
		visit.setStartTimestamp(dateFormat.parse("08:00 22.12.2014"));
		visit.setEndTimestamp(dateFormat.parse("11:00 22.12.2014"));
		visitDao.save(visit);

		visit = new Visit();
		visit.setAd(ad6);
		searchers = new LinkedList<>();
		searchers.add(bernerBaer);
		visit.setSearchers(searchers);
		visit.setStartTimestamp(dateFormat.parse("14:00 26.12.2014"));
		visit.setEndTimestamp(dateFormat.parse("16:00 26.12.2014"));
		visitDao.save(visit);

		visit = new Visit();
		visit.setAd(ad6);
		visit.setSearchers(new LinkedList<>());
		visit.setStartTimestamp(dateFormat.parse("18:30 27.12.2014"));
		visit.setEndTimestamp(dateFormat.parse("20:00 27.12.2014"));
		visitDao.save(visit);

		// Visits for Ad 7
		visit = new Visit();
		visit.setAd(ad7);
		searchers = new LinkedList<>();
		searchers.add(janeDoe);
		visit.setSearchers(searchers);
		visit.setStartTimestamp(dateFormat.parse("10:00 12.12.2014"));
		visit.setEndTimestamp(dateFormat.parse("11:00 12.12.2014"));
		visitDao.save(visit);

		visit = new Visit();
		visit.setAd(ad7);
		searchers = new LinkedList<>();
		searchers.add(janeDoe);
		visit.setSearchers(searchers);
		visit.setStartTimestamp(dateFormat.parse("13:00 13.12.2014"));
		visit.setEndTimestamp(dateFormat.parse("14:00 13.12.2014"));
		visitDao.save(visit);

		visit = new Visit();
		visit.setAd(ad7);
		searchers = new LinkedList<>();
		searchers.add(janeDoe);
		searchers.add(bernerBaer);
		visit.setSearchers(searchers);
		visit.setStartTimestamp(dateFormat.parse("19:00 15.12.2014"));
		visit.setEndTimestamp(dateFormat.parse("21:00 15.12.2014"));
		visitDao.save(visit);

		visit = new Visit();
		visit.setAd(ad7);
		searchers = new LinkedList<>();
		searchers.add(janeDoe);
		visit.setSearchers(searchers);
		visit.setStartTimestamp(dateFormat.parse("08:00 16.12.2014"));
		visit.setEndTimestamp(dateFormat.parse("11:00 16.12.2014"));
		visitDao.save(visit);

		visit = new Visit();
		visit.setAd(ad7);
		visit.setSearchers(new LinkedList<>());
		visit.setStartTimestamp(dateFormat.parse("13:00 19.12.2014"));
		visit.setEndTimestamp(dateFormat.parse("15:30 19.12.2014"));
		visitDao.save(visit);

		// Visits for Ad 8
		visit = new Visit();
		visit.setAd(ad8);
		searchers = new LinkedList<>();
		searchers.add(janeDoe);
		searchers.add(bernerBaer);
		visit.setSearchers(searchers);
		visit.setStartTimestamp(dateFormat.parse("10:00 21.12.2014"));
		visit.setEndTimestamp(dateFormat.parse("11:00 21.12.2014"));
		visitDao.save(visit);

		visit = new Visit();
		visit.setAd(ad8);
		searchers = new LinkedList<>();
		searchers.add(bernerBaer);
		visit.setSearchers(searchers);
		visit.setStartTimestamp(dateFormat.parse("08:00 22.12.2014"));
		visit.setEndTimestamp(dateFormat.parse("11:00 22.12.2014"));
		visitDao.save(visit);

		visit = new Visit();
		visit.setAd(ad8);
		searchers = new LinkedList<>();
		searchers.add(bernerBaer);
		visit.setSearchers(searchers);
		visit.setStartTimestamp(dateFormat.parse("14:00 26.12.2014"));
		visit.setEndTimestamp(dateFormat.parse("16:00 26.12.2014"));
		visitDao.save(visit);

		visit = new Visit();
		visit.setAd(ad8);
		visit.setSearchers(new LinkedList<>());
		visit.setStartTimestamp(dateFormat.parse("18:30 27.12.2014"));
		visit.setEndTimestamp(dateFormat.parse("20:00 27.12.2014"));
		visitDao.save(visit);

		// Visits for Ad 9
		visit = new Visit();
		visit.setAd(ad9);
		searchers = new LinkedList<>();
		searchers.add(janeDoe);
		visit.setSearchers(searchers);
		visit.setStartTimestamp(dateFormat.parse("10:00 12.12.2014"));
		visit.setEndTimestamp(dateFormat.parse("11:00 12.12.2014"));
		visitDao.save(visit);

		visit = new Visit();
		visit.setAd(ad9);
		searchers = new LinkedList<>();
		searchers.add(bernerBaer);
		visit.setSearchers(searchers);
		visit.setStartTimestamp(dateFormat.parse("13:00 13.12.2014"));
		visit.setEndTimestamp(dateFormat.parse("14:00 13.12.2014"));
		visitDao.save(visit);

		visit = new Visit();
		visit.setAd(ad9);
		searchers = new LinkedList<>();
		searchers.add(bernerBaer);
		visit.setSearchers(searchers);
		visit.setStartTimestamp(dateFormat.parse("19:00 15.12.2014"));
		visit.setEndTimestamp(dateFormat.parse("21:00 15.12.2014"));
		visitDao.save(visit);

		visit = new Visit();
		visit.setAd(ad9);
		searchers = new LinkedList<>();
		searchers.add(janeDoe);
		visit.setSearchers(searchers);
		visit.setStartTimestamp(dateFormat.parse("08:00 16.12.2014"));
		visit.setEndTimestamp(dateFormat.parse("11:00 16.12.2014"));
		visitDao.save(visit);

		visit = new Visit();
		visit.setAd(ad9);
		visit.setSearchers(new LinkedList<>());
		visit.setStartTimestamp(dateFormat.parse("13:00 19.12.2014"));
		visit.setEndTimestamp(dateFormat.parse("15:30 19.12.2014"));
		visitDao.save(visit);

		// Visits for Ad 10
		visit = new Visit();
		visit.setAd(ad10);
		searchers = new LinkedList<>();
		searchers.add(bernerBaer);
		visit.setSearchers(searchers);
		visit.setStartTimestamp(dateFormat.parse("10:00 21.12.2014"));
		visit.setEndTimestamp(dateFormat.parse("11:00 21.12.2014"));
		visitDao.save(visit);

		visit = new Visit();
		visit.setAd(ad10);
		searchers = new LinkedList<>();
		searchers.add(bernerBaer);
		visit.setSearchers(searchers);
		visit.setStartTimestamp(dateFormat.parse("08:00 22.12.2014"));
		visit.setEndTimestamp(dateFormat.parse("11:00 22.12.2014"));
		visitDao.save(visit);

		visit = new Visit();
		visit.setAd(ad10);
		searchers = new LinkedList<>();
		searchers.add(bernerBaer);
		visit.setSearchers(searchers);
		visit.setStartTimestamp(dateFormat.parse("14:00 26.12.2014"));
		visit.setEndTimestamp(dateFormat.parse("16:00 26.12.2014"));
		visitDao.save(visit);

		visit = new Visit();
		visit.setAd(ad10);
		visit.setSearchers(new LinkedList<>());
		visit.setStartTimestamp(dateFormat.parse("18:30 27.12.2014"));
		visit.setEndTimestamp(dateFormat.parse("20:00 27.12.2014"));
		visitDao.save(visit);

		// Visits for Ad 11
		visit = new Visit();
		visit.setAd(ad11);
		searchers = new LinkedList<>();
		searchers.add(janeDoe);
		visit.setSearchers(searchers);
		visit.setStartTimestamp(dateFormat.parse("10:00 12.12.2014"));
		visit.setEndTimestamp(dateFormat.parse("11:00 12.12.2014"));
		visitDao.save(visit);

		visit = new Visit();
		visit.setAd(ad11);
		searchers = new LinkedList<>();
		searchers.add(bernerBaer);
		visit.setSearchers(searchers);
		visit.setStartTimestamp(dateFormat.parse("13:00 13.12.2014"));
		visit.setEndTimestamp(dateFormat.parse("14:00 13.12.2014"));
		visitDao.save(visit);

		visit = new Visit();
		visit.setAd(ad11);
		searchers = new LinkedList<>();
		searchers.add(bernerBaer);
		visit.setSearchers(searchers);
		visit.setStartTimestamp(dateFormat.parse("19:00 15.12.2014"));
		visit.setEndTimestamp(dateFormat.parse("21:00 15.12.2014"));
		visitDao.save(visit);

		visit = new Visit();
		visit.setAd(ad11);
		searchers = new LinkedList<>();
		searchers.add(janeDoe);
		visit.setSearchers(searchers);
		visit.setStartTimestamp(dateFormat.parse("08:00 16.12.2014"));
		visit.setEndTimestamp(dateFormat.parse("11:00 16.12.2014"));
		visitDao.save(visit);

		visit = new Visit();
		visit.setAd(ad11);
		visit.setSearchers(new LinkedList<>());
		visit.setStartTimestamp(dateFormat.parse("13:00 19.12.2014"));
		visit.setEndTimestamp(dateFormat.parse("15:30 19.12.2014"));
		visitDao.save(visit);

		// Visits for Ad 12
		visit = new Visit();
		visit.setAd(ad12);
		searchers = new LinkedList<>();
		searchers.add(bernerBaer);
		visit.setSearchers(searchers);
		visit.setStartTimestamp(dateFormat.parse("10:00 21.12.2014"));
		visit.setEndTimestamp(dateFormat.parse("11:00 21.12.2014"));
		visitDao.save(visit);

		visit = new Visit();
		visit.setAd(ad12);
		visit.setSearchers(new LinkedList<>());
		visit.setStartTimestamp(dateFormat.parse("08:00 22.12.2014"));
		visit.setEndTimestamp(dateFormat.parse("11:00 22.12.2014"));
		visitDao.save(visit);

		visit = new Visit();
		visit.setAd(ad12);
		searchers = new LinkedList<>();
		searchers.add(bernerBaer);
		visit.setSearchers(searchers);
		visit.setStartTimestamp(dateFormat.parse("14:00 26.12.2014"));
		visit.setEndTimestamp(dateFormat.parse("16:00 26.12.2014"));
		visitDao.save(visit);
		
		visit = new Visit();
		visit.setAd(ad12);
		visit.setSearchers(new LinkedList<>());
		visit.setStartTimestamp(dateFormat.parse("18:30 27.12.2014"));
		visit.setEndTimestamp(dateFormat.parse("20:00 27.12.2014"));
		visitDao.save(visit);
	}

}
