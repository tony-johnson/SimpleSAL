package org.lsst.sal.camera;

import org.lsst.sal.SAL_camera;

/**
 * Log a generic message (probably should not be used in production)
 * @author tonyj
 */
public class GenericEvent extends CameraEvent {

    private final String message;

    public GenericEvent(int priority, String message) {
        super(priority);
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
    
    @Override
    void logEvent(SAL_camera mgr) {
        // Create events 
        mgr.logEvent(message, getPriority());
    }
}
