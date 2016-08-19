package ch.unibe.ese.team1.model.dao;

import org.springframework.data.repository.CrudRepository;

import ch.unibe.ese.team1.model.Rating;
import ch.unibe.ese.team1.model.User;

public interface RatingDao extends CrudRepository<Rating, Long> {
	public Iterable<Rating> findByRater(User rater);
	public Iterable<Rating> findByRaterAndRatee(User rater, User ratee);
}