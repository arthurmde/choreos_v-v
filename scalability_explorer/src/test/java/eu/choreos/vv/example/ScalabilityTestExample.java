package eu.choreos.vv.example;

import java.util.ArrayList;
import java.util.List;

import eu.choreos.vv.ScalabilityTestingModel;

public class ScalabilityTestExample extends ScalabilityTestingModel {

	List<Integer> resources;
	long sleepTime;
	int resourceIndex;
	
	@Override
	public void resourceScaling(int resourceQuantity) {
		resources.add(resourceQuantity * 5);
	}

	@Override
	public void setUp() {
		resources = new ArrayList<Integer>();
	}

	@Override
	public void beforeTest() {
		sleepTime = resources.get(resourceIndex);
		resourceIndex = resourceIndex < resources.size()-1? resourceIndex+1 : 0; 
	}

	@Override
	public void test() {
		try {
			Thread.sleep(sleepTime);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void tearDown() {}
	
	public static void main(String[] args) {
		ScalabilityTestExample example = new ScalabilityTestExample();
		example.run(5, 1000, 10, 20, 1);
	}

}