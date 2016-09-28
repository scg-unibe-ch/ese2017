package controller;

import com.google.common.collect.Lists;

import application.TweetServer;
import javafx.event.ActionEvent;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import model.TweetModel;
import model.service.TweetService;
import view.DashboardView;

public class DashboardController {

	private TweetServer tweetServer;
	private TweetService tweetService;
	private TweetModel currentTweetModel;
	private DashboardView dashboardView;

	public DashboardController(TweetServer tweetServer, TweetService tweetService, TweetModel currentTweetModel,
			DashboardView dashboardView) {
		this.tweetServer = tweetServer;
		this.tweetService = tweetService;
		this.currentTweetModel = currentTweetModel;
		this.dashboardView = dashboardView;
		this.dashboardView.getHellButton().setOnAction(this::onHellButtonAction);
		this.dashboardView.getHeavenButton().setOnAction(this::onHeavenButtonAction);
		this.dashboardView.setOnKeyPressed(this::onKeyPressed);
	}

	private void onHellButtonAction(ActionEvent actionEvent) {
		this.currentTweetModel.sendToHell();
		this.dashboardView.getWebView().getEngine().executeScript("window.sendToHell();");
		this.currentTweetModel = this.nextTweetModel();
	}

	private void onHeavenButtonAction(ActionEvent actionEvent) {
		this.currentTweetModel.sendToHeaven();
		this.dashboardView.getWebView().getEngine().executeScript("window.sendToHeaven();");
		this.currentTweetModel = this.nextTweetModel();
	}

	private void onKeyPressed(KeyEvent keyEvent) {
		if (!(keyEvent.getCode() == KeyCode.CONTROL || keyEvent.getCode() == KeyCode.ENTER)) {
			return;
		}
		if (keyEvent.getCode() == KeyCode.ENTER) {
			this.tweetService.setQuery(Lists.newArrayList(this.dashboardView.getTextField().getText().split(" ")));
		}
		this.currentTweetModel = this.nextTweetModel();
	}

	private TweetModel nextTweetModel() {
		TweetModel nextTweetModel = this.tweetService.next();
		this.tweetServer.setCurrentTweetModel(nextTweetModel);
		this.dashboardView.getWebView().getEngine().load(this.tweetServer.getUrl());
		return nextTweetModel;
	}

}
