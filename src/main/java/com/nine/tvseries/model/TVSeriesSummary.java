package com.nine.tvseries.model;

public class TVSeriesSummary {

	private String image;
	private String slug;
	private String title;

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getSlug() {
		return slug;
	}

	public void setSlug(String slug) {
		this.slug = slug;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@Override
	public String toString() {
		return "TVSeriesSummary [slug=" + slug + ", title=" + title + ", image=" + image + "]";
	}

}
