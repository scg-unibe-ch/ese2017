package ch.unibe.ese.team1.controller.pojos.forms;

import org.hibernate.validator.constraints.NotBlank;

/** This form is used when a user wants to send a new message */
public class MessageForm {

	@NotBlank(message = "Required")
	private String recipient;

	@NotBlank(message = "Required")
	private String subject;

	@NotBlank(message = "Required")
	private String text;

	public String getRecipient() {
		return recipient;
	}

	public void setRecipient(String recipient) {
		this.recipient = recipient;
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
}