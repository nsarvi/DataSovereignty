package io.pivotal.pde.datasovereignty;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import io.pivotal.pde.datasovereignty.model.Unrestricted;
import io.pivotal.pde.datasovereignty.service.IGeodeService;


@EnableAutoConfiguration
@SpringBootApplication
public class DataSovereigntyLoad {

	private static final Logger logger = LoggerFactory.getLogger(DataSovereigntyLoad.class);
	private static long minimum=1;
	private static long maximum=100000001;
	private static final Random random = new Random();
	  
	@Autowired
	private IGeodeService geodeService;
	
	public static void main(String[] args) throws Exception {
		logger.info("Starting - loading of OrderDetail to gemfire ");
		ConfigurableApplicationContext ctx =SpringApplication.run(DataSovereigntyLoad.class, args);
		DataSovereigntyLoad thisObj= ctx.getBean(DataSovereigntyLoad.class);
		thisObj.generateAllOrderdetails();     
		logger.info("Completed - loading of OrderDetail to gemfire");
	 }
	
	
	public void generateAllOrderdetails(){
		
		Map<Integer,Unrestricted> orderDetails=new HashMap<Integer,Unrestricted>();
		
		for (int i=1;i<=1000;i++) {
			Unrestricted orderDetail=new Unrestricted();
			//orderDetail.setTransId(nextInteger());
			orderDetail.setOrderNumber(nextInteger());
			orderDetail.setOrderDate(new Date());
			orderDetail.setCustomerNumber(nextInteger());
			orderDetail.setShipDate(new Date());
			orderDetail.setShippingCost(randomFloat());
			orderDetails.put(nextInteger(),orderDetail);
		}
		geodeService.putOrderAll(orderDetails);
	}

	public void generateLoad(){
		
		for (int i=1;i<1000;i++) {
			Unrestricted orderDetail=new Unrestricted();
			orderDetail.setOrderNumber(nextInteger());
			orderDetail.setOrderDate(new Date());
			orderDetail.setCustomerNumber(nextInteger());
			orderDetail.setShipDate(new Date());
			orderDetail.setShippingCost(randomFloat());
			geodeService.putOrder(nextInteger(),orderDetail);
		}
	}
	
	  public Integer nextInteger() {
	        int range = (int) (maximum - minimum) + 1;
	        int min = (int) minimum;
	        int value = random.nextInt(range) + min;
	        return new Integer(value);
	    }

	  public Float randomFloat() {
	        int range = (int) (maximum - minimum) + 1;
	        int min = (int) minimum;
	        float value = random.nextFloat();
	        return new Float(value);
	    }
	  
	  public Date randomDate() {
          Calendar cal = Calendar.getInstance();
          cal.add(Calendar.DAY_OF_YEAR, -1 * random.nextInt(365));
          return cal.getTime();
      }
	  
	  
}

 