package pet;

import system.Command;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;

abstract public class Pet {
    private String currMessage;
    private String currImage;

    protected int level;
    protected int exp;

    protected final List<Command> commandList = new ArrayList<>();
    public EventType eventType = null;
    public enum EventType {
        HATCH,      // 부화 이벤트
        LEVEL_UP,
        SPAWN,
        EVOLVE
    }


    /* getters, setters ============================================================================================*/
    protected void setCurrMessage(String currMessage) {
        this.currMessage = currMessage;
    }
    public String getCurrMessage() {
        return currMessage;
    }
    protected void setCurrImage(String currImage) {
        this.currImage = currImage;
    }
    public String getCurrImage() {
        return currImage;
    }

    public int getLevel() {
        return level;
    }
    public int getExp() {
        return exp;
    }

    public List<Command> getCommandList() {
        return commandList;
    }

    /* core =======================================================================================================*/
    protected int randomByRange(int min, int max){
        return ThreadLocalRandom.current().nextInt(min,max+1);
    }
    
    public String  getDontKnowImage(){return null;}

    public abstract Set<EventType> getSupportedEvents();

    public abstract void updatePetState(boolean isActioned);

    public abstract void handleSpecialEvent();

    public abstract boolean checkSpecialEvent();

    public Pet createNewPet() {
        return null;
    }
}




