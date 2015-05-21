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

    @Test(expected = WrongInputExeption.class)
    public void testProfileSetPassword7ExceptionExpected() throws WrongInputExeption{
        profile.setPassword("!!!!!!!!!");
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

    @Test(expected = WrongInputExeption.class)
    public void testProfileSetEmail1ExceptionExpected() throws WrongInputExeption{
        profile.setEmail(null);
    }
    @Test(expected = WrongInputExeption.class)
    public void testProfileSetEmail2ExceptionExpected() throws WrongInputExeption{
        profile.setEmail("dasdasdfe");
    }
    @Test(expected = WrongInputExeption.class)
    public void testProfileSetEmail3ExceptionExpected() throws WrongInputExeption{
        profile.setEmail("1234564");
    }
    @Test(expected = WrongInputExeption.class)
    public void testProfileSetEmail4ExceptionExpected() throws WrongInputExeption{
        profile.setEmail("oabjs.d!?!?!?!@mail.com");
    }
    @Test(expected = WrongInputExeption.class)
    public void testProfileSetEmail5ExceptionExpected() throws WrongInputExeption{
        profile.setEmail("@");
    }
    @Test(expected = WrongInputExeption.class)
    public void testProfileSetEmail6ExceptionExpected() throws WrongInputExeption{
        profile.setEmail("hej@");
    }
    @Test(expected = WrongInputExeption.class)
    public void testProfileSetEmail7ExceptionExpected() throws WrongInputExeption{
        profile.setEmail("@mail.com");
    }
    @Test(expected = WrongInputExeption.class)
    public void testProfileSetEmail8ExceptionExpected() throws WrongInputExeption{
        profile.setEmail("asdasd@asfd.com!");
    }
    @Test
    public void testProfileSetEmailCorrect() {
        try {
            profile.setPassword("carl@albertsson.com");
            profile.setPassword("larssón@test.com");
            assert true;
        } catch (WrongInputExeption e) {
            assert false;
        }
    }

    @Test(expected = WrongInputExeption.class)
    public void testProfileSetPhone1ExceptionExpected() throws WrongInputExeption{
        profile.setPhone(null);
    }
    @Test(expected = WrongInputExeption.class)
    public void testProfileSetPhone2ExceptionExpected() throws WrongInputExeption{
        profile.setPhone("");
    }
    @Test(expected = WrongInputExeption.class)
    public void testProfileSetPhone3ExceptionExpected() throws WrongInputExeption{
        profile.setPhone("01234");
    }
    @Test(expected = WrongInputExeption.class)
    public void testProfileSetPhone4ExceptionExpected() throws WrongInputExeption{
        profile.setPhone("asflfkj");
    }
    @Test(expected = WrongInputExeption.class)
    public void testProfileSetPhone5ExceptionExpected() throws WrongInputExeption{
        profile.setPhone("!?!?!?????");
    }
    @Test(expected = WrongInputExeption.class)
    public void testProfileSetPhone6ExceptionExpected() throws WrongInputExeption{
        profile.setPhone("01010101010101010101010");
    }
    @Test(expected = WrongInputExeption.class)
    public void testProfileSetPhone7ExceptionExpected() throws WrongInputExeption{
        profile.setPhone("070654312q");
    }
    @Test(expected = WrongInputExeption.class)
    public void testProfileSetPhone8ExceptionExpected() throws WrongInputExeption{
        profile.setPhone("070654312!");
    }
    @Test(expected = WrongInputExeption.class)
    public void testProfileSetPhone9ExceptionExpected() throws WrongInputExeption{
        profile.setPhone("0000000000");
    }
    @Test
    public void testProfileSetPhoneCorrect() {
        try {
            profile.setPhone("0708654322");

            assert true;
        } catch (WrongInputExeption e) {
            assert false;
        }
    }

    @Test(expected = WrongInputExeption.class)
    public void testProfileSetAddress1ExceptionExpected() throws WrongInputExeption{
        profile.setAddress(null);
    }
    @Test(expected = WrongInputExeption.class)
    public void testProfileSetAddress2ExceptionExpected() throws WrongInputExeption{
        profile.setAddress("");
    }
    @Test(expected = WrongInputExeption.class)
    public void testProfileSetAddress3ExceptionExpected() throws WrongInputExeption{
        profile.setAddress("123234567");
    }
    @Test(expected = WrongInputExeption.class)
    public void testProfileSetAddress4ExceptionExpected() throws WrongInputExeption{
        profile.setAddress("123 testavenue 123");
    }
    @Test(expected = WrongInputExeption.class)
    public void testProfileSetAddress5ExceptionExpected() throws WrongInputExeption{
        profile.setAddress("!#%€&€/");
    }
    @Test(expected = WrongInputExeption.class)
    public void testProfileSetAddress6ExceptionExpected() throws WrongInputExeption{
        profile.setAddress("road! 23");
    }
    @Test(expected = WrongInputExeption.class)
    public void testProfileSetAddress7ExceptionExpected() throws WrongInputExeption{
        profile.setAddress("23 testväg");
    }
    @Test(expected = WrongInputExeption.class)
    public void testProfileSetAddress8ExceptionExpected() throws WrongInputExeption{
        profile.setAddress("testvägen");
    }
    @Test(expected = WrongInputExeption.class)
    public void testProfileSetAddress9ExceptionExpected() throws WrongInputExeption{
        profile.setAddress("aaaaaaaaa 4");
    }
    @Test
    public void testProfileSetAddressCorrect() {
        try {
            profile.setAddress("Alvgatan 1, Varberg");
            profile.setAddress("ócsa, hungary");
            assert true;
        } catch (WrongInputExeption e) {
            assert false;
        }
    }




}
