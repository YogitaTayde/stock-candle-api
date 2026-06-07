package com.example.demo.entity;

import java.time.Instant;
import java.time.LocalDate;

import org.springframework.data.cassandra.core.cql.PrimaryKeyType;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyClass;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyColumn;

@PrimaryKeyClass
public class CandlePrimaryKey {

	@PrimaryKeyColumn(name = "symbol", ordinal = 0, type = PrimaryKeyType.PARTITIONED)
	private String symbol;

	@PrimaryKeyColumn(name = "trade_date", ordinal = 1, type = PrimaryKeyType.PARTITIONED)
	private LocalDate tradeDate;

	@PrimaryKeyColumn(name = "candle_time", ordinal = 2, type = PrimaryKeyType.CLUSTERED)
	private Instant candleTime;

	public String getSymbol() {
		return symbol;
	}

	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}

	public LocalDate getTradeDate() {
		return tradeDate;
	}

	public void setTradeDate(LocalDate tradeDate) {
		this.tradeDate = tradeDate;
	}

	public Instant getCandleTime() {
		return candleTime;
	}

	public void setCandleTime(Instant candleTime) {
		this.candleTime = candleTime;
	}

	public CandlePrimaryKey() {
		super();
	}

	public CandlePrimaryKey(String symbol, LocalDate tradeDate, Instant candleTime) {
		super();
		this.symbol = symbol;
		this.tradeDate = tradeDate;
		this.candleTime = candleTime;
	}

}