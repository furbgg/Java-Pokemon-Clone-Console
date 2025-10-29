package Javamonv1;

import java.util.Random;
import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        JavamonRepository javamonRepo = new JavamonRepository();
        PokAttackeRepository attackRepo = new PokAttackeRepository();
        SpielLogik logic = new SpielLogik(javamonRepo, attackRepo);
        KonsolenIU iu = new KonsolenIU();
        SoundManager sm = new SoundManager();
        iu.displayWelcomeAnimation();
        sm.playSound("aa.wav");
        scanner.nextLine();

        boolean playAgain;
        do {
            iu.displayPokemonList(javamonRepo.getAllPoke());
            Javamon playerMon = null;
            while (playerMon == null) {

                String playerInput = iu.tryReadString("Deinen Wahl (ID oder Name) ", scanner);


                playerMon = logic.selectPlayer(playerInput);


            }
            Javamon computerMon = logic.selectComp();

            logic.Attackkings(playerMon, 4);
            logic.Attackkings(computerMon, 4);

            iu.displaySelections(playerMon, computerMon);


            boolean playerTurn = playerMon.getSpeed() >= computerMon.getSpeed();


            while (playerMon.getHp() > 0 && computerMon.getHp() > 0) {
                Javamon attacker = playerTurn ? playerMon : computerMon;
                Javamon defender = playerTurn ? computerMon : playerMon;
                PokAttacke chosenAttack;

                if (playerTurn) {

                    chosenAttack = iu.askForAttack(attacker, scanner);
                } else {

                    System.out.println("\nJetzt ist dein Gegner dran...");
                    try { Thread.sleep(1500); } catch (InterruptedException e) {}
                    chosenAttack = attacker.getAttacken().get(new Random().nextInt(attacker.getAttacken().size()));
                }


                int damage = logic.calculateDamage(chosenAttack, attacker, defender);

                defender.setHp(defender.getHp() - damage);


                iu.displayAttackAnimation(attacker, defender, chosenAttack, damage);

                playerTurn = !playerTurn;
            }


            Javamon winner = (playerMon.getHp() > 0) ? playerMon : computerMon;
            Javamon loser = (playerMon.getHp() > 0) ? computerMon : playerMon;

            iu.displayWinnerAnimation(winner, loser);

            playAgain = iu.playAgain("Möchtest du noch einmal spielen? (Ja/Nein): ", scanner);

        } while (playAgain);


        iu.displayClosingAnimation();
        scanner.close();
    }
}