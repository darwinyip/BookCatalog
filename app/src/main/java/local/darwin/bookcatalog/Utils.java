package local.darwin.bookcatalog;

import android.text.TextUtils;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

final class Utils {
    private static final String LOG_TAG = Utils.class.getSimpleName();

    private Utils() {

    }

    static String joinList(List<String> list, String delimiter) {
        Log.d(LOG_TAG, "Joining List");
        if (list == null) return null;
        return list.stream().map(Object::toString).collect(Collectors.joining(delimiter));
    }

    static List<Book> fetchBookData(String requestUrl) {
        URL url = createUrl(requestUrl);
        String jsonResponse = null;
        try {
            jsonResponse = makeHttpRequest(url);
        } catch (IOException e) {
            Log.e(LOG_TAG, "Problem making the HTTP request.", e);
        }

        return extractBookFeatures(jsonResponse);
    }

    private static List<Book> extractBookFeatures(String bookJSON) {
        Log.d(LOG_TAG, "Extracting Book features...");
        if (TextUtils.isEmpty(bookJSON)) {
            return null;
        }

        List<Book> books = new ArrayList<>();

        try {
            JSONObject jsonObject = new JSONObject(bookJSON);
            JSONArray items = jsonObject.getJSONArray("items");
            Log.d(LOG_TAG, "Size of array: " + items.length());
            for (int i = 0; i < items.length(); i++) {
                Book.BookBuilder bookBuilder = new Book.BookBuilder();

                JSONObject volumeInfo = items.getJSONObject(i).getJSONObject("volumeInfo");

                bookBuilder.setTitle(volumeInfo.getString("title"));
                bookBuilder.setAuthors(toList(volumeInfo.getJSONArray("authors")));
                bookBuilder.setPublisher(volumeInfo.getString("publisher"));
                bookBuilder.setDescription(volumeInfo.getString("description"));

                if (volumeInfo.has("industryIdentifiers")) {
                    JSONArray industryIdentifiers = volumeInfo.getJSONArray("industryIdentifiers");
                    for (int j = 0; j < industryIdentifiers.length(); j++) {
                        String type = industryIdentifiers.getJSONObject(j).getString("type");
                        if (type.equals("ISBN_10")) {
                            bookBuilder.setIsbn10(industryIdentifiers.getJSONObject(j).getString("identifier"));
                        } else if (type.equals("ISBN_13")) {
                            bookBuilder.setIsbn13(industryIdentifiers.getJSONObject(j).getString("identifier"));
                        }
                    }
                }
                books.add(bookBuilder.createBook());
            }
        } catch (JSONException e) {
            Log.e(LOG_TAG, "Problem parsing the book JSON results.", e);
        }

        return books;
    }

    private static List<String> toList(JSONArray jsonArray) throws JSONException {
        List<String> list = new ArrayList<>();
        for (int i = 0; i < jsonArray.length(); i++) {
            list.add(jsonArray.getString(i));
        }
        return list;
    }

    private static String makeHttpRequest(URL url) throws IOException {
        String jsonResponse = "";
        HttpURLConnection urlConnection = null;
        InputStream inputStream = null;
        try {
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.setReadTimeout(10000);
            urlConnection.setConnectTimeout(15000);
            urlConnection.connect();
            if (urlConnection.getResponseCode() == 200) {
                inputStream = urlConnection.getInputStream();
                jsonResponse = readFromStream(inputStream);
            } else {
                Log.e(LOG_TAG, String.valueOf(urlConnection.getResponseCode()));
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
            if (inputStream != null) {
                inputStream.close();
            }
        }
        return jsonResponse;
    }

    private static String readFromStream(InputStream inputStream) throws IOException {
        StringBuilder output = new StringBuilder();
        if (inputStream != null) {
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, Charset.forName("UTF-8"));
            BufferedReader reader = new BufferedReader(inputStreamReader);
            String line = reader.readLine();
            while (line != null) {
                output.append(line);
                line = reader.readLine();
            }
        }
        return output.toString();
    }

    private static URL createUrl(String requestUrl) {
        URL url = null;
        try {
            url = new URL(requestUrl);
        } catch (MalformedURLException e) {
            Log.e(LOG_TAG, "Error creating URL.", e);
        }
        return url;
    }
}
