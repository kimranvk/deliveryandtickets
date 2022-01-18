package org.abudhabi.delivery.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;

import org.abudhabi.delivery.beans.Delivery;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import redis.clients.jedis.Jedis;

@Service
@Scope("singleton") 
public class CollectionService implements ICollectionService {
	private static final Logger logger = LoggerFactory.getLogger(CollectionService.class);
	@Value("${jedis.host}")
	private String host;
	@Value("${jedis.port}")
	private Integer port;
	@Value("${app.key}")
	private String key;

	@PostConstruct
	public void init() {
	   key = key + System.currentTimeMillis();
	}
	
	private Jedis connect() {
		if(logger.isDebugEnabled()) {
			logger.debug("starts connecting....host:{}, port:{}", host, port);
		}
		Jedis jedis = new Jedis(host, port);
		// jedis = new Jedis("localhost", 6379);
		logger.info("Connection to server sucessfully --> host:{}, port:{}", host, port);
		if(logger.isDebugEnabled()) {
			// check whether server is running or not
			logger.debug("Server is running: {}", jedis.ping());
		}
		return jedis;
	}

	private void disconnect(Jedis jedis) {
		if(logger.isDebugEnabled()) {
			logger.debug("disconnecting");
		}
		jedis.disconnect();
		logger.info("disconnected successully");

	}

	@Override
	public void resetAll() {
		Jedis jedis = connect();
		jedis.del(key);
		disconnect(jedis);
	}

	@Override
	public void insertAll(List<Delivery> deliveries, Long expiryInSeconds) {
		Jedis jedis = connect();
		for (Delivery delivery : deliveries) {
			logger.info("Delivery:rpush {}", delivery);
			jedis.rpush(key, delivery.toString());
		}
		// expiry
		if (expiryInSeconds > 0l) {
			jedis.expire(key, expiryInSeconds);
		}
		disconnect(jedis);
	}

	@Override
	public List<String> retrieveAll() {
		Jedis jedis = connect();
		List<String> list = jedis.lrange(key, Long.MIN_VALUE, Long.MAX_VALUE);
		disconnect(jedis);
		return list;
	}

//	public static void main(String o[]) {
//		CollectionService mapRepository = new CollectionService();
//		// SortedSet deliveriesList = new TreeSet();
//		List<Delivery> deliveriesList = new ArrayList<Delivery>();
//		deliveriesList.add(new Delivery(1l, "New", "Order delivered", new Date(), 10l, new Date(), 10l));
//		deliveriesList.add(new Delivery(2l, "VIP", "Order prepared", new Date(), 1l, new Date(), 2l));
//		mapRepository.insertAll(deliveriesList, 10l);
//		System.out.println(mapRepository.retrieveAll());
//
//		// System.out.println("Get All Keys:" +
//		// mapRepository.getAllKeys("tickets_"));
//	}

}
