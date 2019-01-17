package hu.iit.uni.miskolc.kovacsgergo.distributedsystemsdemo.controller;

import hu.iit.uni.miskolc.kovacsgergo.distributedsystemsdemo.exception.classes.EntityNotFoundException;
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

@RestController
public class RateController {
    private static final String GET_ALL_RATE = "/rates/all";
    private static final String GET_RATE = "/rates/single/{code}";
    private static final String CREATE_RATE = "/rates/create";
    private static final String DELETE_RATE = "/rates/delete/{code}";
    private static final String TEST_RATE = "/rates/test";

    private static final Logger logger = LoggerFactory.getLogger(RateController.class);
    private static int test_id = 0;

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

        tempExchangeRate.setCode("test_code_" +
                "" + test_id);
        tempExchangeRate.setName("test_name_" + test_id);
        tempExchangeRate.setRate(69.69 + test_id);
        test_id++;

        RateContainer.addRate(tempExchangeRate);

        ArrayList<ExchangeRate> exchangeRates = new ArrayList<>();
        exchangeRates.add(tempExchangeRate);

        logger.info("End getTestRate()");
        return exchangeRates;
    }

    @RequestMapping(value = GET_RATE, method = RequestMethod.GET)
    @ResponseBody
    public ArrayList<ExchangeRate> getRate(@PathVariable @NotNull String code) throws EntityNotFoundException {
        logger.info("Start getRate()");

        ArrayList<ExchangeRate> exchangeRates = RateContainer.getRate(code);

        if (exchangeRates == null)
            logger.info("response is null");

        logger.info("End getRate()");
        return exchangeRates;
    }

    @RequestMapping(value = DELETE_RATE, method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ArrayList<ExchangeRate> deleteRate(@PathVariable @NotNull String code) throws EntityNotFoundException {
        logger.info("Start deleteRate()");
        ArrayList<ExchangeRate> exchangeRate = RateContainer.deleteRate(code);
        logger.info("End deleteRate()");
        return exchangeRate;
    }


    @RequestMapping(value = CREATE_RATE, method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public void createRate(@RequestBody ExchangeRate rate) {
        logger.info("Start createRate()");

        if (rate != null) {
            RateContainer.addRate(rate);
        } else {
            logger.info("JSON is null!");
        }
        logger.info("End createRate()");
    }


}
