package org.lsst.sal.camera;

import java.time.Duration;
import org.lsst.sal.SAL_camera;

/**
 *
 * @author tonyj
 */
public class DisableCommand extends CameraCommand {

    DisableCommand(int cmdId, SAL_camera mgr) {
        super(cmdId, mgr);
    }

    public DisableCommand() {
    }

    @Override
    CommandResponse issueCommand(SAL_camera mgr) {
        camera.command_disable cmd = new camera.command_disable();
        int cmdId = mgr.issueCommand_disable(cmd);
        return new CommandResponse(mgr, this,cmdId);
    }

    @Override
    void waitForResponse(SAL_camera mgr, int cmdId, Duration timeout) {
        mgr.waitForCompletion_disable(cmdId, (int) timeout.getSeconds());
    }

    @Override
    void acknowledgeCommand(int response, int timeout, String message) {
        getManager().ackCommand_disable(getCmdId(), response, timeout, message);
    }

    @Override
    public String toString() {
        return "DisableCommand{" + '}';
    }
}
