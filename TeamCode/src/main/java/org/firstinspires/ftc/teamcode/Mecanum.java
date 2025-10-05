// Stays the same throughout the years, (unless we use a different motor)
package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;

@TeleOp(name = "Mecanum Drive", group = "Iterative Opmode")
public class Mecanum extends OpMode {
    // This creates a true/false flag to keep track of whether the robot’s OpMode is
    // running. Initially, it’s false because the robot hasn’t started.
    private boolean opmodeactive = false;

    // declare and initialize four drive DcMotors (controls movement)
    // = null means we haven’t connected it to the actual hardware yet — that
    // happens later
    private DcMotor front_left = null;
    private DcMotor front_right = null;
    private DcMotor back_left = null;
    private DcMotor back_right = null;

    // declare and intilizae arm motor and rotator motor(rotates robot)
    private DcMotor elbow = null;
    private DcMotor rotator = null;
    // what can change: The company(DcMotor) will change, we may not have the same
    // arm or rotator eithier. So every year, you declare the motors you actually
    // have on your robot, with names that make sense for that design.

    // These are placeholders for servo motors, which are motors that rotate to a
    // specific position.
    Servo servo1;
    Servo servo0;
    Servo shooter;
    Servo wrist;
    Servo grabber;
    // If your robot doesn’t have a shooter, wrist, or grabber this year, you won’t
    // need those servos.You may add new servos for new mechanisms like a rotating
    // arm, tray, or launcher.The names you give them should match the new robot
    // design and hardware configuration.

    int ServoPosition = 1; // Tracks the current position of a servo motor.
    // This variable is used to control or monitor the position of a specific servo.
    // Future Change: The number of servos or their roles may change, so this
    // variable might be renamed, removed, or expanded.

    int elevatorZero = 0; // Represents the "zero" or starting position of the elevator mechanism.
    // This is likely the bottom-most position of the elevator.
    // Future Change: If the new robot doesn't have an elevator, this variable can
    // be removed. If the elevator design changes, this value might need to be
    // recalibrated.

    @Override
    public void init() {
        opmodeactive = true; // Indicates that the operational mode is active.
        // This is likely used to control the robot's behavior during the match.
        // Future Change: This might remain the same unless the control logic changes
        // significantly.

        // Name strings must match up with the config on the Robot Controller
        // app.
        front_left = hardwareMap.get(DcMotor.class, "motor0"); // Front left motor
        // Future Change: The name "motor0" will need to be updated to match the new
        // robot's configuration.
        // The role of this motor (e.g., front left) might also change depending on the
        // drivetrain.

        front_right = hardwareMap.get(DcMotor.class, "BackL2"); // Front right motor
        // Future Change: Update the name "BackL2" to match the new configuration.

        back_left = hardwareMap.get(DcMotor.class, "FrontR1"); // Back left motor
        // Future Change: Update the name "FrontR1" to match the new configuration.

        back_right = hardwareMap.get(DcMotor.class, "BackR3"); // Back right motor
        // Future Change: Update the name "BackR3" to match the new configuration.

        elbow = hardwareMap.get(DcMotor.class, "FrontL0"); // Motor controlling the elbow mechanism (e.g., an arm joint)
        // Future Change: If the new robot doesn't have an elbow mechanism, this motor
        // can be removed.
        // If the mechanism changes, the name "FrontL0" will need to be updated.

        rotator = hardwareMap.get(DcMotor.class, "motor1"); // Motor controlling a rotating mechanism (e.g., a turret)
        // Future Change: If the new robot doesn't have a rotator, this motor can be
        // removed.
        // If the mechanism changes, the name "motor1" will need to be updated.

        // Initialize the servos by mapping them to their names in the Robot Controller
        // configuration.
        servo0 = hardwareMap.get(Servo.class, "Servo0"); // Servo 0 (e.g., for a claw or other mechanism)
        // Future Change: Update the name "Servo0" to match the new configuration.

        servo1 = hardwareMap.get(Servo.class, "Servo1"); // Servo 1 (e.g., for another mechanism)
        // Future Change: Update the name "Servo1" to match the new configuration.

        shooter = hardwareMap.get(Servo.class, "Servo2"); // Servo controlling the shooter mechanism
        // Future Change: If the new robot doesn't have a shooter, this servo can be
        // removed.
        // If the shooter design changes, the name "Servo2" will need to be updated.

        // Additional servos
        wrist = hardwareMap.get(Servo.class, "Servo3"); // Servo controlling the wrist mechanism
        // Future Change: Update the name "Servo3" or remove this if the wrist mechanism
        // changes or is removed.

        grabber = hardwareMap.get(Servo.class, "Servo4"); // Servo controlling the grabber mechanism
        // Future Change: Update the name "Servo4" or remove this if the grabber
        // mechanism changes or is removed.

        // Set the direction of the motors to ensure they spin correctly for the robot's
        // movement.
        front_left.setDirection(DcMotor.Direction.REVERSE); // Reverse the front left motor
        back_left.setDirection(DcMotor.Direction.REVERSE); // Reverse the back left motor
        // Future Change: The directions might need to be adjusted depending on how the
        // motors are mounted on the new robot.

        front_right.setDirection(DcMotor.Direction.REVERSE); // Reverse the front right motor
        back_right.setDirection(DcMotor.Direction.REVERSE); // Reverse the back right motor
        // Future Change: The directions might need to be adjusted depending on the
        // drivetrain configuration.

        elbow.setDirection(DcMotor.Direction.FORWARD); // Set the elbow motor to spin forward
        // Future Change: The direction might need to be adjusted depending on the new
        // mechanism.

        shooter.setPosition(0); // Set the initial position of the shooter servo to 0
        // Future Change: The initial position might need to be adjusted depending on
        // the new shooter design.
    }

    @Override
    public void loop() {
        double drive; // Represents forward/backward movement.
        double strafe; // Represents left/right movement.
        double twist; // Represents rotational movement (turning).

        // Check if the right bumper on gamepad2 is pressed.
        if (gamepad2.right_bumper) {
            // Full-speed control for drive, strafe, and twist using gamepad2 sticks.
            drive = gamepad2.right_stick_x;
            strafe = gamepad2.left_stick_y;
            twist = gamepad2.left_stick_x;
        } else {
            // Reduced-speed control (40% of full speed) for more precise movement.
            drive = gamepad2.right_stick_x * 0.4;
            strafe = gamepad2.left_stick_y * 0.4;
            twist = gamepad2.left_stick_x * 0.4;
        }

        // Check if the dpad_left button on gamepad2 is pressed.
        if (gamepad2.dpad_left) {
            double startTime = getRuntime(); // Record the current runtime.

            // Perform a specific movement for 1 second.
            while (opmodeactive && (getRuntime() - startTime) < 1.0) {
                front_left.setPower(1.0); // Set front left motor to full power.
                front_right.setPower(1.0); // Set front right motor to full power.
                back_left.setPower(-1.0); // Set back left motor to reverse full power.
                back_right.setPower(-1.0); // Set back right motor to reverse full power.
            }

            // Stop all motors after the movement is complete.
            front_left.setPower(0);
            front_right.setPower(0);
            back_left.setPower(0);
            back_right.setPower(0);
        }

        // Control the elbow motor using gamepad1 buttons.
        if (gamepad1.y) {
            elbow.setPower(0.3); // Move the elbow motor forward at 30% power.
        } else if (gamepad1.x) {
            elbow.setPower(-0.3); // Move the elbow motor backward at 30% power.
        } else {
            elbow.setPower(0); // Stop the elbow motor.
        }

        // Control the rotator motor using the left stick on gamepad1.
        if (gamepad1.left_stick_y > 0.05 || gamepad1.left_stick_y < -0.05) {
            rotator.setPower(gamepad1.left_stick_y * 0.4); // Move the rotator at 40% power.
        } else {
            rotator.setPower(0); // Stop the rotator motor.
        }

        // Control the wrist servo using the right stick on gamepad1.
        int wristInput = (int) (gamepad1.right_stick_y * 10); // Scale input for servo position.
        wrist.setPosition(wristInput); // Set the wrist servo position.

        // Control the grabber servo using gamepad1 buttons.
        if (gamepad1.a) {
            grabber.setPosition(0.2); // Set grabber to a specific position (e.g., open).
        }
        if (gamepad1.b) {
            grabber.setPosition(0); // Set grabber to another position (e.g., closed).
        }

        // Control the shooter servo using the dpad_up button on gamepad1.
        if (gamepad1.dpad_up) {
            shooter.setPosition(0.23); // Set shooter to a specific position (e.g., ready to shoot).
        } else {
            shooter.setPosition(0); // Reset shooter to its default position.
        }

        // Calculate motor speeds for mecanum drive based on drive, strafe, and twist
        // inputs.
        double[] speeds = {
                (drive + strafe + twist), // Front left motor speed.
                (drive - strafe - twist), // Front right motor speed.
                (drive - strafe + twist), // Back left motor speed.
                (drive + strafe - twist) // Back right motor speed.
        };

        // Find the maximum speed to normalize motor speeds if necessary.
        double max = Math.abs(speeds[0]);
        for (int i = 0; i < speeds.length; i++) {
            if (max < Math.abs(speeds[i]))
                max = Math.abs(speeds[i]);
        }

        // Normalize motor speeds if any speed exceeds 1.0.
        if (max > 1) {
            for (int i = 0; i < speeds.length; i++)
                speeds[i] /= max;
        }

        // Set the power for each motor based on the calculated speeds.
        front_left.setPower(speeds[0]);
        front_right.setPower(speeds[1]);
        back_left.setPower(speeds[2]);
        back_right.setPower(speeds[3]);

        // Example commented-out code for controlling an elevator motor.
        // float elavatorPower=(gamepad2.right_trigger-gamepad2.left_trigger);
        // if (elavatorPower > 0) {
        // elevator.setPower(elavatorPower * 0.80);
        // } else {
        // elevator.setPower(elavatorPower * 0.50);
        // }

        // Send telemetry data to the driver station for debugging.
        telemetry.addData("strafe", strafe); // Display the strafe value.
        telemetry.update(); // Update the telemetry data.
    }
}
// Things to code for:
// Know the motor combinations so you know which motors go to which speed to
// achieve which task
// Calculate speed, strafe, and turn.
