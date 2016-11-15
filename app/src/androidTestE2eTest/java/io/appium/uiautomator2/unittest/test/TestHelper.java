package io.appium.uiautomator2.unittest.test;

import android.app.Activity;
import android.app.Instrumentation;
import android.content.Intent;
import android.support.test.InstrumentationRegistry;
import android.support.test.uiautomator.By;
import android.support.test.uiautomator.Until;

import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.IOException;

import io.appium.uiautomator2.server.ServerConfig;
import io.appium.uiautomator2.utils.Logger;

import static android.os.SystemClock.elapsedRealtime;
import static io.appium.uiautomator2.utils.Device.getUiDevice;
import static java.util.concurrent.TimeUnit.SECONDS;

public abstract class TestHelper {
    public static final MediaType JSON = MediaType.parse("application/json; " + "charset=utf-8");
    private static final OkHttpClient client = new OkHttpClient();
    private static final String baseUrl = "http://localhost:" + ServerConfig.getServerPort();
    private static final Instrumentation mInstrumentation = InstrumentationRegistry.getInstrumentation();

    static {
        final int timeout = 15 * 1000;
        client.setConnectTimeout(timeout, SECONDS);
        client.setReadTimeout(timeout, SECONDS);
        client.setWriteTimeout(timeout, SECONDS);
    }

    public static String get(final String path) {
        Request request = new Request.Builder().url(baseUrl + path).build();

        return execute(request);
    }

    public static Response get(final String path, Response response) {
        Request request = new Request.Builder().url(baseUrl + path).build();
        response = execute(request,response);
        return response;
    }

    public static final void waitForNetty() {
        long start = elapsedRealtime();
        boolean unsuccessful = true;

        do {
            try {
                get("/wd/hub/status");
                unsuccessful = false;
            } catch (Exception e) {
                Logger.info("Waiting for the server ..");
            }
        } while (unsuccessful && (elapsedRealtime() - start < 5000));

        if (unsuccessful) {
            throw new RuntimeException("Failed to contact io.appium.uiautomator2.server on " + baseUrl);
        }
    }

    public static String post(final String path, String body) {
        Request request = new Request.Builder().url(baseUrl + path).post(RequestBody.create(JSON, body)).build();
        Logger.info("POST: " + body);
        return execute(request);
    }

    public static String delete(final String path, String body) {
        Request request = new Request.Builder().url(path).delete(RequestBody.create(JSON, body)).build();
        Logger.info("DELETE: " + body);
        return execute(request);
    }

    public static Response post(final String path, String body, Response response) {
        Request request = new Request.Builder().url(baseUrl + path).post(RequestBody.create(JSON, body)).build();
        Logger.info("POST: " + body);
        return execute(request, response);
    }

    private static String execute(Request request) {
        String result;
        try {
            Response response = client.newCall(request).execute();
            result = response.body().string();
        } catch (IOException e) {
            throw new RuntimeException(request.method() + " \"" + request.urlString() + "\" failed. ", e);
        }
        return result;
    }

    private static Response execute(Request request, Response response) {
        try {
            response = client.newCall(request).execute();
        } catch (IOException e) {
            throw new RuntimeException(request.method() + " \"" + request.urlString() + "\" failed. ", e);
        }
        return response;
    }

    // Ported from android-support-test/rules/src/main/java/android/support/test/rule/ActivityTestRule.java
    public static <T extends Activity> T launchActivity(Class<T> activityClass) {
        final String targetPackage = mInstrumentation.getTargetContext().getPackageName();
        Intent startIntent = new Intent(Intent.ACTION_MAIN);
        startIntent.setClassName(targetPackage, activityClass.getName());
        startIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

        // The following cast is correct because the activity we're creating is of the
        // same type as the one passed in
        T mActivity = activityClass.cast(mInstrumentation.startActivitySync(startIntent));

        mInstrumentation.waitForIdleSync();

        return mActivity;
    }

    public static void waitForAppToLaunch(String testAppPkg, int LAUNCH_TIMEOUT) throws InterruptedException {
        long start = elapsedRealtime();
        boolean waitStatus;
        do {
            getUiDevice().waitForIdle();
            waitStatus = getUiDevice().wait(Until.hasObject(By.pkg(testAppPkg).depth(0)), LAUNCH_TIMEOUT);
            if (waitStatus) break;
        } while ((elapsedRealtime() - start < LAUNCH_TIMEOUT));
    }

    /**
     * return JSONObjects count in a JSONArray
     *
     * @param jsonArray
     *
     * @return
     */
    public static int getJsonObjectCountInJsonArray(JSONArray jsonArray) {
        int count = 0;
        try {
            for (int i = 0; i < jsonArray.length(); i++, count++) {
                jsonArray.getJSONObject(i);
            }
            return count;
        } catch (JSONException e) {
            return count;
        }
    }
}
