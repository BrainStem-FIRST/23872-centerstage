package org.firstinspires.ftc.teamcode.robot;

import com.arcrobotics.ftclib.controller.PIDFController;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.util.Range;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class LiftTele {

    private final DcMotorEx liftMotor;
    private final HardwareMap hardwareMap;
    private final Telemetry telemetry;


    public LiftState liftState = LiftState.ZERO;

    private final static double kP = 0.1;
    private final static double kI = 0.0;
    private final static double kD = 0.0;
    private final static double kS = 0.005;
    private final static double kF = 0;


    public int levelCounter = 0;

            // fix or tune above values
    PIDFController pidfController = new PIDFController(kP, kI, kD, kF);
    public final static int levelZeroHeight = 0;
    private final static int levelOneHeight = 108;
    private final static int levelTwoHeight = 326;
    private final static int levelThreeHeight = 553;
    public final static int levelFourHeight = 788;
    private final static int levelFiveHeight = 1000;

    public int liftTicks = levelFiveHeight;
//    private final static int levelSixHeight = 600;
//    private final static int levelSevenHeight = 700;
//    private final static int levelEightHeight
//    ht = 800;

    public LiftTele(HardwareMap hardwareMap, Telemetry telemetry) {
        this.hardwareMap = hardwareMap;
        this.telemetry = telemetry;


//        pidfController.setInputBounds(0, 1000);
        liftMotor = new org.firstinspires.ftc.teamcode.util.CachingMotor(hardwareMap.get(DcMotorEx.class, "Lift"));
        liftMotor.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);
        liftMotor.setMode(DcMotorEx.RunMode.RUN_WITHOUT_ENCODER);
        liftMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        liftMotor.setTargetPositionTolerance(5);
    }

    public void heightCounter() {
        if (levelCounter == 0) {
            liftState = LiftState.ZERO;
        }
        if (levelCounter == 1) {
            liftState = LiftState.ONE;
        }
        if (levelCounter == 2) {
            liftState = LiftState.TWO;
        }
        if (levelCounter == 3) {
            liftState = LiftState.THREE;
        }
        if (levelCounter == 4) {
            liftState = LiftState.FOUR;
        }
        if (levelCounter == 5) {
            liftState = LiftState.FIVE;
        }
    }


    public enum LiftState {
        ZERO, ONE, TWO, THREE, FOUR, FIVE, SIX, SEVEN, EIGHT
    }

    public int currentPosition(){
        return liftMotor.getCurrentPosition();
    }

    private double setLiftMotorPIDPower(int liftTicks) {
        pidfController.setPIDF(kP, kI, kD, kS);
        double power = Range.clip(pidfController.calculate(currentPosition(), liftTicks),-1,1);
        return power;
    }


    public void liftState() {
        switch (liftState) {
            case ZERO: {
                liftMotor.setPower(setLiftMotorPIDPower(0));
                telemetry.addData("lift enum", "Zero");
                break;
            }
            case ONE: {
                liftMotor.setPower(setLiftMotorPIDPower(108));
                telemetry.addData("lift enum", "One");
                break;
            }
            case TWO: {
                liftMotor.setPower(setLiftMotorPIDPower(326));
                telemetry.addData("lift enum", "Two");
                break;
            }
            case THREE: {
                liftMotor.setPower(setLiftMotorPIDPower(553));
                telemetry.addData("lift enum", "Three");
                break;
            }
            case FOUR: {
                liftMotor.setPower(setLiftMotorPIDPower(788));
                telemetry.addData("lift enum", "Four");
                break;
            }
            case FIVE: {
                liftMotor.setPower(setLiftMotorPIDPower(1000));
                telemetry.addData("lift enum", "Five");
                break;
            }
//            case SIX: {
//                setLiftHeight(levelSixHeight);
//                break;
//            }
//            case SEVEN: {
//                setLiftHeight(levelSevenHeight);
//                break;
//            }
//            case EIGHT: {
//                setLiftHeight(levelEightHeight);
//                break;
//            }
        }
    }

    public void LiftState(int liftHeight) {
//        pidfController.setTarget(liftHeight);
        double error = liftHeight - liftMotor.getCurrentPosition();}}
//        liftMotor.setPower(pidfController.updateWithError(error) + kS);}};

//    public void setLiftUp() {
//        liftMotor.setPower(0.6);
//    }


//    public void setLiftDown () {
//        liftMotor.setPower(-0.6);
//    }
//
//    public void setLiftOff () {
//        liftMotor.setPower(0);
//    }
//
//    public void setRawPower (double power){
//        liftMotor.setPower(power);
//    }
//
//    private void setLiftZero () {
//        liftState = LiftState.ZERO;
//    }
//
//    private void setLiftOne () {
//        liftState = LiftState.ONE;
//    }
//    private void setLiftTwo() {
//        liftState = LiftState.TWO;
//    }
//    private void setLiftThree() {
//        liftState = LiftState.THREE;
//    }
//    private void setLiftFour() {
//        liftState = LiftState.FOUR;
//    }
//    private void setLiftFive() {
//        liftState = LiftState.FIVE;
////    }
//    private int getPosition () {
//        return liftMotor.getCurrentPosition();
//    }


//<<<<<<< HEAD
//public void setLiftOff () {
//        liftMotor.setPower(0);
//    }
//
//    public void setRawPower ( double power){
//        liftMotor.setPower(power);
//    }
//
//    public void setLiftZero () {
//        liftState = LiftState.ZERO;
//    }
//    public void setLiftFour () {
//        liftState = LiftState.FOUR;
//    }
//
//    public void setLiftOne () {
//        liftState = LiftState.ONE;
//    }
//    public int getPosition () {
//        return liftMotor.getCurrentPosition();
//    }

//}
//=======
////}
//>>>>>>> 814b1801a676b4962bc686fac510955819e220ca
