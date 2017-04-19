package org.lsst.sal.camera;

import java.time.Duration;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import org.junit.AfterClass;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author tonyj
 */
public class CommandSendReceiveTest {

    private static SALCamera camera;
    private static ExecutorService executor;

    public CommandSendReceiveTest() {
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
    public void sendReceiveEnableCommand() throws InterruptedException, SALException, ExecutionException, TimeoutException {
        CameraCommand command = testCommandSendReceive(new EnableCommand());
        assertTrue(command instanceof EnableCommand);
    }

    @Test
    public void sendReceiveDisableCommand() throws InterruptedException, SALException, ExecutionException, TimeoutException {
        CameraCommand command = testCommandSendReceive(new DisableCommand());
        assertTrue(command instanceof DisableCommand);
    }

    @Test
    public void sendReceiveEnterControlCommand() throws InterruptedException, SALException, ExecutionException, TimeoutException {
        CameraCommand command = testCommandSendReceive(new EnterControlCommand());
        assertTrue(command instanceof EnterControlCommand);
    }

    @Test
    public void sendReceiveExitControlCommand() throws InterruptedException, SALException, ExecutionException, TimeoutException {
        CameraCommand command = testCommandSendReceive(new ExitControlCommand());
        assertTrue(command instanceof ExitControlCommand);
    }

    @Test
    public void sendReceiveStartCommand() throws InterruptedException, SALException, ExecutionException, TimeoutException {
        CameraCommand command = testCommandSendReceive(new StartCommand("configuration"));
        assertTrue(command instanceof StartCommand);
        StartCommand sc = (StartCommand) command;
        assertEquals("configuration", sc.getConfiguration());
    }

    @Test
    public void sendReceiveStandbyCommand() throws InterruptedException, SALException, ExecutionException, TimeoutException {
        CameraCommand command = testCommandSendReceive(new StandbyCommand());
        assertTrue(command instanceof StandbyCommand);
    }

    @Test
    public void sendTakeImagesCommand() throws InterruptedException, SALException, ExecutionException, TimeoutException {
        CameraCommand command = testCommandSendReceive(new TakeImagesCommand(10, 2, true, true, true, true, "MyImage"));
        assertTrue(command instanceof TakeImagesCommand);
        TakeImagesCommand tic = (TakeImagesCommand) command;
        assertEquals(2, tic.getNumImages());
        assertEquals(10, tic.getExpTime(), 1e-6);
        assertTrue(tic.isShutter());
        assertTrue(tic.isScience());
        assertTrue(tic.isGuide());
        assertTrue(tic.isWfs());
        assertEquals("MyImage", tic.getImageSequenceName());
    }
    
    @Test
    public void sendReceiveSetFilterCommand() throws InterruptedException, SALException, ExecutionException, TimeoutException {
        CameraCommand command = testCommandSendReceive(new SetFilterCommand("filterAAA"));
        assertTrue(command instanceof SetFilterCommand);
        SetFilterCommand sf = (SetFilterCommand) command;
        assertEquals("filterAAA", sf.getFilterName());
    }

    @Test
    public void sendReceiveInitImageCommand() throws InterruptedException, SALException, ExecutionException, TimeoutException {
        CameraCommand command = testCommandSendReceive(new InitImageCommand(1.234));
        assertTrue(command instanceof InitImageCommand);
        InitImageCommand sf = (InitImageCommand) command;
        assertEquals(1.234, sf.getDeltaT(),  1e-6);
    }
     
    @Test
    public void sendReceiveInitGuidersCommand() throws InterruptedException, SALException, ExecutionException, TimeoutException {
        CameraCommand command = testCommandSendReceive(new InitGuidersCommand("roiSpec"));
        assertTrue(command instanceof InitGuidersCommand);
        InitGuidersCommand ig = (InitGuidersCommand) command;
        assertEquals("roiSpec", ig.getRoiSpec());
    }
    
    private CameraCommand testCommandSendReceive(CameraCommand command) throws SALException, InterruptedException, ExecutionException, TimeoutException {
        Future<CameraCommand> future = executor.submit(() -> camera.getNextCommand(Duration.ofSeconds(10)));
        CommandResponse response = camera.issueCommand(command);
        CameraCommand result = future.get(10, TimeUnit.SECONDS);
        result.reportComplete();
        response.waitForResponse(Duration.ofSeconds(1));
        return result;
    }
}
