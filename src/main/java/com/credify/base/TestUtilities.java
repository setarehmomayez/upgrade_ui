package com.credify.base;

import org.passay.CharacterRule;
import org.passay.EnglishCharacterData;
import org.passay.PasswordGenerator;

import org.testng.Assert;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.concurrent.ThreadLocalRandom;

public class TestUtilities extends BaseTest {

	private static final String TEST_DEFAULT_PROPERTIES = "test.properties";
	private static final String ALPHA_CAPS="ABCDEFGHIJKLMNOPQRSTUVWXYZ";
	private static final String ALPHA="abcdefghijklmnopqrstuvwxyz";
	private static final String NUM="0123456789";

	// STATIC SLEEP 
	protected void sleep(long millis) {
		try {
			Thread.sleep(millis);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public static Properties loadTestProperties() {

		Properties testProperties = new Properties();
		InputStream propertiesInputStream = null;
		try {
			// Loading the default settings 1st
			propertiesInputStream = TestUtilities.class.getClassLoader().getResourceAsStream(TEST_DEFAULT_PROPERTIES);
			if (propertiesInputStream != null) {
				testProperties.load(propertiesInputStream);
			}else{
				Assert.fail("Unable to find test properties: " + TEST_DEFAULT_PROPERTIES);
			}

		} catch (IOException e) {

		} finally {
			if (propertiesInputStream != null) {
				try {
					propertiesInputStream.close();
				} catch (IOException e) {

				}
			}
		}

		return testProperties;
	}

	public static String createPassword(){
		CharacterRule upperCase = new CharacterRule(EnglishCharacterData.UpperCase);
		upperCase.setNumberOfCharacters(1);
		CharacterRule digits = new CharacterRule(EnglishCharacterData.Digit);
		digits.setNumberOfCharacters(1);
		CharacterRule lowerCase = new CharacterRule(EnglishCharacterData.LowerCase);
		lowerCase.setNumberOfCharacters(1);



		PasswordGenerator gen = new PasswordGenerator();
		String password = gen.generatePassword(8, upperCase, digits, lowerCase);
		return password;
	}

	public static String createEmail(){
		return "seti" + ThreadLocalRandom.current().nextInt(1, 999)+"@upgrade-challenge.com";
	}

}
