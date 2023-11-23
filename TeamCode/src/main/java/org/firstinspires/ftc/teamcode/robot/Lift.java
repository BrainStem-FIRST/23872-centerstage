package org.firstinspires.ftc.teamcode.robot;

import static org.firstinspires.ftc.teamcode.robot.Lift.LiftState.FIVE;
import static org.firstinspires.ftc.teamcode.robot.Lift.LiftState.FOUR;
import static org.firstinspires.ftc.teamcode.robot.Lift.LiftState.ONE;
import static org.firstinspires.ftc.teamcode.robot.Lift.LiftState.THREE;
import static org.firstinspires.ftc.teamcode.robot.Lift.LiftState.TWO;
import static org.firstinspires.ftc.teamcode.robot.Lift.LiftState.ZERO;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class Lift {

    private DcMotorEx liftMotor;
    private final HardwareMap hardwareMap;
    private final Telemetry telemetry;


    public LiftState liftState;

    private final static double kP = 0.1;
    private final static double kI = 0.0;
    private final static double kD = 0.0;
    private final static double kS = 0.005;
    PIDFController pidController = new PIDFController(kP, kI, kD);
    private final static int levelZeroHeight = 0;
    private final static int levelOneHeight = 108;
    private final static int levelTwoHeight = 326;
    private final static int levelThreeHeight = 553;
    private final static int levelFourHeight = 788;
    private final static int levelFiveHeight = 1000;
    private final int heightCounter = 0;
    private int liftHeight;
//    private final static int levelSixHeight = 600;
//    private final static int levelSevenHeight = 700;
//    private final static int levelEightHeight
//    ht = 800;


    public Lift(HardwareMap hardwareMap, Telemetry telemetry) {
        this.liftMotor = liftMotor;
        this.hardwareMap = hardwareMap;
        this.telemetry = telemetry;


        pidController.setInputBounds(0, 1000);
        pidController.setOutputBounds(0, 1);
        liftMotor = new org.firstinspires.ftc.teamcode.util.CachingMotor(hardwareMap.get(DcMotorEx.class, "Lift"));
        liftMotor.setMode(DcMotorEx.RunMode.RUN_USING_ENCODER);
        liftMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
    }


    public enum LiftState {
        ZERO, ONE, TWO, THREE, FOUR, FIVE, SIX, SEVEN, EIGHT
    }
    public void liftState() {
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
        liftMotor.setPower(pidController.updateWithError(error) + kS);
//    public void setLiftUp() {
//        liftMotor.setPower(0.6);
//    }
    }

    public void updateLevelCounter() {

    }
    public void levelCounter(){
        if (heightCounter == 0) {
            liftState = ZERO;
        } else if(heightCounter == 108){
            //184
            liftState = ONE;
        } else if(heightCounter == 326){
            //466
            liftState = TWO;
        } else if(heightCounter == 553){
            //686
            liftState = THREE;
        } else if(heightCounter == 788){
            //932
            liftState = FOUR;
        } else if(heightCounter == 1000){
            liftState = FIVE;
        }

    }


    public void setLiftDown () {
        liftMotor.setPower(-0.6);
    }

    public void setLiftOff () {
        liftMotor.setPower(0);
    }

    public void setRawPower ( double power){
        liftMotor.setPower(power);
    }

    public void setLiftZero () {
        liftState = ZERO;
    }
    public void setLiftFour () {
        liftState = FOUR;
    }

    public void setLiftOne () {
        liftState = ONE;
    }
    public int getPosition () {
        return liftMotor.getCurrentPosition();
    }

}
