package org.firstinspires.ftc.teamcode.robot;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class Lift {
    public DcMotorEx liftMotor;
    private final HardwareMap hardwareMap;
    private final Telemetry telemetry;
    public LiftState liftState = LiftState.ZERO;
    private int levelCounter = 0;

    private final static double kP = 0.08;
    private final static double kI = 0.0;
    private final static double kD = 0.0;
    private final static double kS = 0.002;
    PIDController pidController = new PIDController(kP, kI, kD);
    private final static int levelZeroHeight = 0;
    private final static int levelOneHeight = 108;
    private final static int levelTwoHeight = 326;
    private final static int levelThreeHeight = 553;
    private final static int levelFourHeight = 788;
    private final static int levelFiveHeight = 1000;
//    private final static int levelSixHeight = 600;
//    private final static int levelSevenHeight = 700;
//    private final static int levelEightHeight
//    ht = 800;

    public Lift(HardwareMap hardwareMap, Telemetry telemetry) {
        this.hardwareMap = hardwareMap;
        this.telemetry = telemetry;


        pidController.setInputBounds(0, 1000);
        pidController.setOutputBounds(-1, 1);
        liftMotor = new org.firstinspires.ftc.teamcode.util.CachingMotor(hardwareMap.get(DcMotorEx.class, "Lift"));
        liftMotor.setMode(DcMotorEx.RunMode.RUN_WITHOUT_ENCODER);
        liftMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
    }


    public enum LiftState {
        ZERO, ONE, TWO, THREE, FOUR, FIVE, SIX, SEVEN, EIGHT
    }


    public void liftState() {
        updateLevel();
        switch (liftState) {
            case ZERO: {
               setLiftHeight(levelZeroHeight);
                telemetry.addData("lift enum", "Zero");
                break;
            }
            case ONE: {
                setLiftHeight(levelOneHeight);
                telemetry.addData("lift enum", "One");
                break;
            }
            case TWO: {
                setLiftHeight(levelTwoHeight);
                telemetry.addData("lift enum", "Two");
                break;
            }
            case THREE: {
                setLiftHeight(levelThreeHeight);
                telemetry.addData("lift enum", "Three");
                break;
            }
            case FOUR: {
                setLiftHeight(levelFourHeight);
                telemetry.addData("lift enum", "Four");
                break;
            }
            case FIVE: {
                setLiftHeight(levelFiveHeight);
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

    public void setLiftHeight(int liftHeight) {
        pidController.setTarget(liftHeight);
        double error = liftHeight - liftMotor.getCurrentPosition();
        if (Math.abs(error) < 5){
            error = 0;
        }
        double power = pidController.updateWithError(error) + kS;
        if (Math.abs(error) < 25){
            power = power/4;
        }
        liftMotor.setPower(power);
        telemetry.addData("liftError", error);
        telemetry.addData("liftPower", power);

        telemetry.addData("liftTarget", liftHeight);
        telemetry.addData("liftPosition", liftMotor.getCurrentPosition());
    }
//    public void setLiftUp() {
//        liftMotor.setPower(0.6);
//    }

    public void increaseLevel(){
        levelCounter += 1;
        if (levelCounter >= 6){
            levelCounter = 5;
        }

    }
    public void decreaseLevel() {
        levelCounter -= 1;
        if (levelCounter <= 0) {
            levelCounter = 0;
        }
    }

    public void updateLevel(){
        telemetry.addData("Level Counter", levelCounter);
        switch (levelCounter){
            case 0:
                liftState = LiftState.ZERO;
                break;
            case 1:
                liftState = LiftState.ONE;
                break;
            case 2:
                liftState = LiftState.TWO;
                break;
            case 3:
                liftState = LiftState.THREE;
                break;
            case 4:
                liftState = LiftState.FOUR;
                break;
            case 5:
                liftState = LiftState.FIVE;
                break;


        }
    }
//    public void setLiftDown () {
//        liftMotor.setPower(-0.6);
//    }
//
//    public void setRawPower (double power){
//        liftMotor.setPower(power);
//    }
//
    public void setLiftZero () {
        liftState = LiftState.ZERO;
    }
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

}

