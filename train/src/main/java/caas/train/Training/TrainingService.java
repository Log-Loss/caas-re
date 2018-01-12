package caas.train.Training;

import caas.train.Dataset.BuildInDataset;
import caas.train.util.RestCall;
import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONObject;
import org.deeplearning4j.eval.Evaluation;
import org.deeplearning4j.nn.conf.MultiLayerConfiguration;
import org.deeplearning4j.nn.multilayer.MultiLayerNetwork;
import org.nd4j.linalg.dataset.api.iterator.DataSetIterator;
import org.nd4j.linalg.primitives.Pair;
import org.reflections.serializers.JsonSerializer;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.*;

@Service
public class TrainingService {

    public Object trainModel(Integer jobId, String conf, String datasetName, Integer epochs, Integer batchSize) throws Exception {
        int nEpochs = epochs;

        MultiLayerConfiguration configuration = MultiLayerConfiguration.fromJson(conf);

        MultiLayerNetwork model = new MultiLayerNetwork(configuration);

        BuildInDataset dataset = new BuildInDataset(datasetName, epochs, batchSize);
        DataSetIterator trainIter = dataset.getTrainIter();
        DataSetIterator testIter = dataset.getTestIter();

        model.init();
        System.out.println("Train model....");

        CollectScoresIterationListener listener = new CollectScoresIterationListener(1);

        model.setListeners(listener);
        Object result = null;
        Evaluation eval = model.evaluate(testIter);
        for (int i = 0; i < nEpochs; i++) {
            updateJob(jobId, listener, null, null);

            model.fit(trainIter);

            updateJob(jobId, listener, null, null);

            System.out.println("Completed epoch " + i);

            System.out.println("Evaluate model....");
            result = eval.accuracy();
            System.out.println(eval.accuracy());
            testIter.reset();
        }

        model.predict(testIter.next().getFeatureMatrix());
        System.out.println("**************** finished ********************");
        updateJob(jobId, listener, eval.accuracy(), new Date());
        return result;
    }

    public static void updateJob(Integer jobId, CollectScoresIterationListener listener, Double accuracy, Date finishTime) throws IOException {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        listener.exportScores(outputStream);
        List<Map<String, Number>> state = new ArrayList<>();
        for (Pair<Integer, Double> score : listener.getScoreVsIter()) {
            Map<String, Number> map = new HashMap<>();
            map.put("iteration", score.getFirst());
            map.put("score", score.getRight());
            state.add(map);
        }
        JSONArray stateJson = new JSONArray(state);
        RestCall.jobUpdate(jobId, stateJson.toString(), accuracy, finishTime, null);
    }
}
