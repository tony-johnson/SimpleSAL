package org.lsst.sal.camera;

import java.time.Duration;
import org.lsst.sal.SAL_camera;

/**
 *
 * @author tonyj
 */
public class StartCommand extends CameraCommand {

    private final String configuration;

    public StartCommand(String configuration) {
        this.configuration = configuration;
    }
    
    StartCommand(int cmdId, SAL_camera mgr, String configuration) {
        super(cmdId, mgr);
        this.configuration = configuration;
    }

    public String getConfiguration() {
        return configuration;
    }

    @Override
    CommandResponse issueCommand(SAL_camera mgr) {
        camera.command_start cmd = new camera.command_start();
        cmd.configuration = configuration;
        int cmdId = mgr.issueCommand_start(cmd);
        return new CommandResponse(mgr, this,cmdId);    }

    @Override
    void waitForResponse(SAL_camera mgr, int cmdId, Duration timeout) {
        mgr.waitForCompletion_start(cmdId, (int) timeout.getSeconds());
    }

    @Override
    void acknowledgeCommand(int response, int timeout, String message) {
        getManager().ackCommand_start(getCmdId(), response, timeout, message);
    }     

    @Override
    public String toString() {
        return "StartCommand{" + "configuration=" + configuration + '}';
    }
}
