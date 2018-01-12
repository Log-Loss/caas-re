package caas.modelconfig.Recurrent;

import caas.modelconfig.Dataset.Dataset;
import caas.modelconfig.Utils.DataConfig;
import caas.modelconfig.dl_lib.lstm.LSTMLayer;
import org.deeplearning4j.nn.api.OptimizationAlgorithm;
import org.deeplearning4j.nn.conf.MultiLayerConfiguration;
import org.deeplearning4j.nn.conf.NeuralNetConfiguration;

public class RnnModel {
    private DataConfig dataConfig;
    private RnnConfig rnnConfig;
    private NeuralNetConfiguration.Builder builder;

    public RnnModel(RnnConfig rnnConfig) {
        this.builder = new NeuralNetConfiguration.Builder();
        this.rnnConfig = rnnConfig;
        this.dataConfig = Dataset.getDataConfig(rnnConfig.dataset);
    }

    private void setHyperParams() {
        builder.learningRate(rnnConfig.globalVariable.lr);
        builder.iterations(rnnConfig.globalVariable.iteration);
        builder.seed(123);
        builder.regularization(true).l2(rnnConfig.globalVariable.l2);
        builder.optimizationAlgo(OptimizationAlgorithm.STOCHASTIC_GRADIENT_DESCENT);
    }


    public MultiLayerConfiguration getConfig() {
        this.setHyperParams();
        return new LSTMLayer(rnnConfig.layers.get(0).buildConfig()).addLSTMLayerAndOutput(builder.list());
    }
}
