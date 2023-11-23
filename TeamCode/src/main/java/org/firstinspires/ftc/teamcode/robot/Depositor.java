package org.firstinspires.ftc.teamcode.robot;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.PwmControl;
import com.qualcomm.robotcore.hardware.ServoImplEx;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.utils.CachingServo;

public class Depositor {
    Telemetry telemetry;
     HardwareMap hardwareMap;

<<<<<<< HEAD
//<<<<<<< HEAD

     public DepositorServoState depositorServoState = DepositorServoState.RESTING;

//=======
    public PixelState pixelState = PixelState.DROP;
    private final ServoImplEx LeftDepositor;
    private final ServoImplEx RightDepositor;

    private final ServoImplEx TopPixHold;
    private final ServoImplEx BottomPixHold;

    private static final double LEFT_DEPOSITOR_MAX = 2520;
    private static final double LEFT_DEPOSITOR_MIN = 1316;
    private static final double RIGHT_DEPOSITOR_MAX = 765;
    private static final double RIGHT_DEPOSITOR_MIN = 2192;
    private static final double TOP_PIX_HOLD_MAX = 1677;
    private static final double TOP_PIX_HOLD_MIN = 100;
    private static final double BOTTOM_PIX_HOLD_MAX = 1677;
    private static final double BOTTOM_PIX_HOLD_MIN = 100;
=======
     public DepositorServoState depositorServoState = DepositorServoState.RESTING;
     private ServoImplEx LeftDepositor;
    private ServoImplEx RightDepositor;

    private static final double LEFT_DEPOSITOR_MAX = 1500;
    private static final double LEFT_DEPOSITOR_MIN = 500;
    private static final double RIGHT_DEPOSITOR_MAX = 1500;
    private static final double RIGHT_DEPOSITOR_MIN = 500;
>>>>>>> parent of 09c8cc8 (tele op able to score)


    public Depositor(HardwareMap hardwareMap,Telemetry telemetry){
        this.telemetry=telemetry;
        this.hardwareMap=hardwareMap;

        LeftDepositor = new CachingServo(hardwareMap.get(ServoImplEx.class,"LeftDepositor"));
        RightDepositor = new CachingServo(hardwareMap.get(ServoImplEx.class,"RightDepositor"));

        LeftDepositor.setPwmRange(new PwmControl.PwmRange(LEFT_DEPOSITOR_MAX, LEFT_DEPOSITOR_MIN));
        RightDepositor.setPwmRange(new PwmControl.PwmRange(RIGHT_DEPOSITOR_MAX, RIGHT_DEPOSITOR_MIN));



    }

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
        LeftDepositor.setPosition(0.01);
        RightDepositor.setPosition(0.01);
    }
    private void depositorScoring(){
        LeftDepositor.setPosition(0.99);
        RightDepositor.setPosition(0.99);
    }

    public void setRestingState() {
        depositorServoState = DepositorServoState.RESTING;
    }
    public void setScoringState() {
        depositorServoState = DepositorServoState.SCORING;
    }


}

