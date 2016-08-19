package ch.unibe.ese.team1.controller.service;

import java.util.ArrayList;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ch.unibe.ese.team1.model.Ad;
import ch.unibe.ese.team1.model.User;
import ch.unibe.ese.team1.model.Visit;
import ch.unibe.ese.team1.model.VisitEnquiry;
import ch.unibe.ese.team1.model.VisitEnquiryState;
import ch.unibe.ese.team1.model.dao.VisitDao;
import ch.unibe.ese.team1.model.dao.VisitEnquiryDao;

/** Provides operations for getting and saving visits */
@Service
public class VisitService {

	@Autowired
	private VisitDao visitDao;

	@Autowired
	VisitEnquiryDao visitEnquiryDao;

	/**
	 * Returns all possible visits of an advertisement.
	 * 
	 * @return an Iterable of all matching visits
	 */
	@Transactional
	public Iterable<Visit> getVisitsByAd(Ad ad) {
		return visitDao.findByAd(ad);
	}

	/** Returns the visit with the given id. */
	@Transactional
	public Visit getVisitById(long id) {
		return visitDao.findOne(id);
	}

	/** Returns all visits that a user has applied for and was also accepted to. */
	@Transactional
	public Iterable<Visit> getVisitsForUser(User user) {
		// all enquiries sent by user
		Iterable<VisitEnquiry> usersEnquiries = visitEnquiryDao
				.findBySender(user);
		// all visits user has been accepted for
		ArrayList<Visit> usersVisits = new ArrayList<Visit>();
		// fill the list
		for (VisitEnquiry enquiry : usersEnquiries) {
			if (enquiry.getState() == VisitEnquiryState.ACCEPTED)
				(usersVisits).add(enquiry.getVisit());
		}
		return usersVisits;
	}

	/** Returns all visitors for the visit with the given id. */
	public Iterable<User> getVisitorsForVisit(long id) {
		Visit visit = visitDao.findOne(id);
		return visit.getSearchers();
	}
}
