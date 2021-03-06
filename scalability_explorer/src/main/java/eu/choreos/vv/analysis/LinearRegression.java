package eu.choreos.vv.analysis;

import org.apache.commons.math.stat.inference.TestUtils;
import org.apache.commons.math.stat.regression.GLSMultipleLinearRegression;

import eu.choreos.vv.aggregations.AggregationFunction;
import eu.choreos.vv.data.LinearRegressionSample;
import eu.choreos.vv.data.ExperimentReport;

public class LinearRegression extends Analyzer {

	private AggregationFunction function;

	public LinearRegression(AggregationFunction function) {
		this.function = function;
	}

	@Override
	public void analyse(ExperimentReport report) throws Exception {
		GLSMultipleLinearRegression regression = new GLSMultipleLinearRegression();
		System.out.println(report.getName());
		LinearRegressionSample sample = new LinearRegressionSample(function);
		sample.setSample(report);
		regression.newSampleData(sample.getY(), sample.getX(),
				sample.getOmega());

		double[] beta = regression.estimateRegressionParameters();

		for (int i = 0; i < sample.getX().length; i++) {
			double[] observed = new double[sample.getX()[0].length];
			for (int j = 0; j < observed.length; j++) {
				observed[i] = sample.getX()[j][i];
			}
			if (TestUtils.tTest(0, observed, 0.05)) {
				System.out.print(beta[i + 1]
						+ report.getParameterLabels().get(i) + " + ");
			}
			System.out.println(beta[0]);
		}

	}

}
