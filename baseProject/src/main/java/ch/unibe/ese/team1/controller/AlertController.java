package ch.unibe.ese.team1.controller;

import java.security.Principal;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import ch.unibe.ese.team1.controller.pojos.forms.AlertForm;
import ch.unibe.ese.team1.controller.service.AlertService;
import ch.unibe.ese.team1.controller.service.UserService;
import ch.unibe.ese.team1.model.Alert;
import ch.unibe.ese.team1.model.User;

/**
 * This controller handles all requests concerning creating and deleting alerts.
 */
@Controller
public class AlertController {

	@Autowired
	private AlertService alertService;

	@Autowired
	private UserService userService;

	/** Serves the page that allows the user to view their alerts. */
	@RequestMapping(value = "/profile/alerts", method = RequestMethod.GET)
	public ModelAndView alerts(Principal principal) {
		return prepareAlertPage(principal, false, new AlertForm());
	}

	/**
	 * Serves the page that allow the user to view their alerts afert validating
	 * and persisting the new alert through the alert form.
	 */
	@RequestMapping(value = "/profile/alerts", method = RequestMethod.POST)
	public ModelAndView savedAlert(Principal principal,
			@Valid AlertForm alertForm, BindingResult result) {
		if (!result.hasErrors())
			return prepareAlertPage(principal, true, alertForm);
		else
			return new ModelAndView("alerts");
	}

	/** Deletes the alert with the given id */
	@RequestMapping(value = "/profile/alerts/deleteAlert", method = RequestMethod.GET)
	public @ResponseBody void deleteAlert(@RequestParam("id") long id) {
		alertService.deleteAlert(id);
	}

	/**
	 * Prepares the model for an alert page, filling in the user, an alert form
	 * and the alerts of the user.
	 */
	private ModelAndView prepareAlertPage(Principal principal,
			boolean alreadySet, AlertForm alertForm) {
		String username = principal.getName();
		User user = userService.findUserByUsername(username);
		if (alreadySet)
			alertService.saveFrom(alertForm, user);
		ModelAndView model = new ModelAndView("alerts");
		Iterable<Alert> alerts = alertService.getAlertsByUser(user);
		model.addObject("user", user);
		model.addObject("alertForm", alertForm);
		model.addObject("alerts", alerts);
		return model;
	}
}
