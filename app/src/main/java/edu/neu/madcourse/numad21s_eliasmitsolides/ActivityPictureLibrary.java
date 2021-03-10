package edu.neu.madcourse.numad21s_eliasmitsolides;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

public class ActivityPictureLibrary extends AppCompatActivity {

    private static final String TAG = "ActivityPictureLibrary";
    private EditText numberEditText;
    private ImageView imageView;
    private JSONObject imageContentJSON;
    private Drawable imageToDraw;
    private String url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_picture_library);

        //java imageView                imageView in the xml
        //imageView = findViewById(R.id.imageView);
        numberEditText = findViewById(R.id.editTextNumberSigned);
    }

    public static String convertStreamToString(InputStream inputStream){
        StringBuilder stringBuilder=new StringBuilder();
        try {
            BufferedReader bufferedReader=new BufferedReader(new InputStreamReader(inputStream));
            String len;
            while((len=bufferedReader.readLine())!=null){
                stringBuilder.append(len);
            }
            bufferedReader.close();
            return stringBuilder.toString().replace(",", ",\n");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }
    private void closeKeyboard(){
        View view = this.getCurrentFocus();
        if (view != null){
            InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    public void getPhotoButton(View view) throws ProtocolException {
        PingWebServiceTask task = new PingWebServiceTask();
        String urlQuery = "https://jsonplaceholder.typicode.com/photos/";
        String numberEditTextContent = numberEditText.getText().toString();
        if (numberEditTextContent.equals("")){
            closeKeyboard();
            Snackbar.make(view, "Input Number Not Valid, Type In A Number", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show();
            return;
        }
        int whatNumber = Integer.parseInt(numberEditTextContent);
        if (whatNumber <= 0 || whatNumber > 5000){
            closeKeyboard();
            Snackbar.make(view, "Input Number Not Valid, Not Within Range?", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show();
            return;
        }
        try {
            closeKeyboard();
            urlQuery += numberEditTextContent;
            task.execute(urlQuery);

//            InputStream imageStream = null;
//            imageStream = (InputStream) new URL(imageContentJSON.getString("url")).getContent();
//            Drawable imageToRender = Drawable.createFromStream(imageStream, "src name");
//            ImageView result_view = findViewById(R.id.imageView);
//            result_view.setImageDrawable(imageToRender);
        }
        catch (Exception e) {
            closeKeyboard();
            Toast.makeText(getApplication(),e.toString(),Toast.LENGTH_SHORT).show();
        }


    }

    private class PingWebServiceTask  extends AsyncTask<String, Integer, Bitmap> {

        @Override
        protected void onProgressUpdate(Integer... values) {
            Log.i(TAG, "Making progress...");
        }

        @Override
        protected Bitmap doInBackground(String... params) {

        JSONObject jObject = new JSONObject();
        try {

            // Initial website is "https://jsonplaceholder.typicode.com/posts/1"
            URL url = new URL(params[0]);

            HttpURLConnection urlQueryRequest = (HttpURLConnection) url.openConnection();
            urlQueryRequest.setRequestMethod("GET");
            //urlQueryRequest.setRequestProperty("Content-Type", "application/json; utf-8");
            //urlQueryRequest.setRequestProperty("Accept", "application/json");
            urlQueryRequest.setDoInput(true);
            urlQueryRequest.connect();

            InputStream inputFromSite = urlQueryRequest.getInputStream();

            StringBuilder resp = new StringBuilder();
            String returnLines;
            String toUse = null; //the equivalent of NetworkUtil.convertStreamToString(...) in prof's
                            // example
            try {
                BufferedReader readerForInputFromSite = new BufferedReader(new InputStreamReader((inputFromSite)) );
                while ( (returnLines = readerForInputFromSite.readLine() ) != null ){
                  resp.append(returnLines);
                }
                readerForInputFromSite.close();
                toUse = resp.toString().replace(",", ",\n");
            }
            catch(Exception e){

            }//where convertStreamToString
            //where httpResponse ends
            urlQueryRequest.disconnect();

            // JSONArray jArray = new JSONArray(resp);    // Use this if your web service returns an array of objects.  Arrays are in [ ] brackets.
            // Transform String into JSONObject
            jObject = new JSONObject(toUse);

            } catch (MalformedURLException e) {
                Log.e(TAG,"MalformedURLException");
                e.printStackTrace();
            } catch (ProtocolException e) {
                Log.e(TAG,"ProtocolException");
                e.printStackTrace();
            } catch (IOException e) {
                Log.e(TAG,"IOException");
                e.printStackTrace();
            } catch (JSONException e) {
                Log.e(TAG,"JSONException");
                e.printStackTrace();
            }
            //ImageView result_view = (ImageView) findViewById(R.id.imageView);
            imageContentJSON = jObject;
            InputStream imageStream = null;
            Bitmap imageBitMapVersion = null;
            try {
                Log.d("Photo URL get from JSON", jObject.getString("thumbnailUrl"));
                String urlString = jObject.getString("thumbnailUrl");
                imageContentJSON = jObject;
                //imageStream = (InputStream) new URL(urlString).openStream();
//                //imageBitMapVersion = BitmapFactory.decodeStream(imageStream);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            //imageToDraw = Drawable.createFromStream(imageStream, "src name");

            //result_view.setImageDrawable(imageToRender);
            return imageBitMapVersion;

        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {
            //super.onPostExecute(jObject);
            //imageContentJSON = jObject;
            //imageView.setImageBitmap(bitmap);
            String urlString = null;
            try {
                urlString = imageContentJSON.getString("thumbnailUrl");
            } catch (JSONException e) {
                e.printStackTrace();
            }
            //imageStream = web.openStream();
            //imageBitMapVersion = BitmapFactory.decodeStream(imageStream);
            Uri web = Uri.parse(urlString);
            Intent webIntent = new Intent(Intent.ACTION_VIEW, web);
            startActivity(webIntent);

        }
    }
}