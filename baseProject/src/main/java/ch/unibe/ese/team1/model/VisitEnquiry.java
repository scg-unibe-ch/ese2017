package ch.unibe.ese.team1.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonFormat;

/** Models an enquiry for a visit between an advertiser and an ad searcher. */
@Entity
public class VisitEnquiry {

	@Id
	@GeneratedValue
	private long id;

	@ManyToOne
	private User sender;

	@JsonFormat(pattern = "HH:mm, dd.MM.yyyy")
	@Temporal(TemporalType.TIMESTAMP)
	private Date dateSent;

	@Column(nullable = false)
	private VisitEnquiryState state;

	/** Which visit does the enquiry belong to? */
	@ManyToOne
	private Visit visit;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public User getSender() {
		return sender;
	}

	public void setSender(User sender) {
		this.sender = sender;
	}

	public Date getDateSent() {
		return dateSent;
	}

	public void setDateSent(Date dateSent) {
		this.dateSent = dateSent;
	}

	public VisitEnquiryState getState() {
		return state;
	}

	public void setState(VisitEnquiryState state) {
		this.state = state;
	}

	public Visit getVisit() {
		return visit;
	}

	public void setVisit(Visit visit) {
		this.visit = visit;
	}

}