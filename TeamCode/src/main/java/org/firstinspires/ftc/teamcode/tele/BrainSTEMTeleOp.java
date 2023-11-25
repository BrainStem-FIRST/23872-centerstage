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
        StickyButton stickyButtonRightBumper = new StickyButton();
        StickyButton stickyButtonLeftBumper = new StickyButton();
        StickyButton stickyButtonA = new StickyButton();
        StickyButton stickyButtonB = new StickyButton();


        waitForStart();

        while (opModeIsActive()) {
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
                robot.transfer.setTransferIn();
            } else if (gamepad1.left_trigger > 0.2) {
                robot.collector.setCollectorOut();
                robot.transfer.setTransferOut();
            } else {
                robot.collector.setCollectorOff();
                robot.transfer.setTransferOff();
            }

//pixel holder
            if (gamepad1.right_bumper) {
                robot.depositor.setDropState();
            } else if (gamepad1.left_bumper) {
                robot.depositor.setHoldState();
            }
//hanging wind
//            if (gamepad2.x) {
//                robot.hanging.setHangingUnwind();
//            } else if (gamepad2.y) {
//                robot.hanging.setHangingWind();
//            }
//hanging servo
            if (gamepad2.x) {
                robot.hanging.setLockState();
            } else if (gamepad2.y) {
                robot.hanging.setUnlockState();
            }
//drone release
//            if (gamepad2.a) {
//                robot.drone.setClaspServo();
//            } else if (gamepad2.b) {
//                robot.drone.setReleaseServo();
//            }
//lift and depositor
//
            stickyButtonA.update((gamepad1.a));
            stickyButtonB.update(gamepad1.b);
            if (stickyButtonA.getState()) {
                robot.lift.setLiftOne();
            } else if (stickyButtonB.getState()) {
                robot.lift.setLiftZero();
            }

//            stickyButtonA.update((gamepad1.a));
//            stickyButtonB.update(gamepad1.b);
//            if (stickyButtonA.getState()){
//                robot.lift.updateLevel();
//                robot.lift.increaseLevel();
//            }
//            else if(stickyButtonB.getState()) {
//                robot.lift.updateLevel();
//                robot.lift.decreaseLevel();
//            }
//depositor for backup
            if (gamepad1.x) {
                robot.depositor.setScoringState();
            } else if (gamepad1.y) {
                robot.depositor.setRestingState();
            }

            robot.update();
        }
    }

}





