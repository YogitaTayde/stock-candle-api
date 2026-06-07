package com.example.demo.dto;

import java.util.List;

public class CandleResponse {

	private String symbol;

	private String timeframe;

	private List<CandleDto> candles;

	private Integer count;

	public String getSymbol() {
		return symbol;
	}

	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}

	public String getTimeframe() {
		return timeframe;
	}

	public void setTimeframe(String timeframe) {
		this.timeframe = timeframe;
	}

	public List<CandleDto> getCandles() {
		return candles;
	}

	public void setCandles(List<CandleDto> candles) {
		this.candles = candles;
	}

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

	public CandleResponse() {
		super();
	}

	public CandleResponse(String symbol, String timeframe, List<CandleDto> candles, Integer count) {
		super();
		this.symbol = symbol;
		this.timeframe = timeframe;
		this.candles = candles;
		this.count = count;
	}

}