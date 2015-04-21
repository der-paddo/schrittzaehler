package paddo.schrittzaehler;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Paddo on 20.04.2015.
 */
public class SaveDate implements Serializable {
    String datum = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
    int schritte=zaehler.numSteps;
}
