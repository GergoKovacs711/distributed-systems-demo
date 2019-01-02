package hu.iit.uni.miskolc.kovacsgergo.distributedsystemsdemo.controller;

import hu.iit.uni.miskolc.kovacsgergo.distributedsystemsdemo.model.RateContainer;
import hu.iit.uni.miskolc.kovacsgergo.distributedsystemsdemo.model.generated.ExchangeRate;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
public class RateController {

    @RequestMapping(value = {"/rates"}, method = {RequestMethod.GET}, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Collection<ExchangeRate> listRates() {
        return RateContainer.getRates();
    }

}
