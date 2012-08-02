package eu.choreos.vv.aggregations;

import java.util.List;

public class Mean implements Aggregator {

	@Override
	public Double aggregate(List<Double> series) {
		return DescriptiveStatisticsFactory.create(series).getMean();
	}

}
