package utils;

import helpers.HelperMethods;
import org.example.model.Star;
import org.example.utility.RateUtils;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class RateUtilsTest {
    private RateUtils rateUtils;
    private HelperMethods helperMethods;

    public RateUtilsTest() {
         rateUtils=new RateUtils();
         helperMethods=new HelperMethods();
    }

    @Test(expected = IllegalArgumentException.class)
    public void computeNumberOfStars_sendNegativeRate_ExpectedIllegalArgException(){
        //arrange
        double rate=-1.0;
        //act
        rateUtils.computeNumberOfStars(rate);
    }
    @Test
    public void computeNumberOfStars_sendValidRate_ExpectedStarObject(){
        //arrange
        double rate=3.5;
        Star expectedStar=helperMethods.initStar(3,true,1);
        //act
        Star result=rateUtils.computeNumberOfStars(rate);
        System.out.println(result);
        //assert
        assertTrue(result.equals(expectedStar));

    }
}
