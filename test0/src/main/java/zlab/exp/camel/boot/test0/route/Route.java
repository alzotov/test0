package zlab.exp.camel.boot.test0.route;

import org.apache.camel.CamelContext;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.dataformat.JsonLibrary;
import org.springframework.stereotype.Component;

import zlab.exp.camel.boot.test0.model.CardType;
import zlab.exp.camel.boot.test0.processor.impl.CreditCardProcessor;
import zlab.exp.camel.boot.test0.processor.impl.DebitCardProcessor;

//Engine of application
@Component
public class Route extends RouteBuilder {

	public Route() {
	}

	public Route(CamelContext context) {
		super(context);
	}

	@Override
	public void configure() throws Exception {
    	
    	errorHandler(deadLetterChannel("mock:error"));
    	
    	from("direct:start")
            .log("card number: ${body.card.cardNumber}")
            .enrich("direct:enrich",new CardTypeAggregation())
            .setHeader("cardType", simple("${body.cardType}"))
            .log("${body}")        	
        	.log("header.cardType: ${header.cardType}")
            .choice()
        		.when(header("cardType").isEqualTo(CardType.DEBIT))
        			.log("Routing to Debit card processor")
        			.to("direct:debit")
        		.when(header("cardType").isEqualTo("CREDIT"))
        			.log("Routing to Credit card processor")
        			.to("direct:credit")
	    		.otherwise()
    				.log("Error: no processor chosen")
    				.to("mock:error")		        			
    		.end();		                ;
    	
        from("direct:enrich")
        	.log("calling BIN service:")
        	.setHeader("cardNumber", simple("${body.card.cardNumber}"))
        	//.setHeader(Exchange.HTTP_METHOD, constant("GET"))
    		.to("restlet:https://binlist.net:443/json/{cardNumber}")
    		.unmarshal().json(JsonLibrary.Jackson)
			//.unmarshal().json(JsonLibrary.Jackson,BINInfo.class)
			.log("BIN service response: ${body}");        
                  	
		from("direct:credit")
			.log("processing Credit")
			.bean(CreditCardProcessor.class)
			.to("mock:result");
		
		from("direct:debit")
			.log("processing Debit")
			.bean(DebitCardProcessor.class)
			.to("mock:result");     

	}

}
