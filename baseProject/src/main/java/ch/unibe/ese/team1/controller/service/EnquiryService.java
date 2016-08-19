package ch.unibe.ese.team1.controller.service;

import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ch.unibe.ese.team1.model.Rating;
import ch.unibe.ese.team1.model.User;
import ch.unibe.ese.team1.model.Visit;
import ch.unibe.ese.team1.model.VisitEnquiry;
import ch.unibe.ese.team1.model.VisitEnquiryState;
import ch.unibe.ese.team1.model.dao.RatingDao;
import ch.unibe.ese.team1.model.dao.VisitDao;
import ch.unibe.ese.team1.model.dao.VisitEnquiryDao;

/** Provides access to enquiries saved in the database. */
@Service
public class EnquiryService {

	@Autowired
	private VisitEnquiryDao enquiryDao;

	@Autowired
	private RatingDao ratingDao;

	@Autowired
	private VisitDao visitDao;

	/**
	 * Returns all enquiries that were sent to the given user sorted by date
	 * sent
	 * 
	 * @param recipient
	 *            the user to search for
	 * @return an Iterable of all matching enquiries
	 */
	@Transactional
	public Iterable<VisitEnquiry> getEnquiriesByRecipient(User recipient) {
		List<VisitEnquiry> enquiries = new LinkedList<VisitEnquiry>();
		for (VisitEnquiry enquiry : enquiryDao.findAll()) {
			if (enquiry.getVisit().getAd().getUser().getId() == recipient
					.getId()) {
				enquiries.add(enquiry);
			}
		}
		Collections.sort(enquiries, new Comparator<VisitEnquiry>() {
			@Override
			public int compare(VisitEnquiry enquiry1, VisitEnquiry enquiry2) {
				return enquiry2.getDateSent().compareTo(enquiry1.getDateSent());
			}
		});
		return enquiries;
	}

	/** Saves the given visit enquiry. */
	@Transactional
	public void saveVisitEnquiry(VisitEnquiry visitEnquiry) {
		enquiryDao.save(visitEnquiry);
	}

	/** Accepts the enquiry with the given id. */
	@Transactional
	public void acceptEnquiry(long enquiryId) {
		// accept visit
		VisitEnquiry enquiry = enquiryDao.findOne(enquiryId);
		enquiry.setState(VisitEnquiryState.ACCEPTED);
		enquiryDao.save(enquiry);

		// add user to the visitor list
		Visit visit = enquiry.getVisit();
		visit.addToSearchers(enquiry.getSender());
		visitDao.save(visit);

		// create a non-initialized rating
		User ratee = enquiry.getSender();
		User rater = visit.getAd().getUser();
		Rating rating = new Rating();
		rating.setRater(rater);
		rating.setRatee(ratee);
		rating.setRating(0);
		ratingDao.save(rating);
	}

	/** Declines the enquiry with the given id. */
	@Transactional
	public void declineEnquiry(long enquiryId) {
		VisitEnquiry enquiry = enquiryDao.findOne(enquiryId);
		enquiry.setState(VisitEnquiryState.DECLINED);
		enquiryDao.save(enquiry);
	}

	/**
	 * Resets the enquiry with the given id, meaning that its state will be set
	 * to open again.
	 */
	@Transactional
	public void reopenEnquiry(long enquiryId) {
		VisitEnquiry enquiry = enquiryDao.findOne(enquiryId);
		enquiry.setState(VisitEnquiryState.OPEN);
		enquiryDao.save(enquiry);

		Visit visit = enquiry.getVisit();
		visit.removeFromSearchers(enquiry.getSender());
		visitDao.save(visit);
	}

	/**
	 * Gives the ratee the given rating by the rater.
	 * 
	 * @param rater
	 *            the user that issued the rating
	 * @param ratee
	 *            the user that was rated
	 * @param rating
	 *            the rating that was associated with the ratee
	 */
	@Transactional
	public void rate(User rater, User ratee, int rating) {
		Rating newRating = getRatingByRaterAndRatee(rater, ratee);
		newRating.setRating(rating);
		ratingDao.save(newRating);
	}

	/** Returns all ratings that were made by the given user. */
	@Transactional
	public Iterable<Rating> getRatingsByRater(User rater) {
		return ratingDao.findByRater(rater);
	}

	/**
	 * Returns all ratings that were made by the given user for the given ratee.
	 * This method always returns one rating, because one rater can only give
	 * one rating to another user.
	 */
	@Transactional
	public Rating getRatingByRaterAndRatee(User rater, User ratee) {
		Iterable<Rating> ratings = ratingDao.findByRaterAndRatee(rater, ratee);

		// ugly hack, but works
		Iterator<Rating> iterator = ratings.iterator();
		Rating next = iterator.next();
		return next;
	}
}
