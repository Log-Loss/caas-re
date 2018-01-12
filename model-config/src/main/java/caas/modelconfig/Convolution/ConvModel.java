package caas.modelconfig.Convolution;

import caas.modelconfig.Dataset.Dataset;
import caas.modelconfig.Utils.DataConfig;
import caas.modelconfig.dl_lib.cnn.Convolution;
import caas.modelconfig.dl_lib.core.Dropout;
import caas.modelconfig.dl_lib.core.Pooling;
import caas.modelconfig.dl_lib.core.outputLayer;
import caas.modelconfig.dl_lib.dense.Dense;
import org.deeplearning4j.nn.api.OptimizationAlgorithm;
import org.deeplearning4j.nn.conf.MultiLayerConfiguration;
import org.deeplearning4j.nn.conf.NeuralNetConfiguration;

public class ConvModel {

    private DataConfig dataConfig;
    private ConvConfig convConfig;
    private NeuralNetConfiguration.Builder builder;

    public ConvModel(ConvConfig convConfig) {
        this.builder = new NeuralNetConfiguration.Builder();
        this.convConfig = convConfig;
        this.dataConfig = Dataset.getDataConfig(convConfig.dataset);
    }

    private void setHyperParams() {
        builder.learningRate(convConfig.globalVariable.lr);
        builder.iterations(convConfig.globalVariable.iteration);
        builder.seed(123);
        builder.regularization(true).l2(convConfig.globalVariable.l2);
        builder.optimizationAlgo(OptimizationAlgorithm.STOCHASTIC_GRADIENT_DESCENT);
    }

    private NeuralNetConfiguration.ListBuilder addLayers() {
        NeuralNetConfiguration.ListBuilder listBuilder = builder.list();
        for (int i = 0; i < convConfig.layers.size(); i++) {
            switch (convConfig.layers.get(i).type) {
                case "convolution":
                    listBuilder = new Convolution(convConfig.layers.get(i).buildConfig()).addConvLayer(i, listBuilder);
                    break;
                case "dense":
                    listBuilder = new Dense(convConfig.layers.get(i).buildConfig()).addDenseLayer(i, listBuilder);
                    break;
                case "dropout":
                    listBuilder = new Dropout(convConfig.layers.get(i).buildConfig()).addDropoutLayer(i, listBuilder);
                    break;
                case "pooling":
                    listBuilder = new Pooling(convConfig.layers.get(i).buildConfig()).addPoolingLayer(i, listBuilder);
                    break;
                case "output":
                    listBuilder = new outputLayer(convConfig.layers.get(i).buildConfig()).addOutputLayer(i, listBuilder);
                    break;
            }

        }
        return listBuilder;
    }

    public MultiLayerConfiguration getConfig() {
        this.setHyperParams();
        return this.addLayers()
                .setInputType(dataConfig.getImageInputType())
                .backprop(true)
                .pretrain(false)
                .build();

    }
}
