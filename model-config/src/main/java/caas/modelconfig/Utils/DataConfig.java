package caas.modelconfig.Utils;

import org.deeplearning4j.nn.conf.inputs.InputType;

public class DataConfig {
    // the input dimension to a network
    private int inputDim;
    private int outClasses;

    // only support 3 types for now, TENSOR, IMAGE, SEQUENCE
    private String type;

    // IMAGE
    private int height;
    private int width;
    private int channels;

    // SEQUENCE
    private int sequenceLength;

    public DataConfig() { }

    public DataConfig(int inputDim, int outClasses) {
        this.inputDim = inputDim;
        this.outClasses = outClasses;
    }

    public void setTensorType() {
        this.type = "TENSOR";
    }

    public void setImageType(int height, int width, int channels) {
        this.type = "IMAGE";
        this.height = height;
        this.width = width;
        this.channels = channels;
    }

    public void setSequenceType(int sequenceLength) {
        this.type = "SEQUENCE";
        this.sequenceLength = sequenceLength;
    }

    public int getInputDim() {
        return inputDim;
    }


    public InputType getImageInputType() {
        if (this.channels == 1) {
            return InputType.convolutionalFlat(height, width, 1);
        } else {
            return InputType.convolutional(height, width, channels);
        }
    }

    public int getSequenceLength() {
        return sequenceLength;
    }
}
