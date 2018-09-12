package com.nine.tvseries.model;

import java.util.Map;

import com.fasterxml.jackson.annotation.JsonProperty;

public class TVSeries {

	private boolean drm;
	private int episodeCount;
	private String genre;
	private String slug;
	private String title;
	private String imageUri;

	public boolean isDrm() {
		return drm;
	}

	public void setDrm(boolean drm) {
		this.drm = drm;
	}

	public int getEpisodeCount() {
		return episodeCount;
	}

	public void setEpisodeCount(int episodeCount) {
		this.episodeCount = episodeCount;
	}

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
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

	@JsonProperty("image")
	private void unpackNested(Map<String, Object> image) {
		if (image != null && image.get("showImage") != null)
			this.imageUri = (String) image.get("showImage");
	}

	public String getImageUri() {
		return imageUri;
	}

}
