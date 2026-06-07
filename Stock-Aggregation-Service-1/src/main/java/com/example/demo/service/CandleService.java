package com.example.demo.service;

import com.example.demo.entity.StockCandle;
import com.example.demo.exception.CandleServiceException;
import com.example.demo.dto.CandleDto;
import com.example.demo.dto.CandleResponse;

import com.example.demo.repository.CandleRepository;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;

import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import org.slf4j.*;

@Service
public class CandleService {

	Logger logger = LoggerFactory.getLogger(CandleService.class);

	@Autowired
	private CandleRepository stockCandleRepository;

	@Cacheable(value = "candles", key = "#symbol + '-' + #timeframe + '-' + #startDate + '-' + #endDate")

	public CandleResponse getCandles(String symbol, String timeframe, String startDate, String endDate) {

		logger.info("Entered getCandles method with symbol={}, timeframe={}, startDate={}, endDate={}", symbol,
				timeframe, startDate, endDate);

		LocalDate tradeDate;

		try {
			tradeDate = LocalDate.parse(startDate);
			logger.info("Parsed startDate={} successfully", startDate);
		} catch (Exception e) {
			logger.error("Invalid date format provided for startDate={}", startDate, e);
			throw new CandleServiceException("Invalid date format. Use yyyy-MM-dd", HttpStatus.BAD_REQUEST);
		}

		logger.info("Fetching data from Cassandra for symbol={} and tradeDate={}", symbol, tradeDate);

		System.out.println("Fetching data from Cassandra...");

		List<StockCandle> data = stockCandleRepository.findByKeySymbolAndKeyTradeDate(symbol, tradeDate);

		if (data.isEmpty()) {
			logger.warn("No candle data found for symbol={} and tradeDate={}", symbol, tradeDate);
			throw new CandleServiceException("No candle data found for symbol : " + symbol, HttpStatus.NOT_FOUND);
		}

		logger.info("Fetched {} records from Cassandra for symbol={}", data.size(), symbol);

		List<CandleDto> candleDtos = new ArrayList<>();

		for (StockCandle candle : data) {

			CandleDto dto = new CandleDto();

			dto.setDatetime(candle.getKey().getCandleTime().toString());
			dto.setOpen(candle.getOpen());
			dto.setHigh(candle.getHigh());
			dto.setLow(candle.getLow());
			dto.setClose(candle.getClose());
			dto.setVolume(candle.getVolume());

			candleDtos.add(dto);
		}

		logger.info("Mapped {} candle records to DTOs", candleDtos.size());

		CandleResponse response = new CandleResponse();

		response.setSymbol(symbol);
		response.setTimeframe(timeframe);
		response.setCandles(candleDtos);
		response.setCount(candleDtos.size());

		logger.info("Successfully built CandleResponse for symbol={}", symbol);

		return response;
	}
}