// We need to make this extend opMode, not linearOpMode 
package org.firstinspires.ftc.teamcode.teleop;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import org.firstinspires.ftc.teamcode.subsystems.Intake;

@TeleOp(name = "Intake TeleOp | Arjun's code", group = "TeleOp")
public class IntakeTeleOp extends LinearOpMode {

    // The Intake subsystem that controls the pulley-powered roller
    private Intake intake;

    // Adjustable motor power (strength of pulley/roller spin)
    // Represents the magnitude of how hard the rollers pull the ball.
    private double power = 0.6; // start at 60%

    @Override
    public void runOpMode() {

        // Create an Intake object and connect it to the actual motor.
        intake = new Intake(hardwareMap);

        // Wait for start button
        waitForStart();

        // Main TeleOp loop — runs repeatedly during the match
        while (opModeIsActive()) {
            // Math.min keeps values below a max. Math.max keeps values above a min
            // Press Y to increase intake power (spin rollers faster)
            // Press B to run intake forward (suck balls in)
            // This spins motor → pulley → belt → roller → intake wheels INWARDS
            if (gamepad1.left_trigger) {
                intake.run(power);
            }

            // Press A to run intake in reverse (spit balls out)
            // This reverses the entire pulley system
            else if (gamepad1.left_bumper) {
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