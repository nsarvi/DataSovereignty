package io.pivotal.pde.datasovereignty.service;



import java.util.Map;

import org.apache.geode.cache.Region;
import org.apache.geode.cache.client.ClientCache;
import org.apache.geode.cache.client.ClientCacheFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import io.pivotal.pde.datasovereignty.model.Unrestricted;

@Service
public class GeodeServiceImpl implements IGeodeService {
	
	private static final Logger logger = LoggerFactory.getLogger(GeodeServiceImpl.class);
	
	public static ClientCache cache=null;
	
	private static Region<Integer, Unrestricted> region=null;
	
	public GeodeServiceImpl(){
		// Create a client cache
		logger.info("Loading the geode client cache file");
	    cache = new ClientCacheFactory()
	      .set("cache-xml-file", "client/geode-client-cache.xml")
	      .setPdxReadSerialized(true)
	      .create();
	    region=cache.getRegion("unrestricted");	
	}



	public Unrestricted putOrder(Integer key, Unrestricted orderDetail) {
		logger.debug("Put :"+key+" into region :orderDetail");
		return region.put(key, orderDetail);
	}

	public void putOrderAll(Map<Integer,Unrestricted> orderDetails) {
		region.putAll(orderDetails);
	}

	
}
