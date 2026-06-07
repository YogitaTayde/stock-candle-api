package com.example.demo.controller;

import com.example.demo.dto.CandleResponse;
import com.example.demo.service.CandleService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/candles")
public class CandleController {

	private final CandleService service;

	public CandleController(CandleService service) {
		this.service = service;
	}

	@GetMapping()

	public ResponseEntity<CandleResponse> getCandles(@RequestParam String symbol, @RequestParam String timeframe,
			@RequestParam String start_date, @RequestParam String end_date) {

		CandleResponse response = service.getCandles(symbol, timeframe, start_date, end_date);

		return new ResponseEntity<>(response, HttpStatus.OK);
	}

}