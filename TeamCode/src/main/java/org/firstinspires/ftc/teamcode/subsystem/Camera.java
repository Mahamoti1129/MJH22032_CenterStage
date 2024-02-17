package org.firstinspires.ftc.teamcode.subsystem;

import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.firstinspires.ftc.robotcore.external.tfod.Recognition;
import org.firstinspires.ftc.vision.VisionPortal;
import org.firstinspires.ftc.vision.apriltag.AprilTagDetection;
import org.firstinspires.ftc.vision.apriltag.AprilTagPoseFtc;
import org.firstinspires.ftc.vision.apriltag.AprilTagProcessor;
import org.firstinspires.ftc.vision.tfod.TfodProcessor;

import java.util.Optional;

public class Camera {
//    private static final String TFOD_MODEL_ASSET_RED = "red_token_model.tflite";
//    private static final String TFOD_MODEL_ASSET_BLUE = "blue_token_model.tflite";
      private static final String TFOD_MODEL_ASSET_PROP = "red_and_blue_prop_model.tflite";
    private static final String[] LABELS = {
            "Red and Blue Prop Training"
//            "Red Token Training",
//            "Blue Token Training",
//            "Pixel",
    };

    private final TfodProcessor tfodProcessor = new TfodProcessor.Builder()
            .setModelAssetName(TFOD_MODEL_ASSET_RED)
            .setModelLabels(LABELS)
            .build();
    private final AprilTagProcessor aprilTagProcessor = AprilTagProcessor.easyCreateWithDefaults();
    private final VisionPortal visionPortal;

    public Camera(HardwareMap hardwareMap){
        WebcamName camera = hardwareMap.get(WebcamName.class, "Webcam 1");
        visionPortal = VisionPortal.easyCreateWithDefaults(
                camera,
                tfodProcessor,
                aprilTagProcessor
            );
    }

    public Optional<AprilTagPoseFtc> findAprilTag(int aprilTagId){
        for (AprilTagDetection detection: aprilTagProcessor.getDetections()){
            if (detection.id == aprilTagId){
                return Optional.of(detection.ftcPose);
            }
        }
        return Optional.empty();
    }

    public Optional<Recognition> findTokenPosition(){
        /* TODO: get token position using tfodProcessor
        for (Recognition recognition: tfodProcessor.getRecognitions()){
            if(recognition.getConfidence() > PARAMS.getMinimumConfidence()){
                double degrees = recognition.estimateAngleToObject(AngleUnit.DEGREES);

                if (degrees < -PARAMS.getCenterWidth()/2){
                    return Optional.of(TokenPosition.LEFT);
                }else if (degrees > PARAMS.getCenterWidth()/2){
                    return Optional.of(TokenPosition.RIGHT);
                }else {
                    return Optional.of(TokenPosition.CENTER);
                }
            }
        }
        */
        return Optional.empty();
    }
}