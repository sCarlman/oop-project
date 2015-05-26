package edu.ctl.pinjobs.profile;

import org.junit.Before;
import org.junit.Test;

import edu.ctl.pinjobs.profile.model.Profile;
import edu.ctl.pinjobs.profile.model.WrongInputExeption;

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
    //tests a firstname that consist a digit
    @Test(expected = WrongInputExeption.class)
    public void testProfileSetFirstname1ExceptionExpected() throws WrongInputExeption{
        profile.setFirstName("Car7");
    }
    //tests a firstname that is null
    @Test(expected = WrongInputExeption.class)
    public void testProfileSetFirstname2ExceptionExpected() throws WrongInputExeption{
        profile.setFirstName(null);
    }
    //tests a firstname consisting of an empty string
    @Test(expected = WrongInputExeption.class)
    public void testProfileSetFirstname3ExceptionExpected() throws WrongInputExeption{
        profile.setFirstName("");
    }
    //tests a firstname consisting of only numbers
    @Test(expected = WrongInputExeption.class)
    public void testProfileSetFirstname4ExceptionExpected() throws WrongInputExeption{
        profile.setFirstName("78978798");
    }
    //tests a firstname consisting of illegal signs
    @Test(expected = WrongInputExeption.class)
    public void testProfileSetFirstname5ExceptionExpected() throws WrongInputExeption{
        profile.setFirstName("Carl.");
    }
    //test okey firstnames
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
    //tests a lastname that consists of a number
    @Test(expected = WrongInputExeption.class)
    public void testProfileSetLastname1ExceptionExpected() throws WrongInputExeption{
        profile.setLastName("Alb3rtsson");
    }
    //tests a lastname that is too long
    @Test(expected = WrongInputExeption.class)
    public void testProfileSetLastname2ExceptionExpected() throws WrongInputExeption{
        String tooLongName = "";
        for(int i = 0; i<500; i++){
            tooLongName = tooLongName + "x";
        }
        profile.setLastName(tooLongName);
    }
    //tests a lastname that is null
    @Test(expected = WrongInputExeption.class)
    public void testProfileSetLastname3ExceptionExpected() throws WrongInputExeption{
        profile.setLastName(null);
    }
    //tests a lastname consisting of an empty string
    @Test(expected = WrongInputExeption.class)
    public void testProfileSetLastname4ExceptionExpected() throws WrongInputExeption{
        profile.setLastName("");
    }
    //tests a lastname consisting of illegal signs
    @Test(expected = WrongInputExeption.class)
    public void testProfileSetLastname5ExceptionExpected() throws WrongInputExeption{
        profile.setLastName(".Albertsson");
    }
    //tests lastname consisting of numbers and spaces
    @Test(expected = WrongInputExeption.class)
    public void testProfileSetLastname6ExceptionExpected() throws WrongInputExeption{
        profile.setLastName("789789 ");
    }
    //tests lastnames that are okey
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


    //tests a password which is null
    @Test(expected = WrongInputExeption.class)
    public void testProfileSetPassword1ExceptionExpected() throws WrongInputExeption{
        profile.setPassword(null);
    }
    //tests a password consisting of an empty string
    @Test(expected = WrongInputExeption.class)
    public void testProfileSetPassword2ExceptionExpected() throws WrongInputExeption{
        profile.setPassword("");
    }
    //tests a password consisting of only one digit
    @Test(expected = WrongInputExeption.class)
    public void testProfileSetPassword3ExceptionExpected() throws WrongInputExeption{
        profile.setPassword("4");
    }
    //tests a password consisting of only one letter in it
    @Test(expected = WrongInputExeption.class)
    public void testProfileSetPassword4ExceptionExpected() throws WrongInputExeption{
        profile.setPassword("D");
    }
    //tests a password with spaces in it
    @Test(expected = WrongInputExeption.class)
    public void testProfileSetPassword5ExceptionExpected() throws WrongInputExeption{
        profile.setPassword("6738293  327849");
    }
    //tests a password that is too long
    @Test(expected = WrongInputExeption.class)
    public void testProfileSetPassword6ExceptionExpected() throws WrongInputExeption{
        profile.setPassword("aaaaaaaaaaaaaaaaaaaaaaaaa");
    }

    //less than 5 chars should fail but our test profile
    //has only two chars so use this later
    //profile.setPassword("3bgD");

    //tests passwords that are okey
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

    //tests an email which is null
    @Test(expected = WrongInputExeption.class)
    public void testProfileSetEmail1ExceptionExpected() throws WrongInputExeption{
        profile.setEmail(null);
    }
    //tests an email consisting of oly letters
    @Test(expected = WrongInputExeption.class)
    public void testProfileSetEmail2ExceptionExpected() throws WrongInputExeption{
        profile.setEmail("dasdasdfe");
    }
    //tests an email consisting of only digits
    @Test(expected = WrongInputExeption.class)
    public void testProfileSetEmail3ExceptionExpected() throws WrongInputExeption{
        profile.setEmail("1234564");
    }
    //tests an email without spaces in it
    @Test(expected = WrongInputExeption.class)
    public void testProfileSetEmail4ExceptionExpected() throws WrongInputExeption{
        profile.setEmail("oabjs.   d!?!?!?!@mail.com");
    }
    //tests an email without anything before or after the @
    @Test(expected = WrongInputExeption.class)
    public void testProfileSetEmail5ExceptionExpected() throws WrongInputExeption{
        profile.setEmail("@");
    }
    //tests an email without anything after the @
    @Test(expected = WrongInputExeption.class)
    public void testProfileSetEmail6ExceptionExpected() throws WrongInputExeption{
        profile.setEmail("hej@");
    }
    //tests an email without anything before the @
    @Test(expected = WrongInputExeption.class)
    public void testProfileSetEmail7ExceptionExpected() throws WrongInputExeption{
        profile.setEmail("@mail.com");
    }
    //tests an email consisting of signs that are not okey
    @Test(expected = WrongInputExeption.class)
    public void testProfileSetEmail8ExceptionExpected() throws WrongInputExeption{
        profile.setEmail("asdasd@asfd.com!");
    }
    //tests correct email addresses
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

    //tests a phone number which is null
    @Test(expected = WrongInputExeption.class)
    public void testProfileSetPhone1ExceptionExpected() throws WrongInputExeption{
        profile.setPhone(null);
    }
    //tests a phone number consisting of an empty string
    @Test(expected = WrongInputExeption.class)
    public void testProfileSetPhone2ExceptionExpected() throws WrongInputExeption{
        profile.setPhone("");
    }
    //tests a phone number which is too short
    @Test(expected = WrongInputExeption.class)
    public void testProfileSetPhone3ExceptionExpected() throws WrongInputExeption{
        profile.setPhone("01234");
    }
    //tests a phone number without digits consisting of letters
    @Test(expected = WrongInputExeption.class)
    public void testProfileSetPhone4ExceptionExpected() throws WrongInputExeption{
        profile.setPhone("asflfkj");
    }
    //tests a phone number without digits consisting of signs
    @Test(expected = WrongInputExeption.class)
    public void testProfileSetPhone5ExceptionExpected() throws WrongInputExeption{
        profile.setPhone("!?!?!?????");
    }
    //tests a phone number which is too long
    @Test(expected = WrongInputExeption.class)
    public void testProfileSetPhone6ExceptionExpected() throws WrongInputExeption{
        profile.setPhone("01010101010101010101010");
    }
    //tests a phone number with a letter in it
    @Test(expected = WrongInputExeption.class)
    public void testProfileSetPhone7ExceptionExpected() throws WrongInputExeption{
        profile.setPhone("070654312q");
    }
    //tests a phone number with signs in it
    @Test(expected = WrongInputExeption.class)
    public void testProfileSetPhone8ExceptionExpected() throws WrongInputExeption{
        profile.setPhone("070654312!");
    }
    //tests a phone number which don't start with 07
    @Test(expected = WrongInputExeption.class)
    public void testProfileSetPhone9ExceptionExpected() throws WrongInputExeption{
        profile.setPhone("0000000000");
    }
    //tests a correct phone number
    @Test
    public void testProfileSetPhoneCorrect() {
        try {
            profile.setPhone("0708654322");

            assert true;
        } catch (WrongInputExeption e) {
            assert false;
        }
    }

    //test an address which is null
    @Test(expected = WrongInputExeption.class)
    public void testProfileSetAddress1ExceptionExpected() throws WrongInputExeption{
        profile.setAddress(null);
    }
    //test an address consisting of only an empty string
    @Test(expected = WrongInputExeption.class)
    public void testProfileSetAddress2ExceptionExpected() throws WrongInputExeption{
        profile.setAddress("");
    }
    //test an address with only digits in entire address
    @Test(expected = WrongInputExeption.class)
    public void testProfileSetAddress3ExceptionExpected() throws WrongInputExeption{
        profile.setAddress("123234567,123123");
    }
    //test an address with only digits as city name
    @Test(expected = WrongInputExeption.class)
    public void testProfileSetAddress4ExceptionExpected() throws WrongInputExeption{
        profile.setAddress("123 testavenue 123,123");
    }
    //test an address without any letters at all
    @Test(expected = WrongInputExeption.class)
    public void testProfileSetAddress5ExceptionExpected() throws WrongInputExeption{
        profile.setAddress("!#%€&€/,&/(€#");
    }
    //test an address without letters in city
    @Test(expected = WrongInputExeption.class)
    public void testProfileSetAddress6ExceptionExpected() throws WrongInputExeption{
        profile.setAddress("road! 23,%&/#€");
    }
    //test an address without city after coma
    @Test(expected = WrongInputExeption.class)
    public void testProfileSetAddress7ExceptionExpected() throws WrongInputExeption{
        profile.setAddress("23 testväg,");
    }
    //test an address consisting of only a coma which should cast exception
    @Test(expected = WrongInputExeption.class)
    public void testProfileSetAddress8ExceptionExpected() throws WrongInputExeption{
        profile.setAddress(",");
    }
    //test an address without coma which should cast exception
    @Test(expected = WrongInputExeption.class)
    public void testProfileSetAddress9ExceptionExpected() throws WrongInputExeption{
        profile.setAddress("testvägen 23");
    }
    //tests an okey address
    @Test
    public void testProfileSetAddressCorrect() {
        try {
            profile.setAddress("Alvgatan 1,Varberg");

            assert true;
        } catch (WrongInputExeption e) {
            assert false;
        }
    }




}
