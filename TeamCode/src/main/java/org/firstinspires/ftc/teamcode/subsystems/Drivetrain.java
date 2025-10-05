// The Drivetrain package is responsible for controlling the robot's movement system.
// It provides methods to control the motors for forward/backward movement, strafing, rotation, and holonomic drive.
// This class is designed to work with a mecanum drivetrain but can be adapted for other drivetrains if needed.
// Future Changes: If the drivetrain design changes (e.g., switching to tank drive or adding more wheels), 
// the methods and motor configurations in this class will need to be updated accordingly.

package org.firstinspires.ftc.teamcode.subsystems;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class Drivetrain {

    // Declare the four motors that control the drivetrain
    public DcMotor frontLeft; // Front left motor
    public DcMotor frontRight; // Front right motor
    public DcMotor backLeft; // Back left motor
    public DcMotor backRight; // Back right motor

    // Method to rotate the robot to the right
    public void rotateRight(double power) {
        // Set the right motors to move backward and the left motors to move forward
        frontRight.setPower(-power);
        backRight.setPower(-power);
        frontLeft.setPower(power);
        backLeft.setPower(power);

        // Future Changes: If the drivetrain changes (e.g., to tank drive), this method
        // might need to be rewritten.
        // For example, a tank drive might rotate by setting one side to positive power
        // and the other side to negative power.
    }

    // Method to strafe (move sideways) to the left
    public void strafeLeft(double power) {
        // Set the motors to move in a pattern that causes the robot to strafe left
        frontRight.setPower(power);
        backRight.setPower(-power);
        frontLeft.setPower(-power);
        backLeft.setPower(power);

        // Future Changes: Strafing is specific to mecanum or omnidirectional wheels.
        // If the drivetrain changes (e.g., to tank drive), strafing might not be
        // possible, and this method could be removed.
    }

    // Method to initialize the drivetrain motors
    public void init(HardwareMap hardwareMap) {
        // Map the motors to their names in the hardware configuration
        frontLeft = hardwareMap.get(DcMotor.class, "frontLeft");
        frontRight = hardwareMap.get(DcMotor.class, "frontRight");
        backLeft = hardwareMap.get(DcMotor.class, "backLeft");
        backRight = hardwareMap.get(DcMotor.class, "backRight");

        // Reverse the direction of the left motors to ensure proper forward movement
        frontLeft.setDirection(DcMotorSimple.Direction.REVERSE);
        backLeft.setDirection(DcMotorSimple.Direction.REVERSE);

        // Set all motors to zero power to prevent unintended movement during
        // initialization
        frontLeft.setPower(0);
        frontRight.setPower(0);
        backLeft.setPower(0);
        backRight.setPower(0);

        // Future Changes: The motor names ("frontLeft", "frontRight", etc.) will need
        // to match the new robot's configuration.
        // The directions (REVERSE or FORWARD) might need to be adjusted depending on
        // how the motors are mounted.
    }

    // Method to configure the motors to use encoders for precise control
    public void setDriveUsingEncoders() {
        frontLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        frontRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        backLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        backRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        // Future Changes: If the new robot doesn't use encoders, this method might be
        // removed.
        // If additional motors are added (e.g., for a 6-wheel drivetrain), they will
        // need to be included here.
    }

    // Method to set all motors to the same power (e.g., for forward or backward
    // movement)
    public void driveAll(double power) {
        frontLeft.setPower(power);
        frontRight.setPower(power);
        backLeft.setPower(power);
        backRight.setPower(power);

        // Future Changes: This method will remain useful for most drivetrains, but the
        // number of motors might change
        // depending on the new robot's design.
    }

    // Method to control the drivetrain in a holonomic (mecanum) fashion
    // [button]: button to enable slow mode
    // [percentage]: how slow the robot should go in slow mode
    // [y]: forward/backward movement
    // [x]: strafing (left/right movement)
    // [rx]: rotational movement (turning)
    public void setDrivePower(float button, double percentage, double y, double x, double rx) {
        // Calculate the power for each motor based on the input values
        double frontLeftPower = y + x + rx;
        double backLeftPower = y - x + rx;
        double frontRightPower = y - x - rx;
        double backRightPower = y + x - rx;

        // Normalize the motor powers if any value exceeds 1.0
        if (Math.abs(frontLeftPower) > 1 || Math.abs(backLeftPower) > 1 ||
                Math.abs(frontRightPower) > 1 || Math.abs(backRightPower) > 1) {
            // Find the largest power value
            double max = 0;
            max = Math.max(Math.abs(frontLeftPower), Math.abs(backLeftPower));
            max = Math.max(Math.abs(frontRightPower), max);
            max = Math.max(Math.abs(backRightPower), max);

            // Scale all motor powers by dividing by the maximum value
            frontLeftPower /= max;
            backLeftPower /= max;
            frontRightPower /= max;
            backRightPower /= max;
        }

        // If the slow mode button is pressed, reduce the motor powers by the percentage
        if (button > 0) {
            frontLeft.setPower(frontLeftPower * percentage);
            backLeft.setPower(backLeftPower * percentage);
            frontRight.setPower(frontRightPower * percentage);
            backRight.setPower(backRightPower * percentage);
        } else {
            // Otherwise, set the motor powers to the calculated values
            frontLeft.setPower(frontLeftPower);
            backLeft.setPower(backLeftPower);
            frontRight.setPower(frontRightPower);
            backRight.setPower(backRightPower);
        }

        // Future Changes: This method is specific to mecanum wheels. If the drivetrain
        // changes (e.g., to tank drive),
        // this logic will need to be rewritten. The control scheme (e.g., joystick
        // inputs) might also change based
        // on driver preferences or new challenges.
    }
}
// Things to code for:
// Intake motor, ball manipulator motor, shooter motors (2), servos.