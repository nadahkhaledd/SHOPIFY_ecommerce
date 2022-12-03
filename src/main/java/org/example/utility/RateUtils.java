package org.example.utility;

import org.example.model.Star;

public class RateUtils {
   public Star computeNumberOfStars(double rate){
       Star star=new Star();
       star.hasHalfStar=(rate%1 !=0);
       star.numberOfFullStars= (int) (rate-(rate%1));
       star.numberOfEmptyStars=5-star.numberOfFullStars-((star.hasHalfStar)?1:0);
       return star;
   }//numberOfEmptyStars=3, hasHalfStar=true, numberOfFullStars=2
}
