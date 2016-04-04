package zlab.exp.camel.boot.test0.route;

import java.util.Map;

import org.apache.camel.Exchange;
import org.apache.camel.processor.aggregate.AggregationStrategy;

import zlab.exp.camel.boot.test0.model.BINInfo;
import zlab.exp.camel.boot.test0.model.CardTransaction;
import zlab.exp.camel.boot.test0.model.CardTransactionRz;
import zlab.exp.camel.boot.test0.model.CardType;

public class CardTypeAggregation implements AggregationStrategy {
	 
    public Exchange aggregate(Exchange original, Exchange resource) {
        Object originalBody = original.getIn().getBody();
        Object resourceResponse = resource.getIn().getBody();
        
        //BINInfo binf = (BINInfo)resourceResponse;
        Map binf = (Map)resourceResponse;
        //System.out.println(binf);
        String cardType = (String)binf.get("card_type");
        CardTransaction trx = (CardTransaction)originalBody;
        CardTransactionRz trxRz = new CardTransactionRz(trx);
        //trxRz.setCardType(CardType.valueOf(binf.getCard_type()));
        trxRz.setCardType(CardType.valueOf(cardType));
        
        if (original.getPattern().isOutCapable()) {
            original.getOut().setBody(trxRz);
        } else {
            original.getIn().setBody(trxRz);
        }
        return original;
    }
     
}