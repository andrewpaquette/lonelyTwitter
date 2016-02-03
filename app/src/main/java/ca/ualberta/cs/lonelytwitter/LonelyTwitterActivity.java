package ca.ualberta.cs.lonelytwitter;



/**
 * Learning JavaDocs for Lab 5
 * LonelyTwitter let's you input text and view it later.
 *
 * @author Andrew Paquette, but not really
 * @version 1.57, 12/19/03
 * @since 2016-02-02
 *
 */



import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Date;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

public class LonelyTwitterActivity extends Activity {

	private static final String FILENAME = "file.sav";
	private EditText bodyText;
	private ListView oldTweetsList;

	/**
	 * onCreate is called when the activity is first created.
	 * it gathers related classes to the main activity
	 *
	 * @see OnStart()
	 * which is similar but runs more often.
	 */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		bodyText = (EditText) findViewById(R.id.body);
		Button saveButton = (Button) findViewById(R.id.save);
		oldTweetsList = (ListView) findViewById(R.id.oldTweetsList);

		saveButton.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {
				setResult(RESULT_OK);
				String text = bodyText.getText().toString();
				saveInFile(text, new Date(System.currentTimeMillis()));
				finish();
			}
		});
	}

	/**
	 * onStart is called whenever the activity is displayed.
	 * it gathers related classes to the main activity,
	 * and perhaps does some stuff.
	 * @see OnStart()
	 * which is similar but runs less often.
	 */
	@Override
	protected void onStart() {
		// TODO Auto-generated method stub
		super.onStart();
		String[] tweets = loadFromFile();
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
				R.layout.list_item, tweets);
		oldTweetsList.setAdapter(adapter);
	}

	/**
	 * loadFromFile populates the application with whatever currently resides
	 * in the file found at FILENAME.
	 * @return an array of loaded text.
	 */
	private String[] loadFromFile() {
		ArrayList<String> tweets = new ArrayList<String>();
		try {
			FileInputStream fis = openFileInput(FILENAME);
			BufferedReader in = new BufferedReader(new InputStreamReader(fis));
			String line = in.readLine();
			while (line != null) {
				tweets.add(line);
				line = in.readLine();
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return tweets.toArray(new String[tweets.size()]);
	}

	/**
	 * saveInFile takes text and date and saves them to file.
	 * @param text text to be saved
	 * @param date time to be saved alongside entered text
	 */
	private void saveInFile(String text, Date date) {
		try {
			FileOutputStream fos = openFileOutput(FILENAME,
					Context.MODE_APPEND);
			fos.write(new String(date.toString() + " | " + text)
					.getBytes());
			fos.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


	/**
	 * JAVADOCS SYNTAX
	 * Does whatever
	 * <p>HTML tags work!!</p>
	 * @since 1.2.1
	 * @see lonelyTwitterActivity for more information
	 * except don't bc that's this class
	 *
	 * @author Andrew Paquette
	 * @version 1.57, 12/19/03
	 *
	 * (the following is a lie)
	 * @param intent This is the intent to be run immediately after hitting start
	 * @return the value
	 */

}