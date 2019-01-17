package hu.iit.uni.miskolc.kovacsgergo.distributedsystemsdemo.model;

import hu.iit.uni.miskolc.kovacsgergo.distributedsystemsdemo.exception.classes.EntityNotFoundException;
import hu.iit.uni.miskolc.kovacsgergo.distributedsystemsdemo.model.generated.ExchangeRate;
import hu.iit.uni.miskolc.kovacsgergo.distributedsystemsdemo.model.generated.ObjectFactory;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public final class RateContainer implements Serializable {

    private static List<ExchangeRate> exchangeRateList;

    static {
        String[] codes = loadCodes();
        String[] names = loadNames();
        double[] rates = loadRates();

        exchangeRateList = new ArrayList<>();
        ObjectFactory objectFactory = new ObjectFactory();

        for (short index = 0; index < rates.length; index++) {
            ExchangeRate tempExchangeRate = objectFactory.createExchangeRate();
            tempExchangeRate.setCode(codes[index]);
            tempExchangeRate.setName(names[index]);
            tempExchangeRate.setRate(rates[index]);
            exchangeRateList.add(tempExchangeRate);
        }
    }

    private static String[] loadCodes() {
        return new String[]{"BTC", "BCH", "USD", "EUR", "GBP", "JPY", "CAD", "AUD", "CNY", "CHF", "SEK", "NZD", "KRW",
                "AED", "AFN", "ALL", "AMD", "ANG", "AOA", "ARS", "AWG", "AZN", "BAM", "BBD"};
    }

    private static String[] loadNames() {
        return new String[]{"Bitcoin", "Bitcoin Cash", "US Dollar", "Eurozone Euro", "Pound Sterling", "Japanese Yen",
                "Canadian Dollar", "Australian Dollar", "Chinese Yuan", "Swiss Franc", "Swedish Krona",
                "New Zealand Dollar", "South Korean Won", "UAE Dirham", "Afghan Afghani", "Albanian Lek",
                "Armenian Dram", "Netherlands Antillean Guilder", "Angolan Kwanza", "Argentine Peso", "Aruban Florin",
                "Azerbaijani Manat", "Bosnia-Herzegovina Convertible Mark", "Bangladeshi Taka"};
    }

    private static double[] loadRates() {
        return new double[]{1.0, 23.664345, 3703.47, 3250.6, 2905.742562, 405859.745523, 5024.105181, 5275.593015,
                25474.318395, 3635.881673, 33122.954254, 5520.151656, 4123445.986732, 13603.88598, 279414.193778,
                400271.0376, 1792731.727045, 6576.45537, 1142916.76629, 138946.78746, 6666.231186, 6305.157675,
                6309.216678, 7406.94};
    }


    public static List<ExchangeRate> getRates() {
        return exchangeRateList;
    }

    public static void addRate(ExchangeRate rate){
        exchangeRateList.add(rate);
    }

    public static ArrayList<ExchangeRate> getRate(final String codeName) throws EntityNotFoundException{
        Iterator iterator = exchangeRateList.iterator();
        ExchangeRate tempExchangeRate = null;

        while (iterator.hasNext()){
            tempExchangeRate = (ExchangeRate) iterator.next();

            if(tempExchangeRate.getCode().compareTo(codeName) == 0){

                ArrayList<ExchangeRate> tempExchangeRateList = new ArrayList<ExchangeRate>();
                tempExchangeRateList.add(tempExchangeRate);

                return tempExchangeRateList;
            }
        }
        throw new EntityNotFoundException(ExchangeRate.class, "code", codeName.toString());
    }

    public static ArrayList<ExchangeRate> deleteRate(final String codeName) throws EntityNotFoundException{
        Iterator iterator = exchangeRateList.iterator();
        ExchangeRate tempExchangeRate = null;

        while (iterator.hasNext()){
            tempExchangeRate = (ExchangeRate) iterator.next();

            if(tempExchangeRate.getCode().compareTo(codeName) == 0){
               iterator.remove();

               ArrayList<ExchangeRate> tempExchangeRateList = new ArrayList<ExchangeRate>();
               tempExchangeRateList.add(tempExchangeRate);

               return tempExchangeRateList;
            }
        }
        throw new EntityNotFoundException(ExchangeRate.class, "code", codeName.toString());
    }
}
