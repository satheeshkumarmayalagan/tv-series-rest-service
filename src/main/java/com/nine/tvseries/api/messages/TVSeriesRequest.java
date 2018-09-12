package com.nine.tvseries.api.messages;

import java.util.List;

import com.nine.tvseries.model.TVSeries;

public class TVSeriesRequest {

	private List<TVSeries> payload;

	public List<TVSeries> getPayload() {
		return payload;
	}

	public void setPayload(List<TVSeries> payload) {
		this.payload = payload;
	}

	@Override
	public String toString() {
		return "TVSeriesRequest [payload=" + payload + "]";
	}

}
