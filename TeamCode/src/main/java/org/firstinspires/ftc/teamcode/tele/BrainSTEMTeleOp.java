package org.firstinspires.ftc.teamcode.tele;

import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.PoseVelocity2d;
import com.acmerobotics.roadrunner.Twist2d;
import com.acmerobotics.roadrunner.Vector2d;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.MecanumDrive;
import org.firstinspires.ftc.teamcode.TankDrive;
import org.firstinspires.ftc.teamcode.robot.BrainSTEMRobot;

import java.util.HashMap;
import java.util.Map;

@TeleOp (name = "TeleOp", group = "Robot")
public class BrainSTEMTeleOp extends LinearOpMode {
    @Override
    public void runOpMode() {
        MecanumDrive drive = new MecanumDrive(hardwareMap, new Pose2d(0, 0, 0));
        BrainSTEMRobot robot = new BrainSTEMRobot(hardwareMap, telemetry);
        double power = 0.0;
        StickyButton stickyButtonA = new StickyButton();
        StickyButton stickyButtonB = new StickyButton();


        waitForStart();

        while (opModeIsActive()) {
//            setButtons();
            drive.setDrivePowers(new PoseVelocity2d(
                    new Vector2d(
                            -gamepad1.left_stick_y,
                            -gamepad1.left_stick_x
                    ),
                    -gamepad1.right_stick_x * 0.75));

            drive.updatePoseEstimate();

            telemetry.addData("Tele Collector State", "TEST");
            telemetry.update();
//collector
            if (gamepad1.right_trigger > 0.2) {
                robot.collector.setCollectorIn();
                robot.collector.setCollectorState();
                robot.transfer.setTransferIn();
                robot.transfer.transferState();
            } else if (gamepad1.left_trigger > 0.2) {
                robot.collector.setCollectorOut();
                robot.collector.setCollectorState();
                robot.transfer.setTransferOut();
                robot.transfer.transferState();
            } else {
                robot.collector.setCollectorOff();
                robot.collector.setCollectorState();
                robot.transfer.setTransferOff();
                robot.transfer.transferState();
            }

//pixel holder
            if (gamepad1.left_bumper) {
                robot.depositor.setDropState();
            } else if (gamepad1.right_bumper) {
                robot.depositor.setHoldState();
            }
//depositor
            if (gamepad1.x) {
                robot.depositor.setRestingState();
                robot.depositor.depositorServoState();
            } else if (gamepad1.y) {
                robot.depositor.setScoringState();
                robot.depositor.depositorServoState();
            }
//hanging wind
            if (gamepad2.x) {
                robot.hanging.setHangingUnwind();
            } else if (gamepad2.y) {
                robot.hanging.setHangingWind();
            }
//hanging servo
            if (gamepad2.left_bumper) {
                robot.hanging.setLockState();
            } else if (gamepad2.right_bumper) {
                robot.hanging.setUnlockState();
            }
//lift
//            if (gamepad1.a) {
//                robot.lift.setLiftHeight(0);
//                robot.lift.levelCounter();
//                robot.lift.updateLevelCounter();
//            } else if (gamepad1.b) {
//                robot.lift.setLiftHeight(788);
//            }
//
//            if (stickyButtonA.getState()) {
//                robot.lift.levelCounter += 1;
//            } else if (stickyButtonB.getState()) {
//                robot.lift.levelCounter -= 1;
//            }
            robot.update();
        }

    }
}
