package application;

import java.util.List;

import com.google.common.collect.Lists;

import controller.DashboardController;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.TweetModel;
import model.service.TweetService;
import twitter4j.Twitter;
import twitter4j.TwitterFactory;
import view.DashboardView;
import view.TweetView;

public class Application extends javafx.application.Application {

	public static void main(String[] args) throws Exception {
		launch(args);
	}

	@Override
	public void start(Stage stage) throws Exception {
		// bootstrap...
		Twitter twitter = new TwitterFactory().getInstance();
		List<String> query = Lists.newArrayList("think", "dog", "cat");
		DashboardView dashboardView = new DashboardView();
		TweetView tweetView = new TweetView();
		TweetService tweetService = new TweetService(twitter, query);
		TweetModel currentTweetModel = tweetService.next();
		TweetServer tweetServer = new TweetServer(twitter, tweetView, currentTweetModel);
		DashboardController dashboardController = new DashboardController(tweetServer, tweetService, currentTweetModel,
				dashboardView);
		tweetServer.start();
		// ...a little bit of cheating...
		dashboardView.getTextField().setText(String.join(" ", query));
		dashboardView.getWebView().getEngine().load(tweetServer.getUrl());
		// ...and go!
		stage.setTitle("God");
		stage.setScene(new Scene(dashboardView));
		stage.setFullScreen(true);
		stage.setOnCloseRequest(e -> System.exit(0));
		stage.show();
	}

}
