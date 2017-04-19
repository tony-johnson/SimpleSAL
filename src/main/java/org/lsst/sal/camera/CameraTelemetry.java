package org.lsst.sal.camera;

import org.lsst.sal.SAL_camera;

/**
 * Base class for all camera telemetry
 * @author tonyj
 */
public abstract class CameraTelemetry {
    public CameraTelemetry() {
        
    }

    abstract void putSample(SAL_camera mgr);
}
