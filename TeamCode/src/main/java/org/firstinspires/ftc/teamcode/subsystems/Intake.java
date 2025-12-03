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
        intakeMotor.setDirection(DcMotorSimple.Direction.FORWARD); // TODO: test this
    }

    public void run(double power) {
        // clamp power to safe range
        double safePower = Math.max(-0.5, Math.min(0.5, power));
        intakeMotor.setPower(safePower); // Had to change setSpeed to setPower
    }

    public void stop() {
        intakeMotor.setPower(0);
    }
}