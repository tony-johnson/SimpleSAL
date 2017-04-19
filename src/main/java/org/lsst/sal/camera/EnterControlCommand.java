package org.lsst.sal.camera;

import java.time.Duration;
import org.lsst.sal.SAL_camera;

/**
 * 
 * @author tonyj
 */
public class EnterControlCommand extends CameraCommand {

    EnterControlCommand(int cmdId, SAL_camera mgr) {
        super(cmdId, mgr);
    }

    public EnterControlCommand() {
    }
    
    @Override
    CommandResponse issueCommand(SAL_camera mgr) {
        camera.command_enterControl cmd = new camera.command_enterControl();
        int cmdId = mgr.issueCommand_enterControl(cmd);
        return new CommandResponse(mgr, this,cmdId);
    }    

    @Override
    void waitForResponse(SAL_camera mgr, int cmdId, Duration timeout) {
        mgr.waitForCompletion_enterControl(cmdId, (int) timeout.getSeconds());
    }

    @Override
    void acknowledgeCommand(int response, int timeout, String message) {
        getManager().ackCommand_enterControl(getCmdId(), response, timeout, message);
    }

    @Override
    public String toString() {
        return "EnterControlCommand{" + '}';
    }
}
