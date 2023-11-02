package org.firstinspires.ftc.teamcode.robot;

import static org.firstinspires.ftc.teamcode.robot.Collector.CollectorState.IN;
import static org.firstinspires.ftc.teamcode.robot.Collector.CollectorState.OFF;
import static org.firstinspires.ftc.teamcode.robot.Collector.CollectorState.OUT;

import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.util.CachingMotor;

public class Transfer {
    private Telemetry telemetry;
    private DcMotorEx transferMotor;
    private HardwareMap hardwareMap;
    public Transfer(HardwareMap hardwareMap,Telemetry telemetry) {
        this.telemetry=telemetry;
        this.hardwareMap=hardwareMap;

        transferMotor = new CachingMotor(hardwareMap.get(DcMotorEx.class,"transfer"));
    }
    public enum TransferState {
        OFF, IN, OUT
    }

    Transfer transfer;

    TransferState transferState = OFF;
    
    private void transferState() {
        switch (transfer) {
            case OFF: {
                transferOff();
                break;
            }
            case IN: {
                transferIn();
                break;
            }
            case OUT: {
                transferOut();
                break;
            }
        }
    }

    public void transferOff(){
        transferMotor.setPower(0);
    }
    public void transferIn(){
        transferMotor.setPower(0.5);
    }
    public void transferOut(){
        transferMotor.setPower(-0.5);
    }
}
