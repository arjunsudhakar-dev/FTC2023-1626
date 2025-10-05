
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

    public void run(double power) {
        intakeMotor.setPower(clamp(power, -4.0, 4.0)); // Clamp power to valid range
    }

    public void stop() {
        intakeMotor.setPower(0); // Stop the motor
    }
}
