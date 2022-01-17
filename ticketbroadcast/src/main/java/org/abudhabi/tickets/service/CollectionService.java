package org.abudhabi.tickets.service;

import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;

import redis.clients.jedis.Jedis;

@Service
public class CollectionService {
	private static final Logger logger = LoggerFactory.getLogger(CollectionService.class);
	@Value("${jedis.host}")
	private String host;
	@Value("${jedis.port}")
	private Integer port;
	@Value("${app.key.pattern}")
	private String key;

	private Jedis connect() {
		if(logger.isDebugEnabled()) {
			logger.debug("starts connecting....host:{}, port:{}", host, port);
		}

		Jedis jedis = new Jedis(host, port);
		// jedis = new Jedis("localhost", 6379);
		
		logger.info("server [host:{}, port:{}] is connected sucessfully",host, port);
		if(logger.isDebugEnabled()) {
				
			// check whether server is running or not
			logger.debug("Server is running: {}", jedis.ping());
		}
		return jedis;
	}

	private void disconnect(Jedis jedis) {
		if(logger.isDebugEnabled()) {
			logger.debug("disconnecting ...");
		}
		
		jedis.disconnect();
		logger.info("disconnected successully");

	}

	public List<String> retrieveAll(String key) {
		Jedis jedis = connect();
		List<String> list = jedis.lrange(key, Long.MIN_VALUE, Long.MAX_VALUE);
		
		
		disconnect(jedis);
		return list;
	}

	public Set<String> getAllKeys(String pattern) {
		Jedis jedis = connect();
		Set<String> keys = jedis.keys(pattern);
		disconnect(jedis);
		return keys;
	}
	
	public Set<String> getAll() {
		Set<String> keys = getAllKeys(key);
		SortedSet sortedTickets = new TreeSet(new Comparator<String>() {

			@Override
			public int compare(String arg0, String arg1) {
				// TODO Auto-generated method stub
			
				if( arg0.contains("High") && !arg1.contains("High")) {
					return -1;
				}
				
				return 1;
				
			}});
		
		for (String key:  keys) {
			sortedTickets.addAll(retrieveAll(key));
		}
		
		return sortedTickets;
	}
}
