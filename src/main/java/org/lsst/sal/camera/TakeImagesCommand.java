package org.lsst.sal.camera;

import java.time.Duration;
import org.lsst.sal.SAL_camera;

/**
 *
 * @author tonyj
 */
class TakeImagesCommand extends CameraCommand {

    public TakeImagesCommand(int cmdId, SAL_camera mgr, double expTime, int numImages, boolean shutter, boolean science, boolean wfs, boolean guide, String imageSequenceName) {
        super(cmdId, mgr);
    }

    @Override
    CommandResponse issueCommand(SAL_camera mgr) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void waitForResponse(SAL_camera mgr, int cmdId, Duration timeout) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    } 

    @Override
    void acknowledgeCommand(int response, int timeout, String message) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}