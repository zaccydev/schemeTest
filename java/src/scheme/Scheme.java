package scheme;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

public class Scheme extends SchemeBase {
   
    private boolean last = false;   
    private String myMillion = "";    
    
    public Scheme(ETypeJeu game){
    	super(game);              
        this.last = false;
    }
    
    public boolean isLast(){
        this.setIsLast();
        return last;
    }
       
    public String titleScheme() {  
    	return String.valueOf(dt);
    }
    
    public SchemeBase getRandomNumberFromScheme(int nb) {
    	SchemeBase sb = new SchemeBase(game);
    	for (int i = 0; i < nb; i++) {
    		Random r = new Random();
			int n = r.nextInt(game.getNbResultNum());
			if (! sb.hasNumber(this.numbers.get(n),0)) {
				sb.addNumber(this.numbers.get(n));
			} else {
				i--;
			}
    	}
    	
    	return sb;
    }
    
    public String getMyMillion() {
		return myMillion;
	}

	public void setMyMillion(String myMillion) {
		this.myMillion = myMillion;
	} 
        
    public void setTirage(Date date, int[] numeros, int[] extras) throws Exception {
        
        this.numbers = new ArrayList<Number>();        
        date = date;       
        int y = 1;
        for (int i : numeros) {
            numbers.add(new Number(i, false, y));
            y++;
        }
        y = 1;
        for (int i : extras) {
            numbers.add(new Number(i, true, y));
            y++;
        }
    }
    
    public int getNbrEven(boolean extra) {        
        int evenNum = 0;        
        
        for (Number n : getSchemeNumbers()) {
            if (extra == n.isExtra() &&  n.getValue() % 2 == 0)
                evenNum++;
        }
        return evenNum;
    }
    public int getNbrOdd(boolean extra) {        
        int oddNum = 0;
        
        for (Number n : getSchemeNumbers()) {
            if (extra == n.isExtra() && n.getValue() % 2 != 0)
                oddNum++;
        }
        return oddNum;
    }   
    
    

    /*
     * Détermine s'il s'agit du dernier tirage.
     * 
     * 
     * Utilisation de java.util.Calendar : les mois commencent à zéro, les jours avec dimanche à 1
     */
    private void setIsLast() {

        switch (game) {
            case Loto:
			try {
				this.setIsLastLoto();
			} catch (Exception e) {			
				e.printStackTrace();
			}
                break;
            case EuroMillion:
			try {
				this.setIsLastEm();
			} catch (Exception e) {			
				e.printStackTrace();
			}
                break;   
            case Keno:
            break;
        }
    }    

    private void setIsLastLoto() throws Exception {
        //loto : si 21h passé et l, m ou s date de dernier tirage à jour égale jour courant ou mardi si l, jc ou jeudi, ve si me
        //ou d, si s

        Calendar clCurrent = Calendar.getInstance();
        Calendar clGame = Calendar.getInstance();
        clGame.setTime(date);        

        int currentDay = clCurrent.get(Calendar.DAY_OF_WEEK);
        int gameDay = clGame.get(Calendar.DAY_OF_WEEK);


        if (!(gameDay == 2 || gameDay == 4 || gameDay == 7)) {
            Exception e = new Exception("Jour de tirage non prévu pour jeu de type loto");
            throw e;            
        }
        /**
         * Au dela de 4 jours le jeu n'est pas le dernier
         */
        if ((System.currentTimeMillis() - 3600 * 24 * 4 * 1000) > clGame.getTimeInMillis()) {

            return;
        }

        if (!((gameDay == 2 && (currentDay != 2 && currentDay != 3)
                && !(currentDay == 4 && clCurrent.get(Calendar.HOUR_OF_DAY) < 21))
                || (gameDay == 4 && (currentDay != 4 && currentDay != 5 && currentDay != 6)
                && !(currentDay == 7 && clCurrent.get(Calendar.HOUR_OF_DAY) < 21))
                || (gameDay == 7 && (currentDay != 7 && currentDay != 1)
                && !(currentDay == 2 && clCurrent.get(Calendar.HOUR_OF_DAY) < 21)))) {
            last = true;                        
        }
    }

    private void setIsLastEm() throws Exception {

        Calendar clCurrent = Calendar.getInstance();
        Calendar clGame = Calendar.getInstance();
        clGame.setTime(date);        

        int currentDay = clCurrent.get(Calendar.DAY_OF_WEEK);
        int gameDay = clGame.get(Calendar.DAY_OF_WEEK);


        if (!(gameDay == 3 || gameDay == 6)) {
            Exception e = new Exception("Jour de tirage non prévu pour un jeu de type EuroMillion");
            throw e;
        }
        /**
         * Au dela de 5 jours le jeu n'est pas le dernier
         */
        if ((System.currentTimeMillis() - 3600 * 24 * 5 * 1000) > clGame.getTimeInMillis()) {

            return;
        }

        if (!((gameDay == 3 && (currentDay != 3 && currentDay != 4 && currentDay != 5)
                && !(currentDay == 6 && clCurrent.get(Calendar.HOUR_OF_DAY) < 23))
                || (gameDay == 6 && (currentDay != 6 && currentDay != 7 && currentDay != 1 && currentDay != 2)
                && !(currentDay == 3 && clCurrent.get(Calendar.HOUR_OF_DAY) < 23)))) {
            //System.out.println("debug " + gameDay + " debug " + currentDay + " debug " + clCurrent.get(Calendar.HOUR_OF_DAY));                                
            last = true;
        }
    }
}
