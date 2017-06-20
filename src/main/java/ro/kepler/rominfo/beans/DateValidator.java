package ro.kepler.rominfo.beans;

import org.primefaces.context.RequestContext;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by Dragos on 09.06.2017.
 */

@FacesValidator("dateValidator")
public class DateValidator implements Validator{

    private static final String UNIVERSITY_START_DATE = "2016-10-01";
    private static final String UNIVERSITY_END_DATE = "2017-06-30";

    @Override
    public void validate(FacesContext facesContext, UIComponent uiComponent, Object o) throws ValidatorException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date universityStartDate = null;
        Date univeristyEndDate = null;
        try {
            universityStartDate = sdf.parse(UNIVERSITY_START_DATE);
            univeristyEndDate = sdf.parse(UNIVERSITY_END_DATE);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        Calendar c = Calendar.getInstance();
        Date date = (Date)o;
        c.setTime(date);
        int dayOfWeek = c.get(Calendar.DAY_OF_WEEK);

        /* dayOfWeek=7 => SATURDAY
         dayOfWeek=1 => SUNDAY*/
       if(date.before(universityStartDate) || date.after(univeristyEndDate) || dayOfWeek == 1 || dayOfWeek == 7) {
           FacesMessage msg =
                   new FacesMessage("Invalid Date","Invalid Date");
           msg.setSeverity(FacesMessage.SEVERITY_ERROR);
           throw new ValidatorException(msg);
       }else {
           RequestContext.getCurrentInstance().execute("PF('eventDialog').hide()");
       }
    }
}
