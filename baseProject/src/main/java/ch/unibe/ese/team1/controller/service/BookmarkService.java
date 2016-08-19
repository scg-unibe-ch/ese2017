package ch.unibe.ese.team1.controller.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ch.unibe.ese.team1.model.Ad;
import ch.unibe.ese.team1.model.User;
import ch.unibe.ese.team1.model.dao.UserDao;

/** Adds or removes bookmarked ads from the user and updates the user accordingly */
@Service
public class BookmarkService {
	
	@Autowired
	private UserDao userDao;
	
	/**
	 * This method adds or removes ads from the ArrayList.
	 * 
	 * @param id
	 *          it's the current ads' id
	 * @param bookmarked
	 *          tells the function the state of of the ad regarding bookmarks
	 * @param bookmarkedAds
	 *          users current list of bookmarked ads
	 * @param user
	 *          current user
	 *          
	 * @return returns an integer 3 bookmark it
	 *                            2 undo the bookmark
	 * 
	 */
	public int getBookmarkStatus(Ad ad, boolean bookmarked, User user) {
		List<Ad> tempAdList = user.getBookmarkedAds();
		if(bookmarked) {
			tempAdList.remove(ad);
			updateUser(tempAdList, user);
			return 2;
		}
		
		if(!bookmarked) {
			tempAdList.add(ad);
			updateUser(tempAdList, user);
			return 3;
		}
		
		return 1;
	}
	
	// updates effectively the new List into DB
	private void updateUser(List<Ad> bookmarkedAds, User user) {
		user.setBookmarkedAds(bookmarkedAds);
		userDao.save(user);
	
	}
}
