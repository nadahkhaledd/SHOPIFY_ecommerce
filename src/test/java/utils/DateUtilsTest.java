package utils;

import org.example.utility.DateUtils;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;

import javax.servlet.http.HttpSession;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
public class DateUtilsTest {

    private DateUtils dateUtils;
    private Date dateMock;
    private DateFormat dateFormatMock;

    public DateUtilsTest() {
        dateMock = Mockito.mock(Date.class);
        dateFormatMock = Mockito.mock(DateFormat.class);
        dateUtils = new DateUtils();
    }

    @Before
    public void init() {
        MockitoAnnotations.openMocks(this);
    }

    @Test(expected = RuntimeException.class)
    public void testConvertStringToDate_givenInvalidInput_expectRunTimeException(){
        dateUtils.convertStringToDate("11");
    }


    @Test
    public void testDateYearsAgo_givenValueOf5_returnDate5YearsAgo(){
        //Arrange
        LocalDate localDate= LocalDate.parse("2017-12-15");

        //ACT
        LocalDate result = dateUtils.dateYearsAgo(5);

        //Assert
        assertEquals(localDate, result);

    }

    @Test
    public void testConvertDateToString() throws ParseException {
        //Arrange
        Date date = new SimpleDateFormat("dd-MM-yyyy").parse("13-12-1989");

        //ACT
        String result = dateUtils.convertDateToString(date);

        //Assert
        assertEquals("1989-12-13", result);
    }


}
