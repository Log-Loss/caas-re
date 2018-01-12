package caas.modelconfig.Dataset;


import caas.modelconfig.Utils.DataConfig;

public class Dataset {

    private DataConfig MNISTConfig = new DataConfig(28*28, 10);
    private DataConfig IrisConfig = new DataConfig(4, 3);
    private DataConfig CifarConfig = new DataConfig(32*32*3, 10);

    private DataConfig EMNIST_COMPLETEConfig = new DataConfig(28*28, 62);
    private DataConfig EMNIST_MERGEConfig = new DataConfig(28*28, 47);
    private DataConfig EMNIST_BALANCEDConfig = new DataConfig(28*28, 47);
    private DataConfig EMNIST_LETTERConfig = new DataConfig(28*28, 26);
    private DataConfig EMNIST_DIGITSConfig = new DataConfig(28*28, 10);
    private DataConfig EMNIST_MNISTConfig = new DataConfig(28*28, 10);

    private DataConfig ShakespeareConfig = new DataConfig(77, 77);

    private Dataset() {
        MNISTConfig.setImageType(28, 28, 1);
        IrisConfig.setTensorType();
        CifarConfig.setImageType(32, 32, 3);

        EMNIST_COMPLETEConfig.setImageType(28, 28, 1);
        EMNIST_MERGEConfig.setImageType(28, 28, 1);
        EMNIST_BALANCEDConfig.setImageType(28, 28, 1);
        EMNIST_LETTERConfig.setImageType(28, 28, 1);
        EMNIST_DIGITSConfig.setImageType(28, 28, 1);
        EMNIST_MNISTConfig.setImageType(28, 28, 1);

        ShakespeareConfig.setSequenceType(77);
    }

    public static DataConfig getDataConfig(String dataset) {
        switch (dataset) {
            case "minst":
                return new Dataset().MNISTConfig;
            case "iris":
                return new Dataset().IrisConfig;
            case "cifar":
                return new Dataset().CifarConfig;

            case "eminst-complete":
                return new Dataset().EMNIST_COMPLETEConfig;
            case "eminst-merge":
                return new Dataset().EMNIST_MERGEConfig;
            case "eminst-balance":
                return new Dataset().EMNIST_BALANCEDConfig;
            case "eminst-letter":
                return new Dataset().EMNIST_LETTERConfig;
            case "eminst-digits":
                return new Dataset().EMNIST_DIGITSConfig;
            case "eminst-mnist":
                return new Dataset().EMNIST_MNISTConfig;

            case "shakespeare":
                return new Dataset().ShakespeareConfig;
        }
        return new DataConfig();
    }
}
