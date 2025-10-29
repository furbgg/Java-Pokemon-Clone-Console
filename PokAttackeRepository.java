package Javamonv1;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class PokAttackeRepository {
    private Map<Integer, PokAttacke> attackenbyId = new HashMap<>();
    private ArrayList<PokAttacke> attackenList = new ArrayList<>();



    public PokAttackeRepository() {
        String[] attackendata = {
                "1;Pound;Deals damage with no additional effect.;Normal;Physical;40;100%;35",
                "2;Karate Chop;Has a high critical hit ratio.;Fighting;Physical;50;100%;25",
                "3;DoubleSlap;Attacks 2-5 times in one turn.;Normal;Physical;15;85%;10",
                "4;Comet Punch;Attacks 2-5 times in one turn.;Normal;Physical;18;85%;15",
                "5;Mega Punch;Deals damage with no additional effect.;Normal;Physical;80;85%;20",
                "6;Pay Day;The player picks up extra money after in-game battles.;Normal;Physical;40;100%;20",
                "7;Fire Punch;Has a 10% chance to burn the target.;Fire;Physical;75;100%;15",
                "8;Ice Punch;Has a 10% chance to freeze the target.;Ice;Physical;75;100%;15",
                "9;ThunderPunch;Has a 10% chance to paralyze the target.;Electric;Physical;75;100%;15",
                "10;Scratch;Deals damage with no additional effect.;Normal;Physical;40;100%;35"
        };
        for (String line : attackendata) {
            String[] values = line.split(";");
            PokAttacke pa = new PokAttacke();

            pa.setId(Integer.parseInt(values[0]));
            pa.setName(values[1]);
            pa.setType(values[3]);
            pa.setKind(values[4]);
            pa.setPower(Integer.parseInt(values[5]));
            pa.setAccuracy(values[6]);
            pa.setPp(Integer.parseInt(values[7]));
            attackenbyId.put(pa.getId(), pa);
            attackenList.add(pa);
        }
    }



    public  PokAttacke getRandomAtt () {
        Random r = new Random();
        int list = attackenList.size();
        int randomindex =r.nextInt(list);
        return attackenList.get(randomindex);
    }
}

