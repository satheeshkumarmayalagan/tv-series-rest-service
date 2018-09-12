package com.nine.tvseries.api.messages;

import java.util.List;

import com.nine.tvseries.model.TVSeriesSummary;

public class TVSeriesResponse {

	private List<TVSeriesSummary> response;

	public TVSeriesResponse(List<TVSeriesSummary> response) {
		this.response = response;
	}

	public List<TVSeriesSummary> getResponse() {
		return response;
	}

	public void setResponse(List<TVSeriesSummary> response) {
		this.response = response;
	}

	@Override
	public String toString() {
		return "TVSeriesResponse [response=" + response + "]";
	}

}
