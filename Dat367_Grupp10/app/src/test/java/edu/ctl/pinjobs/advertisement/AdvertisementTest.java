package edu.ctl.pinjobs.advertisement;

import org.junit.Before;
import org.junit.Test;

import edu.ctl.pinjobs.advertisement.model.Advertisement;
import edu.ctl.pinjobs.advertisement.model.Category;
import edu.ctl.pinjobs.advertisement.model.WrongAdInputException;
import edu.ctl.pinjobs.profile.MockProfile;

/**
 * Created by Filips on 5/20/2015.
 */
public class AdvertisementTest {

    Advertisement ad;

    @Before
    public void before(){
        try{
            this.ad = new Advertisement(new MockProfile(), "hämta kaffe", "nu tack!",
                    "rosenvägen2a", Category.OTHER, 22, 10, 2015, 1.3, 85.33);
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
    public void testAdvertisementSetLatitudeExceptionShouldNotBeCasted() {
        try {
            ad.setLongitude(90);
            ad.setLongitude(-90);
            assert true;
        } catch (WrongAdInputException e) {
            assert false;
        }
    }

    @Test(expected = WrongAdInputException.class)
    public void testAdvertisementSetTitle1ShouldCastException() throws  WrongAdInputException{
        ad.setTitle(null);
    }
    @Test(expected = WrongAdInputException.class)
    public void testAdvertisementSetTitle2ShouldCastException() throws  WrongAdInputException{
        ad.setTitle("");
    }
    @Test(expected = WrongAdInputException.class)
    public void testAdvertisementSetTitle3ShouldCastException() throws  WrongAdInputException{
        //More than 30 characters
        ad.setTitle("hahahahahahahahahahahahahahahahahahahahahahahahahahahahahahahahaha");
    }

    @Test(expected = WrongAdInputException.class)
    public void testAdvertisementSetTitle4ShouldCastException() throws  WrongAdInputException{
        ad.setTitle("1234");
    }
    @Test(expected = WrongAdInputException.class)
    public void testAdvertisementSetTitle5ShouldCastException() throws  WrongAdInputException{
        ad.setTitle("?!?!@@%#");
    }
    @Test(expected = WrongAdInputException.class)
    public void testAdvertisementSetTitleExceptionShouldNotBeCasted() throws  WrongAdInputException{
        try{
            ad.setTitle("skotta snö på min uppfart");
            assert true;
        }catch(WrongAdInputException e) {
            assert false;
        }
    }

    @Test(expected = WrongAdInputException.class)
    public void testAdvertisementSetDescription1ShouldCastException() throws  WrongAdInputException{
        ad.setDescription(null);
    }
    @Test(expected = WrongAdInputException.class)
    public void testAdvertisementSetDescription2ShouldCastException() throws  WrongAdInputException{
        ad.setDescription("");
    }
    @Test(expected = WrongAdInputException.class)
    public void testAdvertisementSetDescription3ShouldCastException() throws  WrongAdInputException{
        String tooLongString="";
        for(int i = 0; i<400;i++){
            tooLongString = tooLongString + "x";
        }
        ad.setDescription(tooLongString);
    }
    @Test(expected = WrongAdInputException.class)
    public void testAdvertisementSetDescription4ShouldCastException() throws  WrongAdInputException{
        //Should not be able to only write signs
        ad.setDescription("!??!?!?!#€%&(#)");
    }
    @Test(expected = WrongAdInputException.class)
    public void testAdvertisementSetDescription5ShouldCastException() throws  WrongAdInputException{
        //Should not be able to only write numbers
        ad.setDescription("12389734");
    }
    @Test(expected = WrongAdInputException.class)
    public void testAdvertisementSetDescriptionExceptionShouldNotBeCasted() throws  WrongAdInputException{
        try{
            ad.setDescription("Det finns sjukt mycket snö som måste bort. Jag ska till jobbet snart");
            assert true;
        }catch(WrongAdInputException e) {
            assert false;
        }
    }

    @Test(expected = WrongAdInputException.class)
    public void testAdvertisementSetLocation1ShouldCastException() throws  WrongAdInputException{
        ad.setLocation(null);
    }
    @Test(expected = WrongAdInputException.class)
    public void testAdvertisementSetLocation2ShouldCastException() throws  WrongAdInputException{
        ad.setLocation("");
    }
    @Test(expected = WrongAdInputException.class)
    public void testAdvertisementSetLocation3ShouldCastException() throws  WrongAdInputException{
        ad.setLocation("1238976234");
    }
    @Test(expected = WrongAdInputException.class)
    public void testAdvertisementSetLocation4ShouldCastException() throws  WrongAdInputException{
        ad.setLocation("!%&###€&????=)(");
    }
    @Test(expected = WrongAdInputException.class)
    public void testAdvertisementSetLocation5ShouldCastException() throws  WrongAdInputException{
        ad.setLocation("testvägen 1 ???");
    }
    @Test(expected = WrongAdInputException.class)
    public void testAdvertisementSetLocation6ShouldCastException() throws  WrongAdInputException{
        ad.setLocation("123 testvägen 123");
    }
    @Test(expected = WrongAdInputException.class)
    public void testAdvertisementSetLocation7ShouldCastException() throws  WrongAdInputException{
        ad.setLocation(null);
    }

    @Test(expected = WrongAdInputException.class)
    public void testAdvertisementSetLocationExceptionShouldNotBeCasted() throws  WrongAdInputException{
        try{
            ad.setLocation("Alvgatan 1, Varberg");
            assert true;
        }catch(WrongAdInputException e) {
            assert false;
        }
    }

}
