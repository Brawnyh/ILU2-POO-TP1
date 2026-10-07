package histoire;

import personnages.Chef;
import personnages.Druide;
import personnages.Gaulois;
import villagegaulois.Etal;
import villagegaulois.Village;

public class VillageSansChefException extends Exception {
	    public VillageSansChefException(String message) {
	        super(message);
	    }
}
	