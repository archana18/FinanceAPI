package com.arch.stock.stockservice.resource;

import yahoofinance.Stock;
import yahoofinance.YahooFinance;
import yahoofinance.quotes.stock.StockQuote;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/rest/stock")
public class StockResource {
	
	
	@Autowired
	RestTemplate rt;
	
	


	@GetMapping("/{username}")
	public List<Stock> getStock(@PathVariable("username") final String userName) {

        ResponseEntity<List<String>> quoteResponse = rt.exchange("http://localhost:8300/rest/db/" + userName, HttpMethod.GET,
                null, new ParameterizedTypeReference<List<String>>() {
                });


        List<String> quotes = quoteResponse.getBody();
        return quotes
                .stream()
                .map(this::getStockPrice)
                .collect(Collectors.toList());
    }
	
	
	private Stock getStockPrice(String q)
	{
		try {
			return YahooFinance.get(q);
		}catch(IOException e)
		{
			e.printStackTrace();
			return new Stock(q);
		}
	}

}
