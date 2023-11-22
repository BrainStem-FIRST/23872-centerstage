package org.firstinspires.ftc.teamcode.robot;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.PwmControl;
import com.qualcomm.robotcore.hardware.ServoImplEx;

import org.checkerframework.checker.units.qual.C;
import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.utils.CachingServo;

public class Depositor {
    Telemetry telemetry;
     HardwareMap hardwareMap;
     public DepositorServoState depositorServoState = DepositorServoState.RESTING;
     private final ServoImplEx LeftDepositor;
    private final ServoImplEx RightDepositor;
    public PixelState pixelState = PixelState.HOLD;

    private final ServoImplEx TopPixHold;
    private final ServoImplEx BottomPixHold;

    private static final double LEFT_DEPOSITOR_MAX = 2420;
    private static final double LEFT_DEPOSITOR_MIN = 1316;
    private static final double RIGHT_DEPOSITOR_MAX = 665;
    private static final double RIGHT_DEPOSITOR_MIN = 2192;
    private static final double TOP_PIX_HOLD_MAX = 1677;
    private static final double TOP_PIX_HOLD_MIN = 100;
    private static final double BOTTOM_PIX_HOLD_MAX = 1677;
    private static final double BOTTOM_PIX_HOLD_MIN = 100;


    public Depositor(HardwareMap hardwareMap,Telemetry telemetry){
        this.telemetry=telemetry;
        this.hardwareMap=hardwareMap;

        LeftDepositor = new CachingServo(hardwareMap.get(ServoImplEx.class,"LeftDepositor"));
        RightDepositor = new CachingServo(hardwareMap.get(ServoImplEx.class,"RightDepositor"));
        TopPixHold = new CachingServo(hardwareMap.get(ServoImplEx.class,"TopPixHold"));
        BottomPixHold = new CachingServo(hardwareMap.get(ServoImplEx.class,"BottomPixHold"));

        LeftDepositor.setPwmRange(new PwmControl.PwmRange(LEFT_DEPOSITOR_MAX, LEFT_DEPOSITOR_MIN));
        RightDepositor.setPwmRange(new PwmControl.PwmRange(RIGHT_DEPOSITOR_MAX, RIGHT_DEPOSITOR_MIN));
        TopPixHold.setPwmRange(new PwmControl.PwmRange(TOP_PIX_HOLD_MAX, TOP_PIX_HOLD_MIN));
        BottomPixHold.setPwmRange(new PwmControl.PwmRange(BOTTOM_PIX_HOLD_MAX, BOTTOM_PIX_HOLD_MIN));
    }

    public enum PixelState {
        HOLD, DROP
    }

    public void pixelState() {
        switch (pixelState){
            case HOLD: {
                pixelHold();
                break;
            }
            case DROP: {
                pixelDrop();
                break;
            }
        }
    }
    private void pixelHold() {
       telemetry.addData("pixel position","hold");
        TopPixHold.setPosition(0.01);
        BottomPixHold.setPosition(0.01);
    }
    private void pixelDrop() {
        telemetry.addData("pixel position","drop");
        TopPixHold.setPosition(0.99);
        BottomPixHold.setPosition(0.99);
    }

    public void setHoldState() {pixelState = PixelState.HOLD;}
    public void setDropState() {pixelState = PixelState.DROP;}

    public enum DepositorServoState {
        RESTING, SCORING
    }
    public void depositorServoState() {
        switch (depositorServoState){
            case RESTING: {
                depositorResting();
                break;
            }
            case SCORING:{
                depositorScoring();
                break;
            }
        }
    }


    private void depositorResting(){
        telemetry.addData("depositor", "resting");
        LeftDepositor.setPosition(0.99);
        RightDepositor.setPosition(0.99);
    }
    private void depositorScoring(){
        telemetry.addData("depositor", "scoring");
        LeftDepositor.setPosition(0.01);
        RightDepositor.setPosition(0.01);
    }

    public void setRestingState() {depositorServoState = DepositorServoState.RESTING;}
    public void setScoringState() {depositorServoState = DepositorServoState.SCORING;}


}


