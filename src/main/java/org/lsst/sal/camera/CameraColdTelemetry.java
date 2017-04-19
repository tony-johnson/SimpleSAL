package org.lsst.sal.camera;

import camera.Cold;
import org.lsst.sal.SAL_camera;

/**
 *
 * @author tonyj
 */
public class CameraColdTelemetry extends CameraTelemetry {

    private final float[] Compressor_load = new float[6];
    private final float[] Compressor_speed = new float[6];
    private final float[] Dischaarge_pressure = new float[6];
    private final float[] Discharge_temp = new float[6];
    private final short[] Flow_interlock = new short[6];
    private final float[] Heater_current = new float[6];
    private final float[] Heater_voltage = new float[6];
    private final float[] Intake_flow = new float[6];
    private final float[] Intake_pressure = new float[6];
    private final float[] Intake_temp = new float[6];
    private final float[] Ion_pump = new float[4];
    private final short[] Mech_pump = new short[4];
    private final float[] Post_expansion_pressure = new float[6];
    private final float[] Post_expansion_temp = new float[6];
    private final float[] Pre_expansion_pressure = new float[6];
    private final float[] Pre_expansion_temp = new float[6];
    private final int[] RGA = new int[10];
    private final float[] Return_temp = new float[6];
    private float UtilityRoom_temperature;
    private final float[] Vacuum_gauge = new float[5];
    private final short[] Valve_status = new short[6];
    
    public CameraColdTelemetry() {
       
    }

    CameraColdTelemetry(Cold data) {
        System.arraycopy(Compressor_load,0,data.Compressor_load,0,Compressor_load.length);
        System.arraycopy(Compressor_speed,0,Compressor_speed,0,Compressor_speed.length);
        System.arraycopy(Dischaarge_pressure,0,data.Dischaarge_pressure,0,Dischaarge_pressure.length);
        System.arraycopy(Discharge_temp ,0,data.Discharge_temp,0,Discharge_temp.length);
        System.arraycopy(Flow_interlock, 0, data.Flow_interlock,0,Flow_interlock.length);
        System.arraycopy(Heater_current, 0, data.Heater_current,0,Heater_current.length);
        System.arraycopy(Heater_voltage, 0, data.Heater_voltage,0,Heater_voltage.length);
        System.arraycopy(Intake_flow, 0, data.Intake_flow,0,Intake_flow.length);
        System.arraycopy(Intake_pressure, 0, data.Intake_pressure,0,Intake_pressure.length);
        System.arraycopy(Intake_temp, 0, data.Intake_temp,0,Intake_temp.length);
        System.arraycopy(Ion_pump, 0, data.Ion_pump,0,Ion_pump.length);
        System.arraycopy(Mech_pump, 0, data.Mech_pump,0,Mech_pump.length);
        System.arraycopy(Post_expansion_pressure, 0, data.Post_expansion_pressure,0,Post_expansion_pressure.length);
        System.arraycopy(Post_expansion_temp, 0, data.Post_expansion_temp,0,Post_expansion_temp.length);
        System.arraycopy(Pre_expansion_pressure, 0, data.Pre_expansion_pressure,0,Pre_expansion_pressure.length);
        System.arraycopy(Pre_expansion_temp, 0, data.Pre_expansion_temp,0,Pre_expansion_temp.length);
        System.arraycopy(RGA, 0, data.RGA,0,RGA.length);
        System.arraycopy(Return_temp, 0, data.Return_temp,0,Return_temp.length);
        UtilityRoom_temperature = data.UtilityRoom_temperature;
        System.arraycopy(Vacuum_gauge, 0, data.Vacuum_gauge,0,Vacuum_gauge.length);
        System.arraycopy(Valve_status, 0, data.Valve_status,0,Valve_status.length);
    }

    public float getUtilityRoom_temperature() {
        return UtilityRoom_temperature;
    }

    public void setUtilityRoom_temperature(float UtilityRoom_temperature) {
        this.UtilityRoom_temperature = UtilityRoom_temperature;
    }

    public float[] getCompressor_load() {
        return Compressor_load;
    }

    public float[] getCompressor_speed() {
        return Compressor_speed;
    }

    public float[] getDischaarge_pressure() {
        return Dischaarge_pressure;
    }

    public float[] getDischarge_temp() {
        return Discharge_temp;
    }

    public short[] getFlow_interlock() {
        return Flow_interlock;
    }

    public float[] getHeater_current() {
        return Heater_current;
    }

    public float[] getHeater_voltage() {
        return Heater_voltage;
    }

    public float[] getIntake_flow() {
        return Intake_flow;
    }

    public float[] getIntake_pressure() {
        return Intake_pressure;
    }

    public float[] getIntake_temp() {
        return Intake_temp;
    }

    public float[] getIon_pump() {
        return Ion_pump;
    }

    public short[] getMech_pump() {
        return Mech_pump;
    }

    public float[] getPost_expansion_pressure() {
        return Post_expansion_pressure;
    }

    public float[] getPost_expansion_temp() {
        return Post_expansion_temp;
    }

    public float[] getPre_expansion_pressure() {
        return Pre_expansion_pressure;
    }

    public float[] getPre_expansion_temp() {
        return Pre_expansion_temp;
    }

    public int[] getRGA() {
        return RGA;
    }

    public float[] getReturn_temp() {
        return Return_temp;
    }

    public float[] getVacuum_gauge() {
        return Vacuum_gauge;
    }

    public short[] getValve_status() {
        return Valve_status;
    }

    @Override
    void putSample(SAL_camera mgr) {
        camera.Cold data = new camera.Cold();
        data.Compressor_load = Compressor_load;
        data.Compressor_speed = Compressor_speed;
        data.Dischaarge_pressure = Dischaarge_pressure;
        data.Discharge_temp = Discharge_temp;
        data.Flow_interlock = Flow_interlock;
        data.Heater_current = Heater_current;
        data.Heater_voltage = Heater_voltage;
        data.Intake_flow = Intake_flow;
        data.Intake_pressure = Intake_pressure;
        data.Intake_temp = Intake_temp;
        data.Ion_pump = Ion_pump;
        data.Mech_pump = Mech_pump;
        data.Post_expansion_pressure = Post_expansion_pressure;
        data.Post_expansion_temp = Post_expansion_temp;
        data.Pre_expansion_pressure = Pre_expansion_pressure;
        data.Pre_expansion_temp = Pre_expansion_temp;
        data.RGA = RGA;
        data.Return_temp = Return_temp;
        data.UtilityRoom_temperature = UtilityRoom_temperature;
        data.Vacuum_gauge = Vacuum_gauge;
        data.Valve_status = Valve_status;
        mgr.putSample(data);
    }
    
}
