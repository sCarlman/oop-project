package edu.ctl.pinjobs.advertisement;

import org.junit.Before;
import org.junit.Rule;
import org.junit.rules.ExpectedException;
import org.junit.Test;

import edu.ctl.pinjobs.Advertisement.Advertisement;
import edu.ctl.pinjobs.Advertisement.Category;
import edu.ctl.pinjobs.Advertisement.IAdvertisement;
import edu.ctl.pinjobs.Advertisement.WrongAdInputException;

/**
 * Created by Filips on 5/20/2015.
 */
public class AdvertisementTest {

    Advertisement ad;

    @Before
    public void before(){
        try{
            this.ad = new Advertisement(new MockProfile(), "hämta kaffe", "nu tack!",
                    "rosenvägen2a", Category.OTHER, 22, 03, 1992, 1.3, 85.33);
        }catch(WrongAdInputException e){
        }
    }

    @Test(expected = WrongAdInputException.class)
    public void testAdvertisementSetLongitudeExceptionSHouldBeCasted() throws  WrongAdInputException{
        ad.setLongitude(181);
        ad.setLongitude(-181);
    }
    @Test
    public void testAdvertisementSetLongitudeExceptionShouldNotBeCasted(){
        try {
            ad.setLongitude(180);
            //fail("InvalidIPAddressException not thrown.");
        } catch(WrongAdInputException e) {
            //assertNotNull(e.getMessage());
        }
    }


}
