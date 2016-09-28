package application;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.eclipse.jetty.server.Request;
import org.eclipse.jetty.server.Server;

import model.TweetModel;
import twitter4j.OEmbed;
import twitter4j.OEmbedRequest;
import twitter4j.OEmbedRequest.Align;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterObjectFactory;
import view.TweetView;

public class TweetServer extends Server {

	private static final int PORT = 8001;

	private Twitter twitter;
	private TweetView tweetView;
	private TweetModel currentTweetModel;

	public TweetServer(Twitter twitter, TweetView tweetView, TweetModel currentTweetModel) {
		super(PORT);
		this.twitter = twitter;
		this.tweetView = tweetView;
		this.currentTweetModel = currentTweetModel;
	}

	public void setCurrentTweetModel(TweetModel currentTweetModel) {
		this.currentTweetModel = currentTweetModel;
	}

	public String getUrl() {
		return "http://localhost:" + PORT;
	}

	@Override
	public void handle(String target, Request baseRequest, HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		Status status;
		try {
			status = TwitterObjectFactory.createStatus(this.currentTweetModel.getStatus());
		} catch (TwitterException exception) {
			throw new RuntimeException("could not create status", exception);
		}
		OEmbedRequest oEmbedRequest = new OEmbedRequest(status.getId(), null);
		oEmbedRequest.setHideMedia(false);
		oEmbedRequest.setHideThread(false);
		oEmbedRequest.setMaxWidth(550);
		oEmbedRequest.setAlign(Align.CENTER);
		OEmbed oEmbed;
		try {
			oEmbed = this.twitter.getOEmbed(oEmbedRequest);
		} catch (TwitterException exception) {
			throw new RuntimeException("could not get oembed", exception);
		}
		response.setContentType("text/html; charset=utf-8");
		response.setStatus(HttpServletResponse.SC_OK);
		response.getWriter().println(this.tweetView.render(oEmbed.getHtml()));
		baseRequest.setHandled(true);
	}

}
