package org.example.utility;

import org.example.model.Star;

public class RateUtils {
   public Star computeNumberOfStars(double rate){
       if(rate<0)
           throw new IllegalArgumentException();
       Star star=new Star();
       star.setHasHalfStar((rate%1 !=0));
       star.setNumberOfFullStars ((int) (rate-(rate%1)));
       star.setNumberOfEmptyStars(5-star.getNumberOfFullStars()-((star.isHasHalfStar())?1:0));
       return star;
   }//numberOfEmptyStars=3, hasHalfStar=true, numberOfFullStars=2
}
