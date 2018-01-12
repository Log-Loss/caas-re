package caas.modelconfig.Dense;


import caas.modelconfig.Dataset.Dataset;
import caas.modelconfig.Utils.DataConfig;
import caas.modelconfig.dl_lib.core.Dropout;
import caas.modelconfig.dl_lib.core.outputLayer;
import caas.modelconfig.dl_lib.dense.Dense;
import org.deeplearning4j.nn.api.OptimizationAlgorithm;
import org.deeplearning4j.nn.conf.MultiLayerConfiguration;
import org.deeplearning4j.nn.conf.NeuralNetConfiguration;
import org.deeplearning4j.nn.conf.inputs.InputType;

public class DenseModel {

    private DataConfig dataConfig;
    private DenseConfig denseConfig;
    private NeuralNetConfiguration.Builder builder;

    public DenseModel(DenseConfig denseConfig) {
        this.builder = new NeuralNetConfiguration.Builder();
        this.denseConfig = denseConfig;
        this.dataConfig = Dataset.getDataConfig(denseConfig.dataset);
    }

    private void setHyperParams() {
        builder.learningRate(denseConfig.globalVariable.lr);
        builder.iterations(denseConfig.globalVariable.iteration);
        builder.seed(123);
        builder.regularization(true).l2(denseConfig.globalVariable.l2);
        builder.optimizationAlgo(OptimizationAlgorithm.STOCHASTIC_GRADIENT_DESCENT);
    }

    private NeuralNetConfiguration.ListBuilder addLayers() {
        NeuralNetConfiguration.ListBuilder listBuilder = builder.list();
        for (int i = 0; i < denseConfig.layers.size(); i++) {
            switch (denseConfig.layers.get(i).type) {
                case "dense":
                    listBuilder = new Dense(denseConfig.layers.get(i).buildConfig()).addDenseLayer(i, listBuilder);
                    break;
                case "dropout":
                    listBuilder = new Dropout(denseConfig.layers.get(i).buildConfig()).addDropoutLayer(i, listBuilder);
                    break;
                case "output":
                    listBuilder = new outputLayer(denseConfig.layers.get(i).buildConfig()).addOutputLayer(i, listBuilder);
                    break;
            }

        }
        return listBuilder;
    }

    public MultiLayerConfiguration getConfig() {
        this.setHyperParams();
        return this.addLayers()
                .setInputType(InputType.feedForward(dataConfig.getInputDim()))
                .backprop(true)
                .pretrain(false)
                .build();
    }
}
