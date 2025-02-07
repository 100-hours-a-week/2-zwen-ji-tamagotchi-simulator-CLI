package system;

import pet.Egg;
import pet.HatchedPet;
import pet.Pet;

import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class DeviceInterface {
    BufferedWriter buffer = new BufferedWriter(new OutputStreamWriter(System.out, StandardCharsets.UTF_8));
    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    Scanner sc = new Scanner(System.in);

    private String currMessage;
    private String currImg;
    private Pet pet;
    private boolean isWaitingMode = false;

    public DeviceInterface() throws IOException, InterruptedException {
        bootDevice();
        runningDevice();
    }

    private void setCurrMessage(String currMessage) {
        this.currMessage = currMessage;
    }

    private void setCurrImg(String currImg) {
        this.currImg = currImg;
    }

    /* console output ===========================================================================================*/
    private void printScreen() throws IOException {
        clearConsole();
        loadBody();
        loadFooter();
        this.buffer.flush();
    }

    private void clearConsole() throws IOException {
        for (int i = 0; i < 80; i += 1) {
            this.buffer.write("\n");
        }
    }

    private void loadBody() throws IOException {
        String color;
        if (this.isWaitingMode) {
            color = DeviceSetting.RESET;
        } else {
            color = DeviceSetting.CONSOLE_COLOR;
        }
        this.buffer.write(color + currImg + DeviceSetting.RESET);
        this.buffer.write('\n');
    }

    private void loadFooter() throws IOException {
        String color;
        if (this.isWaitingMode) {
            color = DeviceSetting.RESET;
        } else {
            color = DeviceSetting.CONSOLE_COLOR;
        }
        int padding = (50 - currMessage.length()) / 2;
        this.buffer.write(color + DeviceSetting.TOP_FRAME + '\n');
        boolean levelDisplay = this.isWaitingMode && pet.getLevel() != 0;
        if (levelDisplay) {
            this.buffer.write(DeviceSetting.LABEL_LEVEL + pet.getLevel());
            this.buffer.write('\n');
        }

        //printing message
        this.buffer.write(" ".repeat(padding));
        this.buffer.write(color + currMessage + DeviceSetting.RESET);
        this.buffer.write('\n');

        if (levelDisplay) {
            this.buffer.write('\n');
        }
        this.buffer.write(color + DeviceSetting.BOT_FRAME + '\n');
        this.buffer.write('\n');

        //printing commands
        if (this.isWaitingMode) {
            int index = 1;
            for (var entry : pet.getCommandList()) {
                this.buffer.write(DeviceSetting.DIM + String.format(DeviceSetting.COMMAND_LINE_DESCRIPTION, index++, entry.getDescription()));
                this.buffer.write(DeviceSetting.RESET);
                this.buffer.write("\n\n");
            }
            this.buffer.write("─".repeat(DeviceSetting.CONSOLE_WIDTH + 2) + "\n\n");
            this.buffer.write(DeviceSetting.COMMAND_SYSTEM_GUIDE);
            this.buffer.write('\n');
        }
        this.buffer.write('\n');
    }

    private void close() throws IOException {
        if (buffer != null) {
            buffer.close();
        }
    }

    public String input() {
        return sc.nextLine();
    }

    public void moveOn() throws IOException {
        System.out.println(DeviceSetting.MOVE_ON_GUIDE);
        reader.readLine();
    }

    private void updateScreenForEvent() throws IOException {
        updateScreen(false);
        moveOn();
    }

    private void updateScreen(boolean isWaiting) throws IOException {
        this.isWaitingMode = isWaiting;
        this.setCurrMessage(pet.getCurrMessage());
        this.setCurrImg(pet.getCurrImage());
        printScreen();
    }



    void showStatus() throws IOException {
        if (pet instanceof HatchedPet) {
            showHatchedPetStatus((HatchedPet) pet);
        } else if (pet instanceof Egg) {
            showEggStatus((Egg) pet);
        }
        moveOn();
    }

    void showHatchedPetStatus(HatchedPet pet) throws IOException {
        clearConsole();
        this.buffer.write(DeviceSetting.TOP_FRAME + "\n\n");
        this.buffer.write(DeviceSetting.LABEL_LEVEL+ pet.getLevel() + DeviceSetting.LABEL_EXP + pet.getExp() + "\n\n\n");
        this.buffer.write(DeviceSetting.LABEL_SICKNESS + pet.getSickness() + "\n\n");
        this.buffer.write(DeviceSetting.LABEL_SATISFACTION + pet.getSatisfaction() + "\n\n");
        this.buffer.write(DeviceSetting.LABEL_HAPPINESS + pet.getHappiness() + "\n\n");
        this.buffer.write(DeviceSetting.BOT_FRAME);
        this.buffer.flush();
    }

    void showEggStatus(Egg pet) throws IOException {
        clearConsole();
        this.buffer.write( String.format(DeviceSetting.LABEL_HATCH_PROCESS,100 - pet.getHatchProgress())+"\n");
        this.buffer.flush();
    }


    /*Device Running ===========================================================================================*/
    private void bootDevice() throws IOException {
        //Device Init
        this.setCurrImg(DeviceSetting.deviceStatrImage);
        this.setCurrMessage(DeviceSetting.STARTING_GUIDE);
        printScreen();
        moveOn();
        reader = new BufferedReader(new InputStreamReader(System.in));

        //pet init
        pet = new Egg("Miku"); //Todo
        pet.updatePetState(false);
        this.setCurrMessage(pet.getCurrMessage());
        this.setCurrImg(pet.getCurrImage());
        isWaitingMode = true;
        printScreen();
    }

    private void runningDevice() throws IOException, InterruptedException {
        while (true) {
            boolean isActioned = false;

            String input = input().trim();
            switch (input) {
                case "q":
                    quitDevice();
                    break;
                case "s":
                    showStatus();
                    break;
                default:
                    this.isWaitingMode = false;
                    try {
                        int command = Integer.parseInt(input);
                        if (command >= 1 && command <= pet.getCommandList().size()) {
                            pet.getCommandList().get(DeviceSetting.INDEX_FIXER(command)).execute();
                            isActioned = true;
                            this.setCurrImg(pet.getCurrImage());
                            this.setCurrMessage(pet.getCurrMessage());
                        } else {
                            this.setCurrImg(pet.getDontKnowImage());
                            this.setCurrMessage(DeviceSetting.COMMAND_BOUND_DENY);
                        }
                    }
                    catch (NumberFormatException e) {
                        this.setCurrImg(pet.getDontKnowImage());
                        this.setCurrMessage(DeviceSetting.COMMAND_FORMAT_DENY);
                    }
                    finally {
                        printScreen();
                        moveOn();
                    }
                    break;
            }


            if (pet.checkSpecialEvent() && isActioned) {
                if (pet.getSupportedEvents().contains(pet.eventType)) {
                    pet.handleSpecialEvent();
                    updateScreenForEvent();

                    //new pet create occur
                    if (pet.eventType == Pet.EventType.HATCH || pet.eventType == Pet.EventType.EVOLVE) {
                        pet = pet.createNewPet();
                        updateScreenForEvent();
                        isActioned = false;
                    }
                }
            }
            //print default screen
            pet.updatePetState(isActioned);
            updateScreen(true);
        }
    }

    void quitDevice() {
        try {
            close();
            System.out.println(DeviceSetting.SHUT_DOWN_NORMALLY);

        } catch (IOException e) {
            System.err.println(DeviceSetting.SHUT_DOWN_ERROR + e.getMessage());
        } finally {
            System.exit(0);
        }
    }
}
