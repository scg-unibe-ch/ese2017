package model.service;

import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

import com.twitter.hbc.ClientBuilder;
import com.twitter.hbc.core.Constants;
import com.twitter.hbc.core.HttpHosts;
import com.twitter.hbc.core.endpoint.StatusesFilterEndpoint;
import com.twitter.hbc.core.event.Event;
import com.twitter.hbc.core.processor.StringDelimitedProcessor;
import com.twitter.hbc.httpclient.BasicClient;
import com.twitter.hbc.httpclient.auth.Authentication;
import com.twitter.hbc.httpclient.auth.OAuth1;

import model.TweetModel;
import twitter4j.Twitter;

public class TweetService {

	private Twitter twitter;
	private BasicClient client;
	private BlockingQueue<String> tweets;

	public TweetService(Twitter twitter, List<String> query) {
		this.twitter = twitter;
		this.setQuery(query);
	}

	public TweetModel next() {
		String status = null;
		try {
			status = this.tweets.poll(10000, TimeUnit.MILLISECONDS);
		} catch (InterruptedException exception) {
			throw new RuntimeException("interrupted while fetching tweet", exception);
		}
		if (status != null) {
			return new TweetModel(status);
		}
		throw new RuntimeException("couldn't find any tweet");
	}

	public void setQuery(List<String> query) {
		if (this.client != null) {
			this.client.stop();
		}
		this.tweets = new LinkedBlockingQueue<>(10000);
		BlockingQueue<Event> eventQueue = new LinkedBlockingQueue<>(10000);
		StatusesFilterEndpoint hosebirdEndpoint = new StatusesFilterEndpoint();
		hosebirdEndpoint.trackTerms(query);
		Authentication authentication = new OAuth1(this.twitter.getConfiguration().getOAuthConsumerKey(),
				this.twitter.getConfiguration().getOAuthConsumerSecret(),
				this.twitter.getConfiguration().getOAuthAccessToken(),
				this.twitter.getConfiguration().getOAuthAccessTokenSecret());
		ClientBuilder builder = new ClientBuilder().name("god").hosts(new HttpHosts(Constants.STREAM_HOST))
				.authentication(authentication).endpoint(hosebirdEndpoint)
				.processor(new StringDelimitedProcessor(this.tweets)).eventMessageQueue(eventQueue);
		this.client = builder.build();
		this.client.connect();
	}

}
