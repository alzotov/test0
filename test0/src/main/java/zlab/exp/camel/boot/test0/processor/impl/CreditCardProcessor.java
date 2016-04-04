package zlab.exp.camel.boot.test0.processor.impl;
import org.apache.camel.Processor;
import org.springframework.stereotype.Component;

import zlab.exp.camel.boot.test0.model.CardTransactionRz;
import zlab.exp.camel.boot.test0.processor.CardProcessor;

@Component
public class CreditCardProcessor extends CardProcessor implements Processor {

	@Override
	public void processCard(CardTransactionRz trx)  {
	    System.out.println("processing Credit card");
		trx.setCode("0");
	    trx.setDescription("Success");
	}
}
