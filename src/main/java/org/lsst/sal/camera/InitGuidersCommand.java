package org.lsst.sal.camera;

import java.time.Duration;
import org.lsst.sal.SAL_camera;

/**
 *
 * @author tonyj
 */
public class InitGuidersCommand extends CameraCommand {

    private final String roiSpec;

    InitGuidersCommand(int cmdId, SAL_camera mgr, String roiSpec) {
        super(cmdId, mgr);
        this.roiSpec = roiSpec;
    }

    public InitGuidersCommand( String roiSpec) {
        this.roiSpec = roiSpec;
    }

    public String getRoiSpec() {
        return roiSpec;
    }

    @Override
    CommandResponse issueCommand(SAL_camera mgr) {
        camera.command_initGuiders cmd = new camera.command_initGuiders();
        cmd.roiSpec = roiSpec;
        int cmdId = mgr.issueCommand_initGuiders(cmd);
        return new CommandResponse(mgr, this,cmdId);
    }

    @Override
    void waitForResponse(SAL_camera mgr, int cmdId, Duration timeout) {
        mgr.waitForCompletion_initGuiders(cmdId, (int) timeout.getSeconds());
    }

    @Override
    void acknowledgeCommand(int response, int timeout, String message) {
        getManager().ackCommand_initGuiders(getCmdId(), response, timeout, message);
    }  

    @Override
    public String toString() {
        return "InitGuidersCommand{" + "roiSpec=" + roiSpec + '}';
    }
}
