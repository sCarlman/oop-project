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
    public void testAdvertisementSetLongitudeOver180ExceptionSHouldBeCasted() throws  WrongAdInputException{
        ad.setLongitude(181);
    }
    @Test(expected = WrongAdInputException.class)
    public void testAdvertisementSetLongitudeBelow180ExceptionSHouldBeCasted() throws  WrongAdInputException{
        ad.setLongitude(-181);
    }
    @Test
    public void testAdvertisementSetLongitudeExceptionShouldNotBeCasted(){
        try {
            ad.setLongitude(180);
            ad.setLongitude(-180);
            assert true;
        } catch(WrongAdInputException e) {
            assert false;
        }
    }

    @Test(expected = WrongAdInputException.class)
    public void testAdvertisementSetLatitudeOver90ExceptionSHouldBeCasted() throws  WrongAdInputException{
        ad.setLongitude(91);
    }
    @Test(expected = WrongAdInputException.class)
    public void testAdvertisementSetLatitudeBelow90ExceptionSHouldBeCasted() throws  WrongAdInputException{
        ad.setLongitude(-91);
    }
    @Test
    public void testAdvertisementSetLatitudeExceptionShouldNotBeCasted(){
        try {
            ad.setLongitude(90);
            ad.setLongitude(-90);
            assert true;
        } catch(WrongAdInputException e) {
            assert false;
        }
    }

    @Test(expected = WrongAdInputException.class)
    public void testAdvertisementSetLocation1ShouldCastException() throws  WrongAdInputException{
        ad.setTitle("   ");
        ad.setTitle("76537845");
        ad.setTitle("");
        ad.setTitle("asdhasjd%%#(/=%");
        ad.setTitle("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
    }


}
