package com.example.demo.ingestion;

import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.boot.CommandLineRunner;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import com.example.demo.entity.CandlePrimaryKey;
import com.example.demo.entity.StockCandle;
import com.example.demo.repository.CandleRepository;
import com.opencsv.CSVReader;
import java.time.ZoneOffset;

@Component

public class CsvLoader implements CommandLineRunner {

	private final CandleRepository repository;

	public CsvLoader(CandleRepository repository) {
		super();
		this.repository = repository;
	}

	@Override
	public void run(String... args) throws Exception {

		ClassPathResource resource = new ClassPathResource("stock_data.csv");

		CSVReader reader = new CSVReader(new InputStreamReader(resource.getInputStream()));

		reader.readNext();

		String[] row;

		while ((row = reader.readNext()) != null) {

			String symbol = row[0];

			LocalDateTime dateTime = LocalDateTime.parse(row[1], DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));

			CandlePrimaryKey key = new CandlePrimaryKey();

			key.setSymbol(symbol);
			key.setTradeDate(dateTime.toLocalDate());
			key.setCandleTime(dateTime.toInstant(ZoneOffset.UTC));

			StockCandle candle = new StockCandle();
			candle.setKey(key);

			candle.setOpen(new BigDecimal(row[2]));
			candle.setHigh(new BigDecimal(row[3]));
			candle.setLow(new BigDecimal(row[4]));
			candle.setClose(new BigDecimal(row[5]));
			candle.setVolume(Long.valueOf(row[6]));

			repository.save(candle);
		}
	}
}