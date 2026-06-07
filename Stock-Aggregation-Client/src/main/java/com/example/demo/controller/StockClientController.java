package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.CandleResponse;
import com.example.demo.service.StockClientService;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/client")
public class StockClientController {

	@Autowired
	private StockClientService stockClientService;

	@GetMapping("/candles")
	public CandleResponse getCandles(@RequestParam String symbol, @RequestParam String timeframe,
			@RequestParam("start_date") String startDate, @RequestParam("end_date") String endDate) {

		return stockClientService.getCandles(symbol, timeframe, startDate, endDate);
	}
}
