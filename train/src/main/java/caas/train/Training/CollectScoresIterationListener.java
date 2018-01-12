//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package caas.train.Training;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.deeplearning4j.nn.api.Model;
import org.deeplearning4j.optimize.api.IterationListener;
import org.nd4j.linalg.primitives.Pair;

public class CollectScoresIterationListener implements IterationListener {
    private int frequency;
    private int iterationCount;
    private List<Pair<Integer, Double>> scoreVsIter;

    public CollectScoresIterationListener() {
        this(1);
    }

    public CollectScoresIterationListener(int frequency) {
        this.iterationCount = 0;
        this.scoreVsIter = new ArrayList();
        if (frequency <= 0) {
            frequency = 1;
        }

        this.frequency = frequency;
    }

    public boolean invoked() {
        return false;
    }

    public void invoke() {
    }

    public void iterationDone(Model model, int iteration) {
        if (++this.iterationCount % this.frequency == 0) {
            double score = model.score();
            this.scoreVsIter.add(new Pair(this.iterationCount, score));
        }

    }

    public List<Pair<Integer, Double>> getScoreVsIter() {
        return this.scoreVsIter;
    }

    public void exportScores(OutputStream outputStream) throws IOException {
        this.exportScores(outputStream, "\t");
    }

    public void exportScores(OutputStream outputStream, String delimiter) throws IOException {
        StringBuilder sb = new StringBuilder();
        sb.append("Iteration").append(delimiter).append("Score");
        Iterator var4 = this.scoreVsIter.iterator();

        while(var4.hasNext()) {
            Pair<Integer, Double> p = (Pair)var4.next();
            sb.append("\n").append(p.getFirst()).append(delimiter).append(p.getSecond());
        }

        outputStream.write(sb.toString().getBytes("UTF-8"));
    }

    public void exportScores(File file) throws IOException {
        this.exportScores(file, "\t");
    }

    public void exportScores(File file, String delimiter) throws IOException {
        FileOutputStream fos = new FileOutputStream(file);
        Throwable var4 = null;

        try {
            this.exportScores((OutputStream)fos, delimiter);
        } catch (Throwable var13) {
            var4 = var13;
            throw var13;
        } finally {
            if (fos != null) {
                if (var4 != null) {
                    try {
                        fos.close();
                    } catch (Throwable var12) {
                        var4.addSuppressed(var12);
                    }
                } else {
                    fos.close();
                }
            }

        }

    }
}
