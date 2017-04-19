package org.lsst.sal.camera;

import java.time.Duration;
import org.lsst.sal.SAL_camera;

/**
 *
 * @author tonyj
 */
public class InitImageCommand extends CameraCommand {

    private final double deltaT;

    InitImageCommand(int cmdId, SAL_camera mgr, double deltaT) {
        super(cmdId, mgr);
        this.deltaT = deltaT;
    }

    public InitImageCommand(double deltaT) {
        this.deltaT = deltaT;
    }

    public double getDeltaT() {
        return deltaT;
    }

    @Override
    CommandResponse issueCommand(SAL_camera mgr) {
        camera.command_initImage cmd = new camera.command_initImage();
        cmd.deltaT = deltaT;
        int cmdId = mgr.issueCommand_initImage(cmd);
        return new CommandResponse(mgr, this,cmdId);
    }

    @Override
    void waitForResponse(SAL_camera mgr, int cmdId, Duration timeout) {
        mgr.waitForCompletion_initImage(cmdId, (int) timeout.getSeconds());
    }

    @Override
    void acknowledgeCommand(int response, int timeout, String message) {
        getManager().ackCommand_initImage(getCmdId(), response, timeout, message);
    }  

    @Override
    public String toString() {
        return "InitImageCommand{" + "deltaT=" + deltaT + '}';
    }
}
