package com.nine.tvseries.api;

import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.nine.tvseries.api.exception.BadRequestException;
import com.nine.tvseries.api.messages.TVSeriesRequest;
import com.nine.tvseries.api.messages.TVSeriesResponse;
import com.nine.tvseries.model.TVSeriesSummary;
import com.nine.tvservice.service.TVSeriesService;

@RestController
public class TVSeriesController extends BaseController {

	private static final Logger log = LoggerFactory.getLogger(TVSeriesController.class);

	@Autowired
	TVSeriesService tvSeriesService;

	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<?> filter(@Valid @RequestBody TVSeriesRequest tvseriesRequest) {
		log.info("filterTVSeries - {}", tvseriesRequest);
		if (tvseriesRequest != null && tvseriesRequest.getPayload() != null
				&& !tvseriesRequest.getPayload().isEmpty()) {
			List<TVSeriesSummary> summary = tvSeriesService.filter(tvseriesRequest.getPayload());
			return ResponseEntity.ok(new TVSeriesResponse(summary));
		} else {
			throw new BadRequestException("Could not decode request: JSON parsing failed");
		}
	}

}
