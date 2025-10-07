package org.firstinspires.ftc.teamcode.subsystems;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import math.util.clamp; // Import clamp function for limiting power values 

public class Shooter {
    private final DcMotor shooterMotor1;
    private final DcMotor shooterMotor2;

    private final ElapsedTime runtime = new ElapsedTime(); // Timer for controlling firing intervals

    public Shooter(HardwareMap hardwareMap) {
        shooterMotor1 = hardwareMap.get(DcMotor.class, "shooterMotor");
        shooterMotor1.setDirection(DcMotorSimple.Direction.FORWARD);

        shooterMotor2 = hardwareMap.get(DcMotor.class, "shooterMotor");
        shooterMotor2.setDirection(DcMotorSimple.Direction.REVERSE);

    }

    // Clamp power to valid/safe range
    public void setPower(double power) {
        shooterMotor1.setPower(clamp(power, -0.67, 0.67));
        shooterMotor2.setPower(clamp(power, -0.67, 0.67));
    }

    public void stop() {
        shooterMotor1.setPower(0); // Stop the motor
        shooterMotor2.setPower(0);
    }

    public void fire(double speed, double duration) {
        runtime.reset(); // Reset the timer
        // Clamp the speed to the maximum allowed value (e.g., 0.67)
        speed = Math.max(-0.67, Math.min(0.67, speed));
        setSpeed(speed); // Set the shooter motor speed
        while (runtime.seconds() < duration) {
            // Keep the motor running for the specified duration
            // You can add telemetry here if needed to monitor runtime
        }
        stop(); // Stop the motor after firing
    }

}
