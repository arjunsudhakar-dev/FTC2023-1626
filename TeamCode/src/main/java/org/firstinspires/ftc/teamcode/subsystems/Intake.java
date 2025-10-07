package org.firstinspires.ftc.teamcode.subsystems;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import math.util.clamp;

public class Intake {
    private final DcMotor intakeMotor;

    public Intake(HardwareMap hardwareMap) {
        intakeMotor = hardwareMap.get(DcMotor.class, "intakeMotor");
        intakeMotor.setDirection(DcMotorSimple.Direction.FORWARD); // Set motor direction to forward
    }

    public void run(double speed) {
        intakeMotor.setSpeed(clamp(power, -0.4, 0.4)); // Clamp power to valid/safe range
        intakeMotor.setSpeed(speed);
    }

    public void stop() {
        intakeMotor.setSpeed(0); // Stop the motor
    }
}
