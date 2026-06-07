package com.example.demo.service;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.slf4j.*;
import com.example.demo.dto.CandleResponse;

@Service
public class StockClientService {

	Logger logger = LoggerFactory.getLogger(StockClientService.class);

	@Autowired
	private RestTemplate restTemplate;

	public CandleResponse getCandles(String symbol, String timeframe, String startDate, String endDate) {

		logger.info("Entered getCandles method with symbol={}, timeframe={}, startDate={}, endDate={}", symbol,
				timeframe, startDate, endDate);

		if (symbol == null || timeframe == null || startDate == null || endDate == null) {
			logger.warn("One or more input parameters are null: symbol={}, timeframe={}, startDate={}, endDate={}",
					symbol, timeframe, startDate, endDate);
		}

		String url = "http://localhost:8080/api/v1/candles" + "?symbol=" + symbol + "&timeframe=" + timeframe
				+ "&start_date=" + startDate + "&end_date=" + endDate;

		logger.info("Calling Candle API with URL: {}", url);

		CandleResponse response = restTemplate.getForObject(url, CandleResponse.class);

		logger.error("Completed getCandles execution for symbol={} (no exceptions occurred in flow)", symbol);

		return response;
	}

}