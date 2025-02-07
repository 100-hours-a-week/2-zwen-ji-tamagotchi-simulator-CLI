package pet;


import system.Command;
import system.DeviceSetting;

import java.util.Set;

abstract public class HatchedPet extends Pet {
    protected int satisfaction = 60;
    protected int happiness = 40;
    protected boolean sickness = false;


    HatchedPet() {
        level =1;
        commandList.add(new Command("feed", this::feedingPet));
        commandList.add(new Command("grooming", this::groomingPet));
        commandList.add(new Command("healing", this::healingPet));
    }

    public int getSatisfaction() {
        return satisfaction;
    }
    public int getHappiness() {
        return happiness;
    }
    public boolean getSickness(){
        return sickness;
    }

    /* States ===================================================================================================*/
    @Override
    public void updatePetState(boolean isActioned) {
        if(isActioned){
            petStatDecrease();
        }
        evalPetState();
    }
    private void evalPetState(){
        if(sickness){
            triggerSicknessProblem();
        }
        else{
            if (happiness <= 40 || satisfaction <= 40) {
                if (happiness < satisfaction) {
                    triggerHappinessProblem();
                } else {
                    triggerSatisfactionProblem();
                }
            } else {
                setNormalPetState();
            }
        }
    }
    private void petStatDecrease(){
        if(randomByRange(0,10)<1){
            sickness = true;
        }
        happiness -=(randomByRange(1,4));
        satisfaction -=(randomByRange(1,4));
    }


    /* Actions ====================================================================================================*/
    protected String getName(){
        return "펫";
    };
    abstract String getFeedingImage();
    abstract String getGroomingImage();
    abstract String getHealingImage();

    protected int feedingPet() {
        this.setCurrImage(this.getFeedingImage());
        int expIncreaseAmount = randomByRange(DeviceSetting.EXP_INCREASE_MIN, DeviceSetting.EXP_INCREASE_MAX);
        exp += expIncreaseAmount;
        satisfaction += randomByRange(DeviceSetting.STAT_INCREASE_MIN, DeviceSetting.STAT_INCREASE_MAX);
        this.setCurrMessage(String.format("%s에게 밥을 주었다. (+%d exp)", this.getName(), expIncreaseAmount));
        return expIncreaseAmount;
    }

    protected void groomingPet() {
        this.setCurrImage(this.getGroomingImage());
        int expIncreaseAmount = randomByRange(DeviceSetting.EXP_INCREASE_MIN, DeviceSetting.EXP_INCREASE_MAX);
        exp += expIncreaseAmount;
        happiness += randomByRange(DeviceSetting.STAT_INCREASE_MIN, DeviceSetting.STAT_INCREASE_MAX);
        this.setCurrMessage(String.format("%s%s 놀아주었다. (+%d exp)", this.getName(), "과", expIncreaseAmount));
    }

    protected void healingPet() {
        this.setCurrImage(this.getHealingImage());
        sickness = false;
        this.setCurrMessage(String.format("%s%s 치료했다.",this.getName(),"을/를"));
    }


    /* Special Event ==============================================================================================*/
    @Override
    public boolean checkSpecialEvent(){
        if (checkLevelUp()) {
            eventType = EventType.LEVEL_UP;
            return true;
        }
        return false;
    }

    @Override
    public void handleSpecialEvent(){
        switch (eventType) {
            case LEVEL_UP -> levelUpEvent();
            //Todo add more special events
        }
    }

    abstract String getLevelUpImage();
    protected boolean checkLevelUp(){
        return exp >= 100;
    }

    protected void levelUpEvent(){
        this.setCurrImage(this.getLevelUpImage());
        this.setCurrMessage(String.format("%s%s Lv.%d으로 레벨 업 했습니다 !!",this.getName(),"이/가",++level));
        exp -=100;
    }

    abstract void triggerSicknessProblem();
    abstract void triggerHappinessProblem();
    abstract void triggerSatisfactionProblem();
    abstract void setNormalPetState();
}

