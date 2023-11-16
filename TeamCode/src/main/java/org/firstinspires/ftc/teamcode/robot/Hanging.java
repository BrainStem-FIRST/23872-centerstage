package org.firstinspires.ftc.teamcode.robot;

import static org.firstinspires.ftc.teamcode.robot.Hanging.HangingState.REST;
import static org.firstinspires.ftc.teamcode.robot.Hanging.HangingState.UNWIND;
import static org.firstinspires.ftc.teamcode.robot.Hanging.HangingState.WIND;
import static org.firstinspires.ftc.teamcode.robot.Hanging.ServoState.LOCK;
import static org.firstinspires.ftc.teamcode.robot.Hanging.ServoState.UNLOCK;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.PwmControl;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.ServoImplEx;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.util.CachingMotor;
import org.firstinspires.ftc.teamcode.utils.CachingServo;

public class Hanging {
    private final DcMotorEx hangingMotor;
    private ServoImplEx leftClimber;
    private ServoImplEx rightClimber;
    private static final double LEFT_CLIMBER_MAX = 700;
    private static final double LEFT_CLIMBER_MIN = 550;
    private static final double RIGHT_CLIMBER_MAX = 2150;
    private static final double RIGHT_CLIMBER_MIN = 2300;
    private HardwareMap hardwareMap;
    private Telemetry telemetry;

    public Hanging(HardwareMap hardwareMap, Telemetry telemetry){
        this.telemetry = telemetry;
        this.hardwareMap = hardwareMap;

        hangingMotor = new CachingMotor(hardwareMap.get(DcMotorEx.class, "Hanging"));
        hangingMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        leftClimber = new CachingServo(hardwareMap.get(ServoImplEx.class, "LClimber"));
        rightClimber = new CachingServo(hardwareMap.get(ServoImplEx.class, "RClimber"));

        leftClimber.setPwmRange(new PwmControl.PwmRange(LEFT_CLIMBER_MAX, LEFT_CLIMBER_MIN));
        rightClimber.setPwmRange(new PwmControl.PwmRange(RIGHT_CLIMBER_MAX, RIGHT_CLIMBER_MIN));
    }

    public enum ServoState {
        LOCK, UNLOCK
    }

    ServoState servoState = LOCK;

    public void setServoState() {
        switch (servoState) {
            case LOCK: {
                lockServo();
                break;
            }
            case UNLOCK: {
                unlockServo();
                break;
            }
        }
    }

    public void lockServo() {
        leftClimber.setPosition(0.01);
        rightClimber.setPosition(0.01);
    }
    public void unlockServo() {
        leftClimber.setPosition(0.99);
        rightClimber.setPosition(0.99);
    }

    public void setLockState() {servoState = ServoState.LOCK;}
    public void setUnlockState() {servoState = ServoState.UNLOCK;}


    public enum HangingState {
        REST, UNWIND, WIND
    }

    HangingState hangingState = REST;

    public void hangingState() {
        switch (hangingState) {
            case REST: {
                hangingRest();
                break;
            }
            case WIND: {
                hangingWind();
                break;
            }
            case UNWIND: {
                hangingUnwind();
                break;
            }
        }
    }

    public void setHangingRest() {hangingState = HangingState.REST;}
    public void setHangingWind() {hangingState = HangingState.WIND;}
    public void setHangingUnwind() {hangingState = HangingState.UNWIND;}

    private void hangingRest() {hangingMotor.setPower(0);}
    private void hangingWind() {hangingMotor.setPower(1);}
    private void hangingUnwind() {hangingMotor.setPower(-1);}

}





