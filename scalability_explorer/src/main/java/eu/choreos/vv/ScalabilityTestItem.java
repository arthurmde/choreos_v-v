package eu.choreos.vv;

/**
 * Interface to implement a scalability test item. Such an item is supposed to be invoked multiple times with it's parameters being increased by a ScalabilityFunction each time.
 * @author paulo
 *
 */
public interface ScalabilityTestItem {
	/**
	 * Performs one step in a scalabilty test, with the given parameters.
	 * @param params a number of numeric values
	 * @return a measurement of interest collected during the test.
	 * @throws Exception
	 */
	public double test(Number... params) throws Exception;
	
}