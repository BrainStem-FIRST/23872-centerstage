package org.firstinspires.ftc.teamcode.robot;

import static org.firstinspires.ftc.teamcode.robot.CollectorTele.CollectorState.IN;
import static org.firstinspires.ftc.teamcode.robot.CollectorTele.CollectorState.OFF;
import static org.firstinspires.ftc.teamcode.robot.CollectorTele.CollectorState.OUT;

import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.PwmControl;
import com.qualcomm.robotcore.hardware.ServoImplEx;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.util.CachingMotor;
import org.firstinspires.ftc.teamcode.utils.CachingServo;

import java.security.SecureRandom;

public class CollectorTele {
    private final DcMotorEx CollectorMotor;
    public CollectorState collectorState = CollectorState.OFF;
    private HardwareMap hardwareMap;
    private final Telemetry telemetry;

    public CollectorTele(HardwareMap hardwareMap,Telemetry telemetry) {
        this.telemetry = telemetry;
        this.hardwareMap = hardwareMap;

        CollectorMotor = new CachingMotor(hardwareMap.get(DcMotorEx.class, "Collector"));
    }
    public enum CollectorState {
        OFF, IN, OUT
    }


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

    public void setCollectorOff(){
        collectorState = CollectorState.OFF;
    }

    public void setCollectorIn(){
        collectorState = CollectorState.IN;
    }

    public void setCollectorOut(){
        collectorState = CollectorState.OUT;
    }

    private void collectorOff() {CollectorMotor.setPower(0);}
    private void collectorIn(){
        CollectorMotor.setPower(0.5);
    }
    private void collectorOut() { CollectorMotor.setPower(-0.5);
    }

}
