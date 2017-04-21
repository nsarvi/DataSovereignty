package io.pivotal.pde.datasovereignty.service;

import java.util.Map;

import io.pivotal.pde.datasovereignty.model.Unrestricted;

public interface IGeodeService {

	public Unrestricted putOrder(Integer key,Unrestricted orderDetail);
	
	public void putOrderAll(Map<Integer,Unrestricted> orderDetails);
	
}
