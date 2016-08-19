package ch.unibe.ese.team1.test.testData;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ch.unibe.ese.team1.model.User;
import ch.unibe.ese.team1.model.Visit;
import ch.unibe.ese.team1.model.VisitEnquiry;
import ch.unibe.ese.team1.model.VisitEnquiryState;
import ch.unibe.ese.team1.model.dao.UserDao;
import ch.unibe.ese.team1.model.dao.VisitDao;
import ch.unibe.ese.team1.model.dao.VisitEnquiryDao;

/**
 * This inserts some visit enquiries test data into the database.
 */
@Service
public class VisitEnquiryTestDataSaver {

	@Autowired
	private UserDao userDao;

	@Autowired
	private VisitEnquiryDao visitEnquiryDao;

	@Autowired
	private VisitDao visitDao;

	private User bernerBaer; // = user@bern.com
	private User testerMuster; // = ese@unibe.ch
	private User jane; // = jane@doe.com
	private User oprah; // = oprah@winfrey.com

	private Visit visit;

	/**
	 * Creating accepted Enquiries here has no effect, because once an Enquiry
	 * gets accepted, the Sender will be added to the searcher-List of visit the
	 * enquiry belongs to.
	 */
	@Transactional
	public void saveTestData() throws Exception {
		DateFormat dateFormat = new SimpleDateFormat("HH:mm dd.MM.yyyy");

		// load users
		bernerBaer = userDao.findByUsername("user@bern.com");
		testerMuster = userDao.findByUsername("ese@unibe.ch");
		jane = userDao.findByUsername("jane@doe.com");
		oprah = userDao.findByUsername("oprah@winfrey.com");


		// Add some open and declined Enquiries to play with
		VisitEnquiry enquiry;

		// Enquiries for advertiser user@bern.com (Berner Bär)
		// Add 2 Enquiries to Visit 1
		enquiry = new VisitEnquiry();
		visit = visitDao.findOne(1L);
		enquiry.setDateSent(dateFormat.parse(getRandomDummyTime()));
		enquiry.setSender(jane);
		enquiry.setState(VisitEnquiryState.OPEN);
		enquiry.setVisit(visit);
		visitEnquiryDao.save(enquiry);

		enquiry = new VisitEnquiry();
		enquiry.setDateSent(dateFormat.parse(getRandomDummyTime()));
		enquiry.setSender(oprah);
		enquiry.setState(VisitEnquiryState.DECLINED);
		enquiry.setVisit(visit);
		visitEnquiryDao.save(enquiry);

		// Add 1 Enquiry to Visit 6
		enquiry = new VisitEnquiry();
		visit = visitDao.findOne(6L);
		enquiry.setDateSent(dateFormat.parse(getRandomDummyTime()));
		enquiry.setSender(jane);
		enquiry.setState(VisitEnquiryState.OPEN);
		enquiry.setVisit(visit);
		visitEnquiryDao.save(enquiry);

		// Enquiries for advertiser ese (ese@unibe.ch)
		// Add 2 Enquiries to Visit 5
		enquiry = new VisitEnquiry();
		visit = visitDao.findOne(5L);
		enquiry.setDateSent(dateFormat.parse(getRandomDummyTime()));
		enquiry.setSender(jane);
		enquiry.setState(VisitEnquiryState.OPEN);
		enquiry.setVisit(visit);
		visitEnquiryDao.save(enquiry);

		enquiry = new VisitEnquiry();
		enquiry.setDateSent(dateFormat.parse(getRandomDummyTime()));
		enquiry.setSender(bernerBaer);
		enquiry.setState(VisitEnquiryState.DECLINED);
		enquiry.setVisit(visit);
		visitEnquiryDao.save(enquiry);

		// Add 1 Enquiriy to Visit 9
		enquiry = new VisitEnquiry();
		visit = visitDao.findOne(9L);
		enquiry.setDateSent(dateFormat.parse(getRandomDummyTime()));
		enquiry.setSender(jane);
		enquiry.setState(VisitEnquiryState.OPEN);
		enquiry.setVisit(visit);
		visitEnquiryDao.save(enquiry);

		// Add 1 Enquiriy to Visit 10
		enquiry = new VisitEnquiry();
		visit = visitDao.findOne(10L);
		enquiry.setDateSent(dateFormat.parse(getRandomDummyTime()));
		enquiry.setSender(oprah);
		enquiry.setState(VisitEnquiryState.OPEN);
		enquiry.setVisit(visit);
		visitEnquiryDao.save(enquiry);

		// Enquiries for advertiser oprah
		// Add 2 Enquiries to Visit 22
		enquiry = new VisitEnquiry();
		visit = visitDao.findOne(22L);
		enquiry.setDateSent(dateFormat.parse(getRandomDummyTime()));
		enquiry.setSender(bernerBaer);
		enquiry.setState(VisitEnquiryState.OPEN);
		enquiry.setVisit(visit);
		visitEnquiryDao.save(enquiry);
		
		enquiry = new VisitEnquiry();
		enquiry.setDateSent(dateFormat.parse(getRandomDummyTime()));
		enquiry.setSender(testerMuster);
		enquiry.setState(VisitEnquiryState.DECLINED);
		enquiry.setVisit(visit);
		visitEnquiryDao.save(enquiry);
		
		// Accept all enquiries which are already added to the searcher-lists
		// in the Visits (user accepted those enquiries already)
		acceptEnquiries();
	}

	/**
	 * Accept all enquiries that have been added already to the searcher-Lists
	 * in the VisitTestDataSaver (to be consequent).
	 * 
	 * @throws ParseException
	 */
	private void acceptEnquiries() throws ParseException {
		Iterable<Visit> acceptedVisits = visitDao.findAll();
		VisitEnquiry enquiry = new VisitEnquiry();
		DateFormat dateFormat = new SimpleDateFormat("HH:mm dd.MM.yyyy");

		for (Visit visit : acceptedVisits) {

			// Get all accepted enquiries for the visit
			Iterable<User> searchers = visit.getSearchers();
			for (User searcher : searchers) {
				enquiry = new VisitEnquiry();
				enquiry.setDateSent(dateFormat.parse(getRandomDummyTime()));
				enquiry.setSender(searcher);
				enquiry.setState(VisitEnquiryState.ACCEPTED);
				enquiry.setVisit(visit);
				visitEnquiryDao.save(enquiry);
			}
		}
	}

	/**
	 * Gets a pseudo random time from October to November 2014
	 */
	private String getRandomDummyTime() {
		int day = (int) (Math.random() * 30) + 1;
		int month = (int) (Math.random() * 2) + 10; // October or November
		int hour = (int) (Math.random() * 14) + 8;
		int minutes = (int) (Math.random() * 60);

		return String.format("%02d", hour) + ":"
				+ String.format("%02d", minutes) + " "
				+ String.format("%02d", day) + "."
				+ String.format("%02d", month) + ".2014";
	}

}
