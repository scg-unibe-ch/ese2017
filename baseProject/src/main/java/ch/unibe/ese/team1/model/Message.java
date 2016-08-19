package ch.unibe.ese.team1.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonFormat;

/** Represents a message that is sent between two users. */
@Entity
public class Message {

	@Id
	@GeneratedValue
	private long id;

	@Column(nullable = false)
	private MessageState state;

	@Column(nullable = false)
	private String subject;

	@Column(nullable = false)
	@Lob
	private String text;

	@JsonFormat(pattern = "HH:mm, dd.MM.yyyy", timezone = "CET" )
	@Temporal(TemporalType.TIMESTAMP)
	private Date dateSent;

	@ManyToOne
	private User sender;

	@ManyToOne
	private User recipient;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public MessageState getState() {
		return state;
	}

	public void setState(MessageState state) {
		this.state = state;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public Date getDateSent() {
		return dateSent;
	}

	public void setDateSent(Date dateSent) {
		this.dateSent = dateSent;
	}

	public User getSender() {
		return sender;
	}

	public void setSender(User sender) {
		this.sender = sender;
	}

	public User getRecipient() {
		return recipient;
	}

	public void setRecipient(User recipient) {
		this.recipient = recipient;
	}
}
