package hiveswarm.hiveswarm;

import com.parse.ParseClassName;
import com.parse.ParseObject;

@ParseClassName("hive_data")

public class hive_data extends ParseObject {

    public hive_data() {
        // A default constructor is required.
    }

    public int getFramesWithHoney() {
        return getInt(GeneralObservationsActivity.framesWithHoneyOrNectar);
    }

    public boolean getSignsOfDiesease() {
        return getBoolean(PestsDiseases.SignsDiesease);
    }

    public boolean getBroodInAllStages() {
        return getBoolean(EggQueenObservations.broodInAllStages);
    }

    public boolean getTypeOfFood() {
        return getBoolean(EggQueenObservations.broodInAllStages);
    }

    public boolean getQueenMarked() {
        return getBoolean(EggQueenObservations.queenMarked);
    }

    public String getTemper() {
        return getString(GeneralObservationsActivity.temper);
    }

    public int getFramesOfFoundation() {
        return getInt(GeneralObservationsActivity.framesOfFoundation);
    }

    public int getAmountOfFoodFed() {
        return getInt(Feeding.amountOfFoodFed);
    }

    public String getVarroaMiteAffect() {
        return getString(PestsDiseases.varroaMiteAffect);
    }

    public int getAmountOfVarroaMite() {
        return getInt(PestsDiseases.AmountofVarroaMites);
    }

    public boolean getFeedingOrMedication() {
        return getBoolean(Feeding.feedingOrMedication);
    }

    public String getPollenAmount() {
        return getString(GeneralObservationsActivity.PollenAmount);
    }

    public int getWeatherTemperature() {
        return getInt(EnvironmentalConditions.temperature);
    }

    public boolean getCompactBroodPattern() {
        return getBoolean(EggQueenObservations.broodPattern);
    }

    public boolean getAreThereVarroaMites() {
        return getBoolean(PestsDiseases.varroaMite);
    }

    public boolean getNosemaStreaking() {
        return getBoolean(PestsDiseases.nosemaStreaking);
    }

    public boolean getQueenSeen() {
        return getBoolean(EggQueenObservations.queenSeen);
    }

    public boolean getEmergency() {
        return getBoolean(GeneralObservationsActivity.Emergency);
    }

    public boolean getSupersedure() {
        return getBoolean(EggQueenObservations.supersedure);
    }

    public String getBeeCount() {
        return getString(GeneralObservationsActivity.beeCount);
    }

    public String getActionsAndNotes() {
        return getString(GeneralObservationsActivity.actionsNotes);
    }

    public String getReasonForFeeding() {
        return getString(Feeding.reasonForFeeding);
    }

    public int getQueenCellsRemaining() {
        return getInt(EggQueenObservations.queenCellsRemaining);
    }

    public int getFramesBeeOccupy() {
        return getInt(EggQueenObservations.framesUsedBroodChamber);
    }

    public int getFramesWithBrood() {
        return getInt(EggQueenObservations.framesWithBrood);
    }

    public int getSupersInPlace() {
        return getInt(GeneralObservationsActivity.supersInPlace);
    }

    public boolean getSpottyDroneBrood() {
        return getBoolean(EggQueenObservations.spottyDroneBrood);
    }

    public String getPestCount() {
        return getString(GeneralObservationsActivity.pestCount);
    }

    public String getColonyCondition() {
        return getString(GeneralObservationsActivity.colonyCondition);
    }

    public String getWeatherCloudiness() {
        return getString(EnvironmentalConditions.cloudiness);
    }

    public int getFramesOfPollen() {
        return getInt(GeneralObservationsActivity.framesOfPollen);
    }

    public boolean getSwarm() {
        return getBoolean(GeneralObservationsActivity.swarm);
    }

    public int getWeatherPrecipitation() {
        return getInt(EnvironmentalConditions.precipitation);
    }

    public int getSupersAdded() {
        return getInt(GeneralObservationsActivity.supersAdded);
    }

    public boolean getEggLarvaOrPupaSeen() {
        return getBoolean(EggQueenObservations.eggLarvaOrPupaSeen);
    }

    public String getHiveBeetleAffect() {
        return getString(PestsDiseases.hiveBeetleAffect);
    }

    public int getAmountOfHiveBeetles() {
        return getInt(PestsDiseases.AmountofHiveBeetles);
    }

    public String getAmountOfHoney() {
        return getString(GeneralObservationsActivity.HoneyAmount);
    }

    public boolean getRemovedQueenCells() {
        return getBoolean(EggQueenObservations.removedQueenCells);
    }

    public int getFramesOpenComb() {
        return getInt(GeneralObservationsActivity.framesOpenComb);
    }

    public int getWeatherHumidity() {
        return getInt(EnvironmentalConditions.humidity);
    }

    public boolean getAreThereHiveBeetles() {
        return getBoolean(PestsDiseases.hiveBeetle);
    }

    public int getFrequencyOfFeeding() {
        return getInt(Feeding.frequencyOfFeeding);
    }

    public String getHiveId() {
        return getString(GeneralObservationsActivity.hiveId);
    }
}