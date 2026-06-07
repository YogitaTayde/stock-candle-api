package com.example.demo.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.cassandra.repository.CassandraRepository;

import com.example.demo.entity.CandlePrimaryKey;
import com.example.demo.entity.StockCandle;

public interface CandleRepository extends CassandraRepository<StockCandle, CandlePrimaryKey> {

	List<StockCandle> findByKeySymbolAndKeyTradeDate(String symbol, LocalDate tradeDate);
}