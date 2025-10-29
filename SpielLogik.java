package Javamonv1;

import java.util.Random;
import javax.imageio.IIOException;
import java.io.IOException;

public class SpielLogik {

    private final JavamonRepository javamonRepository;
    private final PokAttackeRepository attackenRepository;

    public SpielLogik(JavamonRepository repo, PokAttackeRepository attackRepo) {
        this.javamonRepository = repo;
        this.attackenRepository = attackRepo;
    }

    public Javamon selectPlayer(String eingabe) {
        Javamon foundJavamon = null;
        try {
            int id = Integer.parseInt(eingabe);
            foundJavamon = this.javamonRepository.getPokemonById(id);
        } catch (NumberFormatException e) {
            foundJavamon = this.javamonRepository.getPokemonByName(eingabe);
        }
        if (foundJavamon == null) {
            System.out.println("Fehler!");
            return  null;
        }
        return new Javamon(foundJavamon);
    }

    public Javamon selectComp() {
        Javamon foundJavamonforComp = javamonRepository.getRandomPokemon();
        return new Javamon(foundJavamonforComp);
    }

    public int calculateDamage(PokAttacke attacke, Javamon attacking, Javamon attacked) {
        double nAttacke = (attacke.getPower()) * ((double) attacking.getAttack() / attacked.getDefense()) * (1.0 / 4.0);
        return (int) Math.round(nAttacke);
    }

    public void Attackkings(Javamon javamon, int anzahl) {
        javamon.getAttacken().clear();

        for (int i = 0; i < anzahl; i++) {
            PokAttacke randomattacke = attackenRepository.getRandomAtt();
            javamon.getAttacken().add(randomattacke);
        }
    }

    public void startFight(Javamon spieler, Javamon computer) {
        System.out.println("Kampf startet : " + spieler.getName() + " v s " + computer.getName());
        Attackkings(spieler, 2);
        Attackkings(computer, 2);
        System.out.println(spieler.getName() + " hat die Attacken " + spieler.getAttacken().get(0).getName() + "und " + spieler.getAttacken().get(1).getName());
        System.out.println(computer.getName() + " hat die Attacken " + computer.getAttacken().get(0).getName() + "und" + computer.getAttacken().get(1).getName());

        boolean turn = true;

        if (spieler.getSpeed() >= computer.getSpeed()) {
            turn = true;

            System.out.println(spieler.getName() + " ist schneller und greift zuerst an!");

        } else {
            turn = false;
            System.out.println(computer.getName() + " ist schneller und greift zuerst an!");
        }

        while (spieler.getHp() > 0 && computer.getHp() > 0) {
            if (turn) {
                System.out.println("Du bist dran");
                PokAttacke attackeChoose = spieler.getAttacken().get(0);
                PokAttacke useratt = spieler.getAttacken().get(0);
                int schaden = calculateDamage(useratt, spieler, computer);
                computer.setHp(computer.getHp() - schaden);
                System.out.println(spieler.getName() + " greift mit " + useratt.getName() + " an und verursacht " + schaden + "Schaden !");
                System.out.println("Computer" + computer.getName() + "hat noch " + computer.getHp() + "HP");
            } else {
                System.out.println("Computer ist dran");
                PokAttacke attackeChoseC = computer.getAttacken().get(new Random().nextInt(2));
                int schadenC = calculateDamage(attackeChoseC, computer, spieler);
                spieler.setHp(spieler.getHp() - schadenC);
                System.out.println(computer.getName() + "greift mit " + attackeChoseC.getName() + " an und verursachtr " + schadenC + "Schadeb!!!");
                System.out.println("Du" + spieler.getName() + "hast noch " + spieler.getHp() + "HP");
            }
            turn = !turn;
            try {
                Thread.sleep(1500);
            } catch (InterruptedException e) {

            }

        }
        if (spieler.getHp()>0){
            System.out.println("Du hast gewonnen...");
        } else {
            System.out.println("Computer hat gewonnen....");
        }
    }
}



