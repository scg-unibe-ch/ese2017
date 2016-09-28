package view;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class TweetView {

	private String template;

	public TweetView() throws IOException, URISyntaxException {
		this.template = new String(
				Files.readAllBytes(Paths.get(this.getClass().getResource("TweetView.html").toURI())));
	}

	public String render(String content) {
		return this.template.replace("{{content}}", content);
	}

}
