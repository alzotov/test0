/**
 *  Copyright 2005-2014 Red Hat, Inc.
 *
 *  Red Hat licenses this file to you under the Apache License, version
 *  2.0 (the "License"); you may not use this file except in compliance
 *  with the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or
 *  implied.  See the License for the specific language governing
 *  permissions and limitations under the License.
 */
package zlab.exp.camel.boot.test0;

import java.util.Date;
import java.util.HashMap;

import org.apache.camel.Exchange;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.ContextScanDefinition;
import org.apache.camel.model.dataformat.JsonLibrary;
import org.apache.camel.spring.CamelContextFactoryBean;
import org.apache.camel.spring.SpringCamelContext;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import zlab.exp.camel.boot.test0.model.BINInfo;
import zlab.exp.camel.boot.test0.model.Card;
import zlab.exp.camel.boot.test0.model.CardTransaction;
import zlab.exp.camel.boot.test0.model.CardTransactionRz;
import zlab.exp.camel.boot.test0.model.CardType;
import zlab.exp.camel.boot.test0.processor.impl.CreditCardProcessor;
import zlab.exp.camel.boot.test0.processor.impl.DebitCardProcessor;

@Configuration
@ComponentScan
@EnableAutoConfiguration
public class CamelConfiguration {
	
    public static void main(String[] args) {
    	try (ConfigurableApplicationContext context = new SpringApplicationBuilder(CamelConfiguration.class) 
        		.showBanner(false)
                .web(false)
                .run(args))
	    {        
	    	ProducerTemplate producer = context.getBean(SpringCamelContext.class).createProducerTemplate();
	        
	        CardTransaction t = new CardTransaction();
	        Card c = new Card();
	        c.setName("-");
	        c.setExpiry(new Date());
	        c.setCvv("-");
	        t.setCard(c);
	        c.setCardNumber("431940XXXXXXXX");
	    	
	        producer.sendBody("direct:start", t);
	    }
    }
    
    @Bean
    public CamelContextFactoryBean camelContextFactoryBean(
            ApplicationContext applicationContext) {
        CamelContextFactoryBean ccfb = new CamelContextFactoryBean();
        ccfb.setApplicationContext(applicationContext);
        ccfb.setId("camelContext");
        ccfb.setContextScan(new ContextScanDefinition());
        return ccfb;
    }

}