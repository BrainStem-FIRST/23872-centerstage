package org.firstinspires.ftc.teamcode.robot;

import static org.firstinspires.ftc.teamcode.robot.Depositor.DepositorServoState.RESTING;
import static org.firstinspires.ftc.teamcode.robot.Depositor.DepositorServoState.SCORING;
import static org.firstinspires.ftc.teamcode.robot.Depositor.PixelState.HOLD;
import static org.firstinspires.ftc.teamcode.robot.Depositor.PixelState.DROP;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.PwmControl;
import com.qualcomm.robotcore.hardware.ServoImplEx;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.utils.CachingServo;

public class Depositor {
    private final Telemetry telemetry;
    private HardwareMap hardwareMap;
    public DepositorServoState depositorServoState = DepositorServoState.RESTING;
    public PixelState pixelState = PixelState.DROP;
    private final ServoImplEx LeftDepositor;
    private final ServoImplEx RightDepositor;
    private final ServoImplEx TopPixHold;
    private final ServoImplEx BottomPixHold;

    private static final double LEFT_DEPOSITOR_MAX = 2470;
    private static final double LEFT_DEPOSITOR_MIN = 1220;
    private static final double RIGHT_DEPOSITOR_MAX = 720;
    private static final double RIGHT_DEPOSITOR_MIN = 1990;
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
        TopPixHold.setPosition(0.01);
        BottomPixHold.setPosition(0.01);
    }
    private void pixelDrop() {
        TopPixHold.setPosition(0.99);
        BottomPixHold.setPosition(0.99);
    }
    public enum DepositorServoState {
        RESTING, SCORING
    }

    public void setHoldState() {
        pixelState = PixelState.HOLD;
    }
    public void setDropState() {
        pixelState = PixelState.DROP;
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
        LeftDepositor.setPosition(0.99);
        RightDepositor.setPosition(0.99);
        telemetry.addData("ServoState", "Resting");
    }
    private void depositorScoring(){
        LeftDepositor.setPosition(0.01);
        RightDepositor.setPosition(0.01);
        telemetry.addData("ServoState", "Scoring");
    }

    public void setRestingState() {
        depositorServoState = DepositorServoState.RESTING;
    }
    public void setScoringState() {
        depositorServoState = DepositorServoState.SCORING;
    }


}

