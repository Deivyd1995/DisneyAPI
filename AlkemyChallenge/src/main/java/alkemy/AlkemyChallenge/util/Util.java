package alkemy.AlkemyChallenge.util;

import java.sql.Timestamp;
import java.util.Date;

/**Util es para poder manera las fechas en este caso en paciente tengo una propiedad de fecha de ingreso , por esto
 * siempre tengo que hacer lo mismo*/
public class Util {
    public static Timestamp dateToTimestamp(Date date){
        Timestamp timestamp = new Timestamp(date.getTime());
        return timestamp;

    }
}

