package controllers;

import avro.Companies;
import avro.Price;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import repositories.CompanyRepository;
import repositories.PriceRepository;

import java.util.List;

@RestController
public class ExchangeController {
    private final RestTemplate restTemplate;
    private final CompanyRepository companyRepository;
    private final PriceRepository priceRepository;

    @Autowired
    public ExchangeController(RestTemplate restTemplate, CompanyRepository companyRepository, PriceRepository priceRepository) {
        this.restTemplate = restTemplate;
        this.companyRepository = companyRepository;
        this.priceRepository = priceRepository;
    }

    @RequestMapping(path = "/price", method = RequestMethod.GET)
    public List<Price> pricesHistoryFor(@RequestParam("ticker") String ticker) {
        return priceRepository.findAllByTicker(ticker);
    }


    @RequestMapping(path = "/companies")
    public Iterable<Companies> companies() {
        return companyRepository.findAll();
    }

    @RequestMapping(path = "/save")
    public Companies save(@RequestParam("ticker") String ticker) {
        Companies co = companyRepository.findBySymbol(ticker);
        if (co != null)
            return co;
        String url = String.format("https://api.iextrading.com/1.0/stock/%s/company", ticker.toUpperCase());
        ResponseEntity<Companies> rhi = restTemplate.getForEntity(url, Companies.class);
        co = rhi.getBody();
        if (co != null)
            companyRepository.save(co);
        return co;
    }

}
