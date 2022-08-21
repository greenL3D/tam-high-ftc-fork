package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

@TeleOp
public class TankDrive extends LinearOpMode {
  private DcMotor left_front, right_front;
  private DcMotor left_back, right_back;

  @Override
  public void runOpMode() {
    leftFront = hardwareMap.get(DcMotor.class, "left_front");
    rightFront = hardwareMap.get(DcMotor.class, "right_front");
    leftBack = hardwareMap.get(DcMotor.class, "left_back");
    rightBack = hardwareMap.get(DcMotor.class, "right_back");

    waitForStart();

    while (opModeIsActive()) {
    
    
    }
  }
}
