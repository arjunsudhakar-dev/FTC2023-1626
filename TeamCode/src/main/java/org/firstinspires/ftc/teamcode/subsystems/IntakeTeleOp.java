package org.firstinspires.ftc.teamcode.teleop;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import org.firstinspires.ftc.teamcode.subsystems.Intake;

@TeleOp(name = "Intake TeleOp", group = "TeleOp")
public class IntakeTeleOp extends LinearOpMode {

    // The Intake subsystem that controls the pulley-powered roller
    private Intake intake;

    // Adjustable motor power (strength of pulley/roller spin)
    // Represents the magnitude of how hard the rollers pull the ball.
    private double power = 0.2; // start at 20%

    @Override
    public void runOpMode() {

        // Create an Intake object and connect it to the actual motor.
        intake = new Intake(hardwareMap);

        // Wait for start button
        waitForStart();

        // Main TeleOp loop — runs repeatedly during the match
        while (opModeIsActive()) {

            // Press Y to increase intake power (spin rollers faster)
            if (gamepad1.y) {
                power = Math.min(power + 0.05, 0.5); // cap at 0.5 power
                sleep(150); // debounce so it doesn’t jump too fast
            }

            // Press X to decrease intake power (spin rollers slower)
            if (gamepad1.x) {
                power = Math.max(power - 0.05, 0.1); // minimum 0.1 power
                sleep(150);
            }

            // Press B to run intake forward (suck balls in)
            // This spins motor → pulley → belt → roller → intake wheels INWARDS
            if (gamepad1.b) {
                intake.run(power);
            }

            // Press A to run intake in reverse (spit balls out)
            // This reverses the entire pulley system
            else if (gamepad1.a) {
                intake.run(-power);
            }

            // No buttons pressed → motor OFF → pulley OFF → roller OFF
            else {
                intake.stop();
            }

            // Driver station output
            telemetry.addData("Power", power);
            telemetry.addData("State",
                    gamepad1.b ? "Forward (Intake In)" : gamepad1.a ? "Reverse (Intake Out)" : "Stopped");
            telemetry.update();
        }
    }
}