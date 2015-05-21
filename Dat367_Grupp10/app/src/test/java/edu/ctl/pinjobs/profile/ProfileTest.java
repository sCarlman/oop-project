package edu.ctl.pinjobs.profile;

import org.junit.Before;
import org.junit.Test;

/**
 * Created by Albertsson on 15-05-20.
 */
public class ProfileTest {

    Profile profile;

    @Before
    public void before(){
        try{
            this.profile = new Profile("Carl","Albertsson","xmhdyj1992","carl.albertsson@gmail.com","0706145301","kålgårdsgatan 3B,Göteborg");
        }catch(WrongInputExeption e){
        }
    }

    @Test(expected = WrongInputExeption.class)
    public void testProfileSetFirstname1ExceptionExpected() throws WrongInputExeption{
        profile.setFirstName("Car7");
    }

    @Test(expected = WrongInputExeption.class)
    public void testProfileSetFirstname2ExceptionExpected() throws WrongInputExeption{
        profile.setFirstName(null);
    }

    @Test(expected = WrongInputExeption.class)
    public void testProfileSetFirstname3ExceptionExpected() throws WrongInputExeption{
        profile.setFirstName(" ");
    }

    @Test(expected = WrongInputExeption.class)
    public void testProfileSetFirstname4ExceptionExpected() throws WrongInputExeption{
        profile.setFirstName("78978798");
    }

    @Test(expected = WrongInputExeption.class)
    public void testProfileSetFirstname5ExceptionExpected() throws WrongInputExeption{
        profile.setFirstName("Carl.");
    }

    @Test
    public void testProfileSetFirstnameCorrect() {
        try {
            profile.setFirstName("Carl");
            profile.setFirstName("Aarón");
            assert true;
        } catch (WrongInputExeption e) {
            assert false;
        }
    }

    @Test(expected = WrongInputExeption.class)
    public void testProfileSetLastname1ExceptionExpected() throws WrongInputExeption{
        profile.setLastName("Alb3rtsson");
    }

    @Test(expected = WrongInputExeption.class)
    public void testProfileSetLastname2ExceptionExpected() throws WrongInputExeption{
        profile.setLastName("3go3");
    }

    @Test(expected = WrongInputExeption.class)
    public void testProfileSetLastname3ExceptionExpected() throws WrongInputExeption{
        profile.setLastName(null);
    }

    @Test(expected = WrongInputExeption.class)
    public void testProfileSetLastname4ExceptionExpected() throws WrongInputExeption{
        profile.setLastName(" ");
    }

    @Test(expected = WrongInputExeption.class)
    public void testProfileSetLastname5ExceptionExpected() throws WrongInputExeption{
        profile.setLastName(".Albertsson");
    }

    @Test(expected = WrongInputExeption.class)
    public void testProfileSetLastname6ExceptionExpected() throws WrongInputExeption{
        profile.setLastName("789789 ");
    }

    @Test
    public void testProfileSetLastnameCorrect() {
        try {
            profile.setLastName("Albertsson");
            profile.setLastName("Larssóns");
            assert true;
        } catch (WrongInputExeption e) {
            assert false;
        }
    }

    @Test(expected = WrongInputExeption.class)
    public void testProfileSetPassword1ExceptionExpected() throws WrongInputExeption{
        profile.setPassword("hdjshhjsdakhk");
    }

    @Test(expected = WrongInputExeption.class)
    public void testProfileSetPassword2ExceptionExpected() throws WrongInputExeption{
        profile.setPassword(null);
    }

    @Test(expected = WrongInputExeption.class)
    public void testProfileSetPassword3ExceptionExpected() throws WrongInputExeption{
        profile.setPassword(" ");
    }

    @Test(expected = WrongInputExeption.class)
    public void testProfileSetPassword4ExceptionExpected() throws WrongInputExeption{
        profile.setPassword("4");
    }

    @Test(expected = WrongInputExeption.class)
    public void testProfileSetPassword5ExceptionExpected() throws WrongInputExeption{
        profile.setPassword("D");
    }

    @Test(expected = WrongInputExeption.class)
    public void testProfileSetPassword6ExceptionExpected() throws WrongInputExeption{
        profile.setPassword("673829346327849");
    }

    //less than 5 chars should fail but our test profile
    //has only two chars so use this later
    //profile.setPassword("3bgD");

    @Test
    public void testProfileSetPasswordCorrect() {
        try {
            profile.setPassword("hdshjk28792");
            profile.setPassword("!bdj65D");
            assert true;
        } catch (WrongInputExeption e) {
            assert false;
        }
    }

}
