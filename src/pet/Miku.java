package pet;

import dotted_image.MikuImage;
import system.Command;
import system.DeviceSetting;

import java.util.Set;

class Miku extends HatchedPet {
    Miku() {
        commandList.clear();
        commandList.add(new Command("충전시키기", this::feedingPet));
        commandList.add(new Command("놀아주기", this::groomingPet));
        commandList.add(new Command("치료하기", this::healingPet));
        this.setCurrImage(MikuImage.SPAWN_IMAGE);
        this.setCurrMessage("미쿠가 탄생했습니다.");
        this.eventType = EventType.SPAWN;
    }

    @Override
    protected String getName() {
        return "미쿠";
    }


    /* States =================================================================================================*/
    @Override
    protected void triggerSatisfactionProblem() {
        setCurrImage(MikuImage.HUNGRY_IMAGE);
        setCurrMessage("미쿠가 배고파하는 것 같습니다.");
    }
    @Override
    protected void triggerHappinessProblem() {
        setCurrImage(MikuImage.SAD_IMAGE);
        setCurrMessage("미쿠가 심심해 합니다.");
    }
    @Override
    protected void triggerSicknessProblem() {
        setCurrImage(MikuImage.SICK_IMAGE);
        setCurrMessage("미쿠가 아픈 것 같습니다.");
    }
    @Override
    protected void setNormalPetState() {
        setCurrImage(MikuImage.DEFAULT_IMAGE);
        setCurrMessage("미쿠가 기다리고 있습니다.");
    }

    @Override
    public String getDontKnowImage() {
        return MikuImage.DONTKNOW_IMAGE;
    }

    /* Actions ===========================================================================================*/
    @Override
    protected String getFeedingImage() {
        return MikuImage.FEEDING_IMAGE;
    }

    @Override
    protected String getGroomingImage() {
        return MikuImage.GROOMING_IMAGE;
    }

    @Override
    protected String getHealingImage() {
        return MikuImage.HEALING_IMAGE;
    }

    @Override
    protected int feedingPet() {
        int expIncreaseAmount = super.feedingPet();
        this.setCurrMessage(String.format("미쿠를 충전해 줬다. (+%d exp)",expIncreaseAmount));
        return expIncreaseAmount;
    }


    /* Special Event ===========================================================================================*/
    @Override
    public Set<EventType> getSupportedEvents() {
        return Set.of(EventType.EVOLVE, EventType.LEVEL_UP);
    }

    @Override
    public boolean checkSpecialEvent() {
        if (checkLevelUp()) {
            eventType = EventType.LEVEL_UP;
            if (checkEvolve()) {
                eventType = EventType.EVOLVE;
                return true;
            }
            return true;
        }
        return false;
    }

    @Override
    public void handleSpecialEvent() {
        switch (eventType) {
            case EVOLVE -> levelUpEvent();
            case LEVEL_UP -> levelUpEvent();
        }
    }
    @Override
    protected String getLevelUpImage(){
        return MikuImage.LEVELUP_IMAGE;
    }

    /* Original Events ===========================================================================================*/
    protected boolean checkEvolve() {
        return this.level == 2;
    }

    @Override
    public Pet createNewPet() {
        return new MikuLv2(exp,satisfaction,happiness,sickness);
    }
}



class MikuLv2 extends Miku {
    protected MikuLv2(int exp, int satisfaction, int happiness, boolean sickness) {
        super();
        level = 3;
        this.exp = exp;
        this.satisfaction = satisfaction;
        this.happiness = happiness;
        this.sickness = sickness;

        commandList.add(new Command("보컬 트레이닝",this::vocalTraining));
        commandList.add(new Command("춤 연습 시키기",this::danceTraining));

        this.setCurrImage(MikuImage.EVOLVING_IMAGE);
        this.setCurrMessage("미쿠가 특별한 능력을 얻었다! ");
        this.eventType = EventType.EVOLVE;
    }

    @Override
    public Set<EventType> getSupportedEvents() {
        return Set.of(EventType.LEVEL_UP);
    }

    /* Actions ================================================================================================*/
    private void vocalTraining() {
        this.setCurrImage(MikuImage.SINING_IMAGE);
        int expIncreaseAmount = randomByRange(DeviceSetting.EXP_INCREASE_MIN, DeviceSetting.EXP_INCREASE_MAX) * 2;
        exp += expIncreaseAmount;
        happiness += randomByRange(DeviceSetting.STAT_INCREASE_MIN, DeviceSetting.STAT_INCREASE_MAX) - 3;
        satisfaction += randomByRange(DeviceSetting.STAT_INCREASE_MIN, DeviceSetting.STAT_INCREASE_MAX) - 7;
        this.setCurrMessage(String.format("미쿠가 노래를 부른다. (+%d exp)",expIncreaseAmount));
    }
    private void danceTraining() {
        this.setCurrImage(MikuImage.DANCING_IMAGE);
        int expIncreaseAmount = randomByRange(DeviceSetting.EXP_INCREASE_MIN, DeviceSetting.EXP_INCREASE_MAX) * 2;
        exp += expIncreaseAmount;
        happiness += randomByRange(DeviceSetting.STAT_INCREASE_MIN, DeviceSetting.STAT_INCREASE_MAX) - 7;
        satisfaction += randomByRange(DeviceSetting.STAT_INCREASE_MIN, DeviceSetting.STAT_INCREASE_MAX) - 3;
        this.setCurrMessage(String.format("미쿠가 춤을 연습한다. (+%d exp)",expIncreaseAmount));
    }

}
