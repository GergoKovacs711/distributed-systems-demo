package hu.iit.uni.miskolc.kovacsgergo.distributedsystemsdemo.model;


import hu.iit.uni.miskolc.kovacsgergo.distributedsystemsdemo.model.generated.ExchangeRate;

import java.io.Serializable;
import java.util.ArrayList;

public class ExchangeRates implements Serializable {
    private ArrayList<ExchangeRate> exchangeRatesList;

    public ExchangeRates(ArrayList<ExchangeRate> exchangeRatesList) {
        this.exchangeRatesList = exchangeRatesList;
    }

    public ExchangeRate getExchangeRate(int index){
        return exchangeRatesList.get(index);
    }

    public ArrayList<ExchangeRate> getExchangeRates(){
        return (ArrayList<ExchangeRate>) exchangeRatesList.clone();
    }

}

