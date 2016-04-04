package zlab.exp.camel.boot.test0.route;

import java.util.Date;
import java.util.GregorianCalendar;

import org.apache.camel.EndpointInject;
import org.apache.camel.Produce;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.mock.MockEndpoint;
import org.apache.camel.test.junit4.CamelTestSupport;
import org.junit.Test;

import zlab.exp.camel.boot.test0.model.Card;
import zlab.exp.camel.boot.test0.model.CardTransaction;
import zlab.exp.camel.boot.test0.model.CardTransactionRz;
import zlab.exp.camel.boot.test0.model.CardType;

public class RouteTest extends CamelTestSupport {
	 
    @Produce(uri = "direct:start")
    protected ProducerTemplate template;
    
	@EndpointInject(uri = "mock:result")
    protected MockEndpoint resultEndpoint;
    
	@EndpointInject(uri = "mock:error")
    protected MockEndpoint errorEndpoint; 

 
    @Override
    public boolean isDumpRouteCoverage() {
        return true;
    }
	
    @Test
	public void DebitTest() throws Exception{
        CardTransaction t = new CardTransaction();
        Card c = new Card();
        c.setName("-");
        c.setExpiry(new Date());
        c.setCvv("-");
        t.setCard(c);
        c.setCardNumber("431940XXXXXXXX");        
        
        CardTransactionRz rz = new CardTransactionRz(t);
        rz.setCardType(CardType.DEBIT);
        rz.setCode("0");
        rz.setDescription("Success");
        
        resultEndpoint.expectedBodiesReceived(rz);        
        template.sendBody(t);
        resultEndpoint.assertIsSatisfied();
	}
	@Test
	public void CreditTest() throws Exception{
        CardTransaction t = new CardTransaction();
        Card c = new Card();
        c.setName("ALEXEY ZOTOV");
        c.setExpiry(new GregorianCalendar(1972,9,5).getTime());
        c.setCvv("***");
        t.setCard(c);
        c.setCardNumber("540788");
        
        CardTransactionRz rz = new CardTransactionRz(t);
        rz.setCardType(CardType.CREDIT);
        rz.setCode("0");     
        rz.setDescription("Success");
        
        resultEndpoint.expectedBodiesReceived(rz);        
        template.sendBody(t);
        resultEndpoint.assertIsSatisfied();
	}
	@Test
	public void ErrorTest() throws Exception{
        CardTransaction t = new CardTransaction();
        Card c = new Card();
        t.setCard(c);
        c.setCardNumber("000000");

        CardTransactionRz rz = new CardTransactionRz(t);
        rz.setCardType(CardType.CREDIT);
        rz.setCode("0");     
        rz.setDescription("Success");
        
        //errorEndpoint.expectedBodiesReceived(rz);        
        errorEndpoint.expectedMessageCount(2);
        template.sendBody(t);
        errorEndpoint.assertIsSatisfied();

	}	    
    
    @Override
    protected RouteBuilder createRouteBuilder() {
    	return new Route();
    }
}
