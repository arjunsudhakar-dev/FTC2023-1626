package org.firstinspires.ftc.teamcode.teleop;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import org.firstinspires.ftc.teamcode.subsystems.Intake;

@TeleOp(name = "Intake TeleOp", group = "TeleOp")
public class IntakeTeleOp extends LinearOpMode {

    private Intake intake;
    private double speed = 0.2; // start speed at 20%

    @Override
    public void runOpMode() {
        // Initialize your intake subsystem
        intake = new Intake(hardwareMap);

        waitForStart();

        while (opModeIsActive()) {

            // Y increases forward speed
            if (gamepad1.y) {
                speed = Math.max(0.4, speed + 0.05);
                sleep(200); // This is a a bounce, basically, it prevents the speed from increasing too fast
            }
            // Z decreases the forward speed
            if (gamepad1.z) {
                speed = Math.max(0.4, speed - 0.05);
                sleep(200);
            }

            // X decreases (reverse) speed
            if (gamepad1.x) {
                speed = Math.max(-0.4, speed - 0.05);
                sleep(200);
            }
            // C increases (reverse) speed
            if (gamepad1.c) {
                speed = Math.max(-0.4, speed + 0.05);
                sleep(200);
            }
            // B runs intake forward
            if (gamepad1.b) {
                intake.run(speed);
            }
            // A runs intake reverse
            else if (gamepad1.a) {
                intake.run(-speed);
            }
            // No button pressed = stop
            else {
                intake.stop();
            }

            // telemetry feedback to Driver Station
            telemetry.addData("Speed", "%.0f%%", speed * 100);
            telemetry.addData("Direction",
                    gamepad1.a ? "Reverse" : gamepad1.b ? "Forward" : "Stopped");
            telemetry.update();
        }
    }
}
// In summary,
// - y to increase speed
// - x to decrease speed
// - b to run intake forward
// - a to run intake backward