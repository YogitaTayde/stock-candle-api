# 📊 Stock Aggregation System (Spring Boot 2.x + Cassandra)

This project is a Stock Aggregation system built using Spring Boot (2.x) and Cassandra. It provides OHLCV (Open, High, Low, Close, Volume) candle data for stocks and exposes REST APIs. A separate client application consumes these APIs using RestTemplate.

---

# 🏗️ Architecture

Client Application → RestTemplate → Stock Aggregation Service → Cassandra Database

---

# ⚙️ Technologies Used

- Java 11
- Spring Boot (2.x)
- Spring Web
- Spring Data Cassandra
- RestTemplate
- SLF4J Logging
- Spring Cache
- Docker (Cassandra)

--

# 🗄️ Cassandra Setup

## 🔹 Keyspace

```sql
CREATE KEYSPACE stock_keyspace
WITH replication = {'class': 'SimpleStrategy', 'replication_factor': '1'};


CREATE TABLE stock_keyspace.stock_candles (
    symbol text,
    trade_date date,
    candle_time timestamp,
    open decimal,
    high decimal,
    low decimal,
    close decimal,
    volume bigint,
    PRIMARY KEY ((symbol, trade_date), candle_time)
)
WITH CLUSTERING ORDER BY (candle_time ASC);


📌 Primary Key Design Explanation
Partition Key: (symbol, trade_date)
Groups all candles of a stock for a specific day
Clustering Key: candle_time
Stores candle data in time-series order (ascending)


🚀 API Endpoints
🔹 Stock Aggregation Service
GET /api/v1/candles


Request Parameters:
- symbol
- timeframe
- start_date
- end_date

🔹 Client Application
GET /client/candles
📦 Sample Response
{
    "symbol": "RELIANCE",
    "timeframe": "20m",
    "candles": [
        {
            "datetime": "2026-06-06T10:15:30Z",
            "open": 2500.5,
            "high": 2520.0,
            "low": 2495.2,
            "close": 2510.75,
            "volume": 1250000
        }
    ],
    "count": 1
}


🧪 How to Run Project
1️⃣ Start Cassandra (Docker)
docker start cassandra-node

2️⃣ Open Cassandra Shell
docker exec -it cassandra-node cqlsh

3️⃣ Run Stock Aggregation Service
mvn spring-boot:run

4️⃣ Run Client Application
mvn spring-boot:run

5️⃣ Test API in Postman
http://localhost:8081/client/candles?symbol=RELIANCE&timeframe=15m&start_date=2026-06-06&end_date=2026-06-06


🧾 Features
Stock candle data storage using Cassandra
REST APIs using Spring Boot
Client-service architecture using RestTemplate
DTO-based response structure
Logging using SLF4J
Caching using Spring Cache
Time-series optimized Cassandra schema


🎯 Output Format

API returns structured JSON:


👨‍💻 Author
Yogita Tayde




