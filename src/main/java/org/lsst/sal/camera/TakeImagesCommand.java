package org.lsst.sal.camera;

import java.time.Duration;
import org.lsst.sal.SAL_camera;

/**
 *
 * @author tonyj
 */
public class TakeImagesCommand extends CameraCommand {

    private final double expTime;
    private final int numImages;
    private final boolean shutter;
    private final boolean science;
    private final boolean wfs;
    private final boolean guide;
    private final String imageSequenceName;

    public TakeImagesCommand(double expTime, int numImages, boolean shutter, boolean science, boolean wfs, boolean guide, String imageSequenceName) {
        this.expTime = expTime;
        this.numImages = numImages;
        this.shutter = shutter;
        this.science = science;
        this.wfs = wfs;
        this.guide = guide;
        this.imageSequenceName = imageSequenceName;
    }

    TakeImagesCommand(int cmdId, SAL_camera mgr, double expTime, int numImages, boolean shutter, boolean science, boolean wfs, boolean guide, String imageSequenceName) {
        super(cmdId, mgr);
        this.expTime = expTime;
        this.numImages = numImages;
        this.shutter = shutter;
        this.science = science;
        this.wfs = wfs;
        this.guide = guide;
        this.imageSequenceName = imageSequenceName;
    }

    public double getExpTime() {
        return expTime;
    }

    public int getNumImages() {
        return numImages;
    }

    public boolean isShutter() {
        return shutter;
    }

    public boolean isScience() {
        return science;
    }

    public boolean isWfs() {
        return wfs;
    }

    public boolean isGuide() {
        return guide;
    }

    public String getImageSequenceName() {
        return imageSequenceName;
    }

    @Override
    CommandResponse issueCommand(SAL_camera mgr) {
        camera.command_takeImages cmd = new camera.command_takeImages();
        cmd.numImages = numImages;
        cmd.expTime = expTime;
        cmd.guide = guide;
        cmd.science = science;
        cmd.shutter = shutter;
        cmd.wfs = wfs;
        cmd.imageSequenceName = imageSequenceName;
        int cmdId = mgr.issueCommand_takeImages(cmd);
        return new CommandResponse(mgr, this, cmdId);
    }

    @Override
    void waitForResponse(SAL_camera mgr, int cmdId, Duration timeout) {
        mgr.waitForCompletion_takeImages(cmdId, (int) timeout.getSeconds());
    }

    @Override
    void acknowledgeCommand(int response, int timeout, String message) {
        getManager().ackCommand_takeImages(getCmdId(), response, timeout, message);
    }

    @Override
    public String toString() {
        return "TakeImagesCommand{" + "expTime=" + expTime + ", numImages=" + numImages + ", shutter=" + shutter + ", science=" + science + ", wfs=" + wfs + ", guide=" + guide + ", imageSequenceName=" + imageSequenceName + '}';
    }

}
