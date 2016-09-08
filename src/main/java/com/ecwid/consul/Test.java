package com.ecwid.consul;

import com.ecwid.consul.v1.ConsulRawClient;
import com.ecwid.consul.v1.OperationException;
import com.ecwid.consul.v1.QueryParams;
import com.ecwid.consul.v1.coordinate.CoordinateClient;
import com.ecwid.consul.v1.coordinate.CoordinateConsulClient;

/**
 * @author Vasily Vasilkov (vasily.vasilkov@gmail.com)
 */
public class Test {

	public static void main(String[] args) throws Exception {
		ConsulRawClient rawClient = new ConsulRawClient("localhost", 10000);
		CoordinateClient coordinateClient = new CoordinateConsulClient(rawClient);


		try {
			System.out.println(coordinateClient.getNodes(new QueryParams("us-vir1")));
		} catch (OperationException e) {
			System.out.println(e.getMessage());
		}


	}

}
