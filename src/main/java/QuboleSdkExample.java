import com.qubole.qds.sdk.java.client.DefaultQdsConfiguration;
import com.qubole.qds.sdk.java.client.QdsClient;
import com.qubole.qds.sdk.java.client.QdsClientFactory;
import com.qubole.qds.sdk.java.client.QdsConfiguration;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;


public class QuboleSdkExample {
    public static final Properties properties = new Properties();
    public static final String COMMAND_LINE = "/usr/lib/spark/bin/spark-submit --jars 's3://twi-analytics-sandbox/prep-buddy/jar-files/javallier7.jar' --class org.apache.prepbuddy.EncryptionMain 's3://twi-analytics-sandbox/prep-buddy/jar-files/prep-buddy-1.0-Encryption.jar' 's3://twi-analytics-sandbox/prep-buddy/data/calls.csv' 2 --num-executors 4 --executor-cores 8 --executor-memory 5120M";

    public static void main(String[] args) throws IOException {
        properties.load(new FileInputStream("dev.properties"));
        final String QUBOLE_API_KEY = properties.getProperty("QUBOLE_API_KEY");
        QdsConfiguration configuration = new DefaultQdsConfiguration(QUBOLE_API_KEY);
        QdsClient client = QdsClientFactory.newClient(configuration);
        client.command().spark().clusterLabel("prep-buddy-cluster-1")
                .name("my-query-id")
                .cmdLine(COMMAND_LINE).invoke();

    }
}
