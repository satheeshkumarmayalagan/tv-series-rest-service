package com.nine.tvservice.service;

import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.nine.tvseries.api.exception.BadRequestException;
import com.nine.tvseries.model.TVSeries;
import com.nine.tvseries.model.TVSeriesSummary;

@Service
public class TVSeriesService {

	private static final Logger log = LoggerFactory.getLogger(TVSeriesService.class);

	public List<TVSeriesSummary> filter(List<TVSeries> inList) {
		log.info("filterTVSeries - {}", inList);
		if (inList != null && !inList.isEmpty()) {
			List<TVSeriesSummary> summary = inList.stream().filter(t -> t.getEpisodeCount() > 0 && t.isDrm())
					.map(t -> map(t)).collect(Collectors.toList());
			return summary;
		} else {
			throw new BadRequestException("Could not decode request: JSON parsing failed");
		}
	}

	private TVSeriesSummary map(TVSeries in) {
		TVSeriesSummary out = null;
		if (in != null) {
			out = new TVSeriesSummary();
			out.setImage(in.getImageUri());
			out.setSlug(in.getSlug());
			out.setTitle(in.getTitle());
		}
		return out;
	}

}
