package ch.unibe.ese.team1.model;

/**
 * Describes a picture with properties such as filename, file size etc. Objects
 * of this type should be convertable to JSON. That is also the reason why
 * fileSize is a String attribute.
 */
public class PictureMeta {

	private String name;
	private String size;
	private String type;
	private String url;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
}
