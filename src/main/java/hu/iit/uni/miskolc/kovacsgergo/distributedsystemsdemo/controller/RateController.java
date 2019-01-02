package hu.iit.uni.miskolc.kovacsgergo.distributedsystemsdemo.controller;

import hu.iit.uni.miskolc.kovacsgergo.distributedsystemsdemo.model.RateContainer;
import hu.iit.uni.miskolc.kovacsgergo.distributedsystemsdemo.model.generated.ExchangeRate;
import hu.iit.uni.miskolc.kovacsgergo.distributedsystemsdemo.model.generated.ObjectFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@RestController
public class RateController {
    private static final String GET_ALL_RATE = "/rates/all";
    private static final String GET_RATE = "/rates/single/{code}";
    private static final String CREATE_RATE = "/rates/create";
    private static final String DELETE_RATE = "/rates/delete/{code}";
    private static final String TEST_RATE = "/rates/test";

    //private static Map<Integer, ExchangeRate> exchangeData = new HashMap<Integer, ExchangeRate>();

    private static final Logger logger = LoggerFactory.getLogger(RateController.class);

    @RequestMapping(value = GET_ALL_RATE, method = {RequestMethod.GET}, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Collection<ExchangeRate> listRates() {
        return RateContainer.getRates();
    }

    @RequestMapping(value = TEST_RATE, method = RequestMethod.GET)
    @ResponseBody
    public Collection<ExchangeRate> getTestRate() {
        logger.info("Start getTestRate()");

        ObjectFactory objectFactory = new ObjectFactory();
        ExchangeRate tempExchangeRate = objectFactory.createExchangeRate();

        tempExchangeRate.setCode("test_code");
        tempExchangeRate.setName("test_name");
        tempExchangeRate.setRate(69.69);

        RateContainer.addRate(tempExchangeRate);

        ArrayList<ExchangeRate> exchangeRates = new ArrayList<>();
        exchangeRates.add(tempExchangeRate);

                return exchangeRates;
    }

    @RequestMapping(value = GET_RATE, method = RequestMethod.GET)
    @ResponseBody
    public ExchangeRate getRate(@PathVariable @NotNull String code) {
        logger.info("Start getRate()");

        ExchangeRate exchangeRate = RateContainer.getRate(code.toUpperCase());

        return exchangeRate;
    }

}
