package org.lsst.sal.camera;

import java.time.Duration;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import org.junit.AfterClass;
import static org.junit.Assert.assertTrue;
import org.junit.BeforeClass;
import org.junit.Test;
import org.lsst.sal.camera.SummaryStateEvent.LSE209State;
import static org.junit.Assert.assertTrue;

/**
 * 
 * @author tonyj
 */
public class TelemetrySendReceiveTest {

    private static SALCamera camera;
    private static ExecutorService executor;

    public TelemetrySendReceiveTest() {
    }

    @BeforeClass
    public static void setUpClass() {
        camera = SALCamera.instance();
        executor = Executors.newFixedThreadPool(1);
    }

    @AfterClass
    public static void tearDownClass() throws InterruptedException {
        executor.shutdown();
        executor.awaitTermination(10, TimeUnit.SECONDS);
    }

    @Test
    public void sendColdTelemetry() throws InterruptedException, SALException, ExecutionException, TimeoutException {
        camera.sendTelemetry(new CameraColdTelemetry());
    }
}
