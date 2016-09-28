
import java.io.IOException;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.eclipse.jetty.server.Request;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.AbstractHandler;

import com.google.common.collect.Lists;
import com.twitter.hbc.ClientBuilder;
import com.twitter.hbc.core.Constants;
import com.twitter.hbc.core.Hosts;
import com.twitter.hbc.core.HttpHosts;
import com.twitter.hbc.core.endpoint.StatusesFilterEndpoint;
import com.twitter.hbc.core.event.Event;
import com.twitter.hbc.core.processor.StringDelimitedProcessor;
import com.twitter.hbc.httpclient.BasicClient;
import com.twitter.hbc.httpclient.auth.Authentication;
import com.twitter.hbc.httpclient.auth.OAuth1;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.RowConstraints;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import twitter4j.OEmbed;
import twitter4j.OEmbedRequest;
import twitter4j.OEmbedRequest.Align;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.TwitterObjectFactory;

public class God extends Application {

	public static void main(String[] args) throws Exception {
		launch(args);
	}

	private WebEngine webEngine;
	private GridPane gridPane;
	private Twitter twitter;
	private BlockingQueue<String> statuses;
	private BasicClient hosebirdClient;

	public God() throws Exception {
		super();
		TwitterFactory factory = new TwitterFactory();
		this.twitter = factory.getInstance();
		this.newStatuses(Lists.newArrayList("dank", "memes"));
		this.startServer();
		// build ui components
		WebView webView = new WebView();
		this.webEngine = webView.getEngine();
		TextField textField = new TextField();
		Button hellButton = new Button("hell");
		Button heavenButton = new Button("heaven");
		// layout
		this.gridPane = new GridPane();
		this.gridPane.add(webView, 0, 0, 2, 1);
		this.gridPane.add(textField, 0, 1, 2, 1);
		this.gridPane.add(hellButton, 0, 2);
		this.gridPane.add(heavenButton, 1, 2);
		webView.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
		textField.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
		hellButton.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
		heavenButton.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
		ColumnConstraints column1 = new ColumnConstraints();
		column1.setPercentWidth(50);
		ColumnConstraints column2 = new ColumnConstraints();
		column2.setPercentWidth(50);
		this.gridPane.getColumnConstraints().addAll(column1, column2);
		RowConstraints row1 = new RowConstraints();
		row1.setVgrow(Priority.ALWAYS);
		row1.setPercentHeight(80);
		RowConstraints row2 = new RowConstraints();
		row2.setVgrow(Priority.ALWAYS);
		row2.setPercentHeight(10);
		RowConstraints row3 = new RowConstraints();
		row3.setVgrow(Priority.ALWAYS);
		row3.setPercentHeight(10);
		this.gridPane.getRowConstraints().addAll(row1, row2, row3);
		// styling
		webView.setZoom(1.5);
		textField.setFont(Font.font("Helvetica", FontWeight.BOLD, 48));
		textField.setText("dank memes");
		hellButton.setBackground(
				new Background(new BackgroundFill(Color.rgb(0xff, 0, 0, 0.5), CornerRadii.EMPTY, Insets.EMPTY)));
		heavenButton.setBackground(
				new Background(new BackgroundFill(Color.rgb(0, 0, 0xff, 0.5), CornerRadii.EMPTY, Insets.EMPTY)));
		hellButton.setFont(Font.font("Helvetica", FontWeight.BOLD, 48));
		hellButton.setTextFill(Color.WHITE);
		heavenButton.setFont(Font.font("Helvetica", FontWeight.BOLD, 48));
		heavenButton.setTextFill(Color.WHITE);
		// glue
		hellButton.setOnAction(event -> {
			God.this.webEngine.executeScript("$('#tweet').animate({ 'left': '-300px', 'opacity': 0 }, 500);");
			God.this.webEngine.load("http://localhost:8000/");
		});
		heavenButton.setOnAction(event -> {
			God.this.webEngine.executeScript("$('#tweet').animate({ 'left': '300px', 'opacity': 0 }, 500);");
			God.this.webEngine.load("http://localhost:8000/");
		});
		this.gridPane.setOnKeyPressed(keyEvent -> {
			if (!(keyEvent.getCode() == KeyCode.CONTROL || keyEvent.getCode() == KeyCode.ENTER)) {
				return;
			}
			if (keyEvent.getCode() == KeyCode.ENTER) {
				God.this.newStatuses(Lists.newArrayList(textField.getText().split(" ")));
			}
			God.this.webEngine.executeScript("$('#tweet').animate({ 'top': '-300px', 'opacity': 0 }, 500);");
			God.this.webEngine.load("http://localhost:8000/");
		});
	}

	private void newStatuses(List<String> terms) {
		if (this.hosebirdClient != null) {
			this.hosebirdClient.stop();
		}
		this.statuses = new LinkedBlockingQueue<>(10000);
		BlockingQueue<Event> eventQueue = new LinkedBlockingQueue<>(10000);
		Hosts hosebirdHosts = new HttpHosts(Constants.STREAM_HOST);
		StatusesFilterEndpoint hosebirdEndpoint = new StatusesFilterEndpoint();
		// hosebirdEndpoint.languages(Lists.newArrayList("en"));
		hosebirdEndpoint.trackTerms(terms);
		Authentication hosebirdAuth = new OAuth1(this.twitter.getConfiguration().getOAuthConsumerKey(),
				this.twitter.getConfiguration().getOAuthConsumerSecret(),
				this.twitter.getConfiguration().getOAuthAccessToken(),
				this.twitter.getConfiguration().getOAuthAccessTokenSecret());
		ClientBuilder builder = new ClientBuilder().name("god").hosts(hosebirdHosts).authentication(hosebirdAuth)
				.endpoint(hosebirdEndpoint).processor(new StringDelimitedProcessor(this.statuses))
				.eventMessageQueue(eventQueue);
		this.hosebirdClient = builder.build();
		this.hosebirdClient.connect();
	}

	private void startServer() throws Exception {
		String template = "<!DOCTYPE html><html><head><meta charset=\"utf-8\"><title>god</title><script src=\"https://code.jquery.com/jquery-3.1.0.min.js\"></script><style>#tweet { position: relative; display: none; } body { overflow-x: hidden; overflow-y: hidden; } blockquote { display: none; }</style><script>$(document).ready(function () { $('#tweet').delay(100).fadeIn(); });</script></head><body><div id=\"tweet\">{{content}}</div></body></html>";
		Server server = new Server(8000);
		server.setHandler(new AbstractHandler() {
			@Override
			protected void doHandle(String target, Request baseRequest, HttpServletRequest request,
					HttpServletResponse response) throws IOException, ServletException {
				try {
					String statusObject = God.this.statuses.poll(10000, TimeUnit.MILLISECONDS);
					if (statusObject == null) {
						throw new RuntimeException("couldn't find any tweet");
					}
					Status status = TwitterObjectFactory.createStatus(statusObject);
					OEmbedRequest oEmbedRequest = new OEmbedRequest(status.getId(), null);
					oEmbedRequest.setHideMedia(false);
					oEmbedRequest.setHideThread(false);
					oEmbedRequest.setMaxWidth(550);
					oEmbedRequest.setAlign(Align.CENTER);
					OEmbed oEmbed = God.this.twitter.getOEmbed(oEmbedRequest);
					response.setContentType("text/html; charset=utf-8");
					response.setStatus(HttpServletResponse.SC_OK);
					response.getWriter().println(template.replace("{{content}}", oEmbed.getHtml()));
					baseRequest.setHandled(true);
				} catch (TwitterException | InterruptedException exception) {
					throw new RuntimeException(exception);
				}
			}
		});
		server.start();
	}

	@Override
	public void start(Stage stage) throws Exception {
		stage.setTitle("God");
		stage.setScene(new Scene(this.gridPane));
		stage.setFullScreen(true);
		stage.setOnCloseRequest(e -> System.exit(0));
		stage.show();
		this.webEngine.load("http://localhost:8000/");
	}

}
