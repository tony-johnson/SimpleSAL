package org.lsst.sal.camera;

import java.time.Duration;
import org.lsst.sal.SAL_camera;

/**
 *
 * @author tonyj
 */
public class SetFilterCommand extends CameraCommand {

    private final String filterName;
    SetFilterCommand(int cmdId, SAL_camera mgr, String name) {
        super(cmdId, mgr);
        this.filterName = name;
    }

    public SetFilterCommand(String name) {
        this.filterName = name;
    }

    public String getFilterName() {
        return filterName;
    }
    
    @Override
    CommandResponse issueCommand(SAL_camera mgr) {
        camera.command_setFilter cmd = new camera.command_setFilter();
        cmd.name = filterName;
        int cmdId = mgr.issueCommand_setFilter(cmd);
        return new CommandResponse(mgr, this,cmdId);    }

    @Override
    void waitForResponse(SAL_camera mgr, int cmdId, Duration timeout) {
        mgr.waitForCompletion_setFilter(cmdId, (int) timeout.getSeconds());
    }

    @Override
    void acknowledgeCommand(int response, int timeout, String message) {
        getManager().ackCommand_setFilter(getCmdId(), response, timeout, message);
    } 

    @Override
    public String toString() {
        return "SetFilterCommand{" + "filterName=" + filterName + '}';
    }
}
