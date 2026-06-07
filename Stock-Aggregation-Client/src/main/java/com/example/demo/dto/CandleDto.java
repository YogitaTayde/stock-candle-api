package com.example.demo.dto;

import java.math.BigDecimal;
import java.time.Instant;

public class CandleDto {

	private String datetime;

	private BigDecimal open;
	private BigDecimal high;
	private BigDecimal low;
	private BigDecimal close;

	private Long volume;

	public String getDatetime() {
		return datetime;
	}

	public void setDatetime(String datetime) {
		this.datetime = datetime;
	}

	public BigDecimal getOpen() {
		return open;
	}

	public void setOpen(BigDecimal open) {
		this.open = open;
	}

	public BigDecimal getHigh() {
		return high;
	}

	public void setHigh(BigDecimal high) {
		this.high = high;
	}

	public BigDecimal getLow() {
		return low;
	}

	public void setLow(BigDecimal low) {
		this.low = low;
	}

	public BigDecimal getClose() {
		return close;
	}

	public void setClose(BigDecimal close) {
		this.close = close;
	}

	public Long getVolume() {
		return volume;
	}

	public void setVolume(Long volume) {
		this.volume = volume;
	}

	public CandleDto() {
		super();
	}

	public CandleDto(String datetime, BigDecimal open, BigDecimal high, BigDecimal low, BigDecimal close, Long volume) {
		super();
		this.datetime = datetime;
		this.open = open;
		this.high = high;
		this.low = low;
		this.close = close;
		this.volume = volume;
	}

}