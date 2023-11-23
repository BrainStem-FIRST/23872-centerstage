package org.firstinspires.ftc.teamcode.robot;

import static org.firstinspires.ftc.teamcode.robot.CollectorTele.CollectorState.IN;
import static org.firstinspires.ftc.teamcode.robot.CollectorTele.CollectorState.OFF;
import static org.firstinspires.ftc.teamcode.robot.CollectorTele.CollectorState.OUT;

import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.util.CachingMotor;
<<<<<<< HEAD
import org.firstinspires.ftc.teamcode.utils.CachingServo;

import java.security.SecureRandom;
import java.util.logging.Level;

public class CollectorTele {
    private final DcMotorEx CollectorMotor;
    private  ServoImplEx DrawbridgeServo;
    public DrawbridgeState drawbridgeState = DrawbridgeState.ONE;

    public CollectorState collectorState = CollectorState.OFF;
=======

public class CollectorTele {
    private final DcMotorEx collectorMotor;

>>>>>>> parent of 09c8cc8 (tele op able to score)
    private HardwareMap hardwareMap;
    private Telemetry telemetry;

    private static final double Level1 = 2088;
    private static final double Level2 = 1931;
    private static final double Level3 = 1802;
    private static final double Level4 = 1613;
    private static final double Level5 = 1433;
    public CollectorTele(HardwareMap hardwareMap,Telemetry telemetry) {
        this.telemetry = telemetry;

<<<<<<< HEAD
        CollectorMotor = new CachingMotor(hardwareMap.get(DcMotorEx.class, "Collector"));
        DrawbridgeServo = new CachingServo(hardwareMap.get(ServoImplEx.class, "Drawbridge"));
        DrawbridgeServo.setPwmRange(new  PwmControl.PwmRange(Level5, Level1));

    }
=======
        collectorMotor = new CachingMotor(hardwareMap.get(DcMotorEx.class, "Collector"));
    }

>>>>>>> parent of 09c8cc8 (tele op able to score)
    public enum CollectorState {
        OFF, IN, OUT
    }
    public enum DrawbridgeState {
        ONE, TWO, THREE, FOUR, FIVE

    }

    CollectorState collectorState = OFF;

    public void setCollectorState() {
        telemetry.addData("collectorState", collectorState);
        switch (collectorState) {
            case OFF: {
                collectorOff();
                break;
            }
            case IN: {
                collectorIn();
                break;
            }
            case OUT: {
                collectorOut();
                break;
            }
        }
    }

    public void setDrawbridgeState() {
        telemetry.addData("DrawbridgeState", drawbridgeState);
        switch (drawbridgeState){
            case ONE: {
                DrawbridgeOne();
                break;
            }
            case TWO: {
                setDrawbridgeTwo();
                break;
            }
            case THREE: {
                setDrawbridgeThree();
                break;
            }
            case FOUR: {
                setDrawbridgeFour();
                break;
            }
            case FIVE: {
                setDrawbridgeFive();
                break;
            }

        }
    }

        public void setDrawbridgeOne(){
        drawbridgeState = DrawbridgeState.ONE;
        }
         public void setDrawbridgeTwo(){
        drawbridgeState = DrawbridgeState.TWO;
    }
        public void setDrawbridgeThree(){
        drawbridgeState = DrawbridgeState.THREE;
    }
        public void setDrawbridgeFour(){
        drawbridgeState = DrawbridgeState.FOUR;
    }
        public void setDrawbridgeFive(){
        drawbridgeState = DrawbridgeState.FIVE;
    }

    public void DrawbridgeOne(){
        DrawbridgeServo.setPosition(1);
    }
    public void DrawbridgeTwo(){
        DrawbridgeServo.setPosition(Level2/(Level1-Level5));
    }
    public void DrawbridgeThree(){
        DrawbridgeServo.setPosition(Level3/(Level1-Level5));
    }
    public void DrawbridgeFour(){
        DrawbridgeServo.setPosition(Level4/Level1-Level5);
    }
    public void DrawbridgeFive(){
        DrawbridgeServo.setPosition(0);
    }


        public void setCollectorOff(){
        collectorState = CollectorState.OFF;
    }

    public void setCollectorIn(){
        collectorState = CollectorState.IN;
    }

    public void setCollectorOut(){
        collectorState = CollectorState.OUT;
    }

    private void collectorOff() {collectorMotor.setPower(0);}
    private void collectorIn(){
        collectorMotor.setPower(0.5);
    }
    private void collectorOut() { collectorMotor.setPower(-0.5);
    }

}
