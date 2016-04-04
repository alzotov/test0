package zlab.exp.camel.boot.test0.processor;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;

import zlab.exp.camel.boot.test0.model.CardTransactionRz;

public abstract class CardProcessor {

	public CardProcessor() {
		super();
	}

	public void process(Exchange exchange) throws Exception {
	    System.out.println(exchange);
	    Object body = exchange.getIn().getBody();
	    CardTransactionRz trx = (CardTransactionRz)body;
	    processCard(trx);
	  }

	abstract public void processCard(CardTransactionRz trx);

}