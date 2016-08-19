package ch.unibe.ese.team1.controller.service;

import static org.junit.Assert.assertEquals;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import ch.unibe.ese.team1.model.Ad;
import ch.unibe.ese.team1.model.Gender;
import ch.unibe.ese.team1.model.User;
import ch.unibe.ese.team1.model.UserRole;
import ch.unibe.ese.team1.model.Visit;
import ch.unibe.ese.team1.model.VisitEnquiry;
import ch.unibe.ese.team1.model.VisitEnquiryState;
import ch.unibe.ese.team1.model.dao.AdDao;
import ch.unibe.ese.team1.model.dao.UserDao;
import ch.unibe.ese.team1.model.dao.VisitDao;
import ch.unibe.ese.team1.model.dao.VisitEnquiryDao;

/**
 * 
 * Tests both Visit and VisitEnquiry functionality. Since one makes no sense
 * without the other, these tests were grouped into one suite.
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
		"file:src/main/webapp/WEB-INF/config/springMVC.xml",
		"file:src/main/webapp/WEB-INF/config/springData.xml",
		"file:src/main/webapp/WEB-INF/config/springSecurity.xml"})
@WebAppConfiguration
public class EnquiryServiceTest {
	
	@Autowired
	VisitService visitService;
	
	@Autowired
	EnquiryService enquiryService;
	
	@Autowired
	AdDao adDao;
	
	@Autowired
	UserDao userDao;
	
	@Autowired
	VisitDao visitDao;
	
	@Autowired
	VisitEnquiryDao visitEnquiryDao;
	
	@Test
	public void createVisits() throws Exception {		
		//create user
		User thomyF = createUser("thomy@f.ch", "password", "Thomy", "F",
				Gender.MALE);
		thomyF.setAboutMe("Supreme hustler");
		userDao.save(thomyF);
		
		//save an ad
		Date date = new Date();
		Ad oltenResidence= new Ad();
		oltenResidence.setZipcode(4600);
		oltenResidence.setMoveInDate(date);
		oltenResidence.setCreationDate(date);
		oltenResidence.setPrizePerMonth(1200);
		oltenResidence.setSquareFootage(42);
		oltenResidence.setStudio(false);
		oltenResidence.setSmokers(true);
		oltenResidence.setAnimals(false);
		oltenResidence.setRoomDescription("blah");
		oltenResidence.setPreferences("blah");
		oltenResidence.setRoommates("None");
		oltenResidence.setUser(thomyF);
		oltenResidence.setTitle("Olten Residence");
		oltenResidence.setStreet("Florastr. 100");
		oltenResidence.setCity("Olten");
		oltenResidence.setGarden(false);
		oltenResidence.setBalcony(false);
		oltenResidence.setCellar(false);
		oltenResidence.setFurnished(false);
		oltenResidence.setCable(false);
		oltenResidence.setGarage(false);
		oltenResidence.setInternet(false);
		adDao.save(oltenResidence);
		
		SimpleDateFormat formatter = new SimpleDateFormat("dd.MM.yyyy hh:mm");
		
		//ad two possible visiting times ("visits") to the ad
		Visit visit = new Visit();
		visit.setAd(oltenResidence);
		visit.setStartTimestamp(formatter.parse("16.12.2014 10:00"));
		visit.setEndTimestamp(formatter.parse("16.12.2014 12:00"));
		visitDao.save(visit);

		Visit visit2 = new Visit();
		visit2.setAd(oltenResidence);
		visit2.setStartTimestamp(formatter.parse("18.12.2014 10:00"));
		visit2.setEndTimestamp(formatter.parse("18.12.2014 12:00"));
		visitDao.save(visit2);
		
		Iterable<Visit> oltenVisits = visitService.getVisitsByAd(oltenResidence);
		ArrayList<Visit> oltenList = new ArrayList<Visit>();
		for(Visit oltenvis: oltenVisits)
			oltenList.add(oltenvis);
		
		assertEquals(2, oltenList.size());
	}
	
	@Test
	public void enquireAndAccept() throws Exception {		
		//create two users
		User adolfOgi = createUser("adolf@ogi.ch", "password", "Adolf", "Ogi",
				Gender.MALE);
		adolfOgi.setAboutMe("Wallis rocks");
		userDao.save(adolfOgi);
		
		User blocher = createUser("christoph@blocher.eu", "svp", "Christoph", "Blocher", Gender.MALE);
		blocher.setAboutMe("I own you");
		userDao.save(blocher);
		
		//save an ad
		Date date = new Date();
		Ad oltenResidence= new Ad();
		oltenResidence.setZipcode(4600);
		oltenResidence.setMoveInDate(date);
		oltenResidence.setCreationDate(date);
		oltenResidence.setPrizePerMonth(1200);
		oltenResidence.setSquareFootage(42);
		oltenResidence.setStudio(false);
		oltenResidence.setSmokers(true);
		oltenResidence.setAnimals(false);
		oltenResidence.setRoomDescription("blah");
		oltenResidence.setPreferences("blah");
		oltenResidence.setRoommates("None");
		oltenResidence.setUser(adolfOgi);
		oltenResidence.setTitle("Olten Residence");
		oltenResidence.setStreet("Florastr. 100");
		oltenResidence.setCity("Olten");
		oltenResidence.setGarden(false);
		oltenResidence.setBalcony(false);
		oltenResidence.setCellar(false);
		oltenResidence.setFurnished(false);
		oltenResidence.setCable(false);
		oltenResidence.setGarage(false);
		oltenResidence.setInternet(false);
		adDao.save(oltenResidence);
		
		SimpleDateFormat formatter = new SimpleDateFormat("dd.MM.yyyy hh:mm");
		
		//ad two possible visiting times ("visits") to the ad
		Visit visit = new Visit();
		visit.setAd(oltenResidence);
		visit.setStartTimestamp(formatter.parse("16.12.2014 10:00"));
		visit.setEndTimestamp(formatter.parse("16.12.2014 12:00"));
		visitDao.save(visit);

		Visit visit2 = new Visit();
		visit2.setAd(oltenResidence);
		visit2.setStartTimestamp(formatter.parse("18.12.2014 10:00"));
		visit2.setEndTimestamp(formatter.parse("18.12.2014 12:00"));
		visitDao.save(visit2);
		
		//Ogi is enquiring about Blocher's apartment
		VisitEnquiry enquiry = new VisitEnquiry();
		enquiry.setVisit(visit);
		enquiry.setSender(adolfOgi);
		enquiry.setState(VisitEnquiryState.OPEN);
		visitEnquiryDao.save(enquiry);
		
		Iterable<VisitEnquiry> ogiEnquiries = visitEnquiryDao.findBySender(adolfOgi);
		ArrayList<VisitEnquiry> ogiEnquiryList = new ArrayList<VisitEnquiry>();
		for(VisitEnquiry venq: ogiEnquiries)
			ogiEnquiryList.add(venq);
		
		long venqID = ogiEnquiryList.get(0).getId();
		
		enquiryService.acceptEnquiry(venqID);
		
		assertEquals(VisitEnquiryState.ACCEPTED, visitEnquiryDao.findOne(venqID).getState());
	}
	
	//Lean user creating method
	User createUser(String email, String password, String firstName,
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
}
