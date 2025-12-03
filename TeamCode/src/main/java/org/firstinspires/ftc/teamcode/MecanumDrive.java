// Stays the same throughout the years, (unless we use a different motor)
package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;

public class MecanumDrive extends Opmode {

    // Initilazing the motor
    // Depending on what motors are flipped you have to make motor negative because
    // rudra won't tell me, we will assume that the RF and RB motors are flipped
    DC RFmotor;
    DC LFmotor;
    DC RBmotor;
    DC LBmotor;

    public boid moveDrivetrain() {
        double horizontal = 0; // Left and right
        double pivot = 0; // Steer our robot, aka rotate the robot in place
        double vertical = 0; // Up and down
        vetical = -gamepad1.left_stick_y; // Gamepad time!
        horizontal = gamepad1.left_stick_x;
        pivot = gamepad1.right_stick_x;

        // Alright... so now we are getting into the math! Will explain using paper and
        // pencil later
        RFMotor.setPower(pivot + (-vertical + horizontal));
        LFMotor.setPower(-pivot + (-vertical - horizontal));
        RBMotor.setPower(pivot + (-vertical - horizontal));
        LBMotor.setPower(-pivot + (-vertical + horizontal));

        // This is so they move forward
        RFMotor.setDirection(DCMotorSimple.Direction.REVERSE);
        RBMotor.setDirection(DCMotorSimple.Direction.REVERSE);

    }

    // Setting up the hardware to the software
    public void init() {
        RFmotor = hardwareMap.get(DCMotor.class, "RFMOTOR");
        LFmotor = hardwareMap.get(DCMotor.class, "LFMOTOR");
        RBmotor = hardwareMap.get(DCMotor.class, "RBMOTOR");
        LBmotor = hardwareMap.get(DCMotor.class, "LBMOTOR");
    }

    // As soon as init button is pressed, this loop runs
    @Override
    public void init_loop() {

    }

    // More to add
    @Override
    public void loop() {

    }
}