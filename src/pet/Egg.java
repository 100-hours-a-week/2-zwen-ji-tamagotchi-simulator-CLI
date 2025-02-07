package pet;

import dotted_image.EggImage;
import system.Command;

import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;


public class Egg extends Pet {
    private int hatchProgress;
    private final String toBe; //TOdo

    public Egg(String toBe) {
        this.toBe = toBe;
        commandList.add(new Command("Warm", this::warmingEgg));
    }

    @Override
    public void updatePetState(boolean isActioned) { //Todo
        this.setCurrImage(EggImage.DEFAULT_IMAGE);
        this.setCurrMessage("알이 놓여 있습니다.");
    }

    public int getHatchProgress() {
        return hatchProgress;
    }

    public String  getDontKnowImage() {
        return EggImage.DEFAULT_IMAGE;
    }

    /* Command =====================================================================================================*/
    private void warmingEgg() {
        this.setCurrImage(EggImage.DEFAULT_IMAGE);
        // TOdo 난수 생성
        int statIncreaseAmount = ThreadLocalRandom.current().nextInt(1,40);
        hatchProgress += statIncreaseAmount;
        this.setCurrMessage("알을 따듯하게 만들어줬다. (+" + statIncreaseAmount + ")");
    }


    /* Special Event =============================================================================================*/
    @Override
    public boolean checkSpecialEvent() {
        if (checkHatch()) {
            eventType = EventType.HATCH;
            return true;
        }
        return false;
    }

    @Override
    public void handleSpecialEvent() {
        switch (eventType) {
            case HATCH: {
                hatch();
            }
            // Todo : (Later) Add More Events
        }
    }

    @Override
    public Set<EventType> getSupportedEvents() {
        return Set.of(EventType.HATCH);
    }


    boolean checkHatch() {
        return hatchProgress >= 100;
    }

    void hatch() {
        this.setCurrImage(EggImage.HATCHING_IMAGE);
        this.setCurrMessage("알이 부화하고 있다.");
    }

    @Override
    public Pet createNewPet() {
        switch (toBe) {
            case "Miku": {
                return new Miku();
            }
            // Todo : (Later) Add Some New Products
        }
        return null;
    }
}
