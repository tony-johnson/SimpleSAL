package org.lsst.sal.camera;

import java.time.Duration;
import org.lsst.sal.SAL_camera;

/**
 *
 * @author tonyj
 */
public class StandbyCommand extends CameraCommand {

    public StandbyCommand() {
        
    }
    
    StandbyCommand(int cmdId, SAL_camera mgr) {
        super(cmdId, mgr);
    }

    @Override
    CommandResponse issueCommand(SAL_camera mgr) {
        camera.command_standby cmd = new camera.command_standby();
        int cmdId = mgr.issueCommand_standby(cmd);
        return new CommandResponse(mgr, this,cmdId);
    }

    @Override
    void waitForResponse(SAL_camera mgr, int cmdId, Duration timeout) {
        mgr.waitForCompletion_standby(cmdId, (int) timeout.getSeconds());
    }

    @Override
    void acknowledgeCommand(int response, int timeout, String message) {
        getManager().ackCommand_standby(getCmdId(), response, timeout, message);
    }

    @Override
    public String toString() {
        return "StandbyCommand{" + '}';
    }
}
