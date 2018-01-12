package caas.train.Dataset;

import org.apache.commons.io.FileUtils;
import org.deeplearning4j.datasets.iterator.impl.CifarDataSetIterator;
import org.deeplearning4j.datasets.iterator.impl.EmnistDataSetIterator;
import org.deeplearning4j.datasets.iterator.impl.IrisDataSetIterator;
import org.deeplearning4j.datasets.iterator.impl.MnistDataSetIterator;
import org.nd4j.linalg.dataset.api.iterator.DataSetIterator;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.Random;


public class BuildInDataset {

    private DatasetConfig datasetConfig;

    public BuildInDataset(String datasetName,int epochs,int batchSize) throws Exception {
        switch (datasetName) {
            case "mnist":
                datasetConfig = new DatasetConfig(epochs,
                        batchSize,
                        new MnistDataSetIterator(batchSize,true,12345),
                        new MnistDataSetIterator(batchSize,false,12345) );
                break;
            case "cifar":
                datasetConfig = new DatasetConfig(epochs,
                        batchSize,
                        new CifarDataSetIterator(batchSize, 50000, true),
                        new CifarDataSetIterator(batchSize, 10000, false));
                break;
            case "iris":
                datasetConfig = new DatasetConfig(epochs,
                        batchSize,
                        new IrisDataSetIterator(batchSize, 100),
                        new IrisDataSetIterator(batchSize, 50));
                break;
            case "eminst-complete":
                datasetConfig = new DatasetConfig(epochs,
                        batchSize,
                        new EmnistDataSetIterator(EmnistDataSetIterator.Set.COMPLETE, batchSize, true),
                        new EmnistDataSetIterator(EmnistDataSetIterator.Set.COMPLETE, batchSize, false));
                break;
            case "eminst-merge":
                datasetConfig = new DatasetConfig(epochs,
                        batchSize,
                        new EmnistDataSetIterator(EmnistDataSetIterator.Set.MERGE, batchSize, true),
                        new EmnistDataSetIterator(EmnistDataSetIterator.Set.MERGE, batchSize, false));
                break;
            case "eminst-balance":
                datasetConfig = new DatasetConfig(epochs,
                        batchSize,
                        new EmnistDataSetIterator(EmnistDataSetIterator.Set.BALANCED, batchSize, true),
                        new EmnistDataSetIterator(EmnistDataSetIterator.Set.BALANCED, batchSize, false));
                break;
            case "eminst-letter":
                datasetConfig = new DatasetConfig(epochs,
                        batchSize,
                        new EmnistDataSetIterator(EmnistDataSetIterator.Set.LETTERS, batchSize, true),
                        new EmnistDataSetIterator(EmnistDataSetIterator.Set.LETTERS, batchSize, false));
                break;
            case "eminst-digits":
                datasetConfig = new DatasetConfig(epochs,
                        batchSize,
                        new EmnistDataSetIterator(EmnistDataSetIterator.Set.DIGITS, batchSize, true),
                        new EmnistDataSetIterator(EmnistDataSetIterator.Set.DIGITS, batchSize, false));
                break;
            case "eminst-mnist":
                datasetConfig = new DatasetConfig(epochs,
                        batchSize,
                        new EmnistDataSetIterator(EmnistDataSetIterator.Set.MNIST, batchSize, true),
                        new EmnistDataSetIterator(EmnistDataSetIterator.Set.MNIST, batchSize, false));
                break;

            case "shakespeare":
                datasetConfig = new DatasetConfig(epochs,
                        batchSize,
                        getShakespeareIterator(batchSize, 1000),
                        getShakespeareIterator(batchSize, 1000));
        }
    }

    public DataSetIterator getTrainIter() {
        return this.datasetConfig.getTrainDataIter();
    }

    public DataSetIterator getTestIter() {
        return this.datasetConfig.getTestDataIter();
    }


    public static CharacterIterator getShakespeareIterator(int miniBatchSize, int sequenceLength) throws Exception{
        //The Complete Works of William Shakespeare
        //5.3MB file in UTF-8 Encoding, ~5.4 million characters
        //https://www.gutenberg.org/ebooks/100
        String url = "https://s3.amazonaws.com/dl4j-distribution/pg100.txt";
        String tempDir = System.getProperty("java.io.tmpdir");
        String fileLocation = tempDir + "/Shakespeare.txt";	//Storage location from downloaded file
        File f = new File(fileLocation);
        if( !f.exists() ){
            FileUtils.copyURLToFile(new URL(url), f);
            System.out.println("File downloaded to " + f.getAbsolutePath());
        } else {
            System.out.println("Using existing text file at " + f.getAbsolutePath());
        }

        if(!f.exists()) throw new IOException("File does not exist: " + fileLocation);	//Download problem?

        char[] validCharacters = CharacterIterator.getMinimalCharacterSet();	//Which characters are allowed? Others will be removed
        return new CharacterIterator(fileLocation, Charset.forName("UTF-8"),
                miniBatchSize, sequenceLength, validCharacters, new Random(12345));
    }
}
