package ch.unibe.ese.team1.test.testData;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ch.unibe.ese.team1.model.Message;
import ch.unibe.ese.team1.model.MessageState;
import ch.unibe.ese.team1.model.User;
import ch.unibe.ese.team1.model.dao.MessageDao;
import ch.unibe.ese.team1.model.dao.UserDao;

/**
 * This inserts some messages test data into the database.
 */
@Service
public class MessageTestDataSaver {

	@Autowired
	private UserDao userDao;

	@Autowired
	private MessageDao messageDao;

	private User bernerBaer;
	private User testerMuster;
	private User jane;
	private User oprah;

	@Transactional
	public void saveTestData() throws Exception {
		// load users
		bernerBaer = userDao.findByUsername("user@bern.com");
		testerMuster = userDao.findByUsername("ese@unibe.ch");
		jane = userDao.findByUsername("jane@doe.com");
		oprah = userDao.findByUsername("oprah@winfrey.com");

		Message message;
		DateFormat dateFormat = new SimpleDateFormat("HH:mm dd.MM.yyyy");

		// Messages for testerMuster
		message = new Message();
		message.setSubject("Cool ad");
		message.setText("Hello Mr. Wayne\n\n" + getDummyText1());
		message.setSender(jane);
		message.setRecipient(testerMuster);
		message.setState(MessageState.UNREAD);
		message.setDateSent(dateFormat.parse("12:02 24.02.2014"));
		messageDao.save(message);

		message = new Message();
		message.setSubject("I agree");
		message.setText("Hey again Mr. Wayne\n\n" + getDummyText2());
		message.setSender(bernerBaer);
		message.setRecipient(testerMuster);
		message.setState(MessageState.UNREAD);
		message.setDateSent(dateFormat.parse("12:30 24.02.2014"));
		messageDao.save(message);

		message = new Message();
		message.setSubject("Check this out");
		message.setText("Hello Mr. Bär\n " + getDummyText3());
		message.setSender(oprah);
		message.setRecipient(testerMuster);
		message.setState(MessageState.READ);
		message.setDateSent(dateFormat.parse("11:30 24.02.2014"));
		messageDao.save(message);

		// Messages for JaneDoe
		message = new Message();
		message.setSubject("I agree");
		message.setText("Hey Jane\n\n" + getDummyText2());
		message.setSender(bernerBaer);
		message.setRecipient(jane);
		message.setState(MessageState.UNREAD);
		message.setDateSent(dateFormat.parse("12:30 24.02.2014"));
		messageDao.save(message);

		message = new Message();
		message.setSubject("Check this out");
		message.setText("Whats up Jane?\n\n" + getDummyText3());
		message.setSender(oprah);
		message.setRecipient(jane);
		message.setState(MessageState.READ);
		message.setDateSent(dateFormat.parse("11:30 24.02.2014"));
		messageDao.save(message);

		// Messages for Berner Bär
		message = new Message();
		message.setSubject("Awesome Ad");
		message.setText("Hey Huggy bear\n\n" + getDummyText1());
		message.setSender(jane);
		message.setRecipient(bernerBaer);
		message.setState(MessageState.UNREAD);
		message.setDateSent(dateFormat.parse("12:30 24.02.2014"));
		messageDao.save(message);

		message = new Message();
		message.setSubject("Insane Ad");
		message.setText("Whats up Mr. bear?\n\n" + getDummyText3());
		message.setSender(oprah);
		message.setRecipient(bernerBaer);
		message.setState(MessageState.READ);
		message.setDateSent(dateFormat.parse("11:30 24.02.2014"));
		messageDao.save(message);

		// Messages for Oprah
		message = new Message();
		message.setSubject("Best Ad ever");
		message.setText("Hey Oprah\n\n" + getDummyText1());
		message.setSender(jane);
		message.setRecipient(oprah);
		message.setState(MessageState.UNREAD);
		message.setDateSent(dateFormat.parse("12:30 24.02.2014"));
		messageDao.save(message);

		message = new Message();
		message.setSubject("You gotta see this");
		message.setText("Whats up Oprah?\n\n" + getDummyText2());
		message.setSender(bernerBaer);
		message.setRecipient(oprah);
		message.setState(MessageState.UNREAD);
		message.setDateSent(dateFormat.parse("11:30 24.02.2014"));
		messageDao.save(message);
	}

	private String getDummyText1() {
		return "I'm very interested in your advertisement, it looks just fabulous. The pictures give a good"
				+ "impression of the room and the whole flat. Your roommates seem to be very nice. The price"
				+ "seems fair. I've been living in many flats, I had a great time with various roommates."
				+ "My cooking skills are seriously impressive :). I like to chill out and drink some beer,"
				+ "have a nice meal together. My hoobies are hiking, traveling, music, sports and books. I"
				+ "really like to visit your flat, I sent you an enquiry for a visit.\n"
				+ "\nBest wishes,\n" + "Jane Doe";
	}

	private String getDummyText2() {
		return "I totally agree with you, glad that we settled this. I can visit the flat another time, this"
				+ "is no problem for me, looking forward to it."
				+ "\nSee you later,\n\n" + "Berner Bär";
	}

	private String getDummyText3() {
		return "Your flat just looks awesome, can't wait to see it myself. Those pictures are really promising,"
				+ "and your roommates seem to be dope too. It would be so leet to chill around and smoke some"
				+ "ganja maybe, good old hippy-style man. I have a doggy, he hurts no one, apart from the police."
				+ "Your roommates seem to be very nice. The price"
				+ "seems fair. I've been living in many flats, I had a great time with various roommates."
				+ "My cooking skills are seriously impressive :). I like to chill out and drink some beer,"
				+ "have a nice meal together. My hoobies are hiking, traveling, music, sports and books. I"
				+ "really like to visit your flat, I sent you an enquiry for a visit.\n"
				+ "\nCheerio,\n" + "Oprah";
	}

}
