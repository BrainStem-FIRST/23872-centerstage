package org.firstinspires.ftc.teamcode.robot;

import static org.firstinspires.ftc.teamcode.robot.Hanging.ServoState.LOCK;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.ServoImplEx;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.utils.CachingServo;

public class Drone {
    private ServoImplEx droneServo;

    private HardwareMap hardwareMap;

    private Telemetry telemetry;
    public ServoState servoState = Drone.ServoState.CLASP;

    public Drone(HardwareMap hardwareMap, Telemetry telemetry) {
        this.telemetry = telemetry;
        this.hardwareMap = hardwareMap;

        droneServo = new CachingServo(hardwareMap.get(ServoImplEx.class, "DroneServo"));
    }

    public enum ServoState {
        CLASP, RELEASE
    }

    public void setServoState() {
        switch (servoState) {
            case CLASP: {
                lockServo();
                break;
            }
            case RELEASE: {
                unlockServo();
                break;
            }
        }
    }
    public void setLockState(){
        servoState = ServoState.CLASP;
    }

    public void setUnlockState(){
        servoState = ServoState.RELEASE;
    }
    public void lockServo() {
        droneServo.setPosition(0.01);
    }

    public void unlockServo() {
        droneServo.setPosition(0.99);
    }
}





