package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

// We will need some operators from this library
import Math;

@TeleOp(name="Template: Basic Mecanum Drive", group="Linear Opmode")
public class BasicMecanumDrive extends LinearOpMode {
    // Declare the hardware variables
    private DcMotor left_front, right_front;
    private DcMotor left_back, right_back;

    @Override
    public void runOpMode() {
        // Initialize the hardware variables. Note that the strings used here as
        // parameters
        // to 'get' must correspond to the names assigned during the robot configuration
        leftFront = hardwareMap.get(DcMotor.class, "left_front");
        rightFront = hardwareMap.get(DcMotor.class, "right_front");
        leftBack = hardwareMap.get(DcMotor.class, "left_back");
        rightBack = hardwareMap.get(DcMotor.class, "right_back");

        // One of the pairs of motors needs to be reversed
        rightFront.setDirection(DcMotor.Direction.REVERSE);
        rightBack.setDirection(DcMotor.Direction.REVERSE);

        // Wait for the drive to press the Start button on the Driver Hub
        waitForStart();

        // Loop until the robot is stopped
        while (opModeIsActive()) {
            // The left joystick controls the translation of the robot,
            // while the right joystick controls the rotation.

            // Get the gamepad inputs
            // The y axis is vertical and "backwards",
            // so we negate it to make it positive when going forward
            //     ^ +y
            //     |
            // <-     ->
            // -x  |  +x
            //     v -y
            float deltaY = -gamepad1.left_stick_y;
            float deltaX = gamepad1.left_stick_x;
            // Rotate by moving right stick left-right
            float rotation = gamepad1.right_stick_x;

            // First, we need to split the translation vector into a direction and a magnitude.
            // The direction is the direction to move
            float direction = Math.atan2(deltaY, deltaX);
            // The magnitude is how fast to move
            float magnitude = Math.sqrt(Math.pow(deltaX, 2) + Math.pow(deltaY, 2));

            // TODO Explain these calculations
            float leftFrontPower = magnitude * Math.sin(direction + Math.pi/4) + rotation;
            float leftBackPower = magnitude * Math.sin(direction - Math.pi/4) + rotation;
            float rightFrontPower = magnitude * Math.sin(direction - Math.pi/4) - rotation;
            float rightBackPower = magnitude * Math.sin(direction + Math.pi/4) - rotation;

            // All of the values must be scaled to be within [-1,1]
            // First, find the highest value
            float maxPower = Math.max(leftFrontPower, Math.max(rightBackPower, Math.max(leftBackPower, rightBackPower)));
            // Then divide all the powers by that value.
            leftFrontPower = leftFrontPower / maxPower;
            leftBackPower = leftBackPower / maxPower;
            rightFrontPower = rightFrontPower / maxPower;
            rightBackPower = rightBackPower / maxPower;

            // Set the motor power
            leftFront.setPower(leftFrontPower);
            leftBack.setPower(leftBackPower);
            rightFront.setPower(rightFrontPower);
            rightBack.setPower(rightBackPower);
        }
    }
}
