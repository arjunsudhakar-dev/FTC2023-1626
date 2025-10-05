// This file defines an autonomous program for the robot.
// Autonomous programs are used to control the robot without driver input during the autonomous period of a match.
// This program uses encoders to move the robot a specific distance or turn a specific angle.
// Future Changes: The autonomous strategy, motor configurations, and movement logic may need to be updated
// based on the new robot design and the specific requirements of the game challenge.

package org.firstinspires.ftc.teamcode.auto;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

// Annotation to declare this as an autonomous OpMode
@Autonomous(name = "Auto1")
public class Auto1 extends LinearOpMode {

    /* Declare OpMode members. */
    private ElapsedTime runtime = new ElapsedTime(); // Timer to track elapsed time during the autonomous period.

    // Constants for encoder calculations
    static final double COUNTS_PER_MOTOR_REV = 3600; // Number of encoder counts per motor revolution.
    // Future Changes: This value depends on the motor and encoder being used. If
    // the motor or encoder changes,
    // this value will need to be updated.

    static final double DRIVE_GEAR_REDUCTION = 12.0; // Gear reduction ratio (output/input).
    // Future Changes: If the drivetrain uses a different gear ratio, this value
    // will need to be updated.

    static final double WHEEL_DIAMETER_INCHES = 3.0; // Diameter of the wheels in inches.
    // Future Changes: If the robot uses wheels of a different size, this value will
    // need to be updated.

    static final double COUNTS_PER_INCH = (COUNTS_PER_MOTOR_REV * DRIVE_GEAR_REDUCTION) /
            (WHEEL_DIAMETER_INCHES * 3.1415); // Encoder counts required to move the robot 1 inch.
    // Future Changes: This value is calculated based on the motor, gear ratio, and
    // wheel size. If any of these
    // change, this calculation will need to be updated.

    static final double DRIVE_SPEED = 0.4; // Default speed for driving forward/backward.
    // Future Changes: The speed may be adjusted based on the robot's weight,
    // drivetrain, or game requirements.

    static final double TURN_SPEED = 0.5; // Default speed for turning.
    // Future Changes: The turning speed may need to be adjusted for better control
    // or faster turns.

    // Declare the motors used in the drivetrain
    private DcMotor front_left = null; // Front left motor
    private DcMotor front_right = null; // Front right motor
    // Future Changes: If the drivetrain changes (e.g., to tank drive or additional
    // motors), more motors may need
    // to be added or removed here.

    // Additional motors or servos can be declared here if needed for the autonomous
    // routine.
    // For example:
    private DcMotor back_left = null;
    private DcMotor back_right = null;
    private DcMotor elevator = null;
    private Servo grabber = null;
    Servo servo;
    int ServoPosition = 1;
    int elevatorZero = 0;

    @Override
    public void runOpMode() {
        // This method will contain the logic for the autonomous routine.
        // It will be executed when the autonomous period starts.
        // Future Changes: The logic in this method will need to be updated based on the
        // game challenge.

        front_left = hardwareMap.get(DcMotor.class, "FrontL0");
        front_right = hardwareMap.get(DcMotor.class, "BackL2");
        back_left = hardwareMap.get(DcMotor.class, "FrontR1");
        back_right = hardwareMap.get(DcMotor.class, "BackR3");
        elevator = hardwareMap.get(DcMotor.class, "elevator");
        elevatorZero = elevator.getCurrentPosition();
        servo = hardwareMap.get(Servo.class, "Servo0");

        front_left.setDirection(DcMotor.Direction.REVERSE);
        back_left.setDirection(DcMotor.Direction.REVERSE);

        front_right.setDirection(DcMotor.Direction.FORWARD);
        back_right.setDirection(DcMotor.Direction.FORWARD);
        // Send telemetry message to signify robot waiting;
        telemetry.addData("Status", "Resetting Encoders");
        telemetry.update();

        // Wait for the game to start (driver presses PLAY)
        waitForStart();

        front_left.setPower(-0.5);
        front_right.setPower(0.5);
        back_left.setPower(0.5);
        back_right.setPower(-0.5);

        sleep(1500);
        front_left.setPower(0);
        front_right.setPower(0);
        back_left.setPower(0);
        back_right.setPower(0);
        telemetry.addData("Path", "Complete");
        telemetry.update();
    }
}
// Things you need to code for:
// Robot needs to move toward arrow marks on the field
// Sense the april tag on the goal, reposition itself to the perfect
// orientation, then shoot the three balls into the goal, then back up, and
// turn, and sense the april tag on the obilisque to figure out the order of the
// balls, then use an if function to determine if it's worth going for those
// know the place the robot is on the field
// 4 different autos from the different locations they can start from